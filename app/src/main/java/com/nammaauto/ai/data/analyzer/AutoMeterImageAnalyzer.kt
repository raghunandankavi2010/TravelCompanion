package com.nammaauto.ai.data.analyzer

import android.os.SystemClock
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.nammaauto.ai.domain.model.BangaloreTariff
import com.nammaauto.ai.domain.model.MeterIntegrityStatus
import com.nammaauto.ai.domain.model.MeterReading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.regex.Pattern

class AutoMeterImageAnalyzer : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    private val _readingFlow = MutableStateFlow(MeterReading())
    val readingFlow: StateFlow<MeterReading> = _readingFlow.asStateFlow()

    private var currentGpsDistanceKm: Double = 0.0
    private var lastInferenceTimeMs = 0L
    private val inferenceThrottleMs = 150L // ~6-7 inferences/sec to preserve battery and keep thermal cool

    // Regex for typical Indian digital auto meter displays (e.g., 30.00, 45, FARE 60, ₹75)
    private val numericFarePattern = Pattern.compile("(?:FARE|RS|RUPEES|₹)?\\s*([1-9][0-9]{1,3}(?:\\.[0-9]{1,2})?)", Pattern.CASE_INSENSITIVE)
    private val distancePattern = Pattern.compile("(?:KM|DIST|DISTANCE)?\\s*([0-9]{1,3}(?:\\.[0-9]{1,2})?)\\s*(?:KM)?", Pattern.CASE_INSENSITIVE)

    fun updateCurrentGpsDistance(distanceKm: Double) {
        this.currentGpsDistanceKm = distanceKm
    }

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
                processVisionText(visionText.text)
            }
            .addOnFailureListener {
                // Keep previous state or show searching
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    private fun processVisionText(rawText: String) {
        if (rawText.isBlank()) return

        val lines = rawText.lines()
        var detectedFare: Double? = null
        var detectedDistance: Double? = null

        // Parse extracted OCR text for realistic fare patterns
        for (line in lines) {
            val cleanLine = line.trim().uppercase()
            // Avoid time strings like 12:30 or dates
            if (cleanLine.contains(":") || cleanLine.contains("/")) continue

            val fareMatcher = numericFarePattern.matcher(cleanLine)
            if (fareMatcher.find()) {
                val candidateStr = fareMatcher.group(1)
                val value = candidateStr?.toDoubleOrNull()
                // Valid auto fare in Bangalore is typically between ₹30 and ₹999
                if (value != null && value in 30.0..999.0) {
                    if (detectedFare == null || cleanLine.contains("FARE") || cleanLine.contains("₹") || cleanLine.contains("RS")) {
                        detectedFare = value
                    }
                }
            }

            val distMatcher = distancePattern.matcher(cleanLine)
            if (distMatcher.find()) {
                val distStr = distMatcher.group(1)
                val distValue = distStr?.toDoubleOrNull()
                if (distValue != null && distValue in 0.1..150.0 && distValue != detectedFare) {
                    detectedDistance = distValue
                }
            }
        }

        // Calculate lawful fare benchmark based on trip distance (or detected distance if GPS not moving)
        val benchmarkKm = if (currentGpsDistanceKm > 0.3) currentGpsDistanceKm else (detectedDistance ?: 1.8)
        val legalTariff = BangaloreTariff.calculateLegalFare(benchmarkKm)
        val legalFare = legalTariff.totalLegalFare

        if (detectedFare != null) {
            val deviationPercent = (((detectedFare - legalFare) / legalFare) * 100f).toFloat()

            val (status, reason) = when {
                deviationPercent > 20f -> {
                    Pair(
                        MeterIntegrityStatus.FAST_METER_ALERT,
                        "Fast Meter Alert: Displaying +${deviationPercent.toInt()}% above official BTP rate"
                    )
                }
                deviationPercent in -8f..15f -> {
                    Pair(
                        MeterIntegrityStatus.FAIR_ACCURATE,
                        "Accurate Meter: Matches lawful Govt rate (₹${legalFare.toInt()})"
                    )
                }
                deviationPercent < -15f -> {
                    Pair(
                        MeterIntegrityStatus.SLIGHT_DEVIATION,
                        "Below minimum tariff or old reading"
                    )
                }
                else -> {
                    Pair(
                        MeterIntegrityStatus.SLIGHT_DEVIATION,
                        "Slight variance (${if (deviationPercent > 0) "+" else ""}${deviationPercent.toInt()}%)"
                    )
                }
            }

            _readingFlow.value = MeterReading(
                detectedFareRupees = detectedFare,
                detectedDistanceKm = detectedDistance,
                rawText = rawText.take(120),
                confidence = 0.92f,
                lawfulGovtFare = legalFare,
                deviationPercent = deviationPercent,
                integrityStatus = status,
                anomalyReason = reason,
                timestampMs = System.currentTimeMillis()
            )
        }
    }

    fun simulateDetectedReading(fare: Double, distanceKm: Double) {
        val legalTariff = BangaloreTariff.calculateLegalFare(distanceKm)
        val legalFare = legalTariff.totalLegalFare
        val deviation = (((fare - legalFare) / legalFare) * 100f).toFloat()
        val status = if (deviation > 20f) MeterIntegrityStatus.FAST_METER_ALERT else MeterIntegrityStatus.FAIR_ACCURATE
        _readingFlow.value = MeterReading(
            detectedFareRupees = fare,
            detectedDistanceKm = distanceKm,
            rawText = "SIMULATED FARE ₹$fare KM $distanceKm",
            confidence = 0.98f,
            lawfulGovtFare = legalFare,
            deviationPercent = deviation,
            integrityStatus = status,
            anomalyReason = if (status == MeterIntegrityStatus.FAST_METER_ALERT)
                "Fast Meter Alert: Showing +${deviation.toInt()}% above BTP legal rate"
            else "Accurate Meter: Lawful Govt rate verified",
            timestampMs = System.currentTimeMillis()
        )
    }

    fun close() {
        recognizer.close()
    }
}
