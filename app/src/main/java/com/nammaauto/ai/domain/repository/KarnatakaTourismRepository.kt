package com.nammaauto.ai.domain.repository

import com.nammaauto.ai.domain.model.ForeignerPricingGuidance
import com.nammaauto.ai.domain.model.KarnatakaSpot
import com.nammaauto.ai.domain.model.OfficialBookingInfo
import com.nammaauto.ai.domain.model.TourismCategory

object KarnatakaTourismRepository {

    val spots: List<KarnatakaSpot> = listOf(
        // ==========================================
        // 1. TEMPLES & HERITAGE
        // ==========================================
        KarnatakaSpot(
            id = "hampi_unesco",
            name = "Hampi UNESCO World Heritage Site",
            kannadaName = "ಹಂಪಿ ವಿಶ್ವ ಪರಂಪರೆಯ ತಾಣ",
            tagline = "The Golden Ruins of the Vijayanagara Empire",
            category = TourismCategory.TEMPLES_HERITAGE,
            district = "Vijayanagara (Near Hospet)",
            distanceFromBangaloreKm = 345,
            bestSeason = "October to March (Winter cool weather)",
            timings = "6:00 AM – 6:00 PM (Daily)",
            overview = "Spectacular 14th-century capital of the Vijayanagara Empire spread across 4,100 hectares of otherworldly boulder landscapes and Tungabhadra riverbanks. Highlights include the Virupaksha Temple, the iconic monolithic Stone Chariot at Vijaya Vittala, and Lotus Mahal.",
            keyHighlights = listOf(
                "Stone Chariot & Musical Pillars at Vijaya Vittala",
                "Active 7th-century Virupaksha Temple darshan",
                "Bouldering and coracle (Dongdi) boat ride on Tungabhadra River",
                "Sunset view over boulders from Matanga Hill"
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
                approxUsdEquivalent = "~.20 USD",
                feeIncludes = "Entry to Vijaya Vittala complex, Lotus Mahal & ASI Archaeological Museum.",
                dressCodeRules = "Modest clothing required. Shoulders and knees must be covered inside active Virupaksha temple. Remove footwear at temple entrances.",
                photographyPolicy = "Still photography allowed without fee. Drone videography strictly banned without ASI Delhi written clearance.",
                scamAlertNotes = "Only hire guides wearing the official Ministry of Tourism green badge. Coracle rides should not exceed ₹200–₹300 per person.",
                cashVsDigitalNotes = "UPI works in Hampi bazaar, but keep ₹1,000 cash for coracle rides and auto rickshaws on the river's Hippie Island side."
            ),
            howToReachTransit = "Overnight Hampi Express train from KSR Bengaluru to Hospet Junction (12km away), followed by local bus or ₹200 auto ride.",
            survivalKannadaPhrase = "Hampi Vijaya Vittala temple ge hogalu auto eshtu?",
            survivalKannadaLipi = "ಹಂಪಿ ವಿಜಯ ವಿಠ್ಠಲ ದೇವಸ್ಥಾನಕ್ಕೆ ಹೋಗಲು ಆಟೋ ಎಷ್ಟು?",
            survivalKannadaMeaning = "How much for an auto to Hampi Vijaya Vittala Temple?"
        ),

        KarnatakaSpot(
            id = "belur_halebeedu",
            name = "Belur & Halebeedu Hoysala Temples",
            kannadaName = "ಬೇಲೂರು ಮತ್ತು ಹಳೇಬೀಡು ಹೊಯ್ಸಳ ದೇವಾಲಯಗಳು",
            tagline = "UNESCO 2023 Inscribed Masterpieces of Soapstone Sculpture",
            category = TourismCategory.TEMPLES_HERITAGE,
            district = "Hassan District",
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
                approxUsdEquivalent = "~.60 USD (Halebeedu) / Belur is Free",
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
            id = "murudeshwar_temple",
            name = "Murudeshwar Shiva Temple & Raja Gopura",
            kannadaName = "ಮುರುಡೇಶ್ವರ ಶಿವ ದೇವಸ್ಥಾನ ಮತ್ತು ರಾಜ ಗೋಪುರ",
            tagline = "World's 2nd Tallest Shiva Statue Overlooking the Arabian Sea",
            category = TourismCategory.TEMPLES_HERITAGE,
            district = "Uttara Kannada District",
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
                approxUsdEquivalent = "~bash.24 USD (Lift ticket)",
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
            id = "mysore_palace",
            name = "Mysore Palace (Amba Vilas)",
            kannadaName = "ಮೈಸೂರು ಅರಮನೆ (ಅಂಬಾ ವಿಲಾಸ)",
            tagline = "The Jewel of the Wodeyars & 100,000-Bulb Illumination",
            category = TourismCategory.TEMPLES_HERITAGE,
            district = "Mysuru District",
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
                approxUsdEquivalent = "~2.00 USD (Includes Multilingual Audio Guide)",
                feeIncludes = "Foreigner ticket includes personal high-definition audio headset guide in English, French, German, Spanish, Japanese, or Italian.",
                dressCodeRules = "Footwear strictly prohibited inside royal residential chambers (free token shoe stands at entrance).",
                photographyPolicy = "Still photography allowed without flash. Video cameras require special token.",
                scamAlertNotes = "Avoid auto/tonga drivers who insist on taking you to private 'handicraft emporiums' instead of the palace; they earn 40% commission.",
                cashVsDigitalNotes = "Official counters accept international Visa/Mastercard and UPI QR."
            ),
            howToReachTransit = "Vande Bharat Express or Tipu Express train from KSR Bengaluru to Mysuru Junction (2 hours), then ₹50 auto to Palace North Gate.",
            survivalKannadaPhrase = "Palace illumination raathri eshtu gantege shuru aagutthe?",
            survivalKannadaLipi = "ಅರಮನೆ ಇಲ್ಯುಮಿನೇಷನ್ ರಾತ್ರಿ ಎಷ್ಟು ಗಂಟೆಗೆ ಶುರು ಆಗುತ್ತೆ?",
            survivalKannadaMeaning = "At what time does the Palace illumination begin tonight?"
        ),

