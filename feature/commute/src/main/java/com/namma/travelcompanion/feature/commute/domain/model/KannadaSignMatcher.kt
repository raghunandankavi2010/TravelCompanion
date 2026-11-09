package com.namma.travelcompanion.feature.commute.domain.model

/**
 * Matches Kannada-script OCR output (Tesseract `kan`) against the Kannada names in [BusSignLensDatabase].
 *
 * Words shared by many entries (ನಿಲ್ದಾಣ "station", ಬಸ್ "bus") say little about which board this is, so each
 * word is weighted by 1 / (number of entries containing it). A match needs at least one word shared by at most two
 * entries; the summed weight ranks competing matches.
 */
object KannadaSignMatcher {

    private val separators = Regex("[\\s/().,:;|\\-]+")
    private val joiners = Regex("[\\u200C\\u200D]")
    private const val MIN_WEIGHT = 0.5

    fun tokens(text: String): List<String> =
        text.replace(joiners, "")
            .split(separators)
            .filter { word -> word.length >= 3 && word.any { it in '\u0C80'..'\u0CFF' } }

    private val routeTokens = BusSignLensDatabase.routes.associateWith { tokens(it.destinationKn).toSet() }
    private val signTokens = BusSignLensDatabase.signboards.associateWith { tokens(it.kannadaOriginal).toSet() }

    private val tokenWeight: Map<String, Double> = (routeTokens.values + signTokens.values)
        .flatten()
        .groupingBy { it }
        .eachCount()
        .mapValues { 1.0 / it.value }

    fun matchRoute(ocrText: String): BusRouteInfo? = best(routeTokens, ocrText)

    fun matchSignboard(ocrText: String): DetectedSignboardInfo? = best(signTokens, ocrText)

    private fun <T> best(candidates: Map<T, Set<String>>, ocrText: String): T? {
        val words = tokens(ocrText)
        if (words.isEmpty()) return null
        return candidates.entries
            .map { (item, itemTokens) -> item to itemTokens.filter { it.foundIn(words) }.map { tokenWeight.getValue(it) } }
            .filter { (_, weights) -> weights.any { it >= MIN_WEIGHT } }
            .maxByOrNull { (_, weights) -> weights.sum() }
            ?.first
    }

    // Kannada inflects place names with suffixes (ಮೈಸೂರು → ಮೈಸೂರಿಗೆ "to Mysuru"), so the word minus its final
    // vowel sign also matches as a prefix. Short stems are exact-only so ಧರ್ಮ (Dharma Darshana) does not match ಧರ್ಮಸ್ಥಳ.
    private fun String.foundIn(words: List<String>): Boolean {
        val stem = trimEnd { it in '\u0CBE'..'\u0CCD' }
        return words.any { it == this || (stem.length >= 5 && it.startsWith(stem)) }
    }
}
