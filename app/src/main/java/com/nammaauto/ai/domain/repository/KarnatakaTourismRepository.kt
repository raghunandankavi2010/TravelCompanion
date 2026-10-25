package com.nammaauto.ai.domain.repository

import com.nammaauto.ai.domain.model.CityTransportTariff
import com.nammaauto.ai.domain.model.ForeignerPricingGuidance
import com.nammaauto.ai.domain.model.KarnatakaRegion
import com.nammaauto.ai.domain.model.KarnatakaSpot
import com.nammaauto.ai.domain.model.OfficialBookingInfo
import com.nammaauto.ai.domain.model.RegionalDialectInfo
import com.nammaauto.ai.domain.model.TourismCategory

object KarnatakaTourismRepository {

    val spots: List<KarnatakaSpot> = listOf(
        // ========================================================
        // 1. HERITAGE NORTH (UTTARA KARNATAKA & DECCAN)
        // ========================================================
        KarnatakaSpot(
            id = "hampi_unesco",
            name = "Hampi UNESCO World Heritage Site",
            kannadaName = "ಹಂಪಿ ವಿಶ್ವ ಪರಂಪರೆಯ ತಾಣ",
            tagline = "The Golden Boulders & Capital of the Vijayanagara Empire",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.UTTARA_KARNATAKA,
            district = "Vijayanagara District",
            nearestTransitHub = "Airport: Hubballi HBX (140 km) | Train: Hospet Junction (12 km)",
            distanceFromBangaloreKm = 345,
            bestSeason = "October to March (Pleasant winter weather)",
            timings = "6:00 AM – 6:00 PM (Daily)",
            overview = "Spectacular 14th-century capital of the Vijayanagara Empire spread over 4,100 hectares of otherworldly boulder landscapes and Tungabhadra riverbanks. Highlights include the active 7th-century Virupaksha Temple, the monolithic Stone Chariot at Vijaya Vittala, and Lotus Mahal.",
            keyHighlights = listOf(
                "Iconic Stone Chariot & 56 musical pillars at Vijaya Vittala",
                "Virupaksha Temple darshan with temple elephant Lakshmi blessing",
                "Tungabhadra river coracle (Dongdi) circular boat crossing",
                "Panoramic sunset over boulder horizon from Matanga Hill"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Archaeological Survey of India (ASI)",
                portalUrl = "https://asi.nic.in",
                bookingRule = "Tickets can be purchased online on ASI portal or on-site QR counters. Ticket covers Vittala Temple & Zenana Enclosure.",
                badgeTag = "ASI UNESCO SITE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 40,
                foreignerFeeInr = 600,
                approxUsdEquivalent = "~$7.20 USD",
                feeIncludes = "Entry to Vijaya Vittala complex, Lotus Mahal & ASI Archaeological Museum.",
                dressCodeRules = "Modest clothing required. Shoulders and knees must be covered inside active Virupaksha temple. Remove footwear at temple entrances.",
                photographyPolicy = "Still photography allowed without fee. Drone videography strictly banned without ASI Delhi written clearance.",
                scamAlertNotes = "Only hire guides wearing the official Ministry of Tourism green badge. Coracle rides should not exceed ₹200–₹300 per person.",
                cashVsDigitalNotes = "UPI works in Hampi bazaar, but keep ₹1,000 cash for coracle rides and auto rickshaws on the river's Sanapur/Hippie side."
            ),
            howToReachTransit = "Overnight Hampi Express train from KSR Bengaluru to Hospet Junction, then local bus or ₹200 auto ride.",
            survivalKannadaPhrase = "Hampi Vijaya Vittala temple ge hogalu auto eshtu?",
            survivalKannadaLipi = "ಹಂಪಿ ವಿಜಯ ವಿಠ್ಠಲ ದೇವಸ್ಥಾನಕ್ಕೆ ಹೋಗಲು ಆಟೋ ಎಷ್ಟು?",
            survivalKannadaMeaning = "How much for an auto to Hampi Vijaya Vittala Temple?",
            imageUrl = "https://images.unsplash.com/photo-1600100397608-f010f443b749?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=karnataka+tourism+hampi+official+documentary",
            guideInfo = "ASI registered guides available at Vittala entrance; look for Ministry of Tourism green photo ID card (₹500-₹800 fixed)"
        ),

        KarnatakaSpot(
            id = "badami_caves",
            name = "Badami Cave Temples & Agastya Lake",
            kannadaName = "ಬಾದಾಮಿ ಗುಹಾಂತರ ದೇವಾಲಯಗಳು",
            tagline = "6th-Century Rock-Cut Chalukyan Sandstone Marvels",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.UTTARA_KARNATAKA,
            district = "Bagalkote District",
            nearestTransitHub = "Airport: Hubballi HBX (105 km) | Train: Badami Railway Station (5 km)",
            distanceFromBangaloreKm = 450,
            bestSeason = "October to March",
            timings = "9:00 AM – 5:30 PM",
            overview = "Carved directly into red sandstone ravines overlooking the emerald waters of Agastya Lake. The four cave temples showcase exquisite early Chalukyan rock architecture: Cave 1 dedicated to Shiva (18-armed Nataraja performing Tandava), Caves 2 & 3 to Vishnu (Trivikrama & Varaha), and Cave 4 to Jain Tirthankaras.",
            keyHighlights = listOf(
                "18-armed dancing Nataraja carving depicting 81 Bharatanatyam mudras",
                "Scenic Bhootanatha Temples standing at the water's edge of Agastya Lake",
                "North Fort climb with ancient granaries and treasury chambers",
                "Red sandstone canyon bouldering trails"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Archaeological Survey of India (ASI)",
                portalUrl = "https://asi.nic.in",
                bookingRule = "ASI ticket counter at Cave 1 entrance or via asi.nic.in portal.",
                badgeTag = "ASI ROCK CAVE HERITAGE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 25,
                foreignerFeeInr = 300,
                approxUsdEquivalent = "~$3.60 USD",
                feeIncludes = "Access to all four rock cave temples and Agastya lake pathway.",
                dressCodeRules = "Comfortable shoes for stone stairways. Beware of local macaques; keep food items inside zipped backpacks.",
                photographyPolicy = "Standard photography allowed. Tripods require prior ASI permission.",
                scamAlertNotes = "Local guide charge is standard ₹400-₹600 for all four caves. Verify official tourism badge.",
                cashVsDigitalNotes = "UPI QR accepted at ticket counters and Badami town cafes."
            ),
            howToReachTransit = "Direct train from Hubballi, Bengaluru (Gol Gumbaz Express) to Badami Station, then ₹80 auto to Caves.",
            survivalKannadaPhrase = "Badami cave temples nodoke ticket elli kodthare?",
            survivalKannadaLipi = "ಬಾದಾಮಿ ಗುಹೆ ನೋಡೋಕೆ ಟಿಕೆಟ್ ಎಲ್ಲಿ ಕೊಡ್ತಾರೆ?",
            survivalKannadaMeaning = "Where do they give tickets to view Badami cave temples?",
            imageUrl = "https://images.unsplash.com/photo-1628151015968-3a4429e9ef04?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=badami+cave+temples+karnataka+tourism",
            guideInfo = "Certified rock cave guides at Cave 1 counter (₹400 for all 4 caves)"
        ),

        KarnatakaSpot(
            id = "pattadakal_aihole",
            name = "Pattadakal & Aihole UNESCO Monuments",
            kannadaName = "ಪಟ್ಟದಕಲ್ಲು ಮತ್ತು ಐಹೊಳೆ ದೇವಾಲಯಗಳು",
            tagline = "Cradle of Indian Temple Architecture & Chalukyan Coronation Site",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.UTTARA_KARNATAKA,
            district = "Bagalkote District (Near Badami)",
            nearestTransitHub = "Airport: Hubballi HBX (125 km) | Train: Badami Station (22 km)",
            distanceFromBangaloreKm = 465,
            bestSeason = "October to March",
            timings = "6:00 AM – 6:00 PM",
            overview = "Pattadakal was the royal coronation city of the Chalukyas on the banks of the Malaprabha River, presenting a harmonious confluence of Northern Nagara and Southern Dravidian temple styles. Nearby Aihole holds over 125 stone temples dating from 450 AD, notably the apsidal Durga Temple.",
            keyHighlights = listOf(
                "Virupaksha Temple at Pattadakal built by Queen Lokamahadevi in 740 AD",
                "Durga Temple Aihole with unique semi-circular buddhist chaitya apsidal plan",
                "Ravana Phadi rock-cut cave with Nataraja and Saptamatrikas",
                "Malaprabha river flowing northward (Uttara Vahini)"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Archaeological Survey of India (ASI)",
                portalUrl = "https://asi.nic.in",
                bookingRule = "Combined or individual tickets available on ASI portal.",
                badgeTag = "UNESCO CRADLE OF ART"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 25,
                foreignerFeeInr = 300,
                approxUsdEquivalent = "~$3.60 USD",
                feeIncludes = "Entry to the 10-temple Pattadakal UNESCO complex.",
                dressCodeRules = "Modest dress. Footwear removed when entering active sanctums.",
                photographyPolicy = "Standard photography permitted. Drone flights strictly prohibited.",
                scamAlertNotes = "Taxis from Badami charge ₹1,200–₹1,500 roundtrip to cover both Pattadakal and Aihole.",
                cashVsDigitalNotes = "UPI QR working at ASI ticket booths."
            ),
            howToReachTransit = "22 km from Badami; best reached by auto rickshaw or KSRTC bus from Badami bus stand.",
            survivalKannadaPhrase = "Pattadakal mathu Aihole nodoke auto package eshtu?",
            survivalKannadaLipi = "ಪಟ್ಟದಕಲ್ಲು ಮತ್ತು ಐಹೊಳೆ ನೋಡೋಕೆ ಆಟೋ ಪ್ಯಾಕೇಜ್ ಎಷ್ಟು?",
            survivalKannadaMeaning = "How much is an auto package to tour Pattadakal and Aihole?"
        ),

        KarnatakaSpot(
            id = "gol_gumbaz",
            name = "Gol Gumbaz & Whispering Gallery",
            kannadaName = "ಗೋಲ್ ಗುಂಬಜ್ ಮತ್ತು ಪಿಸುಗುಟ್ಟುವ ಗ್ಯಾಲರಿ",
            tagline = "World's 2nd Largest Dome Unsupported by Pillars & 11-Echo Marvel",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.UTTARA_KARNATAKA,
            district = "Vijayapura (Bijapur) District",
            nearestTransitHub = "Airport: Belagavi IXG (200 km) / Hubballi HBX (195 km) | Train: Vijayapura Station (2 km)",
            distanceFromBangaloreKm = 525,
            bestSeason = "October to March",
            timings = "6:00 AM – 6:00 PM",
            overview = "The mausoleum of Mohammed Adil Shah (1656 AD), Gol Gumbaz features the second largest dome in the world (44m external diameter) erected without central pillar supports. At the base of the dome, the world-famous Whispering Gallery reproduces even the faintest whisper across 38 meters and echoes every sound 7 to 11 times.",
            keyHighlights = listOf(
                "Acoustic Whispering Gallery inside the high dome",
                "Four 7-storey octagonal minaret towers with winding stone staircases",
                "Ibrahim Rauza nearby (often called the 'Taj Mahal of the Deccan')",
                "Malik-e-Maidan (Master of the Battlefield) 55-ton medieval bronze cannon"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Archaeological Survey of India (ASI)",
                portalUrl = "https://asi.nic.in",
                bookingRule = "Tickets available at counter or via asi.nic.in portal.",
                badgeTag = "DECCAN SULTANATE ARCHITECTURE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 25,
                foreignerFeeInr = 300,
                approxUsdEquivalent = "~$3.60 USD",
                feeIncludes = "Access to mausoleum hall, staircase towers, and whispering gallery.",
                dressCodeRules = "Casual modest attire. Good walking stamina needed to climb the 7 storeys to the gallery.",
                photographyPolicy = "Still photography permitted; flashes restricted inside tomb chamber.",
                scamAlertNotes = "Visit early morning (6:30 AM – 8:30 AM) to experience the acoustic echo before crowd noise builds up.",
                cashVsDigitalNotes = "UPI accepted at ticket counters and Vijayanagar bazaar."
            ),
            howToReachTransit = "Direct Gol Gumbaz Express train from Bengaluru/Mysuru/Hubballi directly to Vijayapura Railway Station.",
            survivalKannadaPhrase = "Whispering gallery ge hogalu stair case ellide?",
            survivalKannadaLipi = "ಪಿಸುಗುಟ್ಟುವ ಗ್ಯಾಲರಿಗೆ ಹೋಗಲು ಮೆಟ್ಟಿಲು ಎಲ್ಲಿದೆ?",
            survivalKannadaMeaning = "Where is the staircase to go up to the Whispering Gallery?"
        ),

        KarnatakaSpot(
            id = "bidar_fort",
            name = "Bidar Fort & Mahmud Gawan Madrasa",
            kannadaName = "ಬೀದರ್ ಕೋಟೆ ಮತ್ತು ಮಹಮೂದ್ ಗವಾನ್ ಮದರಸಾ",
            tagline = "Bahmani Sultanate Citadel, Karez Subterranean Aqueducts & Bidriware",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.UTTARA_KARNATAKA,
            district = "Bidar District (Northern Tip of Karnataka)",
            nearestTransitHub = "Airport: Bidar Airport IXX (Civil Terminal) / Hyderabad HYD (140 km) | Train: Bidar Station (3 km)",
            distanceFromBangaloreKm = 690,
            bestSeason = "October to February",
            timings = "8:00 AM – 6:30 PM",
            overview = "A formidable 15th-century Bahmani citadel built atop a red laterite cliff, boasting 5.5 km of ramparts and a triple-layered moat carved directly into solid rock. Bidar is also famous for its ancient Persian Karez underground water network, the Rangin Mahal (painted tile palace), and UNESCO-listed Bidriware silver-inlaid black metal craftsmanship.",
            keyHighlights = listOf(
                "Triple moat and massive gates of Bidar Fort (Sharza Darwaza, Gagan Darwaza)",
                "Rangin Mahal with Persian mother-of-pearl mosaics and colored tiles",
                "Mahmud Gawan Islamic university complex with minaret towers",
                "Guru Nanak Jhira Sahib holy Sikh gurdwara with perennial sweet water spring"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Archaeological Survey of India & Karnataka Tourism",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Fort grounds free entry; protected inner palaces have nominal ticketing.",
                badgeTag = "BAHMANI CITADEL"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 25,
                foreignerFeeInr = 300,
                approxUsdEquivalent = "~$3.60 USD",
                feeIncludes = "Entry to Rangin Mahal and museum complex.",
                dressCodeRules = "Comfortable walking shoes. Head cover required if visiting Guru Nanak Jhira Sahib.",
                photographyPolicy = "Standard photography permitted; drone photography restricted near defence zones.",
                scamAlertNotes = "Purchase authentic Bidriware directly from government artisans at Bidar crafts society near fort.",
                cashVsDigitalNotes = "UPI QR accepted across Bidar town."
            ),
            howToReachTransit = "Direct express trains from Bengaluru, Hyderabad, and Kalaburagi to Bidar Railway Station.",
            survivalKannadaPhrase = "Asali Bidriware silver work elli sigutthe?",
            survivalKannadaLipi = "ಅಸಲಿ ಬಿದ್ರಿ ಕಲೆ ಬೆಳ್ಳಿ ಕೆಲಸ ಎಲ್ಲಿ ಸಿಗುತ್ತೆ?",
            survivalKannadaMeaning = "Where can I find authentic Bidriware silver inlay artwork?"
        ),

        // ========================================================
        // 2. COASTAL KARAVALI (BEACHES, ISLANDS & SHRINES)
        // ========================================================
        KarnatakaSpot(
            id = "gokarna_om_beach",
            name = "Om Beach & Kudle Beach (Gokarna)",
            kannadaName = "ಓಂ ಬೀಚ್ ಮತ್ತು ಕುಡ್ಲೆ ಬೀಚ್ (ಗೋಕರ್ಣ)",
            tagline = "Sacred Natural ॐ Shaped Coastline & Golden Cliffside Hikes",
            category = TourismCategory.BEACHES,
            region = KarnatakaRegion.KARAVALI,
            district = "Uttara Kannada District",
            nearestTransitHub = "Airport: Goa Dabolim GOI (145 km) / Mopa GOX (170 km) | Train: Gokarna Road Station (9 km)",
            distanceFromBangaloreKm = 490,
            bestSeason = "October to March",
            timings = "Open 24 Hours (Swimming recommended: 6:00 AM – 6:30 PM)",
            overview = "Gokarna is where spiritual tranquility meets coastal bohemia. Om Beach is naturally shaped in the sacred Sanskrit 'ॐ' silhouette with two crescent bays separated by rocky headlands. Trekkers can embark on the famous 5-Beach Cliff Trail (Paradise Beach -> Half Moon Beach -> Om Beach -> Kudle Beach -> Main Beach).",
            keyHighlights = listOf(
                "Panoramic cliff-edge trek connecting five secluded beaches",
                "Banana boat rides, sea kayaking, and beginner surfing lessons",
                "Beach shacks serving fresh Karwar coastal seafood and fruit bowls",
                "Sunset drum circles and yoga at Kudle beach"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Karnataka Tourism Public Coastal Zone",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Public access is completely free. Water sports booked at Om Beach counters.",
                badgeTag = "FREE PUBLIC ACCESS",
                hasOnlineBooking = false
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 0,
                foreignerFeeInr = 0,
                approxUsdEquivalent = "Free Entry (Water sports ₹300–₹800)",
                feeIncludes = "Public beach access. Sun loungers at shacks available with food order.",
                dressCodeRules = "Beachwear and swimwear appropriate on beaches; modest clothing when walking through temple street in Gokarna town.",
                photographyPolicy = "Unrestricted photography; respect privacy of fellow travelers on secluded bays.",
                scamAlertNotes = "Avoid swimming beyond the red safety flags; currents on Om Beach rocky inlets can be deceptively strong.",
                cashVsDigitalNotes = "Beach shacks accept UPI, but keep ₹500 cash for auto rickshaws between Gokarna town and Om Beach (₹150-₹200)."
            ),
            howToReachTransit = "Overnight Panchaganga or Matsyagandha train to Gokarna Road Station, then ₹150 auto to town/beaches.",
            survivalKannadaPhrase = "Om Beach inda Kudle Beach ge auto fare eshtu?",
            survivalKannadaLipi = "ಓಂ ಬೀಚ್‌ನಿಂದ ಕುಡ್ಲೆ ಬೀಚ್‌ಗೆ ಆಟೋ ಫೇರ್ ಎಷ್ಟು?",
            survivalKannadaMeaning = "What is the auto fare from Om Beach to Kudle Beach?"
        ),

        KarnatakaSpot(
            id = "st_marys_island",
            name = "St. Mary's Island (Hexagonal Columnar Basalt)",
            kannadaName = "ಸೇಂಟ್ ಮೇರಿಸ್ ದ್ವೀಪ (ಹೆಕ್ಸಾಗೋನಲ್ ಬಸಾಲ್ಟ್ ಕಲ್ಲುಗಳು)",
            tagline = "88-Million-Year-Old Geological Wonder of Hexagonal Lava Columns",
            category = TourismCategory.ISLANDS,
            region = KarnatakaRegion.KARAVALI,
            district = "Udupi District (6 km off Malpe Coast)",
            nearestTransitHub = "Airport: Mangaluru IXE (58 km) | Train: Udupi Railway Station (8 km)",
            distanceFromBangaloreKm = 410,
            bestSeason = "October to May (Strictly closed during June–September monsoons)",
            timings = "Ferries operate 9:00 AM – 5:00 PM (Last boat return from island: 5:45 PM)",
            overview = "Declared a National Geological Monument by the Geological Survey of India. The island is world-famous for its unique hexagonal columnar basaltic lava pillars formed 88 million years ago when Madagascar separated from the Indian tectonic plate. Legend says Vasco da Gama landed here in 1498.",
            keyHighlights = listOf(
                "Walking among the rare hexagonal columnar basaltic lava cliffs",
                "Pristine turquoise clear lagoon water (one of South India's clearest)",
                "Seashell beach covered with millions of polished ocean shells",
                "30-minute thrilling ferry boat voyage across the Arabian Sea from Malpe Jetty"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Malpe Tourist Jetty / Govt Boating Office",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Round-trip ferry tickets purchased at Malpe Tourist Board Jetty. Ferries depart as batches fill up (approx every 20-30 mins).",
                badgeTag = "NATIONAL GEOLOGICAL MONUMENT"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 350,
                foreignerFeeInr = 400,
                approxUsdEquivalent = "~$4.80 USD (Round-trip Ferry Boat + Island Entry)",
                feeIncludes = "Two-way motorized ferry transfer from Malpe Jetty, life jacket, and island environmental upkeep fee.",
                dressCodeRules = "Footwear with good grip recommended for walking on basalt rock surfaces; flip-flops can slip.",
                photographyPolicy = "Still and video photography allowed. Drone flights need coastal security permission.",
                scamAlertNotes = "Plastic bottles and packaging are strictly checked at boarding. Luggage scanner will confiscate single-use plastic.",
                cashVsDigitalNotes = "Official Jetty counter accepts UPI QR codes and cards. Bring cash if buying tender coconut on island."
            ),
            howToReachTransit = "Reach Malpe Beach Jetty from Udupi; board the official Malpe Beach Development Committee ferry boat.",
            survivalKannadaPhrase = "St. Mary's Island ninda last boat yavaga barutthe?",
            survivalKannadaLipi = "ಸೇಂಟ್ ಮೇರಿಸ್ ಐಲ್ಯಾಂಡ್‌ ನಿಂದ ಕೊನೆ ಬೋಟ್ ಯಾವಾಗ ಬರುತ್ತೆ?",
            survivalKannadaMeaning = "When does the last ferry boat depart from St. Mary's Island?",
            imageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=st+marys+island+malpe+karnataka+tourism",
            guideInfo = "Geological Survey information boards along the basalt pillar trails explaining 88-million-year volcanic origin"
        ),

        KarnatakaSpot(
            id = "udupi_krishna_matha",
            name = "Udupi Sri Krishna Matha & Kanakana Kindi",
            kannadaName = "ಉಡುಪಿ ಶ್ರೀ ಕೃಷ್ಣ ಮಠ ಮತ್ತು ಕನಕನ ಕಿಂಡಿ",
            tagline = "13th-Century Dvaita Vedanta Spiritual Capital & World's Free Anna Brahma Feast",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.KARAVALI,
            district = "Udupi District",
            nearestTransitHub = "Airport: Mangaluru IXE (55 km) | Train: Udupi Railway Station (3.5 km)",
            distanceFromBangaloreKm = 400,
            bestSeason = "Year-round (Grand during Paryaya & Krishna Janmashtami)",
            timings = "5:00 AM – 9:30 PM (Anna Prasadam lunch: 11:30 AM – 2:00 PM)",
            overview = "Founded in the 13th century by the saint Madhvacharya, the temple features the unique Kanakana Kindi — a small nine-holed silver window through which Lord Krishna turned westwards to give darshan to the devout low-caste poet-saint Kanakadasa. The temple is celebrated worldwide for its daily free sacred feast (Anna Santharpane) feeding thousands.",
            keyHighlights = listOf(
                "Kanakana Kindi (Nine-holed window darshan of Lord Krishna)",
                "Madhva Sarovara sacred temple tank with oil lamp reflections",
                "Daily free Anna Brahma lunch prasadam served in traditional coastal dining hall",
                "Car Street lined with traditional 800-year-old Ashta Mathas and Mitra Samaj snacks"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Sri Krishna Matha Trust Official",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "General darshan and sacred Anna Prasadam lunch are 100% free for all humans regardless of caste or nationality.",
                badgeTag = "SACRED ASHTA MATHA"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 0,
                foreignerFeeInr = 0,
                approxUsdEquivalent = "Free Entry & Free Sacred Feast",
                feeIncludes = "Darshan and hot satvik lunch prasadam served on plantain leaves.",
                dressCodeRules = "Strict traditional dress code: Men must remove shirts and vests before entering inner shrine. Saree/churidar for women. Shorts strictly barred.",
                photographyPolicy = "Cameras prohibited inside the inner sanctum.",
                scamAlertNotes = "Never pay anyone for lunch or darshan; both are completely free offerings of the Matha.",
                cashVsDigitalNotes = "UPI accepted at temple souvenir stalls for brass Krishna idols."
            ),
            howToReachTransit = "Direct trains from Bengaluru (Panchaganga Express) or KSRTC Airavat AC buses stop right in Udupi city.",
            survivalKannadaPhrase = "Anna Santharpane oota hall ellide?",
            survivalKannadaLipi = "ಅನ್ನ ಸಂತರ್ಪಣೆ ಊಟ ಹಾಲ್ ಎಲ್ಲಿದೆ?",
            survivalKannadaMeaning = "Where is the Anna Santharpane sacred dining hall?"
        ),

        KarnatakaSpot(
            id = "murudeshwar_temple",
            name = "Murudeshwar Shiva Temple & Raja Gopura",
            kannadaName = "ಮುರುಡೇಶ್ವರ ಶಿವ ದೇವಸ್ಥಾನ ಮತ್ತು ರಾಜ ಗೋಪುರ",
            tagline = "World's 2nd Tallest Shiva Statue Overlooking the Arabian Sea",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.KARAVALI,
            district = "Uttara Kannada District",
            nearestTransitHub = "Airport: Goa Dabolim GOI (160 km) / Mangaluru IXE (160 km) | Train: Murudeshwar Station (2 km)",
            distanceFromBangaloreKm = 485,
            bestSeason = "October to March",
            timings = "3:00 AM – 1:00 PM & 3:00 PM – 8:00 PM",
            overview = "Perched on Kanduka Hill surrounded on three sides by the Arabian Sea, Murudeshwar features a colossal 123-foot (37m) Lord Shiva statue and an 18-storey, 249-foot high Raja Gopura with a modern high-speed elevator taking visitors to the top for 360-degree ocean views.",
            keyHighlights = listOf(
                "123-foot Shiva statue glowing against the setting sea sun",
                "Elevator to the 18th floor of the Raja Gopura for panoramic aerial view",
                "Sunset beach walk and Arabian sea watersports right beside temple",
                "Launchpad for Netrani Island scuba diving expeditions"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Murudeshwar Temple Trust Official",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "General darshan is free. Raja Gopura 18th-floor lift ticket is purchased at base counter (₹20).",
                badgeTag = "COASTAL SHRINE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 20,
                foreignerFeeInr = 20,
                approxUsdEquivalent = "~$0.24 USD (Lift ticket)",
                feeIncludes = "Elevator ticket to 18th floor viewing deck. Temple darshan is free.",
                dressCodeRules = "Strict dress code: Dhoti/pants for men, saree/salwar for women. Shorts, bermudas, and mini-skirts are prohibited.",
                photographyPolicy = "Cameras permitted in open courtyard & Gopura; strictly forbidden inside Garbhagriha.",
                scamAlertNotes = "Do not pay private agents for 'VIP darshan queues' unless purchased from official computer trust counter.",
                cashVsDigitalNotes = "Car parking and footwear stall require ₹10–₹20 cash coins/small notes."
            ),
            howToReachTransit = "Overnight Panchaganga Superfast Express train from Yesvantpur / KSR Bengaluru directly to Murudeshwar Railway Station.",
            survivalKannadaPhrase = "Raja Gopura lift ticket elli thagobeku?",
            survivalKannadaLipi = "ರಾಜ ಗೋಪುರ ಲಿಫ್ಟ್ ಟಿಕೆಟ್ ಎಲ್ಲಿ ತಗೋಬೇಕು?",
            survivalKannadaMeaning = "Where should I buy the Raja Gopura lift ticket?"
        ),

        KarnatakaSpot(
            id = "netrani_scuba",
            name = "Netrani Island Scuba Diving & Coral Snorkeling",
            kannadaName = "ನೇತ್ರಾಣಿ ದ್ವೀಪ ಸ್ಕೂಬಾ ಡೈವಿಂಗ್ ಮತ್ತು ಕೋರಲ್ ರೀಫ್",
            tagline = "The Heart-Shaped Coral Reef Sanctuary of the Arabian Sea",
            category = TourismCategory.ADVENTURE_SPORTS,
            region = KarnatakaRegion.KARAVALI,
            district = "Off Murudeshwar Coast (19 km offshore)",
            nearestTransitHub = "Airport: Mangaluru IXE (160 km) / Goa GOI (160 km) | Train: Murudeshwar Station (3 km)",
            distanceFromBangaloreKm = 485,
            bestSeason = "October to May (Clear water visibility up to 15-20 meters)",
            timings = "7:30 AM boat departure from Murudeshwar Jetty (Full day: 7:30 AM – 3:30 PM)",
            overview = "Also known as Pigeon Island, Netrani is a secluded uninhabited island surrounded by vibrant coral reefs. Visibility ranges between 15 and 20 meters, revealing butterflyfish, triggerfish, parrotfish, moray eels, stingrays, and occasional whale shark sightings. Dive is conducted from offshore dive catamarans.",
            keyHighlights = listOf(
                "PADI/SSI beginner Discovery Scuba Diving (DSD) up to 10-12 meters",
                "Guided 1-on-1 dive with personal certified divemaster",
                "High-definition underwater GoPro video & photos included in package",
                "Boat cruise across open Arabian sea waters with dolphin sightings"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "PADI / SSI Murudeshwar Certified Centers",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Advance booking required. Island rock landing is restricted by Indian Navy; all diving occurs directly from dive boats.",
                badgeTag = "PADI DIVE SANCTUARY"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 3500,
                foreignerFeeInr = 3500,
                approxUsdEquivalent = "~$42.00 USD (Includes gear, boat & underwater footage)",
                feeIncludes = "Complete scuba gear (wetsuit, regulator, BCD, tank, fins), 45-min boat ride, training briefing, and 30-min underwater dive.",
                dressCodeRules = "Swimwear, rashguards or comfortable swimming gear. Bring change of dry clothes.",
                photographyPolicy = "Underwater video and photos are included free in your package and transferred directly to your smartphone via OTG/AirDrop.",
                scamAlertNotes = "Ensure your operator is verified with certified PADI/SSI instructors. Non-divers can accompany on the boat for ₹1,999.",
                cashVsDigitalNotes = "All primary dive operators accept GPay/PhonePe and credit cards at Murudeshwar beach offices."
            ),
            howToReachTransit = "Reach Murudeshwar via overnight express train from Bengaluru; dive centers gather at Murudeshwar Temple Beach Jetty.",
            survivalKannadaPhrase = "Netrani boat ride mathu scuba diving ge permission ideya?",
            survivalKannadaLipi = "ನೇತ್ರಾಣಿ ಬೋಟ್ ರೈಡ್ ಮತ್ತು ಸ್ಕೂಬಾ ಡೈವಿಂಗ್‌ಗೆ ಪರ್ಮಿಷನ್ ಇದೆಯಾ?",
            survivalKannadaMeaning = "Is boat ride and scuba diving permitted today at Netrani?",
            imageUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=netrani+island+scuba+diving+murudeshwar",
            guideInfo = "Dedicated PADI / SSI certified divemaster assigned 1-on-1 for every diver under water"
        ),

        KarnatakaSpot(
            id = "honnavar_backwaters",
            name = "Honnavar Sharavathi Mangrove Boardwalk & Boating",
            kannadaName = "ಹೊನ್ನಾವರ ಶರಾವತಿ ಮ್ಯಾಂಗ್ರೋವ್ ಬೋರ್ಡ್‌ವಾಕ್",
            tagline = "Enchanting Backwater Mangrove Tunnels & Arabian Sea Confluence",
            category = TourismCategory.WATERFALLS_NATURE,
            region = KarnatakaRegion.KARAVALI,
            district = "Uttara Kannada District",
            nearestTransitHub = "Airport: Goa Dabolim GOI (175 km) / Hubballi HBX (190 km) | Train: Honnavar Station (4 km)",
            distanceFromBangaloreKm = 460,
            bestSeason = "October to May",
            timings = "8:00 AM – 6:00 PM",
            overview = "Honnavar is Karnataka's tranquil hidden backwater jewel where the sacred Sharavathi River meets the Arabian Sea. The Forest Department's Kandla Mangrove Boardwalk features a wooden walkway elevated above dense mangrove roots, leading to serene boat rides through emerald green river estuaries and untouched sandbars.",
            keyHighlights = listOf(
                "Kandla Mangrove Forest elevated wooden canopy walk",
                "Sharavathi river boat cruises winding through quiet river islands",
                "Eco Beach (Kasarkod) certified with international Blue Flag standard",
                "Apsarakonda coastal waterfall and natural pond overlooking the sea"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Forest Dept & Coastal Boating Union",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Boardwalk ticket ₹20 at counter. Boat rides ₹150–₹300 per head.",
                badgeTag = "MANGROVE ECO-SANCTUARY"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 20,
                foreignerFeeInr = 20,
                approxUsdEquivalent = "~$0.24 USD (Boardwalk Entry)",
                feeIncludes = "Access to wooden mangrove nature trail.",
                dressCodeRules = "Comfortable walking footwear. Mosquito repellent recommended during early evening.",
                photographyPolicy = "Very popular for nature & landscape photography.",
                scamAlertNotes = "Book boats at official Kasarkod / Mavinkurve jetty counters.",
                cashVsDigitalNotes = "UPI accepted at jetty counters."
            ),
            howToReachTransit = "Konkan Railway trains stop at Honnavar Railway Station; KSRTC buses run along coastal NH 66.",
            survivalKannadaPhrase = "Sharavathi backwater boat ride ge ticket counter ellide?",
            survivalKannadaLipi = "ಶರಾವತಿ ಬ್ಯಾಕ್‌ವಾಟರ್ ಬೋಟ್ ರೈಡ್ ಟಿಕೆಟ್ ಕೌಂಟರ್ ಎಲ್ಲಿದೆ?",
            survivalKannadaMeaning = "Where is the ticket counter for the Sharavathi backwater boat ride?"
        ),

        // ========================================================
        // 3. MALNAD & WESTERN GHATS (RAINFORESTS, COFFEE & PEAKS)
        // ========================================================
        KarnatakaSpot(
            id = "jog_falls",
            name = "Jog Falls (Gerosoppa Plunge)",
            kannadaName = "ಜೋಗ್ ಜಲಪಾತ (ಗೇರುಸೊಪ್ಪೆ)",
            tagline = "India's 2nd Highest Plunge Waterfall (253m) of Four Distinct Cascades",
            category = TourismCategory.WATERFALLS_NATURE,
            region = KarnatakaRegion.MALNAD,
            district = "Shivamogga District",
            nearestTransitHub = "Airport: Shivamogga RQY (105 km) / Hubballi HBX (160 km) | Train: Talguppa Station (14 km)",
            distanceFromBangaloreKm = 380,
            bestSeason = "July to December (Monsoon roaring spectacle)",
            timings = "7:30 AM – 9:00 PM (Laser show: 7:00 PM – 8:00 PM)",
            overview = "Fed by the wild Sharavathi River plunging 253 meters (830 ft) down granite chasms in four distinct streams: 'Raja' (the grand dignified plunge), 'Roarer' (surging down rocky cracks), 'Rocket' (jets of water shooting downward), and 'Rani' (gentle winding white ribbon).",
            keyHighlights = listOf(
                "Breathtaking amphitheater view from British bungalow & viewing deck",
                "1,400 stone steps descending to the very mist-shrouded base of the falls",
                "Evening 3D projection laser light and musical fountain show",
                "Sharavathi Valley wildlife sanctuary and viewpoint canopy"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Jog Management Authority (Govt of Karnataka)",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Entry tickets bought at modern visitor complex counter (₹50). Parking separate.",
                badgeTag = "ICONIC WATERFALL"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 50,
                foreignerFeeInr = 50,
                approxUsdEquivalent = "~$0.60 USD",
                feeIncludes = "Entry to landscaped viewpoints, walking promenade, and laser viewing area.",
                dressCodeRules = "Raincoats/umbrellas essential during monsoon; steps to the base are steep and slippery.",
                photographyPolicy = "Personal cameras permitted. Fog often rolls in; morning visits offer best visibility.",
                scamAlertNotes = "Do not cross the safety railings near the cliff edges; spray makes granite rocks treacherous.",
                cashVsDigitalNotes = "UPI QR accepted at tourist gate and KSTDC Mayura restaurant."
            ),
            howToReachTransit = "Train from Bengaluru/Mysuru to Talguppa Railway Station (14 km away), then local bus or taxi to Jog Falls.",
            survivalKannadaPhrase = "Jog falls base ge hogalu steps open ideya?",
            survivalKannadaLipi = "ಜೋಗ್ ಫಾಲ್ಸ್ ಕೆಳಗಡೆ ಹೋಗಲು ಮೆಟ್ಟಿಲುಗಳು ಓಪನ್ ಇದೆಯಾ?",
            survivalKannadaMeaning = "Are the steps to the bottom base of Jog Falls open today?",
            imageUrl = "https://images.unsplash.com/photo-1588714477688-cf28a50e94f7?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=jog+falls+karnataka+tourism+official",
            guideInfo = "Local tourism volunteers at viewpoint deck guide on steps clearance and laser show timings"
        ),

        KarnatakaSpot(
            id = "kudremukh_trek",
            name = "Kudremukh Peak Trek",
            kannadaName = "ಕುದುರೆಮುಖ ಶಿಖರ ಚಾರಣ",
            tagline = "Horse-Face Peak & Velvet Green Shola Grasslands (1,894m)",
            category = TourismCategory.TREKKING_TRAILS,
            region = KarnatakaRegion.MALNAD,
            district = "Chikkamagaluru District (Kudremukh National Park)",
            nearestTransitHub = "Airport: Mangaluru IXE (105 km) | Train: Udupi Station (100 km) / Mangaluru (110 km)",
            distanceFromBangaloreKm = 310,
            bestSeason = "June to January (Post-monsoon emerald greens are hypnotic)",
            timings = "6:00 AM checkpost clearance; must exit by 5:00 PM (No camping allowed)",
            overview = "Named after its distinctive horse-face shaped silhouette, Kudremukh is South India's most celebrated alpine trek. The 22km round-trip trail weaves through rolling montane shola grasslands, crystal cold streams, and mist-draped ridges within a protected tiger and lion-tailed macaque habitat.",
            keyHighlights = listOf(
                "Panoramic 360-degree vista of Western Ghats ridge from summit (1,894m)",
                "Crossing pristine freshwater forest streams",
                "Lush shola forest canopy with orchids and endemic Western Ghats flora",
                "Strict zero-litter conservation model managed by Forest Department"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Aranya Vihaara (Karnataka Forest Department)",
                portalUrl = "https://aranyavihaara.karnataka.gov.in",
                bookingRule = "STRICT ADVANCE ONLINE BOOKING ONLY on aranyavihaara.karnataka.gov.in. Daily entry strictly capped at 50 trekkers. Zero spot tickets!",
                badgeTag = "ARANYA VIHARA PERMIT"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 575,
                foreignerFeeInr = 575,
                approxUsdEquivalent = "~$6.90 USD (Forest Permit) + ~₹100 local guide share",
                feeIncludes = "Official Forest entry permit, environmental cess, and mandatory certified local eco-guide assigned to batches of 10.",
                dressCodeRules = "Full-length trekking pants (essential for leech protection during monsoon), moisture-wicking shirts, and sturdy hiking boots.",
                photographyPolicy = "Personal DSLRs allowed. Drones strictly prohibited and confiscated at checkpost under Wildlife Protection Act.",
                scamAlertNotes = "CRITICAL: Never believe homestays promising 'offline entrance tickets at gate'. All tickets must be booked on aranyavihaara.karnataka.gov.in in advance.",
                cashVsDigitalNotes = "ZERO mobile signal (Airtel/Jio) at base checkpost (Balagal/Mullodi). Carry ₹1,000 cash for 4x4 Jeep ride (₹500/head roundtrip) and leech socks."
            ),
            howToReachTransit = "Overnight KSRTC bus from Bengaluru/Mangaluru to Kalasa (8 hours). From Kalasa take local bus to Balagal, then 4x4 Jeep to Mullodi base camp.",
            survivalKannadaPhrase = "Aranya Vihaara booking permit ide, Mullodi checkpost yavadu?",
            survivalKannadaLipi = "ಅರಣ್ಯ ವಿಹಾರ ಬುಕಿಂಗ್ ಪರ್ಮಿಟ್ ಇದೆ, ಮುಳ್ಳೋಡಿ ಚೆಕ್‌ಪೋಸ್ಟ್ ಯಾವುದು?",
            survivalKannadaMeaning = "I have the Aranya Vihaara booking permit, where is the Mullodi checkpost?",
            imageUrl = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=kudremukh+trek+aranya+vihara+karnataka",
            guideInfo = "Mandatory certified Forest Dept eco-guide assigned to every batch of 10 trekkers at Mullodi checkpoint"
        ),

        KarnatakaSpot(
            id = "mullayanagiri_chik",
            name = "Mullayanagiri & Baba Budangiri",
            kannadaName = "ಮುಳ್ಳಯ್ಯನಗಿರಿ ಮತ್ತು ಬಾಬಾ ಬುಡನ್‌ಗಿರಿ",
            tagline = "Karnataka's Highest Summit (1,930m) & The Birthplace of Indian Coffee",
            category = TourismCategory.TREKKING_TRAILS,
            region = KarnatakaRegion.MALNAD,
            district = "Chikkamagaluru District",
            nearestTransitHub = "Airport: Mangaluru IXE (155 km) | Train: Kadur Junction (40 km) / Birur (45 km)",
            distanceFromBangaloreKm = 265,
            bestSeason = "September to March (Crisp mountain air & drifting clouds)",
            timings = "6:00 AM – 6:00 PM",
            overview = "Mullayanagiri is the rooftop of Karnataka standing at 1,930m (6,330 ft), crowned by a small shrine to Sage Mullappa Swamy. Nearby Baba Budangiri is where the Sufi saint Baba Budan first planted seven raw coffee beans smuggled from Yemen in the 17th century, birthing India's coffee heritage.",
            keyHighlights = listOf(
                "Standing on the highest geographic point in the state of Karnataka",
                "500 stone steps climb through cool mountain mist",
                "Baba Budangiri / Dattatreya Peetha sacred cave shrine",
                "Manikyadhara and Hebbe waterfalls tucked into coffee estates"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Chikkamagaluru District Administration",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Free public entry. Vehicle toll (₹50) collected at hill checkpost.",
                badgeTag = "HIGHEST SUMMIT IN KARNATAKA"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 0,
                foreignerFeeInr = 0,
                approxUsdEquivalent = "Free Entry (Vehicle Toll ₹50)",
                feeIncludes = "Public road access and stairway to the summit temple.",
                dressCodeRules = "Windcheater or fleece jacket (very cold and windy at the peak). Sturdy walking shoes.",
                photographyPolicy = "Spectacular mountain ridge photography.",
                scamAlertNotes = "Narrow winding ghat roads; hire experienced local taxi drivers if unaccustomed to steep hairpin bends.",
                cashVsDigitalNotes = "UPI accepted at roadside coffee stalls and base parking."
            ),
            howToReachTransit = "KSRTC bus from Bengaluru/Mangaluru to Chikkamagaluru town, then private taxi or local bus up the mountain.",
            survivalKannadaPhrase = "Mullayanagiri summit ge car hoguttha?",
            survivalKannadaLipi = "ಮುಳ್ಳಯ್ಯನಗಿರಿ ಶಿಖರಕ್ಕೆ ಕಾರ್ ಹೋಗುತ್ತಾ?",
            survivalKannadaMeaning = "Can the car drive all the way to Mullayanagiri summit parking?"
        ),

        KarnatakaSpot(
            id = "belur_halebeedu",
            name = "Belur & Halebeedu Hoysala Temples",
            kannadaName = "ಬೇಲೂರು ಮತ್ತು ಹಳೇಬೀಡು ಹೊಯ್ಸಳ ದೇವಾಲಯಗಳು",
            tagline = "UNESCO 2023 Inscribed Masterpieces of Soapstone Filigree Art",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.MALNAD,
            district = "Hassan District",
            nearestTransitHub = "Airport: Mangaluru IXE (145 km) / Bengaluru BLR (240 km) | Train: Hassan Junction (38 km)",
            distanceFromBangaloreKm = 220,
            bestSeason = "Year-round (Best: October to February)",
            timings = "7:30 AM – 7:30 PM (Belur) | 6:30 AM – 6:30 PM (Halebeedu)",
            overview = "12th-century celestial architecture carved from chloritic schist (soapstone). Chennakeshava Temple at Belur features 42 Madanika (dancing damsel) bracket figures carved with hyper-fine filigree detailing, while Halebeedu's Hoysaleshwara temple features over 20,000 intricate friezes.",
            keyHighlights = listOf(
                "Chennakeshava Temple Belur (Active temple with daily pujas)",
                "Darpana Sundari (Lady with mirror) soapstone sculpture",
                "Twin Shiva shrines of Hoysaleshwara & Shantaleshwara at Halebeedu",
                "Kedareshwara temple and Jain basadis nearby"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Karnataka Tourism & ASI Muzrai Dept",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Belur Chennakeshava is free general entry. Halebeedu ASI complex has nominal ticketing.",
                badgeTag = "UNESCO 2023 HERITAGE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 25,
                foreignerFeeInr = 300,
                approxUsdEquivalent = "~$3.60 USD (Halebeedu) / Belur is Free",
                feeIncludes = "Access to Halebeedu Hoysaleshwara museum and landscaped temple gardens.",
                dressCodeRules = "Traditional/respectful clothing. Sleeveless tops and short pants discouraged inside temple sanctum.",
                photographyPolicy = "Exterior photography encouraged; flash photography restricted inside dark inner sanctums.",
                scamAlertNotes = "Official guides charge ₹300-₹500 fixed by tourism committee with receipt. Reject non-certified touts.",
                cashVsDigitalNotes = "UPI QR codes available at all souvenir shops and official counters."
            ),
            howToReachTransit = "KSRTC AC Airavat bus from Majestic Bengaluru to Hassan (3.5 hours), then local KSRTC bus or cab to Belur (38 km).",
            survivalKannadaPhrase = "Belur Chennakeshava temple guide charge eshtu aagutthe?",
            survivalKannadaLipi = "ಬೇಲೂರು ಚೆನ್ನಕೇಶವ ದೇವಾಲಯ ಗೈಡ್ ಚಾರ್ಜ್ ಎಷ್ಟು ಆಗುತ್ತೆ?",
            survivalKannadaMeaning = "What is the official guide fee for Belur Chennakeshava Temple?"
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
            survivalKannadaMeaning = "How much is the ticket for the full stretch 9km river rafting?"
        ),

        // ========================================================
        // 4. ROYAL SOUTH (MYSURU, COORG & WILDLIFE TIGER RESERVES)
        // ========================================================
        KarnatakaSpot(
            id = "mysore_palace",
            name = "Mysore Palace (Amba Vilas)",
            kannadaName = "ಮೈಸೂರು ಅರಮನೆ (ಅಂಬಾ ವಿಲಾಸ)",
            tagline = "The Royal Seat of the Wodeyars & 100,000-Bulb Illumination",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.HALE_MYSURU,
            district = "Mysuru District",
            nearestTransitHub = "Airport: Mysuru MYQ (12 km) / Bengaluru BLR (180 km) | Train: Mysuru Junction (2 km)",
            distanceFromBangaloreKm = 145,
            bestSeason = "September to March (Spectacular during Dasara)",
            timings = "10:00 AM – 5:30 PM (Illumination: Sundays & holidays 7:00 – 8:00 PM)",
            overview = "One of India's most visited royal palaces. Designed by British architect Henry Irwin in Indo-Saracenic style with kaleidoscopic stained-glass ceilings, carved rosewood doors, and the fabled Golden Throne. On Sunday evenings, the entire palace is illuminated by 97,000+ electric incandescent lamps.",
            keyHighlights = listOf(
                "Kalyana Mantapa (Octagonal wedding pavilion with stained-glass peacock dome)",
                "Durbar Hall with ornate painted pillars and royal thrones",
                "Sunday night grand illumination spectacle",
                "Sound & Light (Son-et-Lumiere) show in English & Kannada"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Mysore Palace Board (Govt of Karnataka)",
                portalUrl = "https://mysorepalace.karnataka.gov.in",
                bookingRule = "Online e-tickets available on palace board portal or at palace South/West Gate counters.",
                badgeTag = "ROYAL HERITAGE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 100,
                foreignerFeeInr = 1000,
                approxUsdEquivalent = "~$12.00 USD (Includes Multilingual Audio Guide)",
                feeIncludes = "Foreigner ticket includes personal high-definition audio headset guide in English, French, German, Spanish, Japanese, or Italian.",
                dressCodeRules = "Footwear strictly prohibited inside royal residential chambers (free token shoe stands at entrance).",
                photographyPolicy = "Still photography allowed without flash. Video cameras require special token.",
                scamAlertNotes = "Avoid auto/tonga drivers who insist on taking you to private 'handicraft emporiums' instead of the palace; they earn 40% commission.",
                cashVsDigitalNotes = "Official counters accept international Visa/Mastercard and UPI QR."
            ),
            howToReachTransit = "Vande Bharat Express or Tipu Express train from KSR Bengaluru to Mysuru Junction (2 hours), then ₹50 auto to Palace North Gate.",
            survivalKannadaPhrase = "Palace illumination raathri eshtu gantege shuru aagutthe?",
            survivalKannadaLipi = "ಅರಮನೆ ಇಲ್ಯುಮಿನೇಷನ್ ರಾತ್ರಿ ಎಷ್ಟು ಗಂಟೆಗೆ ಶುರು ಆಗುತ್ತೆ?",
            survivalKannadaMeaning = "At what time does the Palace illumination begin tonight?",
            imageUrl = "https://images.unsplash.com/photo-1590050752117-238cb0fb12b1?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=mysore+palace+illumination+karnataka+tourism",
            guideInfo = "Foreigner ticket includes personal high-definition audio headset guide wand in 8 international languages"
        ),

        KarnatakaSpot(
            id = "kabini_safari",
            name = "Kabini / Nagarhole Tiger Reserve",
            kannadaName = "ಕಬಿನಿ ಮತ್ತು ನಾಗರಹೊಳೆ ಹುಲಿ ಸಂರಕ್ಷಿತ ಪ್ರದೇಶ",
            tagline = "World's Premier Leopard, Black Panther & Asiatic Elephant Habitat",
            category = TourismCategory.WILDLIFE_SAFARI,
            region = KarnatakaRegion.HALE_MYSURU,
            district = "Mysuru / Kodagu Border",
            nearestTransitHub = "Airport: Mysuru MYQ (70 km) / Bengaluru BLR (240 km) | Train: Mysuru Junction (75 km)",
            distanceFromBangaloreKm = 220,
            bestSeason = "October to May (Summer waterhole gatherings: March to May)",
            timings = "Morning Safari: 6:00 AM – 9:00 AM | Evening Safari: 3:30 PM – 6:30 PM",
            overview = "Kabini is universally recognized as one of the best places in the world to observe the elusive Indian leopard, wild tiger, and the famous black panther ('Sayee'). Nestled along the banks of the Kabini reservoir, boat and jeep safaris conducted by Jungle Lodges offer front-row views of Asiatic elephant herds gathering on the grassy shores.",
            keyHighlights = listOf(
                "Kabini River boat safari observing elephant herds swimming",
                "World's highest density leopard tracking in Nagarhole forest canopy",
                "Over 300 bird species including Malabar Pied Hornbills and Fish Eagles",
                "Eco-luxury stays at Government-run Jungle Lodges Kabini River Lodge"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Jungle Lodges & Resorts (Govt of Karnataka)",
                portalUrl = "https://junglelodges.com",
                bookingRule = "Safari slots must be booked through Jungle Lodges packages or Karnataka Forest Dept safari booking counters.",
                badgeTag = "PROJECT TIGER RESERVE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 1800,
                foreignerFeeInr = 3000,
                approxUsdEquivalent = "~$36.00 USD (Forest Dept Safari)",
                feeIncludes = "Jeep safari transfer, forest entry permit, and naturalist guide.",
                dressCodeRules = "Earth-tone clothing (olive green, khaki, brown). Avoid bright neon colors or strong perfumes that disturb wildlife.",
                photographyPolicy = "DSLR cameras allowed; special camera lens fees may apply at Forest Department checkpost.",
                scamAlertNotes = "Only book safaris through official Jungle Lodges or Karnataka Forest Department counters at Dammanakatte.",
                cashVsDigitalNotes = "Keep ₹500 cash; network in forest buffer zones is spotty."
            ),
            howToReachTransit = "Drive from Mysuru (2 hours via HD Kote road) or private cab from Mysuru Railway Station.",
            survivalKannadaPhrase = "Forest safari booking Dammanakatte counter ellide?",
            survivalKannadaLipi = "ಫಾರೆಸ್ಟ್ ಸಫಾರಿ ಬುಕಿಂಗ್ ದಮ್ಮನಕಟ್ಟೆ ಕೌಂಟರ್ ಎಲ್ಲಿದೆ?",
            survivalKannadaMeaning = "Where is the Dammanakatte forest safari booking counter?",
            imageUrl = "https://images.unsplash.com/photo-1534188753412-3e26d0d618d6?w=800&auto=format&fit=crop&q=80",
            videoTourUrl = "https://www.youtube.com/results?search_query=kabini+wildlife+safari+jungle+lodges",
            guideInfo = "Expert naturalists and trackers accompany every Jungle Lodges river boat and 4x4 open safari jeep"
        ),

        KarnatakaSpot(
            id = "coorg_madikeri",
            name = "Coorg / Kodagu (Coffee & Dubare Camp)",
            kannadaName = "ಕೊಡಗು (ಮಡಿಕೇರಿ ಮತ್ತು ದುಬಾರೆ ಆನೆ ಶಿಬಿರ)",
            tagline = "The Scotland of India, Emerald Coffee Estates & Elephant Bathing",
            category = TourismCategory.WATERFALLS_NATURE,
            region = KarnatakaRegion.HALE_MYSURU,
            district = "Kodagu District",
            nearestTransitHub = "Airport: Kannur CNN (90 km) / Mangaluru IXE (140 km) / Mysuru MYQ (120 km) | Train: Mysuru Junction (115 km)",
            distanceFromBangaloreKm = 250,
            bestSeason = "October to May (Monsoon June-Sept for lush waterfalls)",
            timings = "Dubare Elephant Camp: 9:00 AM – 11:30 AM (Bathing & Feeding)",
            overview = "Nestled on the slopes of the Western Ghats, Kodagu (Coorg) is celebrated for its lush coffee and cardamom plantations, misty hilltops, martial Kodava warrior culture, and the sacred Talakaveri (birthplace of River Kaveri). At Dubare Elephant Camp on the Kaveri river, travelers can scrub and bathe rescued elephants alongside mahouts.",
            keyHighlights = listOf(
                "Dubare Elephant Camp river scrubbing and feeding sessions",
                "Abbey Falls cascading through spice and coffee plantations",
                "Raja's Seat sunset point overlooking rolling valley views",
                "Namdroling Golden Temple (Tibetan settlement) at Bylakuppe nearby"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Jungle Lodges & Coorg Tourism",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Dubare elephant camp entry (₹100) and river boat crossing bought at counter.",
                badgeTag = "COFFEE HEARTLAND"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 100,
                foreignerFeeInr = 100,
                approxUsdEquivalent = "~$1.20 USD (Dubare Camp)",
                feeIncludes = "Boat crossing across Kaveri river and entrance to elephant interactions.",
                dressCodeRules = "Comfortable walking shoes. Bring clothes you don't mind getting wet during elephant bathing.",
                photographyPolicy = "Standard photography allowed everywhere.",
                scamAlertNotes = "Purchase homemade spices and fresh coffee powder from Kodava cooperative societies in Madikeri town.",
                cashVsDigitalNotes = "UPI QR widely accepted at homestays and restaurants."
            ),
            howToReachTransit = "KSRTC Flybus from Bengaluru Airport direct to Madikeri, or KSRTC Airavat AC bus from Mysuru (2.5 hours).",
            survivalKannadaPhrase = "Dubare aane camp ge boat ticket elli kodthare?",
            survivalKannadaLipi = "ದುಬಾರೆ ಆನೆ ಕ್ಯಾಂಪ್‌ಗೆ ಬೋಟ್ ಟಿಕೆಟ್ ಎಲ್ಲಿ ಕೊಡ್ತಾರೆ?",
            survivalKannadaMeaning = "Where do they issue boat tickets for Dubare elephant camp?"
        ),

        KarnatakaSpot(
            id = "srirangapatna_island",
            name = "Srirangapatna River Island & Fort",
            kannadaName = "ಶ್ರೀರಂಗಪಟ್ಟಣ ನದಿ ದ್ವೀಪ ಮತ್ತು ಕೋಟೆ",
            tagline = "Cauvery River Island Fortress of Tipu Sultan & Sri Ranganatha Shrine",
            category = TourismCategory.ISLANDS,
            region = KarnatakaRegion.HALE_MYSURU,
            district = "Mandya District (15 km from Mysuru)",
            nearestTransitHub = "Airport: Mysuru MYQ (25 km) | Train: Srirangapatna Station (0.5 km)",
            distanceFromBangaloreKm = 125,
            bestSeason = "Year-round (Best: September to March)",
            timings = "6:00 AM – 7:00 PM",
            overview = "An egg-shaped river island enclosed by two branches of the sacred Cauvery River. As the historic fortified capital of Tipu Sultan (the 'Tiger of Mysore') and Hyder Ali, it features the monumental 9th-century Sri Ranganathaswamy Temple, Daria Daulat Bagh (Summer Palace) with Persian frescoes, and the dungeon where British officers were imprisoned.",
            keyHighlights = listOf(
                "Daria Daulat Bagh (Tipu's Teakwood Summer Palace & Museum)",
                "Grand 9th-century Sri Ranganathaswamy Temple in Chola-Hoysala style",
                "Gumbaz (Mausoleum of Tipu Sultan & Hyder Ali)",
                "Historic Water Gate & Obelisk where Tipu fell in the 1799 battle"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "ASI & Karnataka Tourism",
                portalUrl = "https://asi.nic.in",
                bookingRule = "Sri Ranganathaswamy temple is free general entry. Daria Daulat Bagh ASI ticket available on-site or online on asi.nic.in.",
                badgeTag = "RIVER ISLAND FORT"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 25,
                foreignerFeeInr = 300,
                approxUsdEquivalent = "~$3.60 USD (Daria Daulat Bagh Palace entry)",
                feeIncludes = "Entry to Daria Daulat Bagh teakwood palace, royal portrait gallery and landscaped gardens.",
                dressCodeRules = "Modest clothing required inside Sri Ranganathaswamy temple; footwear left at token counter outside.",
                photographyPolicy = "Exterior palace photography permitted; strictly forbidden inside teakwood painted halls to preserve vegetable dye murals.",
                scamAlertNotes = "Beware of unauthorized touts in the parking lot; purchase official museum tickets only at the ASI computerized counter.",
                cashVsDigitalNotes = "UPI QR payments accepted everywhere on the island."
            ),
            howToReachTransit = "All trains between Bengaluru and Mysuru stop at Srirangapatna station, located directly on the island.",
            survivalKannadaPhrase = "Tipu Sultan Summer Palace entry ticket eshtu?",
            survivalKannadaLipi = "ಟಿಪ್ಪು ಸುಲ್ತಾನ್ ಸಮ್ಮರ್ ಪ್ಯಾಲೇಸ್ ಎಂಟ್ರಿ ಟಿಕೆಟ್ ಎಷ್ಟು?",
            survivalKannadaMeaning = "What is the entry ticket price for Tipu Sultan's Summer Palace?"
        ),

        // ========================================================
        // 5. CENTRAL DECCAN & FORTS (BAYALUSEEME)
        // ========================================================
        KarnatakaSpot(
            id = "chitradurga_fort",
            name = "Chitradurga Fort (Kallina Kote)",
            kannadaName = "ಚಿತ್ರದುರ್ಗ ಕೋಟೆ (ಕಲ್ಲಿನ ಕೋಟೆ)",
            tagline = "The Legendary Seven-Walled Fortress of Stone & Onake Obavva",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.BAYALUSEEME,
            district = "Chitradurga District",
            nearestTransitHub = "Airport: Hubballi HBX (200 km) / Bengaluru BLR (210 km) | Train: Chitradurga Station (2 km)",
            distanceFromBangaloreKm = 200,
            bestSeason = "October to February (Avoid peak summer midday)",
            timings = "6:00 AM – 5:30 PM",
            overview = "One of India's most impregnable rock fortresses, built with 7 concentric defensive stone walls (Elu Suttina Kote), 19 gateways, 38 postern gates, and 35 secret subterranean entrances. Famous for the folklore of brave heroine Onake Obavva, who defended the secret crevice against Hyder Ali's invading soldiers wielding a wooden pestle (Onake).",
            keyHighlights = listOf(
                "Seven concentric stone defensive walls winding over granite hills",
                "Onake Obavvana Kindi (the historic stone crevice of valour)",
                "Ancient rainwater harvesting interconnected rock ponds and reservoirs",
                "Hidimbeshwara rock-cut cave temple and massive stone grinding oil mills"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Archaeological Survey of India (ASI)",
                portalUrl = "https://asi.nic.in",
                bookingRule = "Tickets bought at main Rangayyana Bagilu gate or via asi.nic.in portal.",
                badgeTag = "ASI STONE CITADEL"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 25,
                foreignerFeeInr = 300,
                approxUsdEquivalent = "~$3.60 USD",
                feeIncludes = "Access to full 1,500-acre fort complex, gateways, and temples.",
                dressCodeRules = "Good grip trekking shoes mandatory; granite paths can be smooth. Carry plenty of drinking water.",
                photographyPolicy = "Stunning architectural and landscape photography permitted.",
                scamAlertNotes = "Only hire guides registered with the district tourism office inside the main entrance.",
                cashVsDigitalNotes = "UPI QR code accepted at ASI ticket office."
            ),
            howToReachTransit = "KSRTC buses and express trains from Bengaluru, Hubballi, and Davanagere stop right in Chitradurga town.",
            survivalKannadaPhrase = "Onake Obavvana Kindi nodoke dari yavadu?",
            survivalKannadaLipi = "ಒನಕೆ ಓಬವ್ವನ ಕಿಂಡಿ ನೋಡೋಕೆ ದಾರಿ ಯಾವುದು?",
            survivalKannadaMeaning = "Which is the path to see Onake Obavva's historic crevice?"
        ),

        KarnatakaSpot(
            id = "shravanabelagola",
            name = "Shravanabelagola Gommateshwara",
            kannadaName = "ಶ್ರವಣಬೆಳಗೊಳ ಗೊಮ್ಮಟೇಶ್ವರ",
            tagline = "57-Foot Monolithic Bahubali Carved in 981 AD atop Vindhyagiri",
            category = TourismCategory.TEMPLES_HERITAGE,
            region = KarnatakaRegion.BAYALUSEEME,
            district = "Hassan District",
            nearestTransitHub = "Airport: Bengaluru BLR (160 km) | Train: Shravanabelagola Station (2 km)",
            distanceFromBangaloreKm = 145,
            bestSeason = "October to March",
            timings = "6:00 AM – 6:30 PM",
            overview = "The monumental spiritual center of Jainism for over a millennium. Carved from a single block of fine-grained white granite atop the 400-foot Vindhyagiri hill by Ganga commander Chavundaraya in 981 AD, the 57-foot statue of Lord Gommateshwara (Bahubali) is one of the largest free-standing monolithic statues in the world.",
            keyHighlights = listOf(
                "57-foot colossal monolithic statue of Lord Bahubali in meditative kayotsarga posture",
                "Barefoot climb of 650 stone steps cut into Vindhyagiri granite hill",
                "Chandragiri hill with ancient Jain basadis and footprint of Chandragupta Maurya",
                "Bhandara Basadi and Tyagada Kamba stone pillar carvings"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Shravanabelagola Digambar Jain Matha",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Entry is 100% free. Doli (palanquin chair service) available for elderly visitors (₹1,500–₹2,000 fixed).",
                badgeTag = "ANCIENT MONOLITH"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 0,
                foreignerFeeInr = 0,
                approxUsdEquivalent = "Free Entry",
                feeIncludes = "Access to Vindhyagiri and Chandragiri hills and temples.",
                dressCodeRules = "STRICT: Climb must be done strictly BAREFOOT (or with special grip socks). No leather belts or wallets allowed on the hill.",
                photographyPolicy = "Photography permitted in open courtyard; follow instructions of Matha volunteers.",
                scamAlertNotes = "Fix doli (palanquin chair) rates only at the official temple counter at the base of the hill.",
                cashVsDigitalNotes = "UPI accepted at footwear deposit and baseline coconut vendors."
            ),
            howToReachTransit = "Direct train on Bengaluru-Hassan line stops at Shravanabelagola station, or 2.5-hour drive via NH 75.",
            survivalKannadaPhrase = "Bettada mele hogalu doli charge eshtu?",
            survivalKannadaLipi = "ಬೆಟ್ಟದ ಮೇಲೆ ಹೋಗಲು ಡೋಲಿ ಚಾರ್ಜ್ ಎಷ್ಟು?",
            survivalKannadaMeaning = "What is the official doli palanquin charge to go up the hill?"
        )
    )