        // ==========================================
        // 2. ADVENTURE SPORTS
        // ==========================================
        KarnatakaSpot(
            id = "dandeli_rafting",
            name = "Dandeli Kali River White-Water Rafting",
            kannadaName = "ದಾಂಡೇಲಿ ಕಾಳಿ ನದಿ ವೈಟ್-ವಾಟರ್ ರಾಫ್ಟಿಂಗ್",
            tagline = "Grade 3 & 4 Thrilling Rapids in the Dense Western Ghats Rainforest",
            category = TourismCategory.ADVENTURE_SPORTS,
            district = "Uttara Kannada District",
            distanceFromBangaloreKm = 460,
            bestSeason = "October to May (Regulated by Supa Dam water discharge)",
            timings = "8:30 AM – 3:30 PM",
            overview = "Dandeli is South India's capital for white-water river rafting. Guided by certified swift-water rescue river masters, the Kali River offers exhilarating Class II to Class III+ rapids with names like 'Adi's Delusion', 'The Piper', and 'Stanley's Squeeze' surrounded by the hornbill-rich Dandeli-Anshi Tiger Reserve.",
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
                approxUsdEquivalent = "~7.00 – 8.00 USD",
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

        KarnatakaSpot(
            id = "netrani_scuba",
            name = "Netrani Island Scuba Diving & Coral Snorkeling",
            kannadaName = "ನೇತ್ರಾಣಿ ದ್ವೀಪ ಸ್ಕೂಬಾ ಡೈವಿಂಗ್ ಮತ್ತು ಕೋರಲ್ ರೀಫ್",
            tagline = "The Heart-Shaped Coral Reef Island of the Arabian Sea",
            category = TourismCategory.ADVENTURE_SPORTS,
            district = "Off Murudeshwar Coast (19 km offshore)",
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
                approxUsdEquivalent = "~2.00 USD (Includes gear, boat & underwater footage)",
                feeIncludes = "Complete scuba gear (wetsuit, regulator, BCD, tank, fins), 45-min boat ride, training briefing, and 30-min underwater dive.",
                dressCodeRules = "Swimwear, rashguards or comfortable swimming gear. Bring change of dry clothes.",
                photographyPolicy = "Underwater video and photos are included free in your package and transferred directly to your smartphone via OTG/AirDrop.",
                scamAlertNotes = "Ensure your operator is verified with certified PADI/SSI instructors. Non-divers can accompany on the boat for ₹1,999.",
                cashVsDigitalNotes = "All primary dive operators accept GPay/PhonePe and credit cards at Murudeshwar beach offices."
            ),
            howToReachTransit = "Reach Murudeshwar via overnight express train from Bengaluru; dive centers gather at Murudeshwar Temple Beach Jetty.",
            survivalKannadaPhrase = "Netrani boat ride mathu scuba diving ge permission ideya?",
            survivalKannadaLipi = "ನೇತ್ರಾಣಿ ಬೋಟ್ ರೈಡ್ ಮತ್ತು ಸ್ಕೂಬಾ ಡೈವಿಂಗ್‌ಗೆ ಪರ್ಮಿಷನ್ ಇದೆಯಾ?",
            survivalKannadaMeaning = "Is boat ride and scuba diving permitted today at Netrani?"
        ),

