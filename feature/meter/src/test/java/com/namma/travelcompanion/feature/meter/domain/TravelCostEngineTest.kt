package com.namma.travelcompanion.feature.meter.domain

import com.namma.travelcompanion.feature.meter.domain.model.AccommodationType
import com.namma.travelcompanion.feature.meter.domain.model.DiningBudgetPreference
import com.namma.travelcompanion.feature.meter.domain.model.RidePulseEngine
import com.namma.travelcompanion.feature.meter.domain.model.TravelCostEngine
import com.namma.travelcompanion.feature.meter.domain.model.TravelTransportMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TravelCostEngineTest {

    @Test
    fun testPopularDestinationsNotEmpty() {
        val destinations = TravelCostEngine.popularDestinations
        assertFalse(destinations.isEmpty())
        assertTrue(destinations.size >= 10)

        val hampi = destinations.find { it.id == "hampi" }
        assertNotNull(hampi)
        assertEquals(345, hampi?.distanceFromBangaloreKm)
    }

    @Test
    fun testCalculateTotalTripCost() {
        val selected = listOf(
            TravelCostEngine.popularDestinations[0], // Hampi (345 km)
            TravelCostEngine.popularDestinations[1]  // Mysuru (145 km)
        )

        val breakdown = TravelCostEngine.calculateTotalTripCost(
            selectedDestinations = selected,
            tripDays = 3,
            adultsCount = 2,
            childrenCount = 1,
            isForeignNational = false,
            transportMode = TravelTransportMode.KSRTC_AIRAVAT_BUS,
            stayType = AccommodationType.KSTDC_MAYURA_HOTEL,
            diningPreference = DiningBudgetPreference.PURE_VEG_DARSHINI_MID
        )

        assertNotNull(breakdown)
        assertTrue(breakdown.totalEstimatedCostInr > 0)
        assertTrue(breakdown.transportCostInr > 0)
        assertTrue(breakdown.stayCostInr > 0)
        assertTrue(breakdown.diningCostInr > 0)
        assertTrue(breakdown.entryTicketsCostInr > 0)
        assertTrue(breakdown.perPersonCostInr > 0)
        assertFalse(breakdown.moneySavingTips.isEmpty())
    }

    @Test
    fun testRidePulseEngineGeneratesReport() {
        val report = RidePulseEngine.generateRadarReport(
            distanceKm = 5.0,
            fromName = "Silk Board",
            toName = "Koramangala",
            trafficMultiplier = 1.0,
            isRaining = false,
            isNight = false
        )

        assertNotNull(report)
        assertEquals(5.0, report.distanceKm, 0.01)
        assertFalse(report.options.isEmpty())
        assertNotNull(report.bestAppRecommendation)
        assertTrue(report.streetAutoAlternativeFare > 0)
    }
}
