package com.namma.travelcompanion.feature.transit

import com.namma.travelcompanion.feature.transit.domain.model.StatewideTransportData
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StatewideTransitCorridorTest {

    @Test
    fun `transit corridor list contains critical tourist routes`() {
        val corridors = StatewideTransportData.corridorTransitList
        assertFalse(corridors.isEmpty())

        val mysuru = corridors.find { it.destinationCity == "Mysuru" }
        assertNotNull(mysuru)
        assertTrue(mysuru!!.distanceKm in 140..155)
        assertTrue(mysuru.govtBus.fleetOptions.isNotEmpty())
        assertTrue(mysuru.railway.keyTrains.isNotEmpty())
    }

    @Test
    fun `transport comparisons contain valid booking URLs`() {
        val comparisons = StatewideTransportData.transportComparisons
        assertTrue(comparisons.size >= 4)
        comparisons.forEach { mode ->
            assertTrue(mode.bookingUrl.startsWith("https://"))
            assertFalse(mode.pros.isEmpty())
            assertFalse(mode.cons.isEmpty())
        }
    }

    @Test
    fun `all four seasons are documented with advice`() {
        val seasons = StatewideTransportData.seasonalTravelGuides
        assertTrue(seasons.size >= 4)
        seasons.forEach { season ->
            assertFalse(season.topDestinationsToVisit.isEmpty())
            assertTrue(season.whatToAvoid.isNotBlank())
        }
    }
}
