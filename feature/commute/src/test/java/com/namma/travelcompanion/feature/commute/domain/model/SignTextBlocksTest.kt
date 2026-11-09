package com.namma.travelcompanion.feature.commute.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class SignTextBlocksTest {

    @Test
    fun stackedLinesOfOneParagraphMergeIntoOneBlock() {
        val merged = SignTextBlocks.merge(
            listOf(
                SignTextBlock("ಪಾದರಕ್ಷೆ ಇಲ್ಲಿ", 100, 100, 400, 140),
                SignTextBlock("ಬಿಡಿ", 120, 150, 300, 190)
            )
        )
        assertEquals(listOf(SignTextBlock("ಪಾದರಕ್ಷೆ ಇಲ್ಲಿ ಬಿಡಿ", 100, 100, 400, 190)), merged)
    }

    @Test
    fun sideBySideBlocksReadLeftToRightThenNextRow() {
        val exit = SignTextBlock("ನಿರ್ಗಮನ", 600, 100, 900, 140) // right, a little higher
        val entry = SignTextBlock("ಪ್ರವೇಶ", 100, 104, 400, 144) // left, same row
        val far = SignTextBlock("ಪ್ರಸಾದ", 100, 500, 400, 540) // next row
        assertEquals(listOf(entry, exit, far), SignTextBlocks.merge(listOf(far, exit, entry)))
    }

    @Test
    fun noiseWithoutKannadaIsDroppedAndWhitespaceCollapsed() {
        val merged = SignTextBlocks.merge(
            listOf(
                SignTextBlock("  |~ 1 ", 0, 0, 50, 20),
                SignTextBlock("ಅ", 0, 300, 50, 320),
                SignTextBlock(" ಅಮಾನತು \n ಕೊಠಡಿ ", 10, 600, 300, 640)
            )
        )
        assertEquals(listOf(SignTextBlock("ಅಮಾನತು ಕೊಠಡಿ", 10, 600, 300, 640)), merged)
    }
}
