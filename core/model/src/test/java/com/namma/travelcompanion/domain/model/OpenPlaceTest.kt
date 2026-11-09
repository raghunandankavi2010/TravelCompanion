package com.namma.travelcompanion.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class OpenPlaceTest {

    private fun place(name: String, category: String = "Hill", district: String = "", description: String = "") =
        OpenPlace("Q1", name, description, category, 0.0, 0.0, district, name, "A b.jpg", false)

    private val videos = listOf(
        ChannelVideo("a", "Morning chills Nandi Hills Early Morning Fog"),
        ChannelVideo("b", "Savandurga Trek Guide: Asia's Largest Monolith"),
        ChannelVideo("c", "Lalbagh Botanical Garden: Lake & Musical Fountain Walk, Bangalore"),
        ChannelVideo("d", "Mysore Pak tasting in Mysuru")
    )

    @Test fun fullNameMatches() =
        assertEquals(listOf("a"), ChannelVideoMatcher.videosFor(place("Nandi Hills"), videos).map { it.id })

    @Test fun specificWordMatches() {
        assertEquals(listOf("b"), ChannelVideoMatcher.videosFor(place("Savandurga Hill"), videos).map { it.id })
        assertEquals(listOf("c"), ChannelVideoMatcher.videosFor(place("Lalbagh"), videos).map { it.id })
    }

    @Test fun genericOrShortWordsDoNotMatch() {
        assertTrue(ChannelVideoMatcher.videosFor(place("Mysore Palace"), videos).isEmpty())
        assertTrue(ChannelVideoMatcher.videosFor(place("Ulsoor Lake"), videos).isEmpty())
    }

    @Test fun commonsUrlsEncodeSpaces() =
        assertEquals("https://commons.wikimedia.org/wiki/Special:FilePath/A_b.jpg?width=320", place("X").imageUrl(320))

    @Test fun searchMatchesNameDistrictOrDescriptionWithinCategory() {
        val places = listOf(
            place("Jog Falls", "Waterfall", "Shivamogga"),
            place("Hampi", "Heritage", "Vijayanagara", "UNESCO ruins"),
            place("Abbey Falls", "Waterfall", "Kodagu")
        )
        assertEquals(listOf("Jog Falls"), PlaceSearch.filter(places, "shivamogga", null).map { it.name })
        assertEquals(listOf("Hampi"), PlaceSearch.filter(places, "unesco", null).map { it.name })
        assertEquals(2, PlaceSearch.filter(places, "", "Waterfall").size)
        assertEquals(listOf("Waterfall", "Heritage"), PlaceSearch.categories(places))
    }
}
