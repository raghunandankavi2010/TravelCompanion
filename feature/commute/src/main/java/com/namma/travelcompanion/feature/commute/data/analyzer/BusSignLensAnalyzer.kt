package com.namma.travelcompanion.feature.commute.data.analyzer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.SystemClock
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.googlecode.tesseract.android.TessBaseAPI
import com.namma.travelcompanion.feature.commute.domain.model.BusRouteInfo
import com.namma.travelcompanion.feature.commute.domain.model.BusSignLensDatabase
import com.namma.travelcompanion.feature.commute.domain.model.BusSignLensScanResult
import com.namma.travelcompanion.feature.commute.domain.model.DetectedSignboardInfo
import com.namma.travelcompanion.feature.commute.domain.model.KannadaSignMatcher
import com.namma.travelcompanion.feature.commute.domain.model.SignTextBlock
import kotlinx.coroutines.CompletableDeferred
import java.io.File
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import java.util.concurrent.atomic.AtomicReference
import java.util.regex.Pattern

/**
 * Two OCR passes over the same camera frames:
 *  - ML Kit (Latin) every ~180 ms for route codes like 335-E and English destination text.
 *  - Tesseract `kan` every ~1.2 s for Kannada-script destination boards, which ML Kit cannot read at all.
 * Tesseract is synchronous and runs on the analyzer executor; STRATEGY_KEEP_ONLY_LATEST drops frames meanwhile.
 *
 * In [signMode] live scanning stops; [readSignFrame] grabs the next frame and returns it with Tesseract's
 * Kannada text lines and their boxes.
 */
