package com.namma.travelcompanion.feature.tourism.domain

import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaAiTripIntelligence
import com.namma.travelcompanion.feature.tourism.domain.repository.KarnatakaTourismRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class KarnatakaAiTripIntelligenceTest {

    @Test
    fun testKarnatakaTourismRepositorySpotsNotEmpty() {
        val spots = KarnatakaTourismRepository.spots
        assertFalse(spots.isEmpty())
        assertTrue(spots.size >= 15)

        val hampi = spots.find { it.id == "hampi_unesco" }
        assertNotNull(hampi)
        assertEquals("Hampi UNESCO World Heritage Site", hampi?.name)
    }

    @Test
    fun testCommonTouristQueries() {
        val queries = KarnatakaAiTripIntelligence.commonTouristQueries
        assertFalse(queries.isEmpty())
        val palaceQuery = queries.find { it.id == "q_mysore_upi" }
        assertNotNull(palaceQuery)
        assertTrue(palaceQuery!!.conciseAnswer.contains("NO"))
    }

    @Test
    fun testCalculateTripBudget() {
        val result = KarnatakaAiTripIntelligence.calculateTripBudget(
            indianAdults = 2,
            foreignAdults = 1,
            indianChildren = 0,
            foreignChildren = 0,
            selectedSpotIds = listOf("hampi_unesco", "mysore_palace"),
            hasDslrCamera = true
        )
        assertNotNull(result)
        assertTrue(result.totalCostInr > 0)
        assertFalse(result.itemizedCosts.isEmpty())
        assertTrue(result.recommendedPhysicalCashToCarryInr >= 0)
    }
}
