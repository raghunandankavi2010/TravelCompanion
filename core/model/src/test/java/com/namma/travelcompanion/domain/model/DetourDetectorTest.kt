package com.namma.travelcompanion.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DetourDetectorTest {

    private val detector = DetourDetector(toleranceMeters = 700.0)

    @Test fun steadyApproachNeverAlerts() {
        listOf(5000.0, 4200.0, 3000.0, 1500.0, 200.0).forEach {
            assertFalse(detector.isDetour(detector.update(it)))
        }
    }

    @Test fun smallWiggleAwayIsTolerated() {
        detector.update(3000.0)
        assertFalse(detector.isDetour(detector.update(3500.0)))
    }

    @Test fun movingWellAwayFromClosestPointAlerts() {
        detector.update(3000.0)
        detector.update(2000.0)
        val deviation = detector.update(2800.0)
        assertEquals(800.0, deviation, 0.0)
        assertTrue(detector.isDetour(deviation))
    }

    @Test fun resetForgetsTheOldClosestPoint() {
        detector.update(1000.0)
        detector.reset()
        assertFalse(detector.isDetour(detector.update(5000.0)))
    }
}
