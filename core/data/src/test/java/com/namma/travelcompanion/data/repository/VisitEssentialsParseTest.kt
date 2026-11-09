package com.namma.travelcompanion.data.repository

import com.namma.travelcompanion.domain.repository.KarnatakaTourismRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class VisitEssentialsParseTest {

    private val asset: File = listOf(
        File("src/main/assets/essentials/visit_essentials.json"),
        File("../../app/src/main/assets/essentials/visit_essentials.json"),
        File("app/src/main/assets/essentials/visit_essentials.json")
    ).firstOrNull { it.exists() } ?: File("app/src/main/assets/essentials/visit_essentials.json")

    @Test
    fun bundledFileParsesAndEveryEntryIsSourced() {
        val data = parseVisitEssentials(asset.readText())
        val spotIds = KarnatakaTourismRepository.spots.map { it.id }
        val entries = data.places.values.distinctBy { it.placeId }
        entries.forEach {
            assertTrue("${it.placeId} lastVerified", it.lastVerified.isNotBlank())
            assertTrue("${it.placeId} sourceUrl", it.sourceUrl.startsWith("https://"))
        }
        val placeIds = entries.map { it.placeId }.toSet()
        assertTrue("curated spots missing: ${spotIds - placeIds}", placeIds.containsAll(spotIds))
    }

    @Test
    fun indexedByPlaceIdAndWikidataId_optionalBlocksDefault() {
        val data = parseVisitEssentials(
            """{"version":2,"places":[{"placeId":"x","wikidataId":"Q1","lastVerified":"2026-11-06","sourceUrl":"https://a",
               "payment":{"cash":true,"upi":false,"card":false},"specialEntry":[{"name":"Fast queue","bookUrl":""}]}]}"""
        )
        assertEquals(2, data.version)
        val e = data.places.getValue("Q1")
        assertEquals("x", data.places.getValue("x").placeId)
        assertTrue(e.payment!!.cashOnly)
        assertNull(e.specialEntry.single().inr)
        assertTrue(e.fees.isEmpty() && e.parking.isEmpty() && e.annadanam == null)
    }

    @Test
    fun cashOnlyFalseWhenUpiAccepted() {
        assertFalse(com.namma.travelcompanion.domain.model.PaymentModes(cash = true, upi = true, card = false).cashOnly)
    }

    @Test
    fun osmParkingIsTaggedForAttribution() {
        val parking = parseOsmParking("""{"attribution":"x","places":{"Q9":[{"name":"P1","lat":1.0,"lng":2.0,"fee":"yes","distanceM":10}]}}""")
        assertTrue(parking.getValue("Q9").single().fromOsm)
        assertEquals(10, parking.getValue("Q9").single().distanceM)
    }
}
