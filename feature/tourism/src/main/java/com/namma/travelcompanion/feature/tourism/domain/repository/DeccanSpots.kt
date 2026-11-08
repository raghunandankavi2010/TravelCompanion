package com.namma.travelcompanion.feature.tourism.domain.repository

import com.namma.travelcompanion.feature.tourism.domain.model.ForeignerPricingGuidance
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.model.OfficialBookingInfo
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory

val deccanSpots = listOf(
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
        survivalKannadaMeaning = "Which is the path to see Onake Obavva's historic crevice?",
        imageUrl = "https://images.unsplash.com/photo-1599661046827-dacff0c0f09a?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=chitradurga+fort+onake+obavva+karnataka",
        guideInfo = "Certified local guides available at Rangayyana Bagilu entrance (₹400 for 2-hour fort tour)"
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
        survivalKannadaMeaning = "What is the official doli palanquin charge to go up the hill?",
        imageUrl = "https://images.unsplash.com/photo-1590050752117-238cb0fb12b1?w=800&auto=format&fit=crop&q=80",
        videoTourUrl = "https://www.youtube.com/results?search_query=shravanabelagola+gommateshwara+bahubali+karnataka",
        guideInfo = "Matha certified guides at the base counter explain the 1000-year history and Mahamastakabhisheka rituals"
    ),

    KarnatakaSpot(
        id = "dharmasthala_temple",
        name = "Dharmasthala Sri Manjunatha Swamy Temple",
        kannadaName = "ಧರ್ಮಸ್ಥಳ ಶ್ರೀ ಮಂಜುನಾಥ ಸ್ವಾಮಿ ಕ್ಷೇತ್ರ",
        tagline = "The Abode of Dharma, Charity & World-Famous Sacred Annadana",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.KARAVALI,
        district = "Dakshina Kannada",
        nearestTransitHub = "Mangaluru Airport: IXE (75 km) | Train: Subrahmanya Road SBHR (45 km)",
        distanceFromBangaloreKm = 305,
        bestSeason = "September to March (Pleasant weather in foothills)",
        timings = "6:30 AM – 2:00 PM & 5:00 PM – 8:30 PM (Daily)",
        overview = "Over 800 years old, Dharmasthala represents a unique harmony where Lord Shiva is worshipped by Madhwa Vaishnava priests while the temple is traditionally administered by the Jain Pergade/Heggade family. Renowned across India for boundless Dana (charity), free continuous meals at Annapoorna dining hall, and the 39-foot monolithic Bahubali statue atop Ratnagiri.",
        keyHighlights = listOf(
            "Sri Manjunatha Swamy sanctum darshan & Chandranatha Swami Basadi",
            "World-renowned Annapoorna Free Sacred Dining Hall feeding 30,000+ daily",
            "39-foot monolithic Bhagavan Bahubali statue at Ratnagiri Hill",
            "Manjusha Heritage Vintage Car Museum with royal Wadiyar collections"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Sri Kshetra Dharmasthala Trust",
            portalUrl = "https://shridharmasthala.org",
            bookingRule = "Guesthouse rooms (Netravathi, Ganga, Sharavathi) and special sevas can be reserved online.",
            badgeTag = "DHARMASTHALA TRUST"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 0,
            foreignerFeeInr = 0,
            approxUsdEquivalent = "Free Entry & Free Meals",
            feeIncludes = "Free general darshan and multi-course sacred Annadana prasadam.",
            dressCodeRules = "Men must remove shirts/vests before entering sanctum inner hall. Traditional clothing for women. Footwear strictly prohibited inside campus.",
            photographyPolicy = "Handheld photography permitted outside; strictly prohibited inside sanctum.",
            scamAlertNotes = "Beware of unofficial room booking websites; only book via shridharmasthala.org.",
            cashVsDigitalNotes = "UPI accepted at official sevas counter; keep ₹500 cash for auto rickshaws."
        ),
        howToReachTransit = "Direct KSRTC Rajahamsa and Airavat Club Class buses run round the clock from Majestic Bengaluru (6-hour journey via Shiradi Ghat).",
        survivalKannadaPhrase = "Annapoorna oota timings eshtu gantege?",
        survivalKannadaLipi = "ಅನ್ನಪೂರ್ಣ ಊಟ ಟೈಮಿಂಗ್ಸ್ ಎಷ್ಟು ಗಂಟೆಗೆ?",
        survivalKannadaMeaning = "What time does the sacred Annapoorna dining hall serve meals?",
        imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop",
        videoTourUrl = "https://www.youtube.com/results?search_query=dharmasthala+temple+documentary",
        guideInfo = "Temple volunteers assist pilgrims at the main reception counter in multiple languages"
    ),

    KarnatakaSpot(
        id = "sringeri_peetham",
        name = "Sringeri Sharada Peetham & Vidyashankara Temple",
        kannadaName = "ಶೃಂಗೇರಿ ಶಾರದಾ ಪೀಠ ಮತ್ತು ವಿದ್ಯಾಶಂಕರ ದೇವಾಲಯ",
        tagline = "Adi Shankaracharya 1st Dakshinamnaya Matha on the Pristine Tunga River",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.MALNAD,
        district = "Chikkamagaluru",
        nearestTransitHub = "Mangaluru Airport (105 km) | Train: Shimoga Town SMET (90 km)",
        distanceFromBangaloreKm = 320,
        bestSeason = "October to March (Post-monsoon lush Malnad greenery)",
        timings = "6:00 AM – 2:00 PM & 5:00 PM – 9:00 PM",
        overview = "Established in the 8th century by Jagadguru Adi Shankaracharya as the supreme seat of Advaita Vedanta philosophy. The architectural marvel Vidyashankara Temple features 12 zodiac stone pillars engineered such that morning sunlight strikes the astrological pillar corresponding to the current zodiac month. Flanked by sacred fish in the crystal-clear Tunga river.",
        keyHighlights = listOf(
            "Vidyashankara Temple 12 astronomical Zodiac stone pillars",
            "Goddess Sharadamba sanctum blessing for students and artists",
            "Feeding holy fish (Puffed rice / Mandakki) on the Tunga suspension bridge",
            "Divine satvik banana-leaf meal at Sri Bharati Tirtha Bhojanashala"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Sringeri Sharada Peetham Official",
            portalUrl = "https://sringeri.net",
            bookingRule = "Matha guesthouse accommodation and special Chandramouleshwara Pooja passes available online.",
            badgeTag = "SHARADA PEETHAM"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 0,
            foreignerFeeInr = 0,
            approxUsdEquivalent = "Free Entry & Divine Satvik Food",
            feeIncludes = "Temple entry, Goddess darshan, and two free satvik meals daily.",
            dressCodeRules = "Men remove upper clothing (shirts/vests) in inner sanctum. No shorts or skirts permitted.",
            photographyPolicy = "Allowed in outer river courtyard; strictly prohibited in sanctums.",
            scamAlertNotes = "Do not purchase overpriced flower baskets outside; official matha counter provides genuine prasada.",
            cashVsDigitalNotes = "UPI accepted at matha publication and sevas counters."
        ),
        howToReachTransit = "Direct KSRTC non-stop buses from Bengaluru Majestic (7 hours via Hassan-Mudigere-Balehonnur).",
        survivalKannadaPhrase = "Tunga nadiya meenige mandaakki elli siguththe?",
        survivalKannadaLipi = "ತುಂಗಾ ನದಿಯ ಮೀನಿಗೆ ಮಂಡಕ್ಕಿ ಎಲ್ಲಿ ಸಿಗುತ್ತೆ?",
        survivalKannadaMeaning = "Where can I buy puffed rice to feed the sacred Tunga river fish?",
        imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800&auto=format&fit=crop",
        videoTourUrl = "https://www.youtube.com/results?search_query=sringeri+sharada+peetham+temple",
        guideInfo = "Matha scholars explain Advaita philosophy and the zodiac pillar mechanics at the Vidyashankara courtyard"
    ),

    KarnatakaSpot(
        id = "kukke_subramanya",
        name = "Kukke Sri Subramanya Swamy Temple",
        kannadaName = "ಕುಕ್ಕೆ ಶ್ರೀ ಸುಬ್ರಹ್ಮಣ್ಯ ಸ್ವಾಮಿ ಕ್ಷೇತ್ರ",
        tagline = "Sacred Serpent Shrine at the Misty Foot of Kumara Parvatha",
        category = TourismCategory.TEMPLES_HERITAGE,
        region = KarnatakaRegion.KARAVALI,
        district = "Dakshina Kannada",
        nearestTransitHub = "Train: Subrahmanya Road SBHR (12 km) | Airport: Mangaluru IXE (110 km)",
        distanceFromBangaloreKm = 280,
        bestSeason = "September to March",
        timings = "6:00 AM – 1:30 PM & 3:30 PM – 8:30 PM",
        overview = "Nestled amidst the dense rainforests of the Western Ghats beneath the towering Kumara Parvatha peak. Lord Kartikeya (Subramanya) is worshipped here as the ultimate refuge and protector of snakes. Pilgrims take holy purification dips in the Kumara Dhara river before seeking blessings.",
        keyHighlights = listOf(
            "Kumara Dhara river holy dip & temple chariot car street",
            "Base camp for Karnataka toughest trek (Kumara Parvatha)",
            "Sarpa Samskara & Ashlesha Bali sacred ancient rituals",
            "Free Annadana meals at modern mega dining hall"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Kukke Temple Muzrai Administration",
            portalUrl = "https://kukke.org",
            bookingRule = "Mandatory prior online booking required for Sarpa Samskara and Ashlesha Bali rituals.",
            badgeTag = "MUZRAI TEMPLE"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 0,
            foreignerFeeInr = 0,
            approxUsdEquivalent = "Free Entry",
            feeIncludes = "Temple entry and sacred Annadana meals.",
            dressCodeRules = "Men remove shirts/vests before sanctum entry. Women in sarees/churidars. No Western shorts.",
            photographyPolicy = "Strictly prohibited inside inner sanctum.",
            scamAlertNotes = "Book all rituals exclusively via kukke.org to prevent tout fraud.",
            cashVsDigitalNotes = "UPI QR available at temple counter; ATMs near bus stand."
        ),
        howToReachTransit = "Direct overnight train from Bengaluru (Karwar/Kannur Express) stops at Subrahmanya Road station.",
        survivalKannadaPhrase = "Kumara Dhara nadige hege hogodu?",
        survivalKannadaLipi = "ಕುಮಾರಧಾರ ನದಿಗೆ ಹೇಗೆ ಹೋಗೋದು?",
        survivalKannadaMeaning = "How do I reach the holy Kumara Dhara river?",
        imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=800&auto=format&fit=crop",
        videoTourUrl = "https://www.youtube.com/results?search_query=kukke+subramanya+temple",
        guideInfo = "Muzrai temple information booth assists pilgrims with seva schedules and lodging"
    ),

    KarnatakaSpot(
        id = "ranganathittu_bird",
        name = "Ranganathittu Bird Sanctuary (Pakshi Kashi)",
        kannadaName = "ರಂಗನತಿಟ್ಟು ಪಕ್ಷಿಧಾಮ (ಕರ್ನಾಟಕದ ಪಕ್ಷಿ ಕಾಶಿ)",
        tagline = "Cauvery River Islets with Painted Storks & Marsh Crocodiles",
        category = TourismCategory.WILDLIFE_SAFARI,
        region = KarnatakaRegion.HALE_MYSURU,
        district = "Mandya / Srirangapatna",
        nearestTransitHub = "Mysuru Airport (25 km) | Train: Srirangapatna (4 km)",
        distanceFromBangaloreKm = 128,
        bestSeason = "December to June (Peak migratory nesting & breeding season)",
        timings = "9:00 AM – 6:00 PM (Daily)",
        overview = "Comprising six picturesque islets in the Cauvery River, Ranganathittu is the largest bird sanctuary in Karnataka. Guided boat safaris take visitors within meters of nesting colonies of Painted Storks, Spoonbills, Spot-billed Pelicans, and basking Mugger Crocodiles.",
        keyHighlights = listOf(
            "Cauvery river boat safari gliding past roosting migratory bird colonies",
            "Close-up viewing of basking wild Mugger Crocodiles on rocky islets",
            "Lush bamboo groves, nature interpretation center, and butterfly park",
            "Ranganatha Swamy temple & Tipu Sultan monuments within 5 km"
        ),
        bookingInfo = OfficialBookingInfo(
            portalName = "Karnataka Forest Dept (MyEcoTrip)",
            portalUrl = "https://myecotrip.com",
            bookingRule = "Sanctuary entry and special boat safari tickets can be purchased on myecotrip.com or at entrance.",
            badgeTag = "FOREST ECO-TRIP"
        ),
        foreignerPricing = ForeignerPricingGuidance(
            domesticFeeInr = 100,
            foreignerFeeInr = 500,
            approxUsdEquivalent = "~$6.00 USD (Sanctuary Entry + Boat Safari)",
            feeIncludes = "Entry to sanctuary grounds and guided Cauvery river boat safari.",
            dressCodeRules = "Life jackets provided by Forest Dept are mandatory on the boat. Keep hands inside.",
            photographyPolicy = "Camera allowed. Telephoto lens fee ₹250. Drones strictly illegal under Wildlife Act.",
            scamAlertNotes = "Only take the official Forest Department boat counter; private boats are illegal.",
            cashVsDigitalNotes = "UPI QR accepted at entrance ticket counter."
        ),
        howToReachTransit = "Just 3.5 km off Bengaluru-Mysuru Expressway (NH 275) near Srirangapatna bypass.",
        survivalKannadaPhrase = "Doni safari ticket charge eshtu?",
        survivalKannadaLipi = "ದೋಣಿ ಸಫಾರಿ ಟಿಕೆಟ್ ಚಾರ್ಜ್ ಎಷ್ಟು?",
        survivalKannadaMeaning = "What is the ticket charge for the Cauvery boat safari?",
        imageUrl = "https://images.unsplash.com/photo-1575550959106-5a7defe28b56?w=800&auto=format&fit=crop",
        videoTourUrl = "https://www.youtube.com/results?search_query=ranganathittu+bird+sanctuary+boat+safari",
        guideInfo = "Forest Department boatmen are expert ornithologists who identify rare migratory bird species on the river"
    )
)
