package com.namma.travelcompanion.feature.lens

import com.namma.travelcompanion.feature.lens.data.analyzer.BusSignLensAnalyzer
import com.namma.travelcompanion.feature.lens.domain.model.BusSignLensDatabase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BusSignLensDatabaseTest {

    @Test
    fun `convertKannadaNumeralsToArabic correctly translates Kannada numerals`() {
        val kannada = "ಬಸ್ ೩೩೫-ಇ ಮತ್ತು ಕೆಐಎ-೮"
        val converted = BusSignLensDatabase.convertKannadaNumeralsToArabic(kannada)
        assertTrue(converted.contains("335"))
        assertTrue(converted.contains("8"))
    }

    @Test
    fun `analyzer matches KIA-8 route from raw scanned text`() {
        val analyzer = BusSignLensAnalyzer()
        analyzer.processExtractedText("KIAL DEVANAHALLI KIA-8 TO ELECTRONIC CITY")

        val result = analyzer.scanFlow.value
        assertNotNull(result.detectedRoute)
        assertEquals("KIA-8", result.detectedRoute?.routeNumber)
        assertEquals("Electronic City (Wipro Gate / Infosys)", result.detectedRoute?.destinationEn)
        analyzer.close()
    }

    @Test
    fun `analyzer detects temple footwear signboard accurately`() {
        val analyzer = BusSignLensAnalyzer()
        analyzer.processExtractedText("PADARAKSHE COUNTER SHOE STAND CHAPPAL")

        val result = analyzer.scanFlow.value
        assertNotNull(result.detectedSignboard)
        assertEquals("sign_padarakshe", result.detectedSignboard?.id)
        analyzer.close()
    }
}
