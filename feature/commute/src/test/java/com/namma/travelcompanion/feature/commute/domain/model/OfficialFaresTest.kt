package com.namma.travelcompanion.feature.commute.domain.model

import com.namma.travelcompanion.domain.model.BangaloreTariff
import com.namma.travelcompanion.domain.model.OfficialFares
import org.junit.Assert.assertEquals
import org.junit.Test

class OfficialFaresTest {

    private fun auto(city: String) = OfficialFares.autoRickshaw.first { it.name.startsWith(city) }

    @Test fun bengaluruAutoMatchesTheMeterEngine() =
        assertEquals(BangaloreTariff.calculateLegalFare(7.0, isNight = false).totalLegalFare.toInt(), auto("Bengaluru").fareFor(7.0))

    @Test fun baseFareCoversBaseDistance() = assertEquals(40, auto("Mangaluru").fareFor(1.0))

    @Test fun partKilometresRoundUpToTheRupee() = assertEquals(52, auto("Mangaluru").fareFor(2.0)) // 40 + 0.5 × 23 = 51.5

    @Test fun taxiMinimumIsFirstFourKm() {
        assertEquals(100, OfficialFares.taxi[0].fareFor(4.0))
        assertEquals(148, OfficialFares.taxi[0].fareFor(6.0))
    }

    @Test fun metroSlabsIncludeTheirUpperBound() {
        assertEquals(10, OfficialFares.metroFare(2.0))
        assertEquals(20, OfficialFares.metroFare(2.1))
        assertEquals(90, OfficialFares.metroFare(40.0))
    }
}
