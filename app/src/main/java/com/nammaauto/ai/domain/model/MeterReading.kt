package com.nammaauto.ai.domain.model

enum class MeterIntegrityStatus {
    FAIR_ACCURATE,
    SLIGHT_DEVIATION,
    FAST_METER_ALERT,
    UNKNOWN
}

data class MeterReading(
    val detectedFareRupees: Double? = null,
    val detectedDistanceKm: Double? = null,
    val rawText: String = "",
    val confidence: Float = 0f,
    val lawfulGovtFare: Double = 30.0,
    val deviationPercent: Float = 0f,
    val integrityStatus: MeterIntegrityStatus = MeterIntegrityStatus.UNKNOWN,
    val anomalyReason: String = "Align camera with auto meter display",
    val timestampMs: Long = System.currentTimeMillis()
)
