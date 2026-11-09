package com.namma.travelcompanion.feature.commute.domain.model

import com.namma.travelcompanion.domain.model.BangaloreTariff
import com.namma.travelcompanion.feature.commute.domain.model.BusSignLensDatabase
import org.junit.Assert.assertEquals
import org.junit.Test

class BangaloreTariffTest {

    private fun fare(km: Double, wait: Int = 0, night: Boolean = false, luggage: Int = 0) =
        BangaloreTariff.calculateLegalFare(km, wait, night, luggage).totalLegalFare

    @Test fun minimumFareCoversFirstTwoKm() {
        assertEquals(36.0, fare(0.0), 0.0)
        assertEquals(36.0, fare(2.0), 0.0)
        assertEquals(36.0, fare(-3.0), 0.0)
    }

    @Test fun eighteenPerKmAfterTwoKm() = assertEquals(90.0, fare(5.0), 0.0)

    @Test fun nightIsOneAndHalfTimes() = assertEquals(135.0, fare(5.0, night = true), 0.0)

    @Test fun waitingChargedPerStartedFifteenMinuteSlabAfterFiveFree() {
        assertEquals(36.0, fare(2.0, wait = 5), 0.0)
        assertEquals(46.0, fare(2.0, wait = 20), 0.0)
        assertEquals(56.0, fare(2.0, wait = 21), 0.0)
    }

    @Test fun luggageChargedPerStartedTwentyKgSlabAfterTwentyFree() {
        assertEquals(36.0, fare(2.0, luggage = 20), 0.0)
        assertEquals(56.0, fare(2.0, luggage = 45), 0.0)
    }

    @Test fun kannadaNumeralsBecomeArabic() =
        assertEquals("335E", BusSignLensDatabase.convertKannadaNumeralsToArabic("೩೩೫E"))
}
