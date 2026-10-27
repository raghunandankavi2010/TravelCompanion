package com.nammaauto.ai.domain.model

enum class TravelTransportMode(val label: String, val approxPerKmRate: Double, val iconTag: String) {
    KSRTC_AIRAVAT_BUS("KSRTC Airavat / EV Bus", 2.2, "🚌"),
    INDIAN_RAILWAYS("Indian Railways (Express/Vande Bharat)", 1.4, "🚆"),
    SELF_DRIVE_CAR("Self-Drive Car (Fuel + NH Tolls)", 6.5, "🚗"),
    OUTSTATION_CAB("Outstation Private Cab / Taxi", 14.0, "🚖")
}

enum class AccommodationType(val label: String, val avgNightCostInr: Int, val iconTag: String) {
    HERITAGE_HOMESTAY("Malnad/Coastal Homestay", 2200, "🏡"),
    KSTDC_MAYURA_HOTEL("KSTDC Govt Mayura Hotel", 1800, "🏨"),
    LUXURY_JUNGLE_RESORT("Luxury Safari / Jungle Lodge", 7500, "🏕️")
}

enum class DiningBudgetPreference(val label: String, val avgDailyCostPerPersonInr: Int) {
    SATVIK_ANNADANA_BUDGET("Temple Annadana + Local Tiffin (₹200/day)", 200),
    PURE_VEG_DARSHINI_MID("Pure Veg Darshinis & Highway Hubs (₹450/day)", 450),
    PREMIUM_VEG_FINE_DINE("Heritage Thali & AC Dining (₹900/day)", 900)
}

data class TripDestinationItem(
    val id: String,
    val name: String,
    val district: String,
    val distanceFromBangaloreKm: Int,
    val ticketDomesticInr: Int,
    val ticketForeignerInr: Int
)

data class TravelCostBreakdown(
    val totalEstimatedCostInr: Int,
    val approxUsdEquivalent: String,
    val perPersonCostInr: Int,
    val perDayCostInr: Int,
    val transportCostInr: Int,
    val stayCostInr: Int,
    val diningCostInr: Int,
    val entryTicketsCostInr: Int,
    val roundTripDistanceKm: Int,
    val moneySavingTips: List<String>
)

object TravelCostEngine {

    val popularDestinations: List<TripDestinationItem> = listOf(
        TripDestinationItem("hampi", "Hampi UNESCO Monuments", "Vijayanagara", 345, 40, 600),
        TripDestinationItem("mysore_palace", "Mysuru Palace & Chamundi", "Mysuru", 145, 100, 1000),
        TripDestinationItem("kabini", "Kabini Tiger & Elephant Safari", "Mysuru", 215, 1550, 2000),
        TripDestinationItem("bandipur", "Bandipur Tiger Reserve", "Chamarajanagar", 220, 1000, 2500),
        TripDestinationItem("kudremukh", "Kudremukh Peak Trek", "Chikkamagaluru", 330, 575, 575),
        TripDestinationItem("udupi_gokarna", "Udupi & Gokarna Coastal Trail", "Udupi", 410, 0, 0),
        TripDestinationItem("belur_halebidu", "Belur & Halebidu UNESCO Temples", "Hassan", 220, 40, 600),
        TripDestinationItem("jog_falls", "Jog Falls Plunge", "Shivamogga", 380, 20, 100),
        TripDestinationItem("murudeshwar", "Murudeshwar Shiva Temple & Sea", "Uttara Kannada", 460, 0, 0),
        TripDestinationItem("badami", "Badami Cave Temples & Pattadakal", "Bagalkote", 450, 30, 500)
    )

    fun calculateTotalTripCost(
        selectedDestinations: List<TripDestinationItem>,
        tripDays: Int,
        adultsCount: Int,
        childrenCount: Int,
        isForeignNational: Boolean,
        transportMode: TravelTransportMode,
        stayType: AccommodationType,
        diningPreference: DiningBudgetPreference
    ): TravelCostBreakdown {
        val totalPeople = (adultsCount + childrenCount).coerceAtLeast(1)
        val payingPeople = adultsCount + (childrenCount * 0.5)

        // Find maximum round trip distance based on selected destinations
        val maxOneWayKm = selectedDestinations.maxOfOrNull { it.distanceFromBangaloreKm } ?: 150
        val roundTripKm = (maxOneWayKm * 2) + (selectedDestinations.size * 30) // extra local sightseeing buffer

        // Transport Cost
        val transportCost = when (transportMode) {
            TravelTransportMode.SELF_DRIVE_CAR -> (roundTripKm * transportMode.approxPerKmRate).toInt()
            TravelTransportMode.OUTSTATION_CAB -> (roundTripKm * transportMode.approxPerKmRate).toInt()
            TravelTransportMode.KSRTC_AIRAVAT_BUS -> (roundTripKm * transportMode.approxPerKmRate * payingPeople).toInt()
            TravelTransportMode.INDIAN_RAILWAYS -> (roundTripKm * transportMode.approxPerKmRate * payingPeople).toInt()
        }

        // Accommodation Cost (assumes 2 people per room)
        val roomsNeeded = ((totalPeople + 1) / 2).coerceAtLeast(1)
        val stayNights = (tripDays - 1).coerceAtLeast(1)
        val stayCost = roomsNeeded * stayNights * stayType.avgNightCostInr

        // Dining Cost
        val diningCost = (payingPeople * tripDays * diningPreference.avgDailyCostPerPersonInr).toInt()

        // Entry Tickets Cost
        var entryTicketsCost = 0
        selectedDestinations.forEach { spot ->
            val adultTicket = if (isForeignNational) spot.ticketForeignerInr else spot.ticketDomesticInr
            val childTicket = (adultTicket * 0.4).toInt() // free or 50% for children
            entryTicketsCost += (adultsCount * adultTicket) + (childrenCount * childTicket)
        }

        val totalCost = transportCost + stayCost + diningCost + entryTicketsCost
        val perPerson = totalCost / totalPeople
        val perDay = totalCost / tripDays.coerceAtLeast(1)

        val usdEquiv = String.format("~$%.2f USD", totalCost / 83.5)

        val tips = mutableListOf<String>()
        if (selectedDestinations.any { it.ticketDomesticInr > 0 }) {
            tips.add("Book ASI monument tickets online at asi.nic.in to save ~₹5–₹10 per ticket and avoid physical queues.")
        }
        if (transportMode == TravelTransportMode.KSRTC_AIRAVAT_BUS) {
            tips.add("Booking round-trip tickets on ksrtc.in provides a flat 10% discount on return fares.")
        }
        if (diningPreference == DiningBudgetPreference.SATVIK_ANNADANA_BUDGET) {
            tips.add("Enjoying lunch at Dharmasthala/Sringeri/Udupi temple dining halls is completely free, authentic, and divine.")
        }
        if (selectedDestinations.any { it.id == "kabini" || it.id == "bandipur" }) {
            tips.add("Forest safaris sell out fast on weekends; book Jungle Lodges packages or reach ticket counter by 5:30 AM.")
        }

        return TravelCostBreakdown(
            totalEstimatedCostInr = totalCost,
            approxUsdEquivalent = usdEquiv,
            perPersonCostInr = perPerson,
            perDayCostInr = perDay,
            transportCostInr = transportCost,
            stayCostInr = stayCost,
            diningCostInr = diningCost,
            entryTicketsCostInr = entryTicketsCost,
            roundTripDistanceKm = roundTripKm,
            moneySavingTips = tips
        )
    }
}