        KarnatakaSpot(
            id = "ramanagara_climbing",
            name = "Ramanagara Rock Climbing & Bouldering",
            kannadaName = "ರಾಮನಗರ ರಾಕ್ ಕ್ಲೈಂಬಿಂಗ್ ಮತ್ತು ಬೌಲ್ಡರಿಂಗ್",
            tagline = "Granite Monoliths of Sholay & Adventure Hub Just 50km from Bangalore",
            category = TourismCategory.ADVENTURE_SPORTS,
            district = "Ramanagara District",
            distanceFromBangaloreKm = 48,
            bestSeason = "September to March (Avoid scorching mid-day summer sun)",
            timings = "6:00 AM – 5:00 PM",
            overview = "World-renowned bouldering and multi-pitch rock climbing crags carved from granite hills, famous as the shooting location for the Bollywood classic 'Sholay'. Certified climbing clubs offer top-rope climbing, rappelling down 150-foot vertical faces, and cave explorations.",
            keyHighlights = listOf(
                "Single-pitch and multi-pitch technical climbing routes",
                "150-ft cliff rappelling and zip-lining over rocky valley",
                "Ramadevara Betta Vulture Sanctuary visit (India's only vulture reserve)",
                "Mysore-Bangalore Expressway quick 1-hour access"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "General Adventure Booking / Forest Dept",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Climbing on Ramadevara Betta requires entry ticket at Forest checkpost (₹25). Certified climbing gear is essential.",
                badgeTag = "BOULDERING CAPITAL"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 800,
                foreignerFeeInr = 1200,
                approxUsdEquivalent = "~0.00 – 4.00 USD (Guided climbing session)",
                feeIncludes = "Harness, climbing shoes, dynamic ropes, chalk bag, and safety belayer.",
                dressCodeRules = "Flexible athletic track pants and moisture-wicking t-shirts. Hard-grip trekking shoes for approach hike.",
                photographyPolicy = "Drone cameras prohibited inside Vulture Sanctuary zone to protect endangered Long-billed Vultures.",
                scamAlertNotes = "Do not attempt free-soloing without safety ropes or certified guides; granite rocks can become slippery post-monsoon.",
                cashVsDigitalNotes = "UPI QR codes work everywhere along Ramanagara highway."
            ),
            howToReachTransit = "KSR Bengaluru to Ramanagara via suburban MEMU train (45 mins) or drive along Bengaluru-Mysuru Expressway.",
            survivalKannadaPhrase = "Ramadevara Betta rock climbing checkpost ellide?",
            survivalKannadaLipi = "ರಾಮದೇವರ ಬೆಟ್ಟ ರಾಕ್ ಕ್ಲೈಂಬಿಂಗ್ ಚೆಕ್‌ಪೋಸ್ಟ್ ಎಲ್ಲಿದೆ?",
            survivalKannadaMeaning = "Where is the Ramadevara Betta climbing checkpost?"
        ),

