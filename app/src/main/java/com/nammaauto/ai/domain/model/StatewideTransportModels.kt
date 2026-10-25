package com.nammaauto.ai.domain.model

data class TransportModeComparison(
    val id: String,
    val title: String,
    val operatorType: String, // "Govt of Karnataka", "Private Operators", "Indian Railways (SWR)", "Self-Drive"
    val badgeColorHex: Long,
    val headline: String,
    val howItHelpsTravelers: String,
    val keyFeatures: List<String>,
    val pros: List<String>,
    val cons: List<String>,
    val bestSuitedFor: String,
    val whereToBuyTickets: String,
    val bookingUrl: String,
    val approxPriceRange: String
)

data class SeasonTravelInfo(
    val seasonId: String,
    val seasonTitle: String,
    val monthRange: String,
    val climateSummary: String,
    val topDestinationsToVisit: List<String>,
    val whatToAvoid: String,
    val packingAndTravelTip: String,
    val tagColorHex: Long
)

data class OfficialGuideGuideline(
    val id: String,
    val circuitOrSpot: String,
    val guideType: String,
    val officialRateCard: String,
    val howToVerifyOfficialBadge: String,
    val howToBookOrHire: String,
    val scamWarning: String
)

object StatewideTransportData {

    val transportComparisons = listOf(
        TransportModeComparison(
            id = "ksrtc_buses",
            title = "KSRTC State Express & Volvo Buses",
            operatorType = "Govt of Karnataka (KSRTC / NWKRTC / KKRTC)",
            badgeColorHex = 0xFF10B981,
            headline = "The Most Reliable, Punctual & Safe Network Covering Every Corner of Karnataka",
            howItHelpsTravelers = "KSRTC operates India's most modern state fleet: 'Ambari Utsav' (Ultra-luxury Volvo 9600 Scandinavian sleepers), 'Ambari Dream Class', and 'Airavat Club Class' (Multi-axle AC). Government drivers are strictly trained for Western Ghats fog and hairpin turns.",
            keyFeatures = listOf(
                "Ambari Utsav: Individual berths with personal USB, reading lamps & panoramic windows",
                "Flybus: Direct AC luxury coaches from Bengaluru Airport (KIAL) to Mysuru, Coorg & Mangaluru",
                "No arbitrary breakdown abandonments in remote forest ghats",
                "Round-trip 5% ticket discount when booked together"
            ),
            pros = listOf(
                "Drops passengers at central municipal bus terminals rather than remote highway bypasses",
                "Clean, certified roadside KSRTC refreshment rest-stops with clean washrooms",
                "Punctual departure and live GPS tracking on KSRTC Awatar mobile app"
            ),
            cons = listOf(
                "Weekend tickets sell out 1-2 weeks in advance during festive periods"
            ),
            bestSuitedFor = "Solo travelers, families, foreign tourists seeking dependable safety on mountain roads.",
            whereToBuyTickets = "Official KSRTC Portal (ksrtc.in) or KSRTC Mobile App. 30 days advance booking.",
            bookingUrl = "https://ksrtc.in",
            approxPriceRange = "₹350 (Rajahamsa) to ₹1,100–₹1,500 (Ambari Utsav AC Sleeper)"
        ),

        TransportModeComparison(
            id = "private_sleepers",
            title = "Private Overnight Sleeper Buses",
            operatorType = "Private Operators (VRL, SRS, Seabird, Sugama, Durgamba)",
            badgeColorHex = 0xFFF97316,
            headline = "Dense Night Network with Numerous City Boarding Points",
            howItHelpsTravelers = "Private operators run hundreds of multi-axle AC sleeper buses connecting Bengaluru, Mysuru, Hubballi, Belagavi, Gokarna, Karwar, and Mangaluru. Crucial when government buses are fully booked.",
            keyFeatures = listOf(
                "Dozens of suburban pickup points (Silk Board, Majestic, Yesvantpur, Anand Rao Circle)",
                "Frequent dynamic departures up to 11:30 PM",
                "Easy booking via aggregators like RedBus and AbhiBus"
            ),
            pros = listOf(
                "Higher availability for spontaneous, last-minute weekend getaways",
                "Multiple private lounges with AC waiting rooms in major transit hubs"
            ),
            cons = listOf(
                "Often drop passengers at highway ring road flyovers at 4:30 AM rather than central city centers",
                "Dynamic surge pricing can double during festivals and long weekends",
                "Restroom stops at private highway dhabas can vary widely in cleanliness"
            ),
            bestSuitedFor = "Backpackers and travelers needing last-minute departures or suburban pickups.",
            whereToBuyTickets = "RedBus (redbus.in), AbhiBus (abhibus.com), or VRL official (vrlbus.in).",
            bookingUrl = "https://www.redbus.in",
            approxPriceRange = "₹600 to ₹1,800+ (Dynamic Surge)"
        ),

        TransportModeComparison(
            id = "swr_railways",
            title = "South Western Railway (SWR) & Vistadome Trains",
            operatorType = "Indian Railways (SWR / Vande Bharat / Vistadome)",
            badgeColorHex = 0xFF8B5CF6,
            headline = "Scenic Vistadome Glass Coaches & Ultra-Fast Vande Bharat Express",
            howItHelpsTravelers = "Rail connects every major Karnataka tourist hub without highway traffic fatigue. The Panchaganga & Yesvantpur-Karwar Express feature 360-degree glass Vistadome observation coaches winding through the lush Western Ghats (Sakleshpur-Subrahmanya green route) passing misty waterfalls.",
            keyFeatures = listOf(
                "Vistadome 360° Glass Coaches: Sakleshpur to Subrahmanya Western Ghats rail journey",
                "Vande Bharat Semi-High Speed: Bengaluru to Mysuru (1h 45m) & Bengaluru to Hubballi-Dharwad (5h)",
                "Hampi Express & Gol Gumbaz Express: Direct overnight sleepers to heritage capitals"
            ),
            pros = listOf(
                "Unmatched scenic beauty and comfort through Western Ghats tunnels and viaducts",
                "Zero road nausea or mountain road motion sickness",
                "Most economical long-distance travel option in India"
            ),
            cons = listOf(
                "Vistadome coaches have only 44 seats per train; must be booked 15-30 days prior on IRCTC",
                "Rail stations (Hospet for Hampi, Gokarna Road) require 15-minute connecting auto/cab ride"
            ),
            bestSuitedFor = "Scenic nature lovers, photography enthusiasts, and budget-conscious backpackers.",
            whereToBuyTickets = "Official IRCTC portal (irctc.co.in) or IRCTC Rail Connect App.",
            bookingUrl = "https://www.irctc.co.in",
            approxPriceRange = "₹180 (Sleeper) to ₹650 (AC 3T) / ₹1,525 (Vistadome Coach)"
        ),

        TransportModeComparison(
            id = "kstdc_jungle_lodges",
            title = "KSTDC Guided Tours & Jungle Lodges Safaris",
            operatorType = "Govt of Karnataka (KSTDC & Jungle Lodges)",
            badgeColorHex = 0xFFFACC15,
            headline = "The Gold Standard for All-Inclusive Heritage & Wildlife Safaris",
            howItHelpsTravelers = "KSTDC offers curated multi-day package tours (hotel + AC coach + certified guide) for Hampi, Belur-Halebeedu, and Coastal Karnataka. Jungle Lodges & Resorts (JLR) provides eco-luxury wildlife stays with exclusive access to early morning river boat and jeep safaris in core tiger reserves.",
            keyFeatures = listOf(
                "Jungle Lodges: Exclusive core-zone river boat & jeep safaris in Kabini & Bandipur",
                "KSTDC Package Tours: Pre-arranged hotel stay at Mayura hotels + entrance permits + verified guide",
                "Hop-On Hop-Off (HOHO) tourist buses operating in Mysuru and Hampi"
            ),
            pros = listOf(
                "100% scam-free, zero hassle with bookings, entrance passes, or hotel verification",
                "Jungle Lodges naturalists are Karnataka's finest wildlife trackers",
                "Strict ethical tourism respecting wildlife and heritage monuments"
            ),
            cons = listOf(
                "Fixed itinerary schedule with limited flexibility for spontaneous detours",
                "Jungle Lodges packages are premium priced"
            ),
            bestSuitedFor = "Elderly travelers, foreign nationals wanting end-to-end guidance, and wildlife enthusiasts.",
            whereToBuyTickets = "KSTDC (kstdc.co) and Jungle Lodges & Resorts (junglelodges.com).",
            bookingUrl = "https://www.junglelodges.com",
            approxPriceRange = "KSTDC: ₹1,500–₹4,500/day | Jungle Lodges: ₹8,000–₹16,000/night (All-Inclusive)"
        ),

        TransportModeComparison(
            id = "self_drive_rentals",
            title = "Self-Drive Cars vs Chauffeur Outstation Cabs",
            operatorType = "Rental Aggregators (Zoomcar, Revv / Savaari, MakeMyTrip)",
            badgeColorHex = 0xFF38BDF8,
            headline = "Ultimate Itinerary Freedom vs Mountain Driving Reality",
            howItHelpsTravelers = "Renting a self-drive SUV or hiring a chauffeur outstation taxi gives 100% freedom to stop at secluded beaches, roadside waterfalls, and coffee plantations at your own leisure.",
            keyFeatures = listOf(
                "Great for Coastal Highway NH 66 (Maravanthe beach drive between sea and river)",
                "Doorstep car pickup and drop in major airport cities (Bengaluru, Mangaluru)",
                "Chauffeur cabs eliminate fatigue on challenging mountain roads"
            ),
            pros = listOf(
                "Complete flexibility to change route or stay longer at stunning viewpoints",
                "Easy luggage transport for groups and families"
            ),
            cons = listOf(
                "Self-drive on Western Ghats (Agumbe 14 hairpin bends, Charmadi Ghat) requires seasoned hill driving skills",
                "Monsoon rains cause dense fog, landslides, and slippery wet road surfaces",
                "Interstate border permits required if crossing into Goa or Kerala"
            ),
            bestSuitedFor = "Small road-trip friend groups and families on coastal or expressway circuits.",
            whereToBuyTickets = "Zoomcar (zoomcar.com), Revv (revv.co.in), Savaari Cabs (savaari.com).",
            bookingUrl = "https://www.savaari.com",
            approxPriceRange = "Self-Drive: ₹2,000–₹4,500/day | Chauffeur Cab: ₹12–₹16 per km"
        )
    )

