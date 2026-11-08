package com.namma.travelcompanion.feature.tourism.domain.repository

import com.namma.travelcompanion.feature.tourism.domain.model.ForeignerPricingGuidance
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.model.OfficialBookingInfo
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory

val southKarnatakaSpots = listOf(
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
        id = "bandipur_safari",
        name = "Bandipur Tiger Reserve & National Park",
        kannadaName = "ಬಂಡೀಪುರ ಹುಲಿ ಸಂರಕ್ಷಿತ ಪ್ರದೇಶ",
        tagline = "Project Tiger Bastion, Nilgiri Biosphere & Forest Safaris",
        category = TourismCategory.WILDLIFE_SAFARI,
        region = KarnatakaRegion.HALE_MYSURU,
        district = "Chamarajanagar",
        nearestTransitHub = "Airport: Mysuru MYQ (72 km) / Coimbatore CJB (135 km) | Train: Mysuru Junction (78 km)",
        distanceFromBangaloreKm = 220,
        bestSeason = "October to May (Optimal tiger & herd sightings: March to May)",
        timings = "Morning Safari: 6:00 AM – 9:00 AM | Evening Safari: 3:30 PM – 6:00 PM (Melukamanahalli Counter)",
        overview = "Bandipur is one of India's earliest and most successful Project Tiger reserves, spanning over 874 sq km in the Nilgiri Biosphere Reserve. Known for its population of Royal Bengal Tigers, Indian leopards, dholes (wild dogs), and gaurs (Indian bison). Official safaris depart from the Melukamanahalli reception center on the Mysuru-Ooty highway.",
        keyHighlights = listOf(
            "Royal Bengal Tiger and Indian Leopard tracking in deciduous forest",
            "Largest wild population of endangered Asiatic elephants in South Asia",
            "Gaur (Indian Bison), Chital deer, and Sambhar herds roaming grasslands",
            "Official Karnataka Forest Dept Canter bus & open Gypsy safaris"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Bandipur Tiger Reserve (Govt of Karnataka)",
            portalUrl = "https://junglelodges.com",
            bookingRule = "Online tickets open 30 days in advance. Offline counter tickets open 6:00 AM (queues begin 5:15 AM).",
            badgeTag = "PROJECT TIGER RESERVE"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 1000,
            foreignerFeeInr = 2500,
            approxUsdEquivalent = "~$30.00 USD (Forest Dept Safari Bus)",
            feeIncludes = "Shared Canter safari bus entry, sanctuary permit, and forest guide.",
            dressCodeRules = "Muted earth tones (olive, khaki). Strictly no plastic bottles allowed inside safari vehicles.",
            photographyPolicy = "DSLR camera permit ₹250. Video cameras require special commercial permit.",
            scamAlertNotes = "Avoid unauthorized roadside touts claiming 'private core-zone jeep tours'. Only official Forest Dept and Jungle Lodges vehicles are legally permitted in the core tiger zone.",
            cashVsDigitalNotes = "Spot booking counters demand CASH ONLY when highway mobile internet is down. Carry ₹1,500 cash."
        ),
        howToReachTransit = "KSRTC buses from Mysuru to Ooty stop at Bandipur Melukamanahalli Forest Gate directly.",
        survivalKannadaPhrase = "Bandipura safari counter ticket siguttha?",
        survivalKannadaLipi = "ಬಂಡೀಪುರ ಸಫಾರಿ ಕೌಂಟರ್ ಟಿಕೆಟ್ ಸಿಗುತ್ತಾ?",
        survivalKannadaMeaning = "Is the Bandipur safari counter ticket available?",
        imageUrl = "https://images.unsplash.com/photo-1575550959106-5a7defe28b56?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=bandipur+tiger+reserve+safari+karnataka",
        guideInfo = "Certified Forest Department naturalists accompany every morning and evening vehicle batch"
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
        survivalKannadaMeaning = "Where do they issue boat tickets for Dubare elephant camp?",
        imageUrl = "https://images.unsplash.com/photo-1590050752117-238cb0fb12b1?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=coorg+dubare+elephant+camp+karnataka",
        guideInfo = "Mahouts and Karnataka Forest Department staff supervise all elephant interactions"
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
        survivalKannadaMeaning = "What is the entry ticket price for Tipu Sultan's Summer Palace?",
        imageUrl = "https://images.unsplash.com/photo-1599661046827-dacff0c0f09a?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=srirangapatna+island+tipu+sultan+fort+karnataka",
        guideInfo = "ASI registered guides available at Daria Daulat Bagh ticket counter (₹300 for palace tour)"
    )
)
