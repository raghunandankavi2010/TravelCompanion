package com.namma.travelcompanion.feature.tourism.domain.repository

import com.namma.travelcompanion.feature.tourism.domain.model.ForeignerPricingGuidance
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.model.OfficialBookingInfo
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory

val malnadSpots = listOf(
    KarnatakaSpot(
        id = "jog_falls",
        name = "Jog Falls (Gersoppa Falls)",
        kannadaName = "ಜೋಗ ಜಲಪಾತ",
        tagline = "India's 2nd-Highest Plunge Waterfall (Raja, Roarer, Rocket & Rani)",
        category = TourismCategory.WATERFALLS_NATURE,
        region = KarnatakaRegion.MALNAD,
        district = "Shivamogga District",
        nearestTransitHub = "Talguppa Railway Station (14 km) / Sagara (30 km) | Airport: Shivamogga RQY (100 km) / Hubballi HBX (160 km)",
        distanceFromBangaloreKm = 380,
        bestSeason = "July to December (Peak monsoon roar: August to October)",
        timings = "7:30 AM – 6:30 PM (Laser show: 7:00 PM)",
        overview = "The segmented plunge waterfall formed by the Sharavathi River dropping 253 meters (830 feet) in a single spectacular leap down a chasm. The cascade is uniquely divided into four named streams: Raja (grandest unbroken plunge), Roarer (furious rushing jet), Rocket (bursting into high-speed jets), and Rani (delicate graceful white mist).",
        keyHighlights = listOf(
            "Thunderous 253-meter (830-foot) four-stream cascade: Raja, Roarer, Rocket, and Rani",
            "Newly upgraded viewing gallery and cliff-edge cantilever glass bridge platform",
            "1,400 stone steps leading down into the misty spray at the bottom of the gorge",
            "Evening musical laser and 3D mapping fountain show at the amphitheater"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Jog Management Authority (JMA)",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Entry ticket purchased at JMA computerized gate counters or online via Karnataka Tourism.",
            badgeTag = "STATE NATURAL MONUMENT"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 50,
            foreignerFeeInr = 100,
            approxUsdEquivalent = "~$1.20 USD",
            feeIncludes = "Entry to all three viewing decks and amphitheater grounds.",
            dressCodeRules = "Raincoat or waterproof jacket essential during monsoon months. Slip-resistant trekking shoes for stairs.",
            photographyPolicy = "Freely permitted. Morning fog clears between 9:00 AM and 11:30 AM for ideal photography light.",
            scamAlertNotes = "Hire only licensed porters or guides wearing JMA photo identity badges.",
            cashVsDigitalNotes = "UPI QR accepted at JMA ticket counter and KSTDC Hotel Mayura restaurant."
        ),
        howToReachTransit = "Direct train (Talguppa Express) from Bengaluru KSR to Talguppa Station (terminal), then local KSRTC bus (15 mins to Falls gate).",
        survivalKannadaPhrase = "Jog falls bottom ge iliyalu 1400 steps open idiya?",
        survivalKannadaLipi = "ಜೋಗ್ ಫಾಲ್ಸ್ ಕೆಳಗೆ ಇಳಿಯಲು ೧೪೦೦ ಮೆಟ್ಟಿಲು ಓಪನ್ ಇದೆಯಾ?",
        survivalKannadaMeaning = "Are the 1,400 steps open to descend to the bottom of Jog Falls?",
        imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=jog+falls+gersoppa+karnataka+tourism+official",
        guideInfo = "JMA nature guides available at the tourist information center near the main gate"
    ),

    KarnatakaSpot(
        id = "kudremukh_trek",
        name = "Kudremukh Peak & Western Ghats Trek",
        kannadaName = "ಕುದುರೆಮುಖ ಶಿಖರ ಚಾರಣ",
        tagline = "Rolling Shola Grasslands, 1,894m Horse-Face Peak & Rainforests",
        category = TourismCategory.TREKKING_TRAILS,
        region = KarnatakaRegion.MALNAD,
        district = "Chikkamagaluru District",
        nearestTransitHub = "Airport: Mangaluru IXE (100 km) | Train: Mangaluru Junction (105 km)",
        distanceFromBangaloreKm = 330,
        bestSeason = "October to February (Lush green post-monsoon carpets)",
        timings = "Checkpost Entry strictly 6:00 AM – 9:00 AM (Max 50 trekkers per day limit)",
        overview = "The second highest peak in Karnataka at 1,894 meters (6,214 feet). Named Kudremukh ('Horse-Face' in Kannada) for its distinctive equine profile facing the Arabian Sea. The 22 km roundtrip trek leads through emerald Shola forest-grassland complexes, crystal mountain streams, and rolling green hills protected under the Kudremukh National Park.",
        keyHighlights = listOf(
            "Iconic horse-face summit ridge offering 360-degree panoramic views of Western Ghats",
            "Traversing pristine Shola grasslands, bamboo thickets and misty rainforest streams",
            "Encountering Malabar giant squirrels, barking deer, and endemic orchid blooms",
            "Strictly regulated eco-trekking with mandatory local forest guides and zero-plastic policy"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Aranya Vihaara (Karnataka Forest Department)",
            portalUrl = "https://aranyavihaara.karnataka.gov.in",
            bookingRule = "MANDATORY online pre-booking via Aranya Vihaara portal. Strictly capped at 50 trekkers/day. No spot booking permitted.",
            badgeTag = "OFFICIAL ARANYA VIHAARA TREK"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 575,
            foreignerFeeInr = 575,
            approxUsdEquivalent = "~$6.90 USD",
            feeIncludes = "Forest entry permit, eco-conservation fee, and allocated local guide pool share.",
            dressCodeRules = "High-ankle waterproof trekking boots. Mandatory leech socks (available at base homestays for ₹150). No single-use plastic bottles.",
            photographyPolicy = "Personal cameras allowed. Drones strictly illegal under Indian Wildlife Protection Act (violators face confiscation and fines).",
            scamAlertNotes = "Never pay unauthorized agents claiming to arrange 'spot permits'. Every trekker's name must be registered on the official govt portal.",
            cashVsDigitalNotes = "Zero cellular network at Balagal/Mullodi base checkpost. Carry ₹1,000 cash for 4x4 Jeep transfer (₹500/head roundtrip) and food."
        ),
        howToReachTransit = "KSRTC bus from Bengaluru to Kalasa (7 hours), then local 4x4 Jeep transfer from Balagal to Mullodi base camp.",
        survivalKannadaPhrase = "Mullodi homestay inda trek shuru aaguvudu eshtu gantege?",
        survivalKannadaLipi = "ಮುಳ್ಳೋಡಿ ಹೋಂಸ್ಟೇ ಇಂದ ಟ್ರೆಕ್ ಶುರು ಆಗುವುದು ಎಷ್ಟು ಗಂಟೆಗೆ?",
        survivalKannadaMeaning = "What time does the trek start from Mullodi homestay?",
        imageUrl = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=kudremukh+peak+trek+aranya+vihaara+karnataka",
        guideInfo = "Certified Forest Department eco-guides accompany every batch of 10 trekkers from Mullodi checkpost"
    ),

    KarnatakaSpot(
        id = "mullayanagiri_chik",
        name = "Mullayanagiri & Baba Budangiri",
        kannadaName = "ಮುಳ್ಳಯ್ಯನಗಿರಿ ಮತ್ತು ಬಾಬಾ ಬುಡನ್‌ಗಿರಿ",
        tagline = "Karnataka's Highest Peak (1,930m) & Cradle of Indian Coffee",
        category = TourismCategory.TREKKING_TRAILS,
        region = KarnatakaRegion.MALNAD,
        district = "Chikkamagaluru District",
        nearestTransitHub = "Airport: Mangaluru IXE (160 km) / Bengaluru BLR (260 km) | Train: Kadur / Birur Junction (45 km)",
        distanceFromBangaloreKm = 265,
        bestSeason = "September to March (Crisp mountain air & sunrise sea of clouds)",
        timings = "6:00 AM – 6:00 PM (Vehicles restricted after 5:00 PM for safety)",
        overview = "The highest summit in Karnataka standing at 1,930 meters (6,330 feet). Named after sage Mullappa Swamy whose shrine sits atop the summit. Nearby Baba Budangiri is celebrated as the historic birthplace of Indian coffee, where Sufi saint Baba Budan smuggled seven raw coffee beans from Mocha, Yemen in 1670 and planted them in the Chandradrona hill slopes.",
        keyHighlights = listOf(
            "Standing at 1,930m on Karnataka's highest peak touching the morning mist clouds",
            "Climb 500 stone stairs from the ridge parking to the sage Mullappa Swamy summit temple",
            "Historic Baba Budangiri shrine (Sri Guru Dattatreya Bababudan Swamy Dargah)",
            "Lush coffee plantation walks tasting fresh estate-roasted Arabica brew"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Chikkamagaluru District Administration",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Entry is free. Narrow uphill mountain ghat road regulated by traffic police during peak weekends.",
            badgeTag = "HIGHEST SUMMIT IN KARNATAKA"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 0,
            foreignerFeeInr = 0,
            approxUsdEquivalent = "Free Entry",
            feeIncludes = "Free access to the summit viewpoint and temple shrine.",
            dressCodeRules = "Warm windproof jacket or fleece essential; summit temperatures can drop to 12°C with 60 km/h wind gusts.",
            photographyPolicy = "Freely permitted. Morning sunrise offers legendary 'sea of clouds' valley inversion views.",
            scamAlertNotes = "Avoid inexperienced local drivers on the single-lane ghat switchbacks; hire experienced 4x4 mountain drivers.",
            cashVsDigitalNotes = "UPI works at base cafes; keep ₹500 cash for private estate parking."
        ),
        howToReachTransit = "KSRTC bus from Bengaluru to Chikkamagaluru town (4.5 hours), then local cab or 4x4 Jeep up the ghat road (22 km).",
        survivalKannadaPhrase = "Mullayanagiri bettadallii sunrise nodoke best time yavadu?",
        survivalKannadaLipi = "ಮುಳ್ಳಯ್ಯನಗಿರಿ ಬೆಟ್ಟದಲ್ಲಿ ಸನ್‌ರೈಸ್ ನೋಡೋಕೆ ಬೆಸ್ಟ್ ಟೈಮ್ ಯಾವುದು?",
        survivalKannadaMeaning = "What is the best time to view sunrise at Mullayanagiri hill?",
        imageUrl = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=mullayanagiri+highest+peak+chikkamagaluru+karnataka",
        guideInfo = "Local coffee estate naturalists organize guided plantation walks and cupping sessions in nearby estates"
    ),

    KarnatakaSpot(
        id = "belur_halebeedu",
        name = "Belur & Halebeedu Hoysala Temples (UNESCO)",
        kannadaName = "ಬೇಲೂರು ಮತ್ತು ಹಳೇಬೀಡು (ಹೊಯ್ಸಳ ದೇವಾಲಯಗಳು)",
        tagline = "UNESCO World Heritage Masterpieces of Chloritic Soapstone Carvings",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.MALNAD,
        district = "Hassan District",
        nearestTransitHub = "Airport: Bengaluru BLR (220 km) / Mangaluru IXE (140 km) | Train: Hassan Junction (38 km)",
        distanceFromBangaloreKm = 215,
        bestSeason = "October to March",
        timings = "7:30 AM – 6:30 PM (Daily)",
        overview = "Inscribed as UNESCO World Heritage Sites in 2023. The 12th-century Chennakeshava Temple at Belur and Hoysaleshwara Temple at Halebeedu represent the zenith of medieval Indian temple architecture. Built from soft chloritic schist (green soapstone), every square inch is carved with microscopic lace-like delicacy by master sculptors like Jakanachari and Ruvari Mallitamma.",
        keyHighlights = listOf(
            "Star-shaped (Stellate) raised plinths elevating temples like sculptural jewels",
            "Madanikas (Bracket figures depicting celestial dancers in 38 distinct expressive postures)",
            "Friezes of 1,248 battle elephants at the temple base, no two elephants carved identical",
            "Monolithic Nandi bulls carved with polished mirrored obsidian soapstone finish"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Archaeological Survey of India (ASI) & Muzrai",
            portalUrl = "https://asi.nic.in",
            bookingRule = "Belur Chennakeshava is an active temple (free entry). Halebeedu Hoysaleshwara is an ASI monument (₹25 domestic / ₹300 foreigner).",
            badgeTag = "UNESCO WORLD HERITAGE 2023"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 25,
            foreignerFeeInr = 300,
            approxUsdEquivalent = "~$3.60 USD (Halebeedu ASI entry; Belur is free)",
            feeIncludes = "Access to Hoysaleshwara temple complex, archaeological sculpture gallery and gardens.",
            dressCodeRules = "Footwear strictly removed at both temples (token stands at entrance). Cover shoulders and knees inside Belur sanctum.",
            photographyPolicy = "Exterior photography permitted freely. Flash photography prohibited inside darkened sanctums to preserve stone art.",
            scamAlertNotes = "Only engage guides possessing the official Ministry of Tourism or Karnataka Tourism green lanyard photo ID card (₹400–₹600 fixed).",
            cashVsDigitalNotes = "UPI QR code accepted at ASI ticket booth and local handicraft shops."
        ),
        howToReachTransit = "KSRTC AC Airavat bus from Majestic Bengaluru to Hassan (3.5 hours), then local KSRTC bus or cab to Belur (38 km).",
        survivalKannadaPhrase = "Belur Chennakeshava temple guide charge eshtu aagutthe?",
        survivalKannadaLipi = "ಬೇಲೂರು ಚೆನ್ನಕೇಶವ ದೇವಾಲಯ ಗೈಡ್ ಚಾರ್ಜ್ ಎಷ್ಟು ಆಗುತ್ತೆ?",
        survivalKannadaMeaning = "What is the official guide fee for Belur Chennakeshava Temple?",
        imageUrl = "https://images.unsplash.com/photo-1590050752117-238cb0fb12b1?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=belur+halebeedu+hoysala+unesco+heritage+karnataka",
        guideInfo = "Certified Ministry of Tourism guides stationed at the entrance arch; ask to see official green photo ID (₹500 fixed)"
    ),

    KarnatakaSpot(
        id = "dandeli_rafting",
        name = "Dandeli Kali River White-Water Rafting",
        kannadaName = "ದಾಂಡೇಲಿ ಕಾಳಿ ನದಿ ವೈಟ್-ವಾಟರ್ ರಾಫ್ಟಿಂಗ್",
        tagline = "Grade 3 & 4 Thrilling Rapids in the Dense Western Ghats Rainforest",
        category = TourismCategory.ADVENTURE_SPORTS,
        region = KarnatakaRegion.MALNAD,
        district = "Uttara Kannada District",
        nearestTransitHub = "Airport: Hubballi HBX (75 km) / Belagavi IXG (85 km) | Train: Alnavar Junction (32 km)",
        distanceFromBangaloreKm = 460,
        bestSeason = "October to May (Regulated by Supa Dam water discharge)",
        timings = "8:30 AM – 3:30 PM",
        overview = "Dandeli is South India's capital for white-water river rafting. Guided by certified swift-water rescue river masters, the Kali River offers exhilarating Class II to Class III+ rapids with names like 'Adi\'s Delusion', 'The Piper', and 'Stanley\'s Squeeze' surrounded by the hornbill-rich Dandeli-Anshi Tiger Reserve.",
        keyHighlights = listOf(
            "Full 9.5 km stretch with 9 thrilling rapids",
            "Natural river whirlpool swimming with life jackets",
            "Jungle kayaking and river crossing zipline",
            "Malabar Pied Hornbill birdwatching in buffer canopy"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Dandeli Ganeshgudi Single Window / Tourism Counter",
            portalUrl = "https://karnatakatourism.org",
            bookingRule = "Rafting operations depend on Supa Dam hydroelectric water release. Book morning slot at authorized Ganeshgudi counters.",
            badgeTag = "WHITE WATER RAFTING"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 1400,
            foreignerFeeInr = 1500,
            approxUsdEquivalent = "~$17.00 – $18.00 USD",
            feeIncludes = "Rafting gear (CE certified life vest + helmet + paddle), river transport jeep, and licensed instructor.",
            dressCodeRules = "Quick-dry nylon clothing or swimwear. No heavy denims or cottons. Mandatory strap-on water shoes or strapped sandals.",
            photographyPolicy = "Instructors carry waterproof GoPros on helmet mounts; photos/videos provided for ₹300-₹500 per raft.",
            scamAlertNotes = "Confirm whether the operator is offering the Short Rafting (1.5km for ₹500) or Full Long Stretch (9km for ₹1,400). Always insist on certified river guides.",
            cashVsDigitalNotes = "Keep ₹500 cash for river shuttle jeep driver and changing room tokens."
        ),
        howToReachTransit = "Train from Bengaluru to Alnavar Junction or Dharwad, then 1-hour KSRTC bus or taxi to Dandeli town.",
        survivalKannadaPhrase = "Full stretch 9 km river rafting ge ticket eshtu?",
        survivalKannadaLipi = "ಫುಲ್ ಸ್ಟ್ರೆಚ್ ಒಂಬತ್ತು ಕಿ.ಮೀ ರಾಫ್ಟಿಂಗ್‌ಗೆ ಟಿಕೆಟ್ ಎಷ್ಟು?",
        survivalKannadaMeaning = "How much is the ticket for the full stretch 9km river rafting?",
        imageUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=dandeli+kali+river+rafting+karnataka",
        guideInfo = "Certified swift-water rescue river masters steer every 6-person raft with safety kayaks flanking"
    )
)