    // ========================================================
    // REGIONAL DIALECTS & FRIENDLY LOCAL GREETINGS
    // ========================================================
    val regionalDialects: List<RegionalDialectInfo> = listOf(
        RegionalDialectInfo(
            region = KarnatakaRegion.UTTARA_KARNATAKA,
            dialectName = "Uttara Karnataka Kannada (Dharwad / Belagavi / Hampi)",
            commonGreeting = "ಏನ್ ರೀ ಸಮಾಚಾರ? (En ri samachara? - What is the news?)",
            friendlyPhrases = listOf(
                Pair("ಬರಿ ಭೈಯಾ / Bari bhaiya", "Come brother (Warm invitation)"),
                Pair("ಹೌದ್ರೀ / Haudri", "Yes indeed / Exactly"),
                Pair("ಚಂದ ಐತಿ / Chanda aithi", "It is very beautiful / great quality"),
                Pair("ಖರೆ ಹೇಳ್ರಿ / Khare helri", "Tell the genuine truth / fair price"),
                Pair("ಜೋಳದ ರೊಟ್ಟಿ ಊಟ ಮಾಡ್ರಿ / Jolada Rotti oota madori", "Have authentic Jolada Rotti meal")
            ),
            regionalCuisines = listOf("Jolada Rotti", "Badanekayi Ennegai", "Shenga Chutney", "Dharwad Peda", "Gokak Karadantu"),
            localTip = "People here are exceptionally warm and speak with distinct musical elongated vowels. Address elders respectfully as 'Ri'."
        ),
        RegionalDialectInfo(
            region = KarnatakaRegion.KARAVALI,
            dialectName = "Coastal Kundagannada & Tulu (Mangaluru / Udupi / Gokarna)",
            commonGreeting = "ನಮಸ್ಕಾರ! ಎಂಚ ಉಲ್ಲರ್? (Encha ullar? - How are you in Tulu)",
            friendlyPhrases = listOf(
                Pair("ಮಸ್ತ್ ಶೋಕಿ ಉಂಡು / Mast shoki undu", "It is extremely wonderful / scenic"),
                Pair("ಖಾರ ಬೇಡ, ಕಮ್ಮಿ ಮಾಡಿ / Khaara beda, kammi maadi", "No spicy please, make it mild"),
                Pair("ಮೀನ್ ಫ್ರೈ ಕೊಡಿ / Meen fry kodi", "Please serve fresh coastal fish fry"),
                Pair("ನೀರು ದೋಸೆ ಇದೆಯಾ? / Neer dose ideya?", "Do you have fresh Neer Dosa?")
            ),
            regionalCuisines = listOf("Neer Dosa", "Mangalore Buns", "Kori Rotti", "Goli Baje", "Anjal Fish Fry", "Patrode"),
            localTip = "Coastal auto drivers are among the most disciplined and honest in India; meters are almost universally respected without haggling!"
        ),
        RegionalDialectInfo(
            region = KarnatakaRegion.MALNAD,
            dialectName = "Malnad Kannada (Chikkamagaluru / Shivamogga / Coorg)",
            commonGreeting = "ಹೇಗಿದ್ದೀರಾ? ಊಟ ಆಯ್ತಾ? (Heggiddira? Oota aaytha? - How are you? Did you have food?)",
            friendlyPhrases = listOf(
                Pair("ಫಿಲ್ಟರ್ ಕಾಫಿ ಸೂಪರ್ / Filter coffee super", "The estate filter coffee is superb"),
                Pair("ಮಳೆ ಬರ್ತಿದೆ, ರೇನ್‌ಕೋಟ್ ಕೊಡಿ / Male barthide, raincoat kodi", "It is raining, please lend a raincoat"),
                Pair("ಅಕ್ಕಿ ರೊಟ್ಟಿ ಕೊಡಿ / Akki rotti kodi", "Please give traditional rice roti")
            ),
            regionalCuisines = listOf("Akki Rotti", "Jackfruit Chips", "Thambuli", "Bamboo Shoot (Kalale) Curry", "Estate Filter Coffee"),
            localTip = "Homestays are the heart of Malnad; hosts treat guests like family. Always remove outdoor footwear before entering homestay verandas."
        ),
        RegionalDialectInfo(
            region = KarnatakaRegion.HALE_MYSURU,
            dialectName = "Royal South & Mysore Kannada (Mysuru / Mandya)",
            commonGreeting = "ಬನ್ನಿ ಸಾರ್, ಚೆನ್ನಾಗಿದ್ದೀರಾ? (Banni saar, chennagidira? - Welcome sir, are you well?)",
            friendlyPhrases = listOf(
                Pair("ಮೈಸೂರ್ ಪಾಕ್ ಫ್ರೆಶ್ ಇದೆಯಾ? / Mysore Pak fresh ideya?", "Is the Mysore Pak freshly prepared?"),
                Pair("ಚಾಮುಂಡಿ ಬೆಟ್ಟಕ್ಕೆ ಹೋಗೋಣ / Chamundi betta-ge hogona", "Let us go to Chamundi Hill"),
                Pair("ಒಂದು ಮದ್ದೂರ್ ವಡೆ ಕೊಡಿ / Ondu Maddur Vade kodi", "Please give one crispy Maddur Vada")
            ),
            regionalCuisines = listOf("Original Mysore Pak", "Maddur Vada", "Mysore Masala Dosa", "Thatte Idli", "Ragi Mudde with Saaru"),
            localTip = "Mysore Kannada is soft-spoken, polite, and royal. Use 'Banni' (Please come) and 'Hogi' (Please go)."
        )
    )

