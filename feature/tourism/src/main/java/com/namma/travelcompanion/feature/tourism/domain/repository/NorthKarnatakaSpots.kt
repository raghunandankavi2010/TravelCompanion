package com.namma.travelcompanion.feature.tourism.domain.repository

import com.namma.travelcompanion.feature.tourism.domain.model.ForeignerPricingGuidance
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.model.OfficialBookingInfo
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory

val northKarnatakaSpots = listOf(
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
        videoTourUrl = "https://www.youtube.com/results?search_query=badami+cave+temples+chalukya+architecture",
        guideInfo = "Certified local guides available at Cave 1 ticket booth (₹400 for complete 4-cave circuit)"
    ),

    KarnatakaSpot(
        id = "pattadakal_aihole",
        name = "Pattadakal UNESCO & Aihole Cradle of Architecture",
        kannadaName = "ಪಟ್ಟದಕಲ್ಲು ಮತ್ತು ಐಹೊಳೆ",
        tagline = "The Royal Coronation Complex & Cradle of Indian Temple Design",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.UTTARA_KARNATAKA,
        district = "Bagalkote District",
        nearestTransitHub = "Badami Railway Station (22 km) | Hubballi Airport (125 km)",
        distanceFromBangaloreKm = 465,
        bestSeason = "October to March",
        timings = "6:00 AM – 6:00 PM",
        overview = "Pattadakal was the holy site where early Chalukya kings were crowned. It represents the pinnacle of eclectic 7th-8th century art fusing Northern Nagara and Southern Dravida architectural styles side-by-side. Nearby Aihole houses over 120 stone temples from 450 AD, known as the experimental cradle of Hindu temple architecture.",
        keyHighlights = listOf(
            "Virupaksha Temple with intricate Ramayana, Mahabharata and Bhagavata stone friezes",
            "Unique side-by-side Rekha-Nagara and Dravida Vimana towers",
            "Aihole Durga Temple with semi-circular Buddhist Chaitya-style apsidal plan",
            "Ravana Phadi rock-cut cave sanctuary"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Archaeological Survey of India (ASI)",
            portalUrl = "https://asi.nic.in",
            bookingRule = "ASI ticket at Pattadakal complex entry or online on asi.nic.in.",
            badgeTag = "ASI UNESCO CORONATION SITE"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 40,
            foreignerFeeInr = 600,
            approxUsdEquivalent = "~$7.20 USD",
            feeIncludes = "Entry to the 10-temple Pattadakal coronation enclosure and sculpture museum.",
            dressCodeRules = "Cover shoulders and knees. Footwear removed on raised temple plinths.",
            photographyPolicy = "Photography permitted freely in open courtyards.",
            scamAlertNotes = "Shared auto rickshaws from Badami to Pattadakal cost ₹30-₹40; avoid private taxis charging ₹1,500.",
            cashVsDigitalNotes = "UPI QR code functional at ASI ticket counter."
        ),
        howToReachTransit = "Local KSRTC bus or shared auto from Badami bus stand (22 km, 35 mins).",
        survivalKannadaPhrase = "Pattadakal inda Aihole ge bus eshtu gantege ide?",
        survivalKannadaLipi = "ಪಟ್ಟದಕಲ್ಲಿನಿಂದ ಐಹೊಳೆಗೆ ಬಸ್ ಎಷ್ಟು ಗಂಟೆಗೆ ಇದೆ?",
        survivalKannadaMeaning = "What time is the bus from Pattadakal to Aihole?",
        imageUrl = "https://images.unsplash.com/photo-1590050752117-238cb0fb12b1?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=pattadakal+unesco+heritage+chalukya",
        guideInfo = "ASI guides stationed at Pattadakal ticket gate; fixed fee ₹500 for 1.5-hour tour"
    ),

    KarnatakaSpot(
        id = "gol_gumbaz",
        name = "Gol Gumbaz & Whispering Gallery",
        kannadaName = "ಗೋಲ್ ಗುಂಬಜ್ (ವಿಜಯಪುರ)",
        tagline = "World's 2nd-Largest Free-Standing Dome & Acoustic Wonder",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.UTTARA_KARNATAKA,
        district = "Vijayapura (Bijapur)",
        nearestTransitHub = "Airport: Hubballi HBX (195 km) / Belagavi IXG (210 km) | Train: Vijayapura (2 km)",
        distanceFromBangaloreKm = 525,
        bestSeason = "October to February",
        timings = "6:00 AM – 6:00 PM",
        overview = "Mausoleum of Mohammed Adil Shah built in 1656. The colossal dome (44 meters external diameter) stands without any central pillar support. The legendary Whispering Gallery inside reflects even the faintest whisper 11 distinct times across the 38-meter vast chamber.",
        keyHighlights = listOf(
            "Whispering Gallery where even a gentle sigh echoes 11 times across 38 meters",
            "World's second-largest free-standing hemispherical dome without pillar support",
            "Climb 7-storey octagonal minarets for panoramic views of historic Vijayapura",
            "ASI museum displaying Adil Shahi armoury, miniature paintings and coins"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Archaeological Survey of India (ASI)",
            portalUrl = "https://asi.nic.in",
            bookingRule = "ASI ticket at gate or online. Visit at 6:30 AM before crowds enter to hear whisper echo clearly.",
            badgeTag = "ASI MONUMENT"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 25,
            foreignerFeeInr = 300,
            approxUsdEquivalent = "~$3.60 USD",
            feeIncludes = "Entry to tomb complex, Whispering Gallery climb, and Adil Shahi Museum.",
            dressCodeRules = "No specific dress code; comfortable walking shoes for spiral stairway climb.",
            photographyPolicy = "Photography permitted. Video cameras ₹25 extra fee.",
            scamAlertNotes = "Visit first thing at 6:00 AM; after 10 AM, loud schoolchildren scream in the gallery, ruining the acoustic effect.",
            cashVsDigitalNotes = "UPI QR code accepted at ticket booth."
        ),
        howToReachTransit = "Gol Gumbaz Express train directly from Bengaluru KSR to Vijayapura Station (overnight), then ₹50 auto.",
        survivalKannadaPhrase = "Whispering gallery nodoke meledakke hege hogodu?",
        survivalKannadaLipi = "ವಿಸ್ಪರಿಂಗ್ ಗ್ಯಾಲರಿ ನೋಡೋಕೆ ಮೇಲಕ್ಕೆ ಹೇಗೆ ಹೋಗೋದು?",
        survivalKannadaMeaning = "How do we go up to view the Whispering Gallery?",
        imageUrl = "https://images.unsplash.com/photo-1582510003544-4d00b7f74220?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=gol+gumbaz+whispering+gallery+acoustic+echo",
        guideInfo = "ASI registered guides near minaret entrance demonstrate 11-time acoustic echo effect (₹300 fee)"
    ),

    KarnatakaSpot(
        id = "bidar_fort",
        name = "Bidar Fort & Mahmud Gawan Madrasa",
        kannadaName = "ಬೀದರ್ ಕೋಟೆ ಮತ್ತು ಮಹಮೂದ್ ಗವಾನ್ ಮದರಸಾ",
        tagline = "Bahmani Sultanate Citadel, Triple Moat & Persian Bidriware",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.UTTARA_KARNATAKA,
        district = "Bidar District",
        nearestTransitHub = "Airport: Bidar IXX / Hyderabad HYD (140 km) | Train: Bidar Railway Station (3 km)",
        distanceFromBangaloreKm = 690,
        bestSeason = "October to March",
        timings = "8:00 AM – 6:30 PM",
        overview = "Spectacular medieval fortress built on the edge of the Deccan plateau red laterite cliff. Features a unique triple-layered defensive moat carved out of solid rock, royal palaces like Rangeen Mahal with mother-of-pearl inlay, and Mahmud Gawan Madrasa with vibrant Persian blue glazed tilework.",
        keyHighlights = listOf(
            "Triple-layered defensive dry moat hewn directly into red laterite rock",
            "Rangeen Mahal palace with Persian woodcarvings and mother-of-pearl floral inlays",
            "Mahmud Gawan Madrasa (1472 AD) with Islamic glazed blue tile minarets",
            "Ancient Karez underground Persian subterranean aqueduct water tunnel network"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Archaeological Survey of India (ASI)",
            portalUrl = "https://asi.nic.in",
            bookingRule = "General fort grounds are free; Rangeen Mahal palace section requires nominal ₹25 ASI ticket.",
            badgeTag = "DECCAN SULTANATE FORT"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 25,
            foreignerFeeInr = 300,
            approxUsdEquivalent = "~$3.60 USD",
            feeIncludes = "Entry to Rangeen Mahal royal palace and Tarkash Mahal.",
            dressCodeRules = "Comfortable shoes for walking the expansive ramparts and defensive moats.",
            photographyPolicy = "Photography permitted. Mother-of-pearl inlays photograph beautifully in morning light.",
            scamAlertNotes = "Purchase authentic GI-tagged Bidriware (silver inlay on blackened zinc alloy) directly from master artisan workshops in Bidar Old Town.",
            cashVsDigitalNotes = "UPI QR code accepted at Bidriware artisan cooperative stores."
        ),
        howToReachTransit = "Direct train from Bengaluru (Bidar Express) or 2.5-hour drive via NH 65 from Hyderabad International Airport (RGIA).",
        survivalKannadaPhrase = "Asali Bidriware silver art workshop ellide?",
        survivalKannadaLipi = "ಅಸಲಿ ಬಿದ್ರಿ ಕಲೆ ಬೆಳ್ಳಿ ವರ್ಕ್‌ಶಾಪ್ ಎಲ್ಲಿದೆ?",
        survivalKannadaMeaning = "Where is the authentic Bidriware silver art workshop?",
        imageUrl = "https://images.unsplash.com/photo-1599661046827-dacff0c0f09a?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=bidar+fort+rangeen+mahal+karez+karnataka",
        guideInfo = "Local heritage guides available at Sharza Darwaza (₹400 for 2-hour fort and madrasa circuit)"
    )
)
