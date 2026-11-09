package com.namma.travelcompanion.network.api

data class LiveOfficialTariff(
    val spotId: String,
    val spotName: String,
    val authority: String,
    val indianAdultRateInr: Int,
    val foreignerAdultRateInr: Int,
    val approxUsd: String,
    val childRateInr: Int,
    val cameraFeeNotes: String,
    val acceptedPaymentMethods: String,
    val isCashMandatoryWarning: Boolean,
    val groundTruthRealityTip: String,
    val officialBookingUrl: String,
    val counterTimings: String,
    val lastVerifiedDate: String
)

object LiveTourismPriceService {

    val liveTariffs = listOf(
        LiveOfficialTariff(
            spotId = "mysore_palace",
            spotName = "Mysore Palace (Amba Vilas)",
            authority = "Mysuru Palace Board (Govt of Karnataka)",
            indianAdultRateInr = 120,
            foreignerAdultRateInr = 1000,
            approxUsd = "~$12.00 USD (Includes Multilingual Audio Wand)",
            childRateInr = 70,
            cameraFeeNotes = "Mobile phone photography free; Video cameras require ₹200 token. Still cameras allowed without flash.",
            acceptedPaymentMethods = "CASH ONLY AT PHYSICAL GATES (UPI frequently down)",
            isCashMandatoryWarning = true,
            groundTruthRealityTip = "CRITICAL: Physical ticket booths at Varaha Gate strictly take CASH ONLY. UPI / GPay QR codes frequently fail due to server load. Keep ₹500-₹1,000 cash handy before joining queue!",
            officialBookingUrl = "https://mysorepalace.karnataka.gov.in",
            counterTimings = "10:00 AM – 5:30 PM (Sound & Light show has separate counter 6:30 PM)",
            lastVerifiedDate = "Official Govt Revision"
        ),
        LiveOfficialTariff(
            spotId = "kabini_safari",
            spotName = "Nagarhole / Kabini Wildlife Safari (Dammanakatte)",
            authority = "Karnataka Forest Dept & Jungle Lodges (JLR)",
            indianAdultRateInr = 1550,
            foreignerAdultRateInr = 2000,
            approxUsd = "~$24.00 (Canter Bus) / ~$45.00 (Open Gypsy Jeep)",
            childRateInr = 775,
            cameraFeeNotes = "DSLR / Professional telephoto lens (>200mm) incur ₹250–₹500 Forest Dept fee. Drones strictly illegal under Wildlife Protection Act.",
            acceptedPaymentMethods = "Online Pre-Booking Portal (UPI/Cards) | Cash at Dammanakatte counter",
            isCashMandatoryWarning = false,
            groundTruthRealityTip = "Spot tickets at Dammanakatte counter (Antharasanthe) are extremely limited and sell out within 15 minutes of opening (6:00 AM & 3:00 PM). JLR package stays guarantee core-zone river boat and jeep safaris.",
            officialBookingUrl = "https://junglelodges.com",
            counterTimings = "Morning: 6:00 AM – 9:00 AM | Evening: 3:00 PM – 6:00 PM",
            lastVerifiedDate = "Official Forest Tariff"
        ),
        LiveOfficialTariff(
            spotId = "bandipur_safari",
            spotName = "Bandipur Tiger Reserve Safari (Melukamanahalli)",
            authority = "Karnataka Forest Department & Jungle Lodges",
            indianAdultRateInr = 1000,
            foreignerAdultRateInr = 2500,
            approxUsd = "~$30.00 USD (Canter Bus) / ~$45.00+ (Jeep)",
            childRateInr = 500,
            cameraFeeNotes = "DSLR Camera with lens ₹250 fee. Mobile phones are prohibited from being operated inside the reserve during safari.",
            acceptedPaymentMethods = "Online Forest Portal | Cash/Cards at Melukamanahalli Counter",
            isCashMandatoryWarning = false,
            groundTruthRealityTip = "Counter tickets at Melukamanahalli reception open at 6:00 AM, but queues on weekends start at 5:15 AM. Arrive early or book Jungle Lodges Bandipur Safari Lodge package.",
            officialBookingUrl = "https://junglelodges.com",
            counterTimings = "Morning: 6:30 AM – 9:00 AM | Evening: 3:30 PM – 6:00 PM",
            lastVerifiedDate = "Official Tiger Reserve Rate"
        ),
        LiveOfficialTariff(
            spotId = "kudremukh_trek",
            spotName = "Kudremukh Peak Trek (Kudremukh National Park)",
            authority = "Karnataka Forest Dept (Aranya Vihaara)",
            indianAdultRateInr = 575,
            foreignerAdultRateInr = 575,
            approxUsd = "~$6.90 USD (Forest Permit) + ₹100 local guide share",
            childRateInr = 575,
            cameraFeeNotes = "Personal cameras allowed. Drones strictly confiscated at Balagal checkpost.",
            acceptedPaymentMethods = "ONLINE ONLY on aranyavihaara.karnataka.gov.in (Cash for 4x4 Jeep)",
            isCashMandatoryWarning = true,
            groundTruthRealityTip = "ZERO CELLULAR SIGNAL at Balagal/Mullodi base checkpost. UPI will NOT work. You must carry ₹1,000 cash for 4x4 Jeep transfer (₹500/person return), leech socks, and local eco-guide share.",
            officialBookingUrl = "https://aranyavihaara.karnataka.gov.in",
            counterTimings = "Checkpost entry strictly 6:00 AM – 9:00 AM (Max 50 trekkers/day)",
            lastVerifiedDate = "Aranya Vihaara Verified"
        ),
        LiveOfficialTariff(
            spotId = "hampi_unesco",
            spotName = "Hampi UNESCO Monuments (Vittala & Zenana)",
            authority = "Archaeological Survey of India (ASI)",
            indianAdultRateInr = 40,
            foreignerAdultRateInr = 600,
            approxUsd = "~$7.20 USD (Free for under 15 years)",
            childRateInr = 0,
            cameraFeeNotes = "Handheld camera free. Commercial tripods and drones require written clearance from ASI New Delhi.",
            acceptedPaymentMethods = "Online QR Portal (onlinemcticket.asi.nic.in) / Cards / UPI",
            isCashMandatoryWarning = false,
            groundTruthRealityTip = "Booking tickets online on asi.nic.in via phone saves you standing in the 45-minute sun queue at Vijaya Vittala. Same ticket allows entry to Lotus Mahal & ASI Kamalapura Museum on the same day.",
            officialBookingUrl = "https://asi.nic.in",
            counterTimings = "6:00 AM – 6:00 PM (Sunrise to Sunset)",
            lastVerifiedDate = "ASI Central Tariff"
        ),
        LiveOfficialTariff(
            spotId = "netrani_scuba",
            spotName = "Netrani Coral Island Scuba Diving (Murudeshwar)",
            authority = "PADI / SSI Registered Centers & Port Dept",
            indianAdultRateInr = 3500,
            foreignerAdultRateInr = 3500,
            approxUsd = "~$42.00 USD (Includes Gear, Boat, Dive & Underwater GoPro Video)",
            childRateInr = 1999,
            cameraFeeNotes = "Underwater GoPro footage and video clips are included free in the package.",
            acceptedPaymentMethods = "UPI / Card / Cash at registered dive offices in Murudeshwar",
            isCashMandatoryWarning = false,
            groundTruthRealityTip = "Indian Navy prohibits landing on the rocky island itself; all dives are launched directly from the boat. Non-diving family companions can ride the catamaran for ₹1,999.",
            officialBookingUrl = "https://karnatakatourism.org",
            counterTimings = "Boat departs 7:30 AM from Murudeshwar Temple Beach Jetty",
            lastVerifiedDate = "PADI Dive Tariff"
        ),
        LiveOfficialTariff(
            spotId = "st_marys_island",
            spotName = "St. Mary's Island Basaltic Rocks Ferry (Malpe)",
            authority = "Malpe Beach Development Board (Govt of Karnataka)",
            indianAdultRateInr = 350,
            foreignerAdultRateInr = 400,
            approxUsd = "~$4.80 USD (Round-trip Ferry Boat)",
            childRateInr = 150,
            cameraFeeNotes = "Camera allowed. Single-use plastic bottles confiscated at luggage scanner before boarding.",
            acceptedPaymentMethods = "UPI QR / Cash at Malpe Tourist Jetty Counter",
            isCashMandatoryWarning = false,
            groundTruthRealityTip = "Strictly CLOSED June to September monsoons due to rough sea waves. Ferries operate only October to May. Last boat returns from island at 5:45 PM.",
            officialBookingUrl = "https://karnatakatourism.org",
            counterTimings = "9:00 AM – 5:00 PM (Departs as batches fill up)",
            lastVerifiedDate = "Malpe Port Board"
        ),
        LiveOfficialTariff(
            spotId = "gol_gumbaz",
            spotName = "Gol Gumbaz & Whispering Gallery (Vijayapura)",
            authority = "Archaeological Survey of India (ASI)",
            indianAdultRateInr = 25,
            foreignerAdultRateInr = 300,
            approxUsd = "~$3.60 USD (Free for under 15 years)",
            childRateInr = 0,
            cameraFeeNotes = "Still camera free. Video camera ₹25.",
            acceptedPaymentMethods = "Online ASI Portal / UPI / Cash",
            isCashMandatoryWarning = false,
            groundTruthRealityTip = "Visit at 6:30 AM to experience the true 11-time acoustic echo before crowd noise drowns out the Whispering Gallery.",
            officialBookingUrl = "https://asi.nic.in",
            counterTimings = "6:00 AM – 6:00 PM (Daily)",
            lastVerifiedDate = "ASI Central Tariff"
        )
    )

    fun getTariffForSpot(spotId: String): LiveOfficialTariff? {
        return liveTariffs.firstOrNull { it.spotId == spotId }
    }
}
