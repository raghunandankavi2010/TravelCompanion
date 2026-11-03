package com.nammaauto.ai.data.analyzer

import android.os.SystemClock
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.nammaauto.ai.domain.model.BusRouteInfo
import com.nammaauto.ai.domain.model.BusSignLensDatabase
import com.nammaauto.ai.domain.model.BusSignLensScanResult
import com.nammaauto.ai.domain.model.DetectedSignboardInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import java.util.regex.Pattern

class BusSignLensAnalyzer : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    private val _scanFlow = MutableStateFlow(BusSignLensScanResult())
    val scanFlow: StateFlow<BusSignLensScanResult> = _scanFlow.asStateFlow()

    private var lastInferenceTimeMs = 0L
    private val inferenceThrottleMs = 180L

    // Alphanumeric bus route pattern e.g., KIA-8, KIA 9, 335E, 335-E, 500 D, 201G, G4, G-4, V-335E
    private val routePattern = Pattern.compile(
        "\\b(KIA[- ]?\\d{1,2}[A-Z]?|V[- ]?\\d{3}[A-Z]?|\\d{3}[- ]?[A-Z]{1,2}|G[- ]?\\d{1,2}|MF[- ]?\\d{1,2}|KSRTC)\\b",
        Pattern.CASE_INSENSITIVE
    )

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
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

        val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        recognizer.process(inputImage)
            .addOnSuccessListener { visionText ->
                processExtractedText(visionText.text)
            }
            .addOnFailureListener {
                // Ignore transient frame failure
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
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

        // 3. Scan for Signboard & Temple / Highway Markers
        matchedSign = findSignboardByKeywords(upperText)
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
    }
}
