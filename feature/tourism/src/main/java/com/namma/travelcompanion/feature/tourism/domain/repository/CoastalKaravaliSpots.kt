package com.namma.travelcompanion.feature.tourism.domain.repository

import com.namma.travelcompanion.feature.tourism.domain.model.ForeignerPricingGuidance
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.model.OfficialBookingInfo
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory

val coastalKaravaliSpots = listOf(
    KarnatakaSpot(
        id = "gokarna_om_beach",
        name = "Gokarna (Om Beach & Kudle Beach)",
        kannadaName = "ಗೋಕರ್ಣ (ಓಂ ಬೀಚ್ ಮತ್ತು ಕುಡ್ಲೆ ಬೀಚ್)",
        tagline = "Sacred Mahabaleshwara Atmalinga & Coastal Cliff Trekking",
        category = TourismCategory.BEACHES,
        region = KarnatakaRegion.KARAVALI,
        district = "Uttara Kannada District",
        nearestTransitHub = "Gokarna Road Railway Station (9 km) / Ankola (25 km) | Goa Dabolim GOI (140 km)",
        distanceFromBangaloreKm = 485,
        bestSeason = "October to March (Calm Arabian sea waves)",
        timings = "Mahabaleshwara Temple: 6:00 AM – 12:30 PM & 5:00 PM – 8:00 PM | Beaches: Open 24/7",
        overview = "Where the Western Ghats tumble directly into the Arabian Sea. Gokarna is celebrated for two contrasting souls: the sacred 4th-century Mahabaleshwara Temple housing the Pranalinga of Lord Shiva, and pristine crescent beaches (Om Beach, Kudle Beach, Half Moon Beach, and Paradise Beach) connected by rugged coastal cliff trekking trails.",
        keyHighlights = listOf(
            "Natural Om-shaped coastline (Om Beach) with rocky outcrops and tidal pools",
            "5-Beach Cliff Trek from Kudle to Paradise Beach via seaside headlands",
            "Sacred Mahabaleshwara Temple Atmalinga sparsha darshan",
            "Jet-skiing, banana rides and sea kayaking at Om Beach"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Karnataka Tourism & Temple Trust",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Temple darshan is free. Special Pooja passes at temple office. Beach water sports booked directly at Om Beach counter.",
            badgeTag = "COASTAL HERITAGE & BEACH"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 0,
            foreignerFeeInr = 0,
            approxUsdEquivalent = "Free Beach Access",
            feeIncludes = "Free access to all beaches and public cliff trails.",
            dressCodeRules = "Temple dress code: Men must wear traditional dhotis without shirts; women in sarees/churidars. Beachwear strictly prohibited in temple town streets.",
            photographyPolicy = "Freely permitted on beaches; photography strictly banned inside the inner sanctum of Mahabaleshwara Temple.",
            scamAlertNotes = "Avoid unauthorized touts posing as temple priests at Kotiteertha holy tank offering 'expedited pooja'.",
            cashVsDigitalNotes = "UPI accepted at beach cafes; carry ₹1,000 cash for cliff auto rides and boat transfers."
        ),
        howToReachTransit = "Panchaganga Express train from Bengaluru KSR direct to Gokarna Road Station (overnight), then ₹150 auto to beach.",
        survivalKannadaPhrase = "Om beach ninda Paradise beach ge boat charge eshtu?",
        survivalKannadaLipi = "ಓಂ ಬೀಚ್‌ನಿಂದ ಪ್ಯಾರಡೈಸ್ ಬೀಚ್‌ಗೆ ಬೋಟ್ ಚಾರ್ಜ್ ಎಷ್ಟು?",
        survivalKannadaMeaning = "How much is the boat charge from Om Beach to Paradise Beach?",
        imageUrl = "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=gokarna+om+beach+cliff+trek+karnataka+tourism",
        guideInfo = "Certified local surf instructors and sea-kayak guides at Om Beach water-sports counter"
    ),

    KarnatakaSpot(
        id = "st_marys_island",
        name = "St. Mary's Island (Columnar Basalt)",
        kannadaName = "ಸೇಂಟ್ ಮೇರೀಸ್ ದ್ವೀಪ (ಮಾಲ್ಪೆ)",
        tagline = "Geological Monument of Hexagonal Volcanic Basalt Columns",
        category = TourismCategory.ISLANDS,
        region = KarnatakaRegion.KARAVALI,
        district = "Udupi District",
        nearestTransitHub = "Malpe Tourist Jetty (4 km from Udupi) | Train: Udupi Station (10 km) | Airport: Mangaluru IXE (65 km)",
        distanceFromBangaloreKm = 410,
        bestSeason = "October to May (Closed during June–September monsoons)",
        timings = "9:00 AM – 5:00 PM (Ferries operate continuously as they fill)",
        overview = "A national geological monument designated by the Geological Survey of India. The island is formed of distinct hexagonal columnar basaltic lava rocks created 88 million years ago when Madagascar split away from the Indian subcontinent. Surrounded by turquoise waters and shell-strewn beaches.",
        keyHighlights = listOf(
            "Unique hexagonal columnar jointed basalt rock pillars formed 88 million years ago",
            "Scenic 30-minute passenger ferry boat cruise from Malpe Beach Tourist Jetty",
            "Crystal-clear shallow turquoise waters and shell beach walking path",
            "Zero single-use plastic eco-island zone with shade gazebos"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Malpe Beach Development Committee (Govt of Karnataka)",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Ferry boat tickets purchased at Malpe Sea Walk Tourist Jetty counter or online.",
            badgeTag = "NATIONAL GEOLOGICAL MONUMENT"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 350,
            foreignerFeeInr = 400,
            approxUsdEquivalent = "~$4.80 USD (Round-trip Ferry)",
            feeIncludes = "Round-trip passenger ferry boat cruise and island eco-maintenance fee.",
            dressCodeRules = "Beach casual, comfortable footwear for climbing volcanic rocks. No single-use plastic bottles allowed.",
            photographyPolicy = "Freely allowed everywhere. Hexagonal basalt rocks offer world-class backdrop for landscape photos.",
            scamAlertNotes = "Only purchase tickets from the official Malpe Beach Development Committee counter at the jetty.",
            cashVsDigitalNotes = "UPI QR code accepted at Malpe jetty ticket counter."
        ),
        howToReachTransit = "Auto from Udupi Railway Station to Malpe Tourist Jetty (₹150, 15 mins), then 30-minute ferry boat to island.",
        survivalKannadaPhrase = "St. Mary's dweepakke ferry boat return ticket charge eshtu?",
        survivalKannadaLipi = "ಸೇಂಟ್ ಮೇರೀಸ್ ದ್ವೀಪಕ್ಕೆ ಫೆರ್ರಿ ಬೋಟ್ ರಿಟರ್ನ್ ಟಿಕೆಟ್ ಚಾರ್ಜ್ ಎಷ್ಟು?",
        survivalKannadaMeaning = "What is the return ticket charge for the ferry boat to St. Mary's Island?",
        imageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=st+marys+island+malpe+columnar+basalt+karnataka",
        guideInfo = "Geological information boards maintained across the island by Geological Survey of India"
    ),

    KarnatakaSpot(
        id = "udupi_krishna_matha",
        name = "Udupi Sri Krishna Matha & Ashta Mathas",
        kannadaName = "ಉಡುಪಿ ಶ್ರೀ ಕೃಷ್ಣ ಮಠ",
        tagline = "13th-Century Dvaita Seat, Kanakana Kindi & Sacred Annadana",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.KARAVALI,
        district = "Udupi District",
        nearestTransitHub = "Train: Udupi Railway Station (4 km) | Airport: Mangaluru IXE (58 km)",
        distanceFromBangaloreKm = 400,
        bestSeason = "Year-round (Best: October to March)",
        timings = "5:30 AM – 9:00 PM (Daily)",
        overview = "Founded in the 13th century by the great Vaishnava saint Sri Madhvacharya. The deity of Balakrishna is uniquely worshipped not through an open doorway, but through the sacred nine-holed silver-plated window called 'Kanakana Kindi' where Lord Krishna miraculously turned around to give darshan to his ardent devotee Kanakadasa.",
        keyHighlights = listOf(
            "Darshan of Balakrishna through the historic 9-holed Kanakana Kindi silver window",
            "World-famous Bhojana Shala serving free divine satvik Annadana meals daily to thousands",
            "Eight historic Ashta Mathas surrounding the sacred Madhwa Sarovara holy tank",
            "Swarna Ratha (Golden Chariot) procession during evening poojas"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Sri Krishna Matha Paryaya Office",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Entry is 100% free. Special Sevas and Prasadam tokens available at Matha administrative counter.",
            badgeTag = "VAISHNAVA PARYAYA SHRINE"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 0,
            foreignerFeeInr = 0,
            approxUsdEquivalent = "Free Entry & Free Satvik Meals",
            feeIncludes = "Free general darshan and multi-course Udupi satvik banana-leaf meal.",
            dressCodeRules = "Men must remove upper clothing (shirts/vests) before entering sanctum inner hall. Traditional attire required.",
            photographyPolicy = "Strictly prohibited inside the sanctum inner hall; allowed in outer Car Street.",
            scamAlertNotes = "No entry fee exists. Never pay touts outside for 'speedy darshan'.",
            cashVsDigitalNotes = "UPI accepted at sevas counter and matha sweet stalls."
        ),
        howToReachTransit = "Direct overnight train (Panchaganga Express) or KSRTC Airavat AC bus from Bengaluru to Udupi (8 hours).",
        survivalKannadaPhrase = "Kanakana Kindi darshanakke line elli shuru aagutthe?",
        survivalKannadaLipi = "ಕನಕನ ಕಿಂಡಿ ದರ್ಶನಕ್ಕೆ ಲೈನ್ ಎಲ್ಲಿ ಶುರು ಆಗುತ್ತೆ?",
        survivalKannadaMeaning = "Where does the queue start for the Kanakana Kindi darshan?",
        imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=udupi+krishna+matha+kanakana+kindi+documentary",
        guideInfo = "Matha volunteers in traditional attire guide pilgrims through the darshan lines free of charge"
    ),

    KarnatakaSpot(
        id = "murudeshwar_temple",
        name = "Murudeshwar Shiva Statue & Rajagopura",
        kannadaName = "ಮುರುಡೇಶ್ವರ ಶಿವನ ಪ್ರತಿಮೆ ಮತ್ತು ರಾಜಗೋಪುರ",
        tagline = "123-Foot Colossal Shiva Statue Overlooking the Arabian Sea",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.KARAVALI,
        district = "Uttara Kannada District",
        nearestTransitHub = "Train: Murudeshwar Railway Station (2 km) | Airport: Mangaluru IXE (155 km) / Goa GOI (175 km)",
        distanceFromBangaloreKm = 485,
        bestSeason = "October to March",
        timings = "6:00 AM – 1:00 PM & 3:00 PM – 8:30 PM | Rajagopura Lift: 8:00 AM – 7:00 PM",
        overview = "Perched on Kanduka Giri peninsula surrounded on three sides by the Arabian Sea. Murudeshwar features the world's second-tallest statue of Lord Shiva towering at 123 feet, alongside the monumental 20-storey (249-foot) Rajagopura equipped with a high-speed glass elevator that takes visitors to the 18th floor for breathtaking 360-degree ocean views.",
        keyHighlights = listOf(
            "123-foot colossal Shiva statue radiating over the sapphire Arabian Sea",
            "High-speed elevator ride to the 18th floor of the 249-foot Rajagopura for panoramic views",
            "Atmalinga sanctum darshan at the ancient coastal rock temple",
            "Underground cave museum depicting the legend of Ravana and the Atmalinga"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Murudeshwar Temple Trust",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Temple entry is free. Rajagopura elevator ticket (₹20) and Cave Museum ticket (₹10) bought at counter.",
            badgeTag = "COASTAL PENINSULA SHRINE"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 20,
            foreignerFeeInr = 20,
            approxUsdEquivalent = "~$0.25 USD (Rajagopura Elevator)",
            feeIncludes = "High-speed lift ride to the top 18th floor viewing gallery.",
            dressCodeRules = "Shoulders and knees must be covered inside the inner sanctum. Footwear left at deposit counters outside.",
            photographyPolicy = "Freely permitted everywhere in the temple grounds and from the top of the Rajagopura.",
            scamAlertNotes = "Only purchase elevator tokens at the computerized counter inside the Rajagopura arch.",
            cashVsDigitalNotes = "UPI QR code accepted at ticket counters and beach cafes."
        ),
        howToReachTransit = "Panchaganga Express train from Bengaluru stops directly at Murudeshwar Station, then ₹50 auto to temple.",
        survivalKannadaPhrase = "Rajagopura meledakke lift ticket elli kodthare?",
        survivalKannadaLipi = "ರಾಜಗೋಪುರ ಮೇಲಕ್ಕೆ ಲಿಫ್ಟ್ ಟಿಕೆಟ್ ಎಲ್ಲಿ ಕೊಡ್ತಾರೆ?",
        survivalKannadaMeaning = "Where do they issue tickets for the lift to the top of the Rajagopura?",
        imageUrl = "https://images.unsplash.com/photo-1582510003544-4d00b7f74220?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=murudeshwar+temple+shiva+statue+rajagopura+karnataka",
        guideInfo = "Temple audio-visual museum at the base of the statue narrates the Atmalinga legend in 4 languages"
    ),

    KarnatakaSpot(
        id = "netrani_scuba",
        name = "Netrani Island Coral Reef Scuba Diving",
        kannadaName = "ನೇತ್ರಾಣಿ ದ್ವೀಪ (ಸ್ಕೂಬಾ ಡೈವಿಂಗ್)",
        tagline = "Heart-Shaped Pigeon Island & India's Premier Scuba Diving Hotspot",
        category = TourismCategory.ADVENTURE_SPORTS,
        region = KarnatakaRegion.KARAVALI,
        district = "Uttara Kannada (Off Murudeshwar Coast)",
        nearestTransitHub = "Murudeshwar Jetty (18 km boat ride) | Train: Murudeshwar Station (3 km)",
        distanceFromBangaloreKm = 490,
        bestSeason = "October to May (Optimal underwater visibility 15–25 meters)",
        timings = "7:30 AM – 3:30 PM (Boats depart early morning)",
        overview = "Known as 'Pigeon Island', this heart-shaped volcanic coral island situated 19 km off Murudeshwar is the premier scuba diving destination in mainland India. The clear waters and thriving coral gardens are home to butterflyfish, parrotfish, stingrays, sea turtles, and harmless whale sharks.",
        keyHighlights = listOf(
            "PADI / SSI certified scuba diving for beginners (Discovery Scuba Dive)",
            "Thriving coral gardens with visibility up to 25 meters",
            "Spotting stingrays, moray eels, barracudas, and sea turtles",
            "Scenic 90-minute speedboat or catamaran cruise across the Arabian Sea"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "PADI / SSI Dive Centers Murudeshwar",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Must be booked 1–3 days in advance with authorized PADI registered dive centers in Murudeshwar.",
            badgeTag = "PADI SCUBA REEF"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 3500,
            foreignerFeeInr = 3500,
            approxUsdEquivalent = "~$42.00 USD (All-Inclusive)",
            feeIncludes = "Boat transfer, PADI instructor 1-on-1 dive, full gear (wetsuit, fins, regulator, tank), and underwater GoPro photos/videos.",
            dressCodeRules = "Swimwear or rashguard. Instructors provide sanitized neoprene wetsuits.",
            photographyPolicy = "Underwater GoPro footage and 4K video clips are included free in the diving package.",
            scamAlertNotes = "Ensure the operator has certified PADI/SSI instructors and valid emergency oxygen cylinders on board.",
            cashVsDigitalNotes = "UPI and credit cards accepted at dive center offices in Murudeshwar."
        ),
        howToReachTransit = "Report to dive center at Murudeshwar Beach at 7:30 AM for equipment fitting and boat departure.",
        survivalKannadaPhrase = "Netrani scuba diving ge underwater video free kodthira?",
        survivalKannadaLipi = "ನೇತ್ರಾಣಿ ಸ್ಕೂಬಾ ಡೈವಿಂಗ್‌ಗೆ ಅಂಡರ್‌ವಾಟರ್ ವಿಡಿಯೋ ಫ್ರೀ ಕೊಡ್ತೀರಾ?",
        survivalKannadaMeaning = "Do you provide underwater video for free with the Netrani scuba diving?",
        imageUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=netrani+island+scuba+diving+murudeshwar+karnataka",
        guideInfo = "Certified PADI and SSI Dive Masters accompany every diver on a 1-on-1 ratio with emergency safety gear"
    ),

    KarnatakaSpot(
        id = "honnavar_backwaters",
        name = "Honnavar Sharavathi River Backwaters & Mangroves",
        kannadaName = "ಹೊನ್ನಾವರ ಶರಾವತಿ ಹಿನ್ನೀರು ಮತ್ತು ಮ್ಯಾಂಗ್ರೋವ್",
        tagline = "Untouched Mangrove Forests & Boardwalk Eco-Tourism",
        category = TourismCategory.WATERFALLS_NATURE,
        region = KarnatakaRegion.KARAVALI,
        district = "Uttara Kannada District",
        nearestTransitHub = "Honnavar Railway Station (3 km) | Goa Dabolim GOI (170 km)",
        distanceFromBangaloreKm = 460,
        bestSeason = "October to May",
        timings = "8:00 AM – 6:00 PM (Sunset boat cruise 5:00 PM)",
        overview = "Where the mighty Sharavathi River meets the Arabian Sea in a maze of tranquil mangrove backwater islands. Honnavar features a wooden mangrove boardwalk path at Kandalavann and country boat cruises weaving through silent brackish waterways filled with kingfishers and mudskippers.",
        keyHighlights = listOf(
            "Kandalavann (Mangrove) wooden elevated boardwalk canopy trail",
            "Sharavathi river backwater boating to Mavinkurve island",
            "Eco Beach Kasarkod (Blue Flag certified pristine sustainable beach)",
            "Authentic coastal Karavali cuisine and fresh coconut water on riverbanks"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Honnavar Forest & Tourism Department",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Boardwalk entry (₹20) and river boating tickets available at Sharavathi tourist boat jetty.",
            badgeTag = "MANGROVE ECO-TOURISM"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 20,
            foreignerFeeInr = 50,
            approxUsdEquivalent = "~$0.60 USD (Boardwalk)",
            feeIncludes = "Entry to mangrove wooden boardwalk and conservation interpretation boards.",
            dressCodeRules = "Comfortable walking shoes; bug spray recommended for mangrove nature trails.",
            photographyPolicy = "Freely permitted. Wooden boardwalk through dense mangrove canopy is a photographer's dream.",
            scamAlertNotes = "Boat rides have fixed rates set by the Port Department (₹150–₹250 per seat).",
            cashVsDigitalNotes = "UPI QR code accepted at boat jetty counter."
        ),
        howToReachTransit = "All Konkan railway express trains (Matsyagandha / Panchaganga) stop at Honnavar Station, then ₹60 auto to jetty.",
        survivalKannadaPhrase = "Sharavathi backwaters boating charge eshtu aagutthe?",
        survivalKannadaLipi = "ಶರಾವತಿ ಹಿನ್ನೀರು ಬೋಟಿಂಗ್ ಚಾರ್ಜ್ ಎಷ್ಟು ಆಗುತ್ತೆ?",
        survivalKannadaMeaning = "How much is the charge for Sharavathi backwaters boating?",
        imageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=honnavar+mangrove+boardwalk+sharavathi+backwaters+karnataka",
        guideInfo = "Forest Department eco-guides explain mangrove bio-filtering and estuary ecosystems at the boardwalk center"
    )
)
