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

    val corridorTransitList = listOf(
        StatewideCorridorTransit(
            id = "corridor_blr_mys",
            routeTitle = "Bengaluru ➔ Mysuru",
            originCity = "Bengaluru",
            destinationCity = "Mysuru",
            distanceKm = 145,
            approxTravelTime = "1h 45m (Train) / 2h 15m (Expressway Bus)",
            govtBus = GovtBusCorridorInfo(
                fleetOptions = listOf(
                    Triple("KSRTC Flybus", "Direct from Airport KIAL", "₹800 (Luggage Incl.)"),
                    Triple("Airavat Club Class", "Multi-Axle AC Volvo", "₹360 – ₹420"),
                    Triple("Rajahamsa Executive", "Deluxe Air Suspension", "₹240 – ₹270"),
                    Triple("Karnataka Sarige", "Express (Shakti Free for Women)", "₹170")
                ),
                departureHub = "Satellite Bus Stand (Mysore Road) & Majestic (Platform 2A)",
                frequency = "Departs every 5 minutes around the clock",
                liveTrackingAvailable = true,
                portalUrl = "https://ksrtc.in",
                shaktiSchemeApplicable = true,
                travelerTip = "KSRTC uses the 10-lane Access-Controlled Expressway. Buses do not get stuck in city traffic after Kengeri. Zero luggage surcharge."
            ),
            railway = RailCorridorInfo(
                keyTrains = listOf(
                    TrainOption("20607 / 20661", "Vande Bharat Express", "10:30 AM / 05:45 AM", "12:15 PM / 07:30 AM", "1h 45m", "CC: ₹550 | EC: ₹1,050", true),
                    TrainOption("12007", "Shatabdi Express", "11:00 AM", "01:00 PM", "2h 00m", "CC: ₹495 | EC: ₹980", true),
                    TrainOption("16216", "Chamundi Express", "06:15 PM", "08:50 PM", "2h 35m", "2S: ₹80 | CC: ₹285", false),
                    TrainOption("12614", "Tipu Express (Superfast)", "03:00 PM", "05:30 PM", "2h 30m", "2S: ₹80 | CC: ₹315", false)
                ),
                stationNames = "KSR Bengaluru (SBC) / SMVT to Mysuru Junction (MYS)",
                liveTrackingUrl = "https://enquiry.indianrail.gov.in",
                irctcBookingUrl = "https://www.irctc.co.in",
                utsMobileAdvice = "Download UTS on Mobile app to buy unreserved 2S tickets in 10 seconds without standing in railway ticket line.",
                travelerTip = "Chamundi & Tipu Express are Karnataka's most punctual commuter trains with comfortable padded seating."
            ),
            privateBus = PrivateBusCorridorInfo(
                majorOperators = listOf("VRL Travels", "SRS Travels", "Seabird", "InterCity SmartBus"),
                nonPeakFareRange = "₹350 – ₹550",
                surgeWeekendFareRange = "₹700 – ₹1,100 (During Dasara / Long Weekends)",
                boardingPointsAdvisory = "Boarding is often at highway flyovers (Madiwala, Silk Board) which can be congested. Government KSRTC is faster due to dedicated terminal bay.",
                bookingUrl = "https://www.redbus.in",
                travelerTip = "Private buses are rarely needed on this route because KSRTC departs every 5 minutes and trains run hourly."
            ),
            liveRouteAdvisory = "Bengaluru-Mysuru Expressway (NH 275) has automated speed cameras (100 km/h limit). Autos and 2-wheelers banned on main expressway."
        ),

        StatewideCorridorTransit(
            id = "corridor_blr_hmp",
            routeTitle = "Bengaluru ➔ Hospet / Hampi (UNESCO)",
            originCity = "Bengaluru",
            destinationCity = "Hospet / Hampi",
            distanceKm = 345,
            approxTravelTime = "7h 30m (Overnight Bus / Train)",
            govtBus = GovtBusCorridorInfo(
                fleetOptions = listOf(
                    Triple("Ambari Dream Class", "AC Multi-Axle Sleeper", "₹850 – ₹950"),
                    Triple("KSRTC Non-AC Sleeper", "Comfortable Berths", "₹580 – ₹650"),
                    Triple("Rajahamsa Executive", "Semi-Sleeper Deluxe", "₹450 – ₹520")
                ),
                departureHub = "Kempegowda Bus Station (Majestic) Platform 2B",
                frequency = "8 departures nightly (8:30 PM to 11:15 PM)",
                liveTrackingAvailable = true,
                portalUrl = "https://ksrtc.in",
                shaktiSchemeApplicable = false,
                travelerTip = "KSRTC drops at Hospet New Bus Stand. Local BMTC/NWKRTC connecting shuttle to Hampi Bazaar departs every 15 mins for ₹18."
            ),
            railway = RailCorridorInfo(
                keyTrains = listOf(
                    TrainOption("16592", "Hampi Express", "09:50 PM", "07:10 AM", "9h 20m", "SL: ₹245 | 3A: ₹655 | 2A: ₹930", false),
                    TrainOption("06243", "Hosapete Intercity Fast", "05:00 AM", "02:30 PM", "9h 30m", "2S: ₹145 | CC: ₹520", false)
                ),
                stationNames = "KSR Bengaluru (SBC) / Yesvantpur (YPR) to Hosapete Junction (HPT)",
                liveTrackingUrl = "https://enquiry.indianrail.gov.in",
                irctcBookingUrl = "https://www.irctc.co.in",
                utsMobileAdvice = "Hampi Express sleeper berths get booked out 2 weeks in advance. Tatkal quota opens at 11:00 AM 1 day prior.",
                travelerTip = "Hampi Express is the favorite overnight train for international backpackers; safe, smooth, and arrives just in time for sunrise."
            ),
            privateBus = PrivateBusCorridorInfo(
                majorOperators = listOf("VRL Travels", "SRS Travels", "Greenline", "Go Tour"),
                nonPeakFareRange = "₹750 – ₹950",
                surgeWeekendFareRange = "₹1,400 – ₹1,900 (High Tourist Season Nov–Feb)",
                boardingPointsAdvisory = "Some private operators drop directly near Hampi Bazaar or Kamalapur, saving the Hospet-to-Hampi auto ride (₹200 value).",
                bookingUrl = "https://www.redbus.in",
                travelerTip = "Verify whether your private bus drop is at Hospet Bypass or Hampi village before booking."
            ),
            liveRouteAdvisory = "Four-lane highway via Chitradurga is smooth. Winter morning fog between Sira and Hospet can slow early arrivals."
        ),

        StatewideCorridorTransit(
            id = "corridor_blr_mng_udp",
            routeTitle = "Bengaluru ➔ Mangaluru & Udupi (Karavali)",
            originCity = "Bengaluru",
            destinationCity = "Mangaluru / Udupi",
            distanceKm = 360,
            approxTravelTime = "7h 00m (Expressway) / 8h 30m (Scenic Rail)",
            govtBus = GovtBusCorridorInfo(
                fleetOptions = listOf(
                    Triple("Ambari Utsav", "Volvo 9600 Scandinavian Sleeper", "₹1,250 – ₹1,450"),
                    Triple("Airavat Club Class", "Multi-Axle AC Semi-Sleeper", "₹820 – ₹950"),
                    Triple("Karnataka Sarige", "Express via Shiradi Ghat", "₹450")
                ),
                departureHub = "Kempegowda Bus Station (Majestic) & Satellite Bus Stand",
                frequency = "Departs hourly day and night 24x7",
                liveTrackingAvailable = true,
                portalUrl = "https://ksrtc.in",
                shaktiSchemeApplicable = false,
                travelerTip = "KSRTC drivers are certified specifically for Western Ghats night navigation and hairpin curve handling."
            ),
            railway = RailCorridorInfo(
                keyTrains = listOf(
                    TrainOption("16595", "Panchaganga Overnight Express", "06:45 PM", "03:40 AM / 04:30 AM", "8h 45m", "SL: ₹265 | 3A: ₹710 | 2A: ₹1,010", false),
                    TrainOption("16515", "Yesvantpur-Karwar Express (Vistadome)", "07:00 AM", "04:45 PM", "9h 45m", "2S: ₹165 | EV (Vistadome Glass): ₹1,525", true),
                    TrainOption("16575", "Gomteshwara Day Express", "07:00 AM", "04:30 PM", "9h 30m", "2S: ₹165 | CC: ₹590", false)
                ),
                stationNames = "Yesvantpur (YPR) / KSR Bengaluru to Mangaluru Central (MAQ) / Udupi (UD)",
                liveTrackingUrl = "https://enquiry.indianrail.gov.in",
                irctcBookingUrl = "https://www.irctc.co.in",
                utsMobileAdvice = "Book Vistadome coach (EV) on Train #16515 / #16575 to see Sakleshpur waterfalls from 360° rotating glass seats.",
                travelerTip = "The Day Train through Western Ghats passes through 57 tunnels and over 100 viaducts—one of India's top 3 rail journeys."
            ),
            privateBus = PrivateBusCorridorInfo(
                majorOperators = listOf("VRL Travels", "Durgamba", "Sugama Tourist", "Sea Bird", "Anand Travels"),
                nonPeakFareRange = "₹750 – ₹1,050",
                surgeWeekendFareRange = "₹1,600 – ₹2,300 (Monsoon & Festival Weekends)",
                boardingPointsAdvisory = "Hundreds of boarding points across Bengaluru (Indiranagar, Silk Board, Jayanagar, Majestic, Yesvantpur).",
                bookingUrl = "https://www.redbus.in",
                travelerTip = "Coastal private fleets like Durgamba and Sugama are legendary for punctual morning arrivals in Udupi and Manipal."
            ),
            liveRouteAdvisory = "Shiradi Ghat road (NH 75) is fully concreted. Charmadi Ghat route is recommended for breathtaking daytime valley views."
        ),

        StatewideCorridorTransit(
            id = "corridor_blr_coorg",
            routeTitle = "Bengaluru ➔ Madikeri (Coorg)",
            originCity = "Bengaluru",
            destinationCity = "Madikeri",
            distanceKm = 260,
            approxTravelTime = "5h 30m",
            govtBus = GovtBusCorridorInfo(
                fleetOptions = listOf(
                    Triple("KSRTC Flybus", "Direct from Bengaluru Airport (KIAL)", "₹1,100"),
                    Triple("Airavat Club Class", "AC Multi-Axle", "₹620 – ₹700"),
                    Triple("Rajahamsa Executive", "Deluxe Air Suspension", "₹420 – ₹470"),
                    Triple("Karnataka Sarige", "Express (Shakti Scheme)", "₹310")
                ),
                departureHub = "Satellite Bus Stand (Mysore Road)",
                frequency = "Departs every 45 minutes",
                liveTrackingAvailable = true,
                portalUrl = "https://ksrtc.in",
                shaktiSchemeApplicable = true,
                travelerTip = "Buses stop at Kushalnagar gate (Tibetan Golden Temple Bylakuppe) before ascending the final 30 km ghat climb to Madikeri."
            ),
            railway = RailCorridorInfo(
                keyTrains = listOf(
                    TrainOption("12007", "Shatabdi Express (Up to Mysuru)", "11:00 AM", "01:00 PM", "2h 00m + 2h Cab", "CC: ₹495 + ₹2,000 Cab", true),
                    TrainOption("20607", "Vande Bharat (Up to Mysuru)", "10:30 AM", "12:15 PM", "1h 45m + 2h Cab", "CC: ₹550 + ₹2,000 Cab", true)
                ),
                stationNames = "No direct railway to Coorg. Take train to Mysuru (MYS) or Hassan (HAS), then connecting KSRTC bus or taxi (2 hrs).",
                liveTrackingUrl = "https://enquiry.indianrail.gov.in",
                irctcBookingUrl = "https://www.irctc.co.in",
                utsMobileAdvice = "Mysuru Railway Station has a direct KSRTC bus counter right outside the main porch connecting to Madikeri every 20 mins.",
                travelerTip = "Train to Mysuru + Highway KSRTC bus is faster and cheaper than an all-way private taxi."
            ),
            privateBus = PrivateBusCorridorInfo(
                majorOperators = listOf("Greenline Travels", "Ashwini Travels", "Poornima Tours"),
                nonPeakFareRange = "₹650 – ₹900",
                surgeWeekendFareRange = "₹1,300 – ₹1,800",
                boardingPointsAdvisory = "Departs usually between 10:30 PM and 11:45 PM from Anand Rao Circle and Majestic.",
                bookingUrl = "https://www.redbus.in",
                travelerTip = "Overnight sleepers arrive in Madikeri very early (4:45 AM). Make sure your homestay has 24-hr check-in pre-arranged."
            ),
            liveRouteAdvisory = "Kushalnagar-Madikeri ghat section is well-paved. Heavy monsoon rains (July–August) require cautious driving."
        ),

        StatewideCorridorTransit(
            id = "corridor_blr_gokarna",
            routeTitle = "Bengaluru ➔ Gokarna & Murudeshwar",
            originCity = "Bengaluru",
            destinationCity = "Gokarna",
            distanceKm = 490,
            approxTravelTime = "9h 30m (Overnight Bus / Rail)",
            govtBus = GovtBusCorridorInfo(
                fleetOptions = listOf(
                    Triple("Ambari Dream Class", "AC Multi-Axle Sleeper", "₹1,150 – ₹1,350"),
                    Triple("KSRTC Non-AC Sleeper", "Long Distance Berths", "₹720 – ₹820"),
                    Triple("Rajahamsa", "Semi-Sleeper", "₹580 – ₹650")
                ),
                departureHub = "Kempegowda Bus Station (Majestic)",
                frequency = "6 overnight departures nightly (8:00 PM – 10:30 PM)",
                liveTrackingAvailable = true,
                portalUrl = "https://ksrtc.in",
                shaktiSchemeApplicable = false,
                travelerTip = "KSRTC bus drops right at Gokarna Town Bus Stand (walking distance to Mahabaleshwar Temple and main beach)."
            ),
            railway = RailCorridorInfo(
                keyTrains = listOf(
                    TrainOption("16595", "Panchaganga Express", "06:45 PM", "06:10 AM", "11h 25m", "SL: ₹335 | 3A: ₹910 | 2A: ₹1,290", false),
                    TrainOption("16523", "Bengaluru-Karwar Express", "07:00 PM", "07:30 AM", "12h 30m", "SL: ₹335 | 3A: ₹910", false)
                ),
                stationNames = "KSR Bengaluru / Yesvantpur to Gokarna Road (GOK) / Kumta (KT)",
                liveTrackingUrl = "https://enquiry.indianrail.gov.in",
                irctcBookingUrl = "https://www.irctc.co.in",
                utsMobileAdvice = "Gokarna Road railway station is 9 km from town. Fixed-rate share autos charge ₹50/head, private auto charges ₹200.",
                travelerTip = "Panchaganga Express is the best train to reach Gokarna just in time for early morning beach breakfast and temple darshan."
            ),
            privateBus = PrivateBusCorridorInfo(
                majorOperators = listOf("VRL Travels", "Seabird", "Sugama Tourist", "SRS Travels"),
                nonPeakFareRange = "₹900 – ₹1,200",
                surgeWeekendFareRange = "₹1,700 – ₹2,500 (Peak Beach Season Nov–Jan)",
                boardingPointsAdvisory = "VRL has comfortable VIP waiting lounges at Anand Rao Circle and Navrang Circle.",
                bookingUrl = "https://www.redbus.in",
                travelerTip = "Check whether drop-off is at Gokarna Cross (NH 66, 10 km outside) or Gokarna Checkpost/Town before booking!"
            ),
            liveRouteAdvisory = "Scenic coastal highway NH 66 has smooth dual-carriageway sections with sea views past Honnavar."
        ),

        StatewideCorridorTransit(
            id = "corridor_blr_hub_blg",
            routeTitle = "Bengaluru ➔ Hubballi & Belagavi",
            originCity = "Bengaluru",
            destinationCity = "Hubballi / Belagavi",
            distanceKm = 410,
            approxTravelTime = "5h 00m (Vande Bharat) / 6h 30m (Express Bus)",
            govtBus = GovtBusCorridorInfo(
                fleetOptions = listOf(
                    Triple("Ambari Utsav", "Volvo 9600 Sleeper", "₹1,350"),
                    Triple("Airavat Club Class", "Multi-Axle AC", "₹750 – ₹890"),
                    Triple("NWKRTC Sarige", "Express via NH 48", "₹480")
                ),
                departureHub = "Kempegowda Bus Station (Majestic) Platform 1A",
                frequency = "Departs every 20 minutes day & night",
                liveTrackingAvailable = true,
                portalUrl = "https://ksrtc.in",
                shaktiSchemeApplicable = true,
                travelerTip = "NWKRTC and KSRTC maintain clean rest-stop highway motels at Chitradurga and Davanagere (famous Benne Dosa spot!)."
            ),
            railway = RailCorridorInfo(
                keyTrains = listOf(
                    TrainOption("20661", "Vande Bharat Express", "05:45 AM", "10:45 AM", "5h 00m", "CC: ₹1,205 | EC: ₹2,395", true),
                    TrainOption("12725", "Siddhaganga Intercity Express", "01:00 PM", "08:15 PM", "7h 15m", "2S: ₹175 | CC: ₹615", false),
                    TrainOption("16589", "Rani Chennamma Express", "11:00 PM", "06:15 AM", "7h 15m", "SL: ₹295 | 3A: ₹790 | 2A: ₹1,120", false)
                ),
                stationNames = "KSR Bengaluru (SBC) / Yesvantpur to Hubballi Junction (UBL) / Belagavi (BGM)",
                liveTrackingUrl = "https://enquiry.indianrail.gov.in",
                irctcBookingUrl = "https://www.irctc.co.in",
                utsMobileAdvice = "Vande Bharat runs at 130 km/h on the newly electrified double line, cutting travel time by over 2.5 hours.",
                travelerTip = "Hubballi station hosts the World's Longest Railway Platform (Platform 8: 1,507 meters long) with clean battery cart transfers."
            ),
            privateBus = PrivateBusCorridorInfo(
                majorOperators = listOf("VRL Logistics (HQ Hubli)", "SRS Travels", "Barde Roadlines", "Reshma Travels"),
                nonPeakFareRange = "₹650 – ₹950",
                surgeWeekendFareRange = "₹1,200 – ₹1,800",
                boardingPointsAdvisory = "VRL operates luxury mega-terminals with boarding gates at Anand Rao Circle.",
                bookingUrl = "https://www.redbus.in",
                travelerTip = "VRL is Hubballi's home carrier and runs the largest fleet of multi-axle Volvos on this highway."
            ),
            liveRouteAdvisory = "National Highway 48 (Pune-Bengaluru Asian Highway 47) is fully 6-lane access-controlled with continuous fuel and EV chargers."
        )
    )
}