class BusSignLensAnalyzer(private val context: Context) : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    private val _scanFlow = MutableStateFlow(BusSignLensScanResult())
    val scanFlow: StateFlow<BusSignLensScanResult> = _scanFlow.asStateFlow()

    private var lastInferenceTimeMs = 0L
    private val inferenceThrottleMs = 180L

    private val tessLock = Any()
    private var tess: TessBaseAPI? = null
    private var tessUnavailable = false
    private var isClosed = false
    private var lastKannadaTimeMs = 0L
    private val kannadaThrottleMs = 1200L

    /** Sign-translation mode: skip bus/sign matching, only serve [readSignFrame] requests. */
    @Volatile var signMode = false
    private val pendingSignRead = AtomicReference<CompletableDeferred<SignFrame?>?>(null)

    /** Freezes the next camera frame and OCRs it. Null if Kannada OCR is unavailable or failed. */
    suspend fun readSignFrame(): SignFrame? {
        val request = CompletableDeferred<SignFrame?>()
        pendingSignRead.getAndSet(request)?.complete(null)
        return request.await()
    }

    // Alphanumeric bus route pattern e.g., KIA-8, KIA 9, 335E, 335-E, 500 D, 201G, G4, G-4, V-335E
    private val routePattern = Pattern.compile(
        "\\b(KIA[- ]?\\d{1,2}[A-Z]?|V[- ]?\\d{3}[A-Z]?|\\d{3}[- ]?[A-Z]{1,2}|G[- ]?\\d{1,2}|MF[- ]?\\d{1,2}|KSRTC)\\b",
        Pattern.CASE_INSENSITIVE
    )

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (signMode) {
            pendingSignRead.getAndSet(null)?.complete(readKannadaLines(imageProxy))
            imageProxy.close()
            return
        }
        val now = SystemClock.elapsedRealtime()
        if (now - lastInferenceTimeMs < inferenceThrottleMs) {
            imageProxy.close()
            return
        }
        lastInferenceTimeMs = now

        val mediaImage = imageProxy.image
        if (mediaImage == null) {
            imageProxy.close()
            return
        }

        val kannadaText = if (now - lastKannadaTimeMs >= kannadaThrottleMs) {
            lastKannadaTimeMs = now
            recognizeKannada(imageProxy)
        } else {
            ""
        }

        val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        recognizer.process(inputImage)
            .addOnSuccessListener { visionText ->
                processExtractedText(listOf(visionText.text, kannadaText).filter { it.isNotBlank() }.joinToString("\n"))
            }
            .addOnFailureListener {
                // Ignore transient frame failure
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    private fun recognizeKannada(imageProxy: ImageProxy): String {
        synchronized(tessLock) {
            val api = kannadaEngine() ?: return ""
            return try {
                api.setImage(imageProxy.toUprightBitmap(maxEdge = 1280))
                api.getUTF8Text().orEmpty()
            } catch (e: Exception) {
                Log.w(TAG, "Kannada OCR failed on frame", e)
                ""
            } finally {
                api.clear()
            }
        }
    }

    private fun readKannadaLines(imageProxy: ImageProxy): SignFrame? = synchronized(tessLock) {
        val api = kannadaEngine() ?: return null
        val bitmap = imageProxy.toUprightBitmap(maxEdge = 1600)
        try {
            api.setImage(bitmap)
            api.getUTF8Text() // runs recognition; the iterator below reads its result
            val lines = mutableListOf<SignTextBlock>()
            api.resultIterator?.let { iterator ->
                val level = TessBaseAPI.PageIteratorLevel.RIL_TEXTLINE
                iterator.begin()
                do {
                    val text = iterator.getUTF8Text(level)
                    val box = iterator.getBoundingBox(level) // left, top, right, bottom
                    if (!text.isNullOrBlank() && box.size == 4) lines += SignTextBlock(text, box[0], box[1], box[2], box[3])
                } while (iterator.next(level))
                iterator.delete()
            }
            SignFrame(bitmap, lines)
        } catch (e: Exception) {
            Log.w(TAG, "Sign OCR failed", e)
            null
        } finally {
            api.clear()
        }
    }

    /** Lazily copies the bundled kan.traineddata out of assets (Tesseract needs a real file) and initialises. */
    private fun kannadaEngine(): TessBaseAPI? {
        if (isClosed || tessUnavailable) return null
        tess?.let { return it }
        return try {
            val dataDir = File(context.noBackupFilesDir, "tesseract")
            val model = File(dataDir, "tessdata/$KANNADA_MODEL.traineddata")
            if (!model.exists()) {
                model.parentFile?.mkdirs()
                val tmp = File(model.path + ".tmp")
                context.assets.open("tessdata/$KANNADA_MODEL.traineddata").use { input ->
                    tmp.outputStream().use { input.copyTo(it) }
                }
                tmp.renameTo(model)
            }
            TessBaseAPI().also { api ->
                check(api.init(dataDir.absolutePath, KANNADA_MODEL)) { "Tesseract init failed" }
                api.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SPARSE_TEXT)
                tess = api
            }
        } catch (e: Exception) {
            Log.e(TAG, "Kannada OCR unavailable, continuing with Latin only", e)
            tessUnavailable = true
            null
        }
    }

    private fun ImageProxy.toUprightBitmap(maxEdge: Int): Bitmap {
        val source = toBitmap()
        val scale = minOf(1f, maxEdge.toFloat() / maxOf(source.width, source.height))
        val matrix = Matrix().apply {
            postScale(scale, scale)
            postRotate(imageInfo.rotationDegrees.toFloat())
        }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    fun processExtractedText(rawText: String) {
        if (rawText.isBlank()) return

        // 1. First convert any Kannada numerals to standard digits
        val normalizedText = BusSignLensDatabase.convertKannadaNumeralsToArabic(rawText)
        val upperText = normalizedText.uppercase(Locale.ROOT)

        var matchedRoute: BusRouteInfo? = null
        var matchedSign: DetectedSignboardInfo? = null
        var confidence = 0.5f

        // 2. Scan for Bus Route Indicators
        // First check regex route matching
        val matcher = routePattern.matcher(upperText)
        if (matcher.find()) {
            val candidateCode = matcher.group(1)?.replace(" ", "-")?.uppercase(Locale.ROOT) ?: ""
            matchedRoute = findRouteByCode(candidateCode)
            if (matchedRoute != null) {
                confidence = 0.95f
            }
        }

        // If not found by regex code, check for destination names or KSRTC city corridors
        if (matchedRoute == null) {
            matchedRoute = findRouteByCityKeywords(upperText)
            if (matchedRoute != null) {
                confidence = 0.85f
            }
        }

        // Kannada-script destination boards (Tesseract output)
        if (matchedRoute == null) {
            matchedRoute = KannadaSignMatcher.matchRoute(normalizedText)
            if (matchedRoute != null) {
                confidence = 0.80f
            }
        }

        // 3. Scan for Signboard & Temple / Highway Markers
        matchedSign = findSignboardByKeywords(upperText) ?: KannadaSignMatcher.matchSignboard(normalizedText)
        if (matchedSign != null && matchedRoute == null) {
            confidence = 0.90f
        }

        if (matchedRoute != null || matchedSign != null) {
            _scanFlow.value = BusSignLensScanResult(
                detectedRoute = matchedRoute,
                detectedSignboard = matchedSign,
                rawExtractedText = rawText.take(150),
                detectionConfidence = confidence,
                scanTimestampMs = System.currentTimeMillis()
            )
        }
    }

    private fun findRouteByCode(candidate: String): BusRouteInfo? {
        val cleanCandidate = candidate.replace("-", "").replace(" ", "").trim()
        return BusSignLensDatabase.routes.firstOrNull { route ->
            val cleanRouteNum = route.routeNumber.replace("-", "").replace(" ", "").trim()
            cleanRouteNum.equals(cleanCandidate, ignoreCase = true) ||
                    cleanCandidate.contains(cleanRouteNum) ||
                    cleanRouteNum.contains(cleanCandidate)
        }
    }

    private fun findRouteByCityKeywords(text: String): BusRouteInfo? {
        return when {
            text.contains("AIRPORT") || text.contains("KIAL") || text.contains("DEVANAHALLI") -> {
                if (text.contains("ELECTRONIC CITY") || text.contains("SILK BOARD")) {
                    BusSignLensDatabase.routes.find { it.routeNumber == "KIA-8" }
                } else {
                    BusSignLensDatabase.routes.find { it.routeNumber == "KIA-9" }
                }
            }
            text.contains("WHITEFIELD") || text.contains("KADUGODI") || text.contains("ITPL") || text.contains("MARATHAHALLI") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "335-E" }
            }
            text.contains("OUTER RING") || text.contains("BELLANDUR") || text.contains("HEBBAL") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "500-D" }
            }
            text.contains("BANNERGHATTA") || text.contains("ZOO") || text.contains("SAFARI") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "G-4" }
            }
            text.contains("MYSURU") || text.contains("MYSORE") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "KSRTC MYS-EXP" }
            }
            text.contains("DHARMASTHALA") || text.contains("SHIRADI") || text.contains("KUKKE") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "KSRTC DTL-EXP" }
            }
            text.contains("HAMPI") || text.contains("HOSPET") || text.contains("TUNGABHADRA") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "KSRTC HMP-EXP" }
            }
            text.contains("MANGALURU") || text.contains("MANGALORE") || text.contains("BEJAI") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "KSRTC MNG-EXP" }
            }
            text.contains("MADIKERI") || text.contains("COORG") || text.contains("KUSHALNAGAR") -> {
                BusSignLensDatabase.routes.find { it.routeNumber == "KSRTC COG-EXP" }
            }
            else -> null
        }
    }

    private fun findSignboardByKeywords(text: String): DetectedSignboardInfo? {
        return when {
            text.contains("PADARAKSHE") || text.contains("FOOTWEAR") || text.contains("SHOES") || text.contains("CHAPPAL") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_padarakshe" }
            }
            text.contains("SHEEGRA") || text.contains("SPECIAL DARSHAN") || text.contains("VISHESHA") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_sheegra_darshana" }
            }
            text.contains("DHARMA DARSHAN") || text.contains("FREE DARSHAN") || text.contains("GENERAL QUEUE") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_dharma_darshana" }
            }
            text.contains("PRASADA") || text.contains("DASOHA") || text.contains("LUNCH") || text.contains("ANNA") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_prasada" }
            }
            text.contains("CLOAK") || text.contains("LUGGAGE") || text.contains("AMANATHU") || text.contains("LOCKER") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_amanathu" }
            }
            text.contains("NO MOBILE") || text.contains("CAMERA PROHIBITED") || text.contains("NISHEEDHA") || text.contains("NO PHOTO") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_mobile_nisheeda" }
            }
            text.contains("ENTRY") || text.contains("PRAVESHA") || text.contains("WAY IN") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_pravesha" }
            }
            text.contains("EXIT") || text.contains("NIRGAMANA") || text.contains("WAY OUT") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_nirgamana" }
            }
            text.contains("PLATFORM") || text.contains("BAY") || text.contains("NILDAANA") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_platform" }
            }
            text.contains("TOLL") || text.contains("FASTAG") || text.contains("HIGHWAY") || text.contains("EXPRESSWAY") || text.contains("NH-") || text.contains("NH ") -> {
                BusSignLensDatabase.signboards.find { it.id == "sign_nh_milestone" }
            }
            else -> null
        }
    }

    /**
     * Demo & Testing simulation support.
     */
    fun simulateRouteScan(routeNumber: String) {
        val found = BusSignLensDatabase.routes.find { it.routeNumber.equals(routeNumber, ignoreCase = true) }
        if (found != null) {
            _scanFlow.value = BusSignLensScanResult(
                detectedRoute = found,
                detectedSignboard = null,
                rawExtractedText = "SIMULATED: ${found.routeNumber} ${found.originEn} -> ${found.destinationEn}",
                detectionConfidence = 0.99f,
                scanTimestampMs = System.currentTimeMillis()
            )
        }
    }

    fun simulateSignboardScan(signId: String) {
        val found = BusSignLensDatabase.signboards.find { it.id == signId }
        if (found != null) {
            _scanFlow.value = BusSignLensScanResult(
                detectedRoute = null,
                detectedSignboard = found,
                rawExtractedText = "SIMULATED: ${found.kannadaOriginal} (${found.englishTitle})",
                detectionConfidence = 0.98f,
                scanTimestampMs = System.currentTimeMillis()
            )
        }
    }

    fun clearScan() {
        _scanFlow.value = BusSignLensScanResult()
    }

    fun close() {
        try {
            recognizer.close()
        } catch (_: Exception) {}
        pendingSignRead.getAndSet(null)?.complete(null)
        synchronized(tessLock) {
            isClosed = true
            tess?.recycle()
            tess = null
        }
    }

    private companion object {
        const val TAG = "BusSignLensAnalyzer"
        const val KANNADA_MODEL = "kan"
    }
}

/** An upright camera frame and the raw Kannada text lines Tesseract found on it (bitmap pixel coordinates). */
class SignFrame(val bitmap: Bitmap, val lines: List<SignTextBlock>)
