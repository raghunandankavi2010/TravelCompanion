package com.nammaauto.ai.domain.model

data class NightCorridorState(
    val isMonitoringActive: Boolean = false,
    val originName: String = "",
    val destinationName: String = "",
    val currentSpeedKmh: Float = 0f,
    val distanceCoveredKm: Double = 0.0,
    val currentDeviationMeters: Double = 0.0,
    val isOffRouteAlert: Boolean = false,
    val isStationaryInDarkZone: Boolean = false,
    val emergencyContactPhone: String = "",
    val emergencyContactName: String = "",
    val safetyStatusMessage: String = "Corridor monitoring active. Safe journey."
)