data class TrainOption(
    val trainNumber: String,
    val trainName: String,
    val departureTime: String,
    val arrivalTime: String,
    val travelDuration: String,
    val classFares: String,
    val isVandeBharatOrVistadome: Boolean = false
)

data class GovtBusCorridorInfo(
    val fleetOptions: List<Triple<String, String, String>>, // Fleet, Class, Fare
    val departureHub: String,
    val frequency: String,
    val liveTrackingAvailable: Boolean,
    val portalUrl: String,
    val shaktiSchemeApplicable: Boolean,
    val travelerTip: String
)

data class RailCorridorInfo(
    val keyTrains: List<TrainOption>,
    val stationNames: String,
    val liveTrackingUrl: String,
    val irctcBookingUrl: String,
    val utsMobileAdvice: String,
    val travelerTip: String
)

data class PrivateBusCorridorInfo(
    val majorOperators: List<String>,
    val nonPeakFareRange: String,
    val surgeWeekendFareRange: String,
    val boardingPointsAdvisory: String,
    val bookingUrl: String,
    val travelerTip: String
)

data class StatewideCorridorTransit(
    val id: String,
    val routeTitle: String,
    val originCity: String,
    val destinationCity: String,
    val distanceKm: Int,
    val approxTravelTime: String,
    val govtBus: GovtBusCorridorInfo,
    val railway: RailCorridorInfo,
    val privateBus: PrivateBusCorridorInfo,
    val liveRouteAdvisory: String
)

