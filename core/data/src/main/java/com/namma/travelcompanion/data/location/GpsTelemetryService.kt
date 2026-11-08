package com.namma.travelcompanion.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.namma.travelcompanion.domain.model.BangaloreTariff
import com.namma.travelcompanion.domain.model.FareBreakdown
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.roundToInt

data class LiveTripState(
    val isRunning: Boolean = false,
    val distanceKm: Double = 0.0,
    val currentSpeedKmh: Float = 0f,
    val durationSeconds: Long = 0L,
    val waitingMinutes: Int = 0,
    val currentLegalFare: Double = 30.0,
    val fareBreakdown: FareBreakdown = BangaloreTariff.calculateLegalFare(0.0),
    val lastKnownLocationLat: Double? = null,
    val lastKnownLocationLng: Double? = null
)

class GpsTelemetryService(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val _tripState = MutableStateFlow(LiveTripState())
    val tripState: StateFlow<LiveTripState> = _tripState.asStateFlow()

    private var lastLocation: Location? = null
    private var tripStartTimeMs: Long = 0L

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            val location = result.lastLocation ?: return
            processNewLocation(location)
        }
    }

    @SuppressLint("MissingPermission")
    fun startTrip() {
        resetTrip()
        tripStartTimeMs = System.currentTimeMillis()
        _tripState.value = _tripState.value.copy(isRunning = true)

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000L)
            .setMinUpdateIntervalMillis(1000L)
            .setMinUpdateDistanceMeters(3.0f)
            .build()

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            // Handled via permission flow
        }
    }

    fun stopTrip() {
        _tripState.value = _tripState.value.copy(isRunning = false)
        try {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        } catch (e: Exception) {
            // Ignore
        }
    }

    fun resetTrip() {
        stopTrip()
        lastLocation = null
        val initialFare = BangaloreTariff.calculateLegalFare(0.0)
        _tripState.value = LiveTripState(
            isRunning = false,
            distanceKm = 0.0,
            currentSpeedKmh = 0f,
            durationSeconds = 0L,
            waitingMinutes = 0,
            currentLegalFare = initialFare.totalLegalFare,
            fareBreakdown = initialFare
        )
    }

    private fun processNewLocation(newLocation: Location) {
        if (!_tripState.value.isRunning) return

        var addedDistanceMeters = 0.0f
        val prev = lastLocation
        if (prev != null) {
            if (newLocation.accuracy < 30.0f && prev.accuracy < 30.0f) {
                val stepDistance = prev.distanceTo(newLocation)
                if (stepDistance in 2.0f..400.0f) {
                    addedDistanceMeters = stepDistance
                }
            }
        }
        lastLocation = newLocation

        val currentDistKm = _tripState.value.distanceKm + (addedDistanceMeters / 1000.0)
        val speedKmh = if (newLocation.hasSpeed()) (newLocation.speed * 3.6f) else 0f
        val durationSec = (System.currentTimeMillis() - tripStartTimeMs) / 1000L

        val waitMins = if (speedKmh < 4.0f && durationSec > 60) ((durationSec / 60) * 0.4).roundToInt() else 0

        val fare = BangaloreTariff.calculateLegalFare(currentDistKm, waitMinutes = waitMins)

        _tripState.value = LiveTripState(
            isRunning = true,
            distanceKm = (currentDistKm * 100.0).roundToInt() / 100.0,
            currentSpeedKmh = speedKmh,
            durationSeconds = durationSec,
            waitingMinutes = waitMins,
            currentLegalFare = fare.totalLegalFare,
            fareBreakdown = fare,
            lastKnownLocationLat = newLocation.latitude,
            lastKnownLocationLng = newLocation.longitude
        )
    }

    fun simulateDistanceProgress(addKm: Double) {
        val newDist = _tripState.value.distanceKm + addKm
        val fare = BangaloreTariff.calculateLegalFare(newDist)
        _tripState.value = _tripState.value.copy(
            distanceKm = (newDist * 100.0).roundToInt() / 100.0,
            currentLegalFare = fare.totalLegalFare,
            fareBreakdown = fare
        )
    }
}