    val seasonalTravelGuides = listOf(
        SeasonTravelInfo(
            seasonId = "winter_peak",
            seasonTitle = "Winter (Peak Heritage & Coastal Season)",
            monthRange = "November to February",
            climateSummary = "Pleasant, clear skies with cool nights (18°C – 28°C). The best time of the year for almost the entire state.",
            topDestinationsToVisit = listOf(
                "Hampi, Badami & Pattadakal (Comfortable 25°C exploration without summer heat)",
                "St. Mary's Island & Gokarna Beaches (Calm turquoise waters & sea breeze)",
                "Netrani Island Scuba Diving (Highest underwater visibility: 15–20 meters)",
                "Mysore Palace & Srirangapatna (Cool evening walks & illumination)"
            ),
            whatToAvoid = "High peak crowd during Christmas & New Year; pre-book hotels and trains well in advance.",
            packingAndTravelTip = "Light cottons for daytime, light jacket/shawl for cool evenings in North Karnataka and Ghats.",
            tagColorHex = 0xFF10B981
        ),

        SeasonTravelInfo(
            seasonId = "monsoon_magic",
            seasonTitle = "Monsoon (Waterfalls & Rainforest Magic)",
            monthRange = "July to September",
            climateSummary = "Heavy, rhythmic rainfall across Western Ghats and Karavali coast, transforming the hills into an emerald green paradise.",
            topDestinationsToVisit = listOf(
                "Jog Falls (Roaring at maximum 253m deluge power)",
                "Agumbe Rainforest (The Cherrapunji of South India with active cascading streams)",
                "Shivanasamudra Twin Falls (Gaganachukki & Barachukki Cauvery torrent)",
                "Chikkamagaluru & Coorg Coffee Plantations (Misty verandas & hot filter coffee)"
            ),
            whatToAvoid = "STRICTLY AVOID sea swimming and island boat ferries (St. Mary's and Netrani are completely closed by port authorities due to rough sea).",
            packingAndTravelTip = "High-quality waterproof rain jacket, quick-dry synthetic clothes, waterproof phone pouch, and leech socks for forest walks.",
            tagColorHex = 0xFF38BDF8
        ),

        SeasonTravelInfo(
            seasonId = "autumn_festivals",
            seasonTitle = "Autumn (Treks & Mysore Dasara Festival)",
            monthRange = "September to November",
            climateSummary = "Crisp, rain-washed landscapes, lush rolling meadows, and post-monsoon blooming orchids.",
            topDestinationsToVisit = listOf(
                "Mysuru Dasara (10-day grand royal cultural extravaganza & illuminated palace)",
                "Kudremukh & Kumara Parvatha Treks (Velvet green shola grasslands via Aranya Vihaara)",
                "Belur & Halebeedu Hoysala Temples (Clean sunny weather & temple ponds filled)",
                "Dandeli Kali River Rafting (Dam water releases resumed for river sports)"
            ),
            whatToAvoid = "Mysuru hotels become 100% occupied during Dasara; book accommodations months in advance.",
            packingAndTravelTip = "Hiking boots with deep rubber treads for mountain trails, camera for post-monsoon greenery.",
            tagColorHex = 0xFFFACC15
        ),

        SeasonTravelInfo(
            seasonId = "summer_wildlife",
            seasonTitle = "Summer (Tiger Safaris & Hill Station Escapes)",
            monthRange = "March to May",
            climateSummary = "Hot in the plains (34°C – 40°C in North Karnataka), pleasantly temperate in the Western Ghats hill stations (20°C – 28°C).",
            topDestinationsToVisit = listOf(
                "Kabini & Nagarhole National Park (Best tiger, leopard & elephant sightings as animals crowd drying riverbeds)",
                "Bandipur Tiger Reserve (High predator sighting probability around waterholes)",
                "Coorg (Madikeri, Talakaveri) & Chikkamagaluru (Cool mountain retreat amidst coffee blossoms)",
                "Kemmangundi & Kudremukh (Cooler forest shade escapes)"
            ),
            whatToAvoid = "Avoid midday walking at Hampi, Badami, or Bijapur (granite rocks absorb heat, reaching 42°C). If visiting, tour 6:00 AM – 10:30 AM and 4:30 PM – 6:30 PM.",
            packingAndTravelTip = "Sun hat, high-SPF sunscreen, polarized sunglasses, cotton linen apparel, and electrolyte hydration packs.",
            tagColorHex = 0xFFF97316
        )
    )