        // ==========================================
        // 3. TREKKING & NATURE TRAILS (ARANYA VIHARA)
        // ==========================================
        KarnatakaSpot(
            id = "kudremukh_trek",
            name = "Kudremukh Peak Trek",
            kannadaName = "ಕುದುರೆಮುಖ ಶಿಖರ ಚಾರಣ",
            tagline = "Horse-Face Peak & Velvet Green Shola Grasslands (1,894m)",
            category = TourismCategory.TREKKING_TRAILS,
            district = "Chikkamagaluru District (Kudremukh National Park)",
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
                approxUsdEquivalent = "~.90 USD (Forest Permit) + ~₹100 local guide share",
                feeIncludes = "Official Forest entry permit, environmental cess, and mandatory certified local eco-guide assigned to batches of 10.",
                dressCodeRules = "Full-length trekking pants (essential for leech protection during monsoon), moisture-wicking shirts, and sturdy hiking boots.",
                photographyPolicy = "Personal DSLRs allowed. Drones strictly prohibited and confiscated at checkpost under Wildlife Protection Act.",
                scamAlertNotes = "CRITICAL: Never believe homestays promising 'offline entrance tickets at gate'. All tickets must be booked on aranyavihaara.karnataka.gov.in in advance.",
                cashVsDigitalNotes = "ZERO mobile signal (Airtel/Jio) at base checkpost (Balagal/Mullodi). Carry ₹1,000 cash for 4x4 Jeep ride (₹500/head roundtrip) and leech socks."
            ),
            howToReachTransit = "Overnight KSRTC bus from Bengaluru to Kalasa (8 hours). From Kalasa take local bus to Balagal, then 4x4 Jeep to Mullodi base camp.",
            survivalKannadaPhrase = "Aranya Vihaara booking permit ide, Mullodi checkpost yavadu?",
            survivalKannadaLipi = "ಅರಣ್ಯ ವಿಹಾರ ಬುಕಿಂಗ್ ಪರ್ಮಿಟ್ ಇದೆ, ಮುಳ್ಳೋಡಿ ಚೆಕ್‌ಪೋಸ್ಟ್ ಯಾವುದು?",
            survivalKannadaMeaning = "I have the Aranya Vihaara booking permit, where is the Mullodi checkpost?"
        ),

        KarnatakaSpot(
            id = "skandagiri_trek",
            name = "Skandagiri Sunrise Trek (Kalavara Durga)",
            kannadaName = "ಸ್ಕಂದಗಿರಿ ಸೂರ್ಯೋದಯ ಚಾರಣ (ಕಳವಾರ ದುರ್ಗ)",
            tagline = "Walk Above a Sea of Clouds Just 65km from Bangalore",
            category = TourismCategory.TREKKING_TRAILS,
            district = "Chikkaballapura District",
            distanceFromBangaloreKm = 62,
            bestSeason = "October to March (Winter months offer thick cloud inversions)",
            timings = "Morning Batch: 4:00 AM – 9:00 AM (Regulated entry)",
            overview = "An ancient hill fortress (Kalavara Durga) standing at 1,450m that witnessed battles between Tipu Sultan and the British. Famous for its breathtaking 5:30 AM sunrise spectacle, where hikers look down upon a surreal ocean of white clouds stretching into the horizon.",
            keyHighlights = listOf(
                "4:00 AM early morning hike under a canopy of stars",
                "Hypnotic cloud bed (sea of clouds) inversion below your feet at sunrise",
                "Historic fort stone walls and ruined temple at the summit plateau",
                "Quick morning weekend getaway returning to Bangalore by noon"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Aranya Vihaara (Karnataka Forest Department)",
                portalUrl = "https://aranyavihaara.karnataka.gov.in",
                bookingRule = "Permits must be booked in advance via aranyavihaara.karnataka.gov.in (Trekking section). Night walking without forest escort is prohibited.",
                badgeTag = "ARANYA VIHARA PERMIT"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 250,
                foreignerFeeInr = 250,
                approxUsdEquivalent = "~.00 USD (Forest Permit)",
                feeIncludes = "Official Forest entry permit and trail access. Parking fee extra at base (₹50).",
                dressCodeRules = "Warm layers or windcheater jacket (summit gets very windy and chilly before dawn). Headlamp or torch mandatory!",
                photographyPolicy = "Still photography and timelapse phones highly popular; ensure phone is 100% charged before starting.",
                scamAlertNotes = "Only enter through the official Forest Department checkpost at Kalavara village. Avoid unauthorized trails through private farmland.",
                cashVsDigitalNotes = "Forest checkpost QR scanner accepts UPI, but keep ₹100 cash for village parking and hot tea at the base."
            ),
            howToReachTransit = "Drive from Bengaluru via Bellary Road / Airport Highway to Chikkaballapura (1 hour 15 mins) to reach Kalavara village base.",
            survivalKannadaPhrase = "Skandagiri base camp ge dari yavadu?",
            survivalKannadaLipi = "ಸ್ಕಂದಗಿರಿ ಬೇಸ್ ಕ್ಯಾಂಪ್‌ಗೆ ದಾರಿ ಯಾವುದು?",
            survivalKannadaMeaning = "Which is the way to Skandagiri base camp?"
        ),

        KarnatakaSpot(
            id = "kumara_parvatha",
            name = "Kumara Parvatha Trek (Pushpagiri Sanctuary)",
            kannadaName = "ಕುಮಾರ ಪರ್ವತ ಚಾರಣ (ಪುಷ್ಪಗಿರಿ ವನ್ಯಜೀವಿ ಧಾಮ)",
            tagline = "The Toughest & Most Rewarding 22km Mountain Trek in Karnataka",
            category = TourismCategory.TREKKING_TRAILS,
            district = "Dakshina Kannada / Kodagu Border",
            distanceFromBangaloreKm = 280,
            bestSeason = "October to February (Avoid heavy monsoon due to flash runoff)",
            timings = "6:00 AM start from Kukke / Beedahalli base. Summit closes at 1:00 PM for descent",
            overview = "Standing at 1,712 meters, Kumara Parvatha is Karnataka's legendary endurance trek. Starting from behind the sacred Kukke Subramanya temple or the Somwarpet Beedahalli route, the trail challenges hikers with steep rocky ascents through the Shesha Parvatha cliff edge before reaching the Pushpagiri summit.",
            keyHighlights = listOf(
                "Historic pitstop at Bhattara Mane for traditional warm malnad meals",
                "Dramatic Shesha Parvatha vertical rock wall ridge walk",
                "Unmatched panoramic views across Kodagu and Dakshina Kannada hills",
                "Pristine virgin evergreen rainforest of Pushpagiri Wildlife Sanctuary"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Aranya Vihaara (Karnataka Forest Department)",
                portalUrl = "https://aranyavihaara.karnataka.gov.in",
                bookingRule = "Mandatory online registration on aranyavihaara.karnataka.gov.in. Daily quota capped to protect Pushpagiri sanctuary ecology.",
                badgeTag = "ARANYA VIHARA PERMIT"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 350,
                foreignerFeeInr = 500,
                approxUsdEquivalent = "~.20 – .00 USD",
                feeIncludes = "Forest entry permit and eco-development committee trail maintenance fee.",
                dressCodeRules = "Hardened trekking shoes with deep treads essential; 2.5 liters of drinking water per person is mandatory.",
                photographyPolicy = "Standard photography allowed. Camping at summit is strictly banned by high court directive to avoid littering.",
                scamAlertNotes = "Do not purchase single-use plastic bottles; Forest officers conduct thorough bag inspections at the Girigadde checkpost.",
                cashVsDigitalNotes = "Keep ₹500 cash for traditional lunch at Bhattara Mane (approx ₹150) and hydration buttermilk."
            ),
            howToReachTransit = "KSRTC overnight Rajahamsa/Airavat bus from Majestic Bengaluru to Kukke Subramanya (7.5 hours). Base trail begins 1.5 km from temple.",
            survivalKannadaPhrase = "Bhattara Mane oota ge booking beka?",
            survivalKannadaLipi = "ಭಟ್ಟರ ಮನೆ ಊಟಕ್ಕೆ ಬುಕಿಂಗ್ ಬೇಕಾ?",
            survivalKannadaMeaning = "Is advance booking required for food at Bhattara Mane?"
        ),

        // ==========================================
        // 4. COASTAL BEACHES
        // ==========================================
        KarnatakaSpot(
            id = "gokarna_om_beach",
            name = "Om Beach & Kudle Beach (Gokarna)",
            kannadaName = "ಓಂ ಬೀಚ್ ಮತ್ತು ಕುಡ್ಲೆ ಬೀಚ್ (ಗೋಕರ್ಣ)",
            tagline = "Sacred Natural ॐ Shaped Coastline & Golden Cliffside Hikes",
            category = TourismCategory.BEACHES,
            district = "Uttara Kannada District",
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
            howToReachTransit = "Overnight train from Bengaluru to Gokarna Road Station (10 km away) or KSRTC Non-AC Sleeper bus to Gokarna bus stand.",
            survivalKannadaPhrase = "Om Beach inda Kudle Beach ge auto fare eshtu?",
            survivalKannadaLipi = "ಓಂ ಬೀಚ್‌ನಿಂದ ಕುಡ್ಲೆ ಬೀಚ್‌ಗೆ ಆಟೋ ಫೇರ್ ಎಷ್ಟು?",
            survivalKannadaMeaning = "What is the auto fare from Om Beach to Kudle Beach?"
        ),

        KarnatakaSpot(
            id = "malpe_sea_walk",
            name = "Malpe Beach & 500m Ocean Sea Walk",
            kannadaName = "ಮಲ್ಪೆ ಬೀಚ್ ಮತ್ತು ಸೀ ವಾಕ್ (ಉಡುಪಿ)",
            tagline = "Silvery Sands, Water Sports & Gateway to St. Mary's Island",
            category = TourismCategory.BEACHES,
            district = "Udupi District",
            distanceFromBangaloreKm = 405,
            bestSeason = "October to April",
            timings = "Beach: 24/7 | Sea Walk: 6:00 AM – 8:00 PM | Water Sports: 9:00 AM – 6:00 PM",
            overview = "Malpe is Karnataka's premier family beach hub located 6 km from Udupi temple town. It features pristine white sand, 24/7 beach safety patrol, speed boats, jet skis, parasailing, and the iconic Malpe Sea Walk — a 500-meter promenade extending right into the crashing waves of the Arabian Sea.",
            keyHighlights = listOf(
                "Malpe Sea Walk promenade extending into the open ocean",
                "Ferry boat jetty connecting directly to St. Mary's Island",
                "Parasailing, banana rides, speedboats, and camel rides",
                "Authentic Udupi fish fry shacks (Kaanane, Anjal, Prawns) along beach avenue"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Malpe Beach Development Committee (Govt of Karnataka)",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Beach access is free. Sea Walk ticket ₹20 at gate. Water sports tickets bought at Malpe central counters.",
                badgeTag = "BLUE FLAG RECOGNISED"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 20,
                foreignerFeeInr = 20,
                approxUsdEquivalent = "~bash.24 USD (Sea Walk Entry)",
                feeIncludes = "Access to 500-meter sea walk jetty with ocean viewing gazebos.",
                dressCodeRules = "Casual beachwear permitted. Clean beach initiative strictly penalizes glass bottles.",
                photographyPolicy = "Photography encouraged; popular for spectacular sunset portraits.",
                scamAlertNotes = "Buy water sports tickets strictly from the official Malpe Beach Development Board counter to ensure life jacket compliance.",
                cashVsDigitalNotes = "UPI and cards widely accepted at all official counters and restaurants."
            ),
            howToReachTransit = "Overnight train or KSRTC Airavat AC bus from Bengaluru to Udupi (8 hours), then local bus or ₹100 auto to Malpe Beach.",
            survivalKannadaPhrase = "St. Mary's Island ge boat ticket counter ellide?",
            survivalKannadaLipi = "ಸೇಂಟ್ ಮೇರಿಸ್ ಐಲ್ಯಾಂಡ್‌ಗೆ ಬೋಟ್ ಟಿಕೆಟ್ ಕೌಂಟರ್ ಎಲ್ಲಿದೆ?",
            survivalKannadaMeaning = "Where is the boat ticket counter for St. Mary's Island?"
        ),

        KarnatakaSpot(
            id = "maravanthe_beach",
            name = "Maravanthe Beach Coastal Highway (NH 66)",
            kannadaName = "ಮರವಂತೆ ಬೀಚ್ ಕರಾವಳಿ ಹೆದ್ದಾರಿ (NH 66)",
            tagline = "The Only Spot in India Where Sea & River Flow Parallel on Either Side",
            category = TourismCategory.BEACHES,
            district = "Udupi District (Near Kundapura)",
            distanceFromBangaloreKm = 440,
            bestSeason = "October to March (Mesmerizing at sunset)",
            timings = "Open 24 Hours along National Highway 66",
            overview = "Ranked among the most scenic coastal road stretches in Asia. For over 1 kilometer, National Highway 66 runs on a narrow strip of land with the roaring turquoise Arabian Sea on the left and the calm freshwater Souparnika River flowing parallel on the right against a backdrop of the Kodachadri mountain hills.",
            keyHighlights = listOf(
                "Unbelievable visual perspective of roaring ocean on one side and tranquil river on the other",
                "Designated viewing bays and highway parking spots for sunset photography",
                "Souparnika river boating and nearby Maraswami Temple visit",
                "Stopover destination en route between Udupi and Murudeshwar/Gokarna"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "National Highway Public Sightseeing",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Open highway viewing stretch with free roadside parking corridors.",
                badgeTag = "SCENIC HIGHWAY",
                hasOnlineBooking = false
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 0,
                foreignerFeeInr = 0,
                approxUsdEquivalent = "Free Public Viewing",
                feeIncludes = "Free public roadside walkway and viewpoint.",
                dressCodeRules = "Casual road trip attire.",
                photographyPolicy = "Unrestricted photography; use safe designated pedestrian overpasses to cross the highway.",
                scamAlertNotes = "Do not venture into the sea here as the undertow is extremely violent; view from the sea wall.",
                cashVsDigitalNotes = "Roadside tender coconut vendors and seafood shacks accept GPay / cash."
            ),
            howToReachTransit = "Drive along NH 66 from Udupi (50 km north) or take express bus from Kundapura.",
            survivalKannadaPhrase = "Maravanthe viewpoint alli car nillisoke jaga ideya?",
            survivalKannadaLipi = "ಮರವಂತೆ ವ್ಯೂಪಾಯಿಂಟ್‌ನಲ್ಲಿ ಕಾರ್ ನಿಲ್ಲಿಸೋಕೆ ಜಾಗ ಇದೆಯಾ?",
            survivalKannadaMeaning = "Is there parking space to stop the car at Maravanthe viewpoint?"
        ),

        // ==========================================
        // 5. ISLANDS TO VISIT
        // ==========================================
        KarnatakaSpot(
            id = "st_marys_island",
            name = "St. Mary's Island (Columnar Basaltic Rocks)",
            kannadaName = "ಸೇಂಟ್ ಮೇರಿಸ್ ದ್ವೀಪ (ಹೆಕ್ಸಾಗೋನಲ್ ಬಸಾಲ್ಟ್ ಕಲ್ಲುಗಳು)",
            tagline = "88-Million-Year-Old Geological Wonder of Hexagonal Lava Pillars",
            category = TourismCategory.ISLANDS,
            district = "6 km off Malpe Coast (Udupi)",
            distanceFromBangaloreKm = 410,
            bestSeason = "October to May (Strictly closed during June–September monsoons)",
            timings = "Ferries operate 9:00 AM – 5:00 PM (Last boat return from island: 5:45 PM)",
            overview = "Declared a National Geological Monument by the Geological Survey of India. Legend says Vasco da Gama landed here in 1498 and named it after Mother Mary. The island is world-famous for its unique hexagonal columnar basaltic lava columns formed 88 million years ago when Madagascar separated from the Indian tectonic plate.",
            keyHighlights = listOf(
                "Walking among the rare hexagonal columnar basaltic lava cliffs",
                "Pristine turquoise clear lagoon water (one of South India's clearest)",
                "Seashell beach covered with billions of polished whole shells",
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
                approxUsdEquivalent = "~.80 USD (Round-trip Ferry Boat + Island Entry)",
                feeIncludes = "Two-way motorized ferry transfer from Malpe Jetty, life jacket, and island environmental upkeep fee.",
                dressCodeRules = "Footwear with good grip recommended for walking on basalt rock surfaces; flip-flops can slip.",
                photographyPolicy = "Still and video photography allowed. Drone flights need coastal security permission.",
                scamAlertNotes = "Plastic bottles and packaging are strictly checked at boarding. Luggage scanner will confiscate single-use plastic.",
                cashVsDigitalNotes = "Official Jetty counter accepts UPI QR codes and cards. Bring cash if buying tender coconut on island."
            ),
            howToReachTransit = "Reach Malpe Beach Jetty from Udupi; board the official Malpe Beach Development Committee ferry boat.",
            survivalKannadaPhrase = "St. Mary's Island ninda last boat yavaga barutthe?",
            survivalKannadaLipi = "ಸೇಂಟ್ ಮೇರಿಸ್ ಐಲ್ಯಾಂಡ್‌ ನಿಂದ ಕೊನೆ ಬೋಟ್ ಯಾವಾಗ ಬರುತ್ತೆ?",
            survivalKannadaMeaning = "When does the last ferry boat depart from St. Mary's Island?"
        ),

        KarnatakaSpot(
            id = "netrani_coral_island",
            name = "Netrani Island (Pigeon Island)",
            kannadaName = "ನೇತ್ರಾಣಿ ಹವಳದ ದ್ವೀಪ (ಪಾರಿವಾಳ ದ್ವೀಪ)",
            tagline = "Isolated Marine Island & Biodiverse Coral Sanctuary",
            category = TourismCategory.ISLANDS,
            district = "Off Murudeshwar Coast (19 km in Arabian Sea)",
            distanceFromBangaloreKm = 485,
            bestSeason = "October to May (Monsoon rough sea closure: June to September)",
            timings = "Boat tours depart 7:30 AM – 8:30 AM; return by 2:30 PM – 3:30 PM",
            overview = "An uninhabited rocky island shaped like a heart when viewed from the air. Netrani is recognized by marine biologists as one of India's richest coral reef biospheres outside the Andaman and Lakshadweep archipelagos, home to over 80 species of reef fish, sea turtles, and corals.",
            keyHighlights = listOf(
                "Heart-shaped island formation rising steeply from deep sea waters",
                "Snorkeling and scuba diving right around the sheltered island bays",
                "Hundreds of wild pigeons nesting on high vertical rock crevices",
                "Encountering pods of humpback dolphins during the open-sea crossing"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Murudeshwar Port & Navy Regulated Dive Boats",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Stepping foot onto island rocks is prohibited by Indian Navy for defense and sanctuary preservation; all tours are conducted from certified expedition boats.",
                badgeTag = "MARINE BIOSPHERE"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 1999,
                foreignerFeeInr = 3500,
                approxUsdEquivalent = "~4.00 (Sightseeing boat ride) / ~2.00 (Full Scuba Dive)",
                feeIncludes = "Boat transfer, life jacket, safety officer, and snorkeling mask.",
                dressCodeRules = "Breathable quick-dry clothing, sun hat, and high-SPF reef-safe sunscreen.",
                photographyPolicy = "High quality underwater video included with diving; bring waterproof phone pouch for boat photos.",
                scamAlertNotes = "Never board unauthorized private fishing trawlers claiming to visit the island; strict coastal guard patrols intercept unauthorized vessels.",
                cashVsDigitalNotes = "Book tickets at registered dive centers in Murudeshwar town prior to morning departure."
            ),
            howToReachTransit = "Boat departures from Murudeshwar Temple Beach Jetty (1-hour high-speed catamaran ride).",
            survivalKannadaPhrase = "Boat alli dolphin nodoke siguttha?",
            survivalKannadaLipi = "ಬೋಟ್‌ನಲ್ಲಿ ಡಾಲ್ಫಿನ್ ನೋಡೋಕೆ ಸಿಗುತ್ತಾ?",
            survivalKannadaMeaning = "Will we be able to spot dolphins from the boat?"
        ),

        KarnatakaSpot(
            id = "kurumgad_island",
            name = "Kurumgad Tortoise Island (Karwar)",
            kannadaName = "ಕುರುಂಗಡ್ ಆಮೆ ದ್ವೀಪ (ಕಾರವಾರ)",
            tagline = "Tortoise-Shaped Historic Island with Narasimha Shrine & Fort Ruins",
            category = TourismCategory.ISLANDS,
            district = "Karwar (Uttara Kannada)",
            distanceFromBangaloreKm = 520,
            bestSeason = "October to May",
            timings = "Boat rides from Devbagh/Baithkol port: 8:30 AM – 5:00 PM",
            overview = "A naturally tortoise-shaped island located 4 km off Karwar coastline. It holds historic remnants of an ancient Maratha/Portuguese coastal fortress, a sacred hilltop Narasimha Temple celebrated during the annual Pushya Purnima jathra, and crystal clear coves frequented by playful dolphins.",
            keyHighlights = listOf(
                "Historic stone fort wall remains and old cannons on the promontory",
                "Hilltop trek to the ancient Narasimha Temple with sea views",
                "Dolphin spotting boat rides in the Kali river estuary confluence",
                "Beach camping and sea fishing off the rocky shores"
            ),
            bookingInfo = OfficialBookingInfo(
                portalName = "Karwar Port & Local Boatmen Union",
                portalUrl = "https://karnatakatourism.org",
                bookingRule = "Boat transfers operate from Baithkol port or Devbagh beach counter. Weather permits dependent.",
                badgeTag = "FORT ISLAND"
            ),
            foreignerPricing = ForeignerPricingGuidance(
                domesticFeeInr = 400,
                foreignerFeeInr = 600,
                approxUsdEquivalent = "~.00 – .20 USD (Boat Return)",
                feeIncludes = "Return boat ride from Karwar jetty and island pathway access.",
                dressCodeRules = "Comfortable walking/hiking shoes for the hill climb to the fortress shrine.",
                photographyPolicy = "Standard photography permitted; drone photography restricted near Karwar naval base corridor.",
                scamAlertNotes = "Fix boat round-trip fare including waiting time before boarding at the jetty.",
                cashVsDigitalNotes = "Keep ₹500 cash for boatmen and island coconut stall."
            ),
            howToReachTransit = "Reach Karwar by train or KSRTC bus from Bengaluru, then 10 min taxi to Baithkol Jetty for island boat.",
            survivalKannadaPhrase = "Kurumgad island ge boat return yavaga?",
            survivalKannadaLipi = "ಕುರುಂಗಡ್ ಐಲ್ಯಾಂಡ್‌ಗೆ ಬೋಟ್ ರಿಟರ್ನ್ ಯಾವಾಗ?",
            survivalKannadaMeaning = "When does the boat return from Kurumgad island?"
        ),

        KarnatakaSpot(
            id = "srirangapatna_island",
            name = "Srirangapatna River Island & Fort",
            kannadaName = "ಶ್ರೀರಂಗಪಟ್ಟಣ ನದಿ ದ್ವೀಪ ಮತ್ತು ಕೋಟೆ",
            tagline = "Cauvery River Island Fortress of Tipu Sultan & Sri Ranganatha Shrine",
            category = TourismCategory.ISLANDS,
            district = "Mandya District (15km from Mysuru)",
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
                approxUsdEquivalent = "~.60 USD (Daria Daulat Bagh Palace entry)",
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
        )
    )

    fun getSpotsByCategory(category: TourismCategory): List<KarnatakaSpot> {
        return spots.filter { it.category == category }
    }

    fun getSpotById(id: String): KarnatakaSpot? {
        return spots.firstOrNull { it.id == id }
    }
}
