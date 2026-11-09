package com.namma.travelcompanion.domain.model

import com.namma.travelcompanion.network.api.LiveOfficialTariff
import com.namma.travelcompanion.network.api.LiveTourismPriceService

data class AiBudgetCalculationResult(
    val totalCostInr: Int,
    val totalCostUsdEstimate: String,
    val itemizedCosts: List<Triple<String, String, Int>>, // Spot Name, Category/Details, Cost
    val groundTruthWarnings: List<String>,
    val recommendedPhysicalCashToCarryInr: Int,
    val bookingUrls: List<Pair<String, String>>
)

data class AiQnaItem(
    val id: String,
    val question: String,
    val conciseAnswer: String,
    val detailedGroundTruth: String,
    val officialPricingTag: String,
    val bookingUrl: String,
    val survivalKannada: String,
    val phoneticKannada: String
)

object KarnatakaAiTripIntelligence {

    val commonTouristQueries = listOf(
        AiQnaItem(
            id = "q_mysore_upi",
            question = "Does Mysore Palace accept UPI / GPay at the ticket counter?",
            conciseAnswer = "NO at physical booths! Physical counters at Varaha Gate strictly take CASH ONLY.",
            detailedGroundTruth = "While online portal e-tickets can be bought digitally, the physical ticket booths at Varaha Gate strictly demand CASH Indian Rupee notes due to constant bank server and mobile network jamming from crowds. Always carry exact cash (₹120/Indian, ₹1,000/Foreigner) before joining the line!",
            officialPricingTag = "Indians: ₹120 | Foreigners: ₹1,000 (Incl. Audio Kit)",
            bookingUrl = "https://mysorepalace.karnataka.gov.in",
            survivalKannada = "ಆನ್‌ಲೈನ್ ಟಿಕೆಟ್ ಇದೆಯಾ? ಗೂಗಲ್ ಪೇ ಆಗುತ್ತಾ?",
            phoneticKannada = "Online ticket ideya? Google Pay aaguttha?"
        ),

        AiQnaItem(
            id = "q_kabini_pricing",
            question = "How much does Nagarhole (Kabini) Safari cost for Indians vs Foreigners?",
            conciseAnswer = "Shared Canter Bus: ₹1,550 (Indian) / ₹2,000 (Foreigner). Open Jeep: ₹2,300–₹3,600+.",
            detailedGroundTruth = "Karnataka Forest Dept runs Canter safaris from Dammanakatte (Antharasanthe gate near HD Kote). If you book through Jungle Lodges & Resorts (Kabini River Lodge), stay packages include 2 guaranteed core-zone safaris (river boat & jeep with master naturalists) for ₹10,000–₹14,000/night (Indian) and ₹16,000–₹18,000/night (Foreigner).",
            officialPricingTag = "Indians: ₹1,550 (Bus) | Foreigners: ₹2,000 (Bus) / ₹2,800+ (Jeep)",
            bookingUrl = "https://junglelodges.com",
            survivalKannada = "ಕಬಿನಿ ಸಫಾರಿ ಬುಕಿಂಗ್ ದಮ್ಮನಕಟ್ಟೆ ಕೌಂಟರ್ ಎಲ್ಲಿದೆ?",
            phoneticKannada = "Kabini safari booking Dammanakatte counter ellide?"
        ),

        AiQnaItem(
            id = "q_bandipur_offline",
            question = "Can I get Bandipur Safari tickets on the spot without online booking?",
            conciseAnswer = "Yes, but spot counter tickets sell out in 15–20 minutes! Arrive by 5:15 AM.",
            detailedGroundTruth = "The Karnataka Forest Department keeps an offline quota at the Melukamanahalli reception counter on the Mysuru-Ooty highway. Morning counter opens at 6:00 AM, but weekend queues form by 5:15 AM. Mobile phones are strictly prohibited from being operated inside the reserve during the ride.",
            officialPricingTag = "Indians: ₹1,000 (Bus) | Foreigners: ₹2,500 (Bus) / ₹3,500+ (Jeep)",
            bookingUrl = "https://junglelodges.com",
            survivalKannada = "ಬಂಡೀಪುರ ಸಫಾರಿ ಕೌಂಟರ್ ಟಿಕೆಟ್ ಸಿಗುತ್ತಾ?",
            phoneticKannada = "Bandipura safari counter ticket siguttha?"
        ),

        AiQnaItem(
            id = "q_kudremukh_network",
            question = "How much cash should I carry for Kudremukh and Western Ghats treks?",
            conciseAnswer = "Carry at least ₹1,000 to ₹1,500 cash per person! Zero mobile reception at base.",
            detailedGroundTruth = "At Balagal and Mullodi checkposts, mobile signals (Jio/Airtel) are zero. UPI will completely fail. You must carry physical cash for the mandatory 4x4 Jeep transfer (₹500/head roundtrip), leech socks, and local eco-guide pool. Also note: Single-use plastic bottles are confiscated with a ₹500 fine!",
            officialPricingTag = "Forest Permit: ₹575 online via aranyavihaara.karnataka.gov.in",
            bookingUrl = "https://aranyavihaara.karnataka.gov.in",
            survivalKannada = "ಅರಣ್ಯ ವಿಹಾರ ಪರ್ಮಿಟ್ ಇದೆ, ಗೈಡ್ ಚಾರ್ಜ್ ಎಷ್ಟು?",
            phoneticKannada = "Aranya Vihaara permit ide, guide charge eshtu?"
        ),

        AiQnaItem(
            id = "q_hampi_tickets",
            question = "Do I need separate tickets for all monuments in Hampi?",
            conciseAnswer = "One ASI ticket (₹40 Indian / ₹600 Foreigner) covers Vittala, Lotus Mahal & Museum on the same day.",
            detailedGroundTruth = "General ruins and active Virupaksha temple are free (nominal ₹5 footwear / ₹10 elephant blessing). The ticketed ASI section is Vijaya Vittala and the Zenana Enclosure (Lotus Mahal). Buying the ticket on asi.nic.in avoids waiting in the 45-minute sun queue at the gate.",
            officialPricingTag = "Indians: ₹40 | Foreigners: ₹600 (Under 15 Free)",
            bookingUrl = "https://asi.nic.in",
            survivalKannada = "ಹಂಪಿ ವಿಜಯ ವಿಠ್ಠಲ ದೇವಸ್ಥಾನ ಟಿಕೆಟ್ ಎಷ್ಟು?",
            phoneticKannada = "Hampi Vijaya Vittala devasthana ticket eshtu?"
        )
    )