    // ========================================================
    // STATEWIDE CITY TRANSPORT & AUTO TARIFF BENCHMARKS
    // ========================================================
    val cityTariffs: List<CityTransportTariff> = listOf(
        CityTransportTariff(
            cityName = "Mangaluru & Udupi",
            baseFare = "₹40.00 (First 1.5 km)",
            perKmRate = "₹20.00 / km",
            localAdvice = "Ranked #1 for auto driver honesty in Karnataka. Drivers automatically turn on digital meters without asking. Night fare (+50%) applies 10 PM – 5 AM.",
            sharedAutoOption = "Local city buses (private blue buses in Mangalore) run every 3 minutes for ₹10–₹25."
        ),
        CityTransportTariff(
            cityName = "Mysuru",
            baseFare = "₹30.00 (First 2.0 km)",
            perKmRate = "₹15.00 / km",
            localAdvice = "At Mysuru Railway Station, use the official Pre-Paid Auto Booth near Exit 1 to avoid haggling. Tongas (horse buggies) near Palace charge ₹150–₹250.",
            sharedAutoOption = "KSRTC city circular buses cover Chamundi Hill, Zoo, and KRS Brindavan Gardens."
        ),
        CityTransportTariff(
            cityName = "Hubballi & Dharwad",
            baseFare = "₹30.00 (First 1.8 km)",
            perKmRate = "₹16.00 / km",
            localAdvice = "Hubballi-Dharwad features the ultra-fast HDBRTS (Bus Rapid Transit System) dedicated corridor linking both twin cities every 2 minutes.",
            sharedAutoOption = "Shared 'Tum-Tum' 8-seater autos operate along highway corridors for flat ₹15–₹25."
        ),
        CityTransportTariff(
            cityName = "Hampi & Hospet",
            baseFare = "Day-Hire: ₹800 – ₹1,200",
            perKmRate = "Flat tour packages",
            localAdvice = "Hampi monuments are spread over 25 km. Hire an auto for a full day (₹800–₹1,000 for Vijaya Vittala, Royal Enclosure, Virupaksha, and sunset point).",
            sharedAutoOption = "Bicycles (₹150/day) and electric scooters (₹400/day) are available for rent in Hampi bazaar."
        )
    )

    fun getSpotsByCategory(category: TourismCategory): List<KarnatakaSpot> {
        return spots.filter { it.category == category }
    }

    fun getSpotsByRegion(region: KarnatakaRegion): List<KarnatakaSpot> {
        return if (region == KarnatakaRegion.ALL) spots
        else spots.filter { it.region == region }
    }

    fun getSpotById(id: String): KarnatakaSpot? {
        return spots.firstOrNull { it.id == id }
    }
}
