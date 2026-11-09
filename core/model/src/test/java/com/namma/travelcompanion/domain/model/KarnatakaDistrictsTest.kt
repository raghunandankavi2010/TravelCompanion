package com.namma.travelcompanion.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KarnatakaDistrictsTest {

    @Test fun thirtyOneUniqueDistricts() {
        assertEquals(31, KarnatakaDistricts.all.size)
        assertEquals(31, KarnatakaDistricts.all.map { it.id }.toSet().size)
    }

    @Test fun listRunsFromChamarajanagarToBidar() {
        assertEquals("chamarajanagar", KarnatakaDistricts.all.first().id)
        assertEquals("bidar", KarnatakaDistricts.all.last().id)
    }

    @Test fun pointInPolygonHandlesConcaveShapes() {
        // An L shape: the notch at (1.5, 1.5) is outside.
        val l = listOf(GeoPoint(0.0, 0.0), GeoPoint(2.0, 0.0), GeoPoint(2.0, 1.0), GeoPoint(1.0, 1.0), GeoPoint(1.0, 2.0), GeoPoint(0.0, 2.0))
        assertTrue(l.containsPoint(GeoPoint(0.5, 1.5)))
        assertTrue(l.containsPoint(GeoPoint(1.5, 0.5)))
        assertFalse(l.containsPoint(GeoPoint(1.5, 1.5)))
        assertFalse(l.containsPoint(GeoPoint(3.0, 0.5)))
    }

    @Test fun placesGroupIntoTravellerCategoriesInDisplayOrder() {
        fun place(category: String) = OpenPlace("Q$category", category, "", category, 0.0, 0.0, "", "", "", false)
        val groups = PlaceCategory.group(listOf(place("Palace"), place("Waterfall"), place("Fort"), place("Temple")))
        assertEquals(listOf(PlaceCategory.TEMPLES, PlaceCategory.WATERFALLS, PlaceCategory.FORTS), groups.map { it.first })
        assertEquals(2, groups.last().second.size)
    }
}