    fun calculateTripBudget(
        indianAdults: Int,
        foreignAdults: Int,
        indianChildren: Int,
        foreignChildren: Int,
        selectedSpotIds: List<String>,
        hasDslrCamera: Boolean
    ): AiBudgetCalculationResult {
        var totalInr = 0
        val items = mutableListOf<Triple<String, String, Int>>()
        val warnings = mutableListOf<String>()
        val bookingUrls = mutableListOf<Pair<String, String>>()
        var recommendedCash = 0

        selectedSpotIds.forEach { id ->
            val tariff = com.namma.travelcompanion.network.api.LiveTourismPriceService.getTariffForSpot(id)
            if (tariff != null) {
                var spotCost = 0

                // Indian Adults
                if (indianAdults > 0) {
                    val cost = indianAdults * tariff.indianAdultRateInr
                    spotCost += cost
                    items.add(Triple(tariff.spotName, "$indianAdults Indian Adult(s) @ ₹${tariff.indianAdultRateInr}", cost))
                }

                // Foreign Adults
                if (foreignAdults > 0) {
                    val cost = foreignAdults * tariff.foreignerAdultRateInr
                    spotCost += cost
                    items.add(Triple(tariff.spotName, "$foreignAdults Foreign National Adult(s) @ ₹${tariff.foreignerAdultRateInr}", cost))
                }

                // Indian Children
                if (indianChildren > 0 && tariff.childRateInr > 0) {
                    val cost = indianChildren * tariff.childRateInr
                    spotCost += cost
                    items.add(Triple(tariff.spotName, "$indianChildren Child(ren) @ ₹${tariff.childRateInr}", cost))
                }

                // DSLR Camera Fee
                if (hasDslrCamera && (id == "bandipur_safari" || id == "kabini_safari" || id == "mysore_palace")) {
                    val cameraFee = 250
                    spotCost += cameraFee
                    items.add(Triple(tariff.spotName, "DSLR Camera Permit Fee", cameraFee))
                }

                totalInr += spotCost

                if (tariff.isCashMandatoryWarning) {
                    warnings.add(tariff.groundTruthRealityTip)
                    recommendedCash += (spotCost + 500)
                }

                bookingUrls.add(Pair(tariff.spotName, tariff.officialBookingUrl))
            }
        }

        if (recommendedCash == 0 && totalInr > 0) {
            recommendedCash = 500 // baseline buffer
        }

        val usdEstimate = "~$" + String.format("%.2f", totalInr / 83.5) + " USD"

        return AiBudgetCalculationResult(
            totalCostInr = totalInr,
            totalCostUsdEstimate = usdEstimate,
            itemizedCosts = items,
            groundTruthWarnings = warnings,
            recommendedPhysicalCashToCarryInr = recommendedCash,
            bookingUrls = bookingUrls
        )
    }

    fun answerTouristQuery(query: String): AiQnaItem {
        val lower = query.lowercase().trim()

        return when {
            lower.contains("mysore") || lower.contains("palace") || lower.contains("upi") || lower.contains("cash") -> {
                commonTouristQueries[0]
            }
            lower.contains("kabini") || lower.contains("nagarhole") -> {
                commonTouristQueries[1]
            }
            lower.contains("bandipur") || lower.contains("spot") || lower.contains("counter") -> {
                commonTouristQueries[2]
            }
            lower.contains("kudremukh") || lower.contains("trek") || lower.contains("plastic") || lower.contains("signal") -> {
                commonTouristQueries[3]
            }
            lower.contains("hampi") || lower.contains("asi") || lower.contains("vittala") -> {
                commonTouristQueries[4]
            }
            else -> {
                // Default comprehensive advice
                commonTouristQueries[0]
            }
        }
    }
}