    val guideGuidelines = listOf(
        OfficialGuideGuideline(
            id = "guide_hampi",
            circuitOrSpot = "Hampi, Badami & Pattadakal (UNESCO Monuments)",
            guideType = "ASI & Ministry of Tourism Certified Guides",
            officialRateCard = "Half-Day: ₹500 – ₹800 | Full-Day: ₹1,200 – ₹1,500 (For group of up to 5)",
            howToVerifyOfficialBadge = "Must wear an official Ministry of Tourism green/blue lanyard photo identity card with hologram badge.",
            howToBookOrHire = "Hire directly at the ASI ticket counter outside Virupaksha Temple or Vijaya Vittala entrance.",
            scamWarning = "Decline touts approaching you at car parking or riverbanks claiming to be 'student guides' or 'special access guides'."
        ),
        OfficialGuideGuideline(
            id = "guide_mysore_palace",
            circuitOrSpot = "Mysore Palace (Amba Vilas)",
            guideType = "Multilingual Audio Guide Device & KSTDC Guides",
            officialRateCard = "Audio Guide Device: Included FREE in Foreigner ticket (₹1,000) / ₹100 for Domestic. Physical Guide: ₹400 fixed.",
            howToVerifyOfficialBadge = "Audio guide booths are located right inside the palace residential entrance counter.",
            howToBookOrHire = "Collect electronic audio wand at South Gate counter (available in English, French, German, Spanish, Japanese, Italian, Hindi, Kannada).",
            scamWarning = "Do not follow auto/tonga drivers who offer to take you to 'private palace viewpoints' or unverified handicraft shops."
        ),
        OfficialGuideGuideline(
            id = "guide_aranya_treks",
            circuitOrSpot = "Western Ghats Treks (Kudremukh, Kumara Parvatha, Skandagiri)",
            guideType = "Karnataka Forest Department Certified Eco-Guide",
            officialRateCard = "Mandatory 1 guide assigned per batch of 10 trekkers (₹1,000 total per batch / approx ₹100 per trekker).",
            howToVerifyOfficialBadge = "Eco-guides wear official Karnataka Forest Department uniform badges at the base checkpost.",
            howToBookOrHire = "Automatically allocated when you present your valid Aranya Vihaara booking permit at the base checkpoint (Balagal/Mullodi).",
            scamWarning = "Never attempt to hike Western Ghats peak trails without a certified forest guide; forest guards strictly intercept and penalize unauthorized trekkers."
        ),
        OfficialGuideGuideline(
            id = "guide_scuba_netrani",
            circuitOrSpot = "Netrani Island Scuba Diving (Murudeshwar)",
            guideType = "PADI & SSI Certified Divemasters",
            officialRateCard = "Included in ₹3,500 beginner discovery package (1-on-1 underwater guided dive).",
            howToVerifyOfficialBadge = "Ask to see the divemaster's PADI / SSI active certification credentials at the dive center office.",
            howToBookOrHire = "Book at registered dive centers in Murudeshwar beach town prior to 7:30 AM morning departure.",
            scamWarning = "Never board unauthorized local fishing trawlers offering unofficial snorkeling without certified dive gear or life jackets."
        )
    )
}
