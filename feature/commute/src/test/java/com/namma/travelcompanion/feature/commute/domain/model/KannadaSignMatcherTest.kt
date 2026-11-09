package com.namma.travelcompanion.feature.commute.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class KannadaSignMatcherTest {

    @Test fun matchesDestinationBoard() =
        assertEquals("KSRTC MYS-EXP", KannadaSignMatcher.matchRoute("ಮೈಸೂರು")?.routeNumber)

    @Test fun matchesInflectedPlaceName() =
        assertEquals("KSRTC MYS-EXP", KannadaSignMatcher.matchRoute("ಮೈಸೂರಿಗೆ")?.routeNumber)

    @Test fun toleratesOcrNoiseAroundTheName() =
        assertEquals("KSRTC DTL-EXP", KannadaSignMatcher.matchRoute("| ಧರ್ಮಸ್ಥಳ . ೧೨")?.routeNumber)

    @Test fun genericWordsAloneDoNotMatch() {
        assertNull(KannadaSignMatcher.matchRoute("ಬಸ್ ನಿಲ್ದಾಣ"))
        assertNull(KannadaSignMatcher.matchRoute("ABC 123"))
    }

    @Test fun picksTheMoreSpecificSignboard() =
        assertEquals("sign_dharma_darshana", KannadaSignMatcher.matchSignboard("ಧರ್ಮ ದರ್ಶನ")?.id)

    @Test fun dharmaDarshanaDoesNotFireOnDharmasthalaBoard() =
        assertNull(KannadaSignMatcher.matchSignboard("ಧರ್ಮಸ್ಥಳ"))
}
