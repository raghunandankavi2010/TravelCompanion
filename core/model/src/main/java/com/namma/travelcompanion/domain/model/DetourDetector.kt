package com.namma.travelcompanion.domain.model

/**
 * Flags a ride that keeps moving away from the chosen drop point.
 *
 * Roads wiggle, so instead of a straight-line corridor it tracks the closest approach to the destination so far
 * and alerts once the vehicle is [toleranceMeters] further away than that.
 */
class DetourDetector(val toleranceMeters: Double = 700.0) {

    private var closestMeters = Double.MAX_VALUE

    fun reset() {
        closestMeters = Double.MAX_VALUE
    }

    /** @return metres the vehicle has moved away from its closest approach to the destination. */
    fun update(distanceToDestinationMeters: Double): Double {
        closestMeters = minOf(closestMeters, distanceToDestinationMeters)
        return distanceToDestinationMeters - closestMeters
    }

    fun isDetour(deviationMeters: Double): Boolean = deviationMeters >= toleranceMeters
}
