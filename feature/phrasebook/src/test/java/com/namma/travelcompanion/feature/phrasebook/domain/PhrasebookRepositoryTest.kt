package com.namma.travelcompanion.feature.phrasebook.domain

import com.namma.travelcompanion.feature.phrasebook.data.translator.CommuteTranslator
import com.namma.travelcompanion.feature.phrasebook.domain.model.PhraseCategory
import com.namma.travelcompanion.feature.phrasebook.domain.repository.PhrasebookRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PhrasebookRepositoryTest {

    @Test
    fun `phrasebook contains all categories with valid phrases`() {
        val allPhrases = PhrasebookRepository.phrases
        assertTrue(allPhrases.isNotEmpty())

        PhraseCategory.values().forEach { category ->
            val categorized = PhrasebookRepository.getPhrasesByCategory(category)
            assertTrue("Category $category should have phrases", categorized.isNotEmpty())
            categorized.forEach { phrase ->
                assertEquals(category, phrase.category)
                assertTrue(phrase.kannadaLipi.isNotBlank())
                assertTrue(phrase.phoneticEnglish.isNotBlank())
                assertTrue(phrase.englishMeaning.isNotBlank())
            }
        }
    }

    @Test
    fun `commute translator matches bangalore colloquial phrases offline`() = runTest {
        val translator = CommuteTranslator(apiService = null)

        val result = translator.translateToKannada("no meter")
        assertTrue(result.kannadaLipi.isNotBlank())
        assertTrue(result.isBangaloreSlangOptimized)

        val destinationResult = translator.translateToKannada("drop me near the main gate")
        assertTrue(destinationResult.kannadaLipi.contains("ಗೇಟ್"))
    }

    @Test
    fun `commute translator handles empty input`() = runTest {
        val translator = CommuteTranslator(apiService = null)
        val result = translator.translateToKannada("   ")
        assertEquals("", result.kannadaLipi)
    }
}
