package com.namma.travelcompanion.feature.dining

import com.namma.travelcompanion.feature.dining.data.repository.KarnatakaVegFoodRepository
import com.namma.travelcompanion.feature.dining.domain.model.VegCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KarnatakaVegFoodRepositoryTest {

    @Test
    fun `restaurants list contains all key vegetarian hubs`() {
        val all = KarnatakaVegFoodRepository.restaurants
        assertTrue(all.size >= 10)

        val categories = all.map { it.category }.toSet()
        assertTrue(categories.contains(VegCategory.HIGHWAY_VEG_HUB))
        assertTrue(categories.contains(VegCategory.TEMPLE_ANNADANA))
        assertTrue(categories.contains(VegCategory.HERITAGE_TIFFIN))
        assertTrue(categories.contains(VegCategory.NORTH_KA_KHANAVALI))
    }

    @Test
    fun `getRestaurantsByCategory returns only matching category`() {
        val highwayHubs = KarnatakaVegFoodRepository.getRestaurantsByCategory(VegCategory.HIGHWAY_VEG_HUB)
        assertFalse(highwayHubs.isEmpty())
        highwayHubs.forEach {
            assertEquals(VegCategory.HIGHWAY_VEG_HUB, it.category)
        }
    }

    @Test
    fun `searchRestaurants matches dishes and locations`() {
        val idliResults = KarnatakaVegFoodRepository.searchRestaurants("Thatte Idli")
        assertTrue(idliResults.any { it.id == "kamath_lokaruchi_ramanagara" })

        val mysoreResults = KarnatakaVegFoodRepository.searchRestaurants("Mysuru")
        assertFalse(mysoreResults.isEmpty())
    }

    @Test
    fun `templeAnnadanaGuidelines covers major pilgrim sites with zero cost`() {
        val guidelines = KarnatakaVegFoodRepository.templeAnnadanaGuidelines
        assertTrue(guidelines.size >= 4)
        guidelines.forEach {
            assertTrue(it.lunchTimings.isNotBlank())
            assertTrue(it.diningHallName.isNotBlank())
        }
    }
}
