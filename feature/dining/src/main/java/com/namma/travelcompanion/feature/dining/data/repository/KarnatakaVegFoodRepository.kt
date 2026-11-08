package com.namma.travelcompanion.feature.dining.data.repository

import com.namma.travelcompanion.feature.dining.domain.model.PureVegType
import com.namma.travelcompanion.feature.dining.domain.model.TempleAnnadanaGuidance
import com.namma.travelcompanion.feature.dining.domain.model.VegCategory
import com.namma.travelcompanion.feature.dining.domain.model.VegRestaurant

object KarnatakaVegFoodRepository {

    val restaurants: List<VegRestaurant> = listOf(
        // ----------------------------------------------------
        // 1. HIGHWAY PURE VEG HUBS (Road Trippers Lifeline)
        // ----------------------------------------------------
        VegRestaurant(
            id = "kamath_lokaruchi_ramanagara",
            name = "Kamath Lokaruchi (Jaanapada Loka)",
            kannadaName = "ಕಾಮತ್ ಲೋಕರುಚಿ (ರಾಮನಗರ)",
            district = "Ramanagara",
            highwayOrLocation = "Bengaluru-Mysuru Expressway / NH 275 (Near Channapatna)",
            category = VegCategory.HIGHWAY_VEG_HUB,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 220,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.8,
            signatureDishes = listOf("Mudde Oota", "Thatte Idli with Pure Ghee", "Akki Rotti", "Jolada Rotti Meals", "Filter Coffee"),
            timings = "6:30 AM – 10:30 PM (Daily)",
            address = "Adjacent to Janapada Loka, Bangalore-Mysore Highway, Ramanagara",
            mapsSearchQuery = "Kamath Lokaruchi Ramanagara",
            proTip = "Stop here for breakfast on the way to Mysuru/Bandipur. The Thatte Idli served in banana leaf with raw coconut chutney and red chili paste is legendary.",
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Unsplash Creative Commons"
        ),

        VegRestaurant(
            id = "paakashala_kunigal",
            name = "Paakashala (NH 75 Kunigal Bypass)",
            kannadaName = "ಪಾಕಶಾಲಾ (ಕುಣಿಗಲ್ ಬೈಪಾಸ್)",
            district = "Tumakuru",
            highwayOrLocation = "NH 75 (Bengaluru - Hassan - Mangaluru Highway)",
            category = VegCategory.HIGHWAY_VEG_HUB,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 250,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.9,
            signatureDishes = listOf("Crispy Benne Masala Dosa", "Open Butter Masala Dosa", "Bisi Bele Bath with Boondi", "Filter Coffee"),
            timings = "6:00 AM – 11:00 PM (Daily)",
            address = "NH 75, Near Kunigal Toll Plaza, Tumakuru District",
            mapsSearchQuery = "Paakashala Kunigal NH 75",
            proTip = "The top-rated highway food court for travelers driving to Belur, Halebidu, Dharmasthala, or Kudremukh. Spotless EV fast chargers and executive restrooms.",
            imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Paakashala Archive"
        ),

        VegRestaurant(
            id = "swathi_delicacy_nh75",
            name = "Swathi Delicacy (Yediyur NH 75)",
            kannadaName = "ಸ್ವಾತಿ ಡೆಲಿಕಸಿ (ಯಡಿಯೂರು)",
            district = "Tumakuru",
            highwayOrLocation = "NH 75 (Yediyur Siddhalingeshwara Cross)",
            category = VegCategory.HIGHWAY_VEG_HUB,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 180,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.8,
            signatureDishes = listOf("Paper Roast Masala Dosa", "Mangalore Bajji", "Sweet Pongal", "Kharabath"),
            timings = "6:30 AM – 10:30 PM",
            address = "NH 75, Yediyur, Kunigal Taluk, Karnataka 572142",
            mapsSearchQuery = "Swathi Delicacy Yediyur NH75",
            proTip = "Famous family highway pitstop. Highly efficient token ordering system ensures under 5-minute meal serving even during Sunday rush hours.",
            imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Swathi Archive"
        ),

        VegRestaurant(
            id = "shivalli_channarayapatna",
            name = "Shivalli Restaurant (Channarayapatna)",
            kannadaName = "ಶಿವಳ್ಳಿ ರೆಸ್ಟೋರೆಂಟ್ (ಚನ್ನರಾಯಪಟ್ಟಣ)",
            district = "Hassan",
            highwayOrLocation = "NH 75 Hassan Highway (Near Shravanabelagola turn-off)",
            category = VegCategory.HIGHWAY_VEG_HUB,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 190,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.7,
            signatureDishes = listOf("Traditional Udupi South Indian Thali", "Neer Dosa", "Pineapple Menaskai", "Majige Huli"),
            timings = "7:00 AM – 10:30 PM",
            address = "NH 75, Channarayapatna Bypass, Hassan District",
            mapsSearchQuery = "Shivalli Restaurant Channarayapatna",
            proTip = "Authentic coastal Udupi Brahmin flavours on the highway. Ideal halt before ascending the 650 stone steps of Shravanabelagola Bahubali.",
            imageUrl = "https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Creative Commons"
        ),

        // ----------------------------------------------------
        // 2. SACRED TEMPLE ANNADANA (Free Divine Satvik Meals)
        // ----------------------------------------------------
        VegRestaurant(
            id = "dharmasthala_annapoorna",
            name = "Dharmasthala Sri Manjunatha Annapoorna Dining Hall",
            kannadaName = "ಧರ್ಮಸ್ಥಳ ಶ್ರೀ ಮಂಜುನಾಥ ಅನ್ನಪೂರ್ಣ ಭೋಜನ ಶಾಲೆ",
            district = "Dakshina Kannada",
            highwayOrLocation = "Dharmasthala Temple Complex, Belthangady",
            category = VegCategory.TEMPLE_ANNADANA,
            vegType = PureVegType.SATVIK_NO_ONION_GARLIC,
            approxCostPerPersonInr = 0,
            isFreeTempleAnnadana = true,
            cleanlinessRating = 5.0,
            signatureDishes = listOf("Sacred Prasada Meals", "Dharmasthala Temple Sambar", "Authentic Rice & Ghee", "Payasa (Kheer)"),
            timings = "Lunch: 11:00 AM – 3:30 PM | Dinner: 7:00 PM – 10:00 PM",
            address = "Annapoorna Dining Complex, Sri Kshetra Dharmasthala",
            mapsSearchQuery = "Annapoorna Dining Hall Dharmasthala",
            proTip = "One of the world's largest automated green kitchens, feeding up to 50,000 pilgrims every day free of cost. Complete silence and discipline are maintained; leave footwear at the shoe stand outside.",
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Temple Archives"
        ),

        VegRestaurant(
            id = "sringeri_bhojanashala",
            name = "Sringeri Sharada Peetham Sri Bharati Tirtha Bhojanashala",
            kannadaName = "ಶೃಂಗೇರಿ ಶಾರದಾ ಪೀಠ ಶ್ರೀ ಭಾರತೀತೀರ್ಥ ಭೋಜನಶಾಲೆ",
            district = "Chikkamagaluru",
            highwayOrLocation = "Sringeri Temple Town (By Tunga River)",
            category = VegCategory.TEMPLE_ANNADANA,
            vegType = PureVegType.SATVIK_NO_ONION_GARLIC,
            approxCostPerPersonInr = 0,
            isFreeTempleAnnadana = true,
            cleanlinessRating = 5.0,
            signatureDishes = listOf("Satvik Banana-Leaf Feast", "Traditional Sringeri Temple Rasam", "Sweet Rice Payasa", "Curd Rice"),
            timings = "Lunch: 12:15 PM – 2:30 PM | Dinner: 7:15 PM – 8:30 PM",
            address = "Sharada Peetham Campus, Across Tunga Bridge, Sringeri",
            mapsSearchQuery = "Sri Bharati Tirtha Bhojanashala Sringeri",
            proTip = "Pure traditional satvik meal cooked on firewood using temple cow milk ghee. Men are requested to remove shirts or wear traditional dhotis if sitting in traditional lines.",
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Peetham Media"
        ),

        VegRestaurant(
            id = "horanadu_annapoorneshwari",
            name = "Horanadu Sri Adhishakthyathmaka Annapoorneshwari Temple",
            kannadaName = "ಹೊರನಾಡು ಶ್ರೀ ಅನ್ನಪೂರ್ಣೇಶ್ವರಿ ನಿತ್ಯ ಅನ್ನದಾನ",
            district = "Chikkamagaluru",
            highwayOrLocation = "Kalasam-Horanadu Ghat Road, Western Ghats",
            category = VegCategory.TEMPLE_ANNADANA,
            vegType = PureVegType.SATVIK_NO_ONION_GARLIC,
            approxCostPerPersonInr = 0,
            isFreeTempleAnnadana = true,
            cleanlinessRating = 4.9,
            signatureDishes = listOf("Divine Nitya Annadana", "Steamed Sona Masoori Rice", "Special Malnad Huli", "Sweet Boondi / Payasam"),
            timings = "Lunch: 12:00 PM – 3:00 PM | Dinner: 7:30 PM – 9:30 PM",
            address = "Sri Kshetra Horanadu, Mudigere Taluk, Chikkamagaluru",
            mapsSearchQuery = "Horanadu Annapoorneshwari Temple",
            proTip = "Goddess Annapoorneshwari is the Goddess of Food and Nourishment. No pilgrim ever leaves Horanadu hungry. All devotees, regardless of caste or creed, are fed a sumptuous multi-course meal.",
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Temple Media"
        ),

        VegRestaurant(
            id = "udupi_krishna_matha_chowki",
            name = "Udupi Sri Krishna Matha Chowki Bhojana",
            kannadaName = "ಉಡುಪಿ ಶ್ರೀ ಕೃಷ್ಣ ಮಠ ಚೌಕಿ ಭೋಜನ (ಅನ್ನಬ್ರಹ್ಮ)",
            district = "Udupi",
            highwayOrLocation = "Car Street, Udupi Temple Square",
            category = VegCategory.TEMPLE_ANNADANA,
            vegType = PureVegType.SATVIK_NO_ONION_GARLIC,
            approxCostPerPersonInr = 0,
            isFreeTempleAnnadana = true,
            cleanlinessRating = 4.9,
            signatureDishes = listOf("Original Udupi Saaru (Rasam)", "Moong Dal Payasa", "Raw Banana Palya", "Temple Curd Rice"),
            timings = "Lunch: 11:45 AM – 2:30 PM | Dinner: 8:00 PM – 9:30 PM",
            address = "Bhojana Shaale, Sri Krishna Matha, Car Street, Udupi",
            mapsSearchQuery = "Sri Krishna Matha Udupi Dining Hall",
            proTip = "The very birthplace of global Udupi Cuisine! Meals are prepared with extreme devotion, entirely without onion, garlic, or exotic spices. An absolute bucket-list spiritual culinary experience.",
            imageUrl = "https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Udupi Paryaya Matha"
        ),

        VegRestaurant(
            id = "kukke_subramanya_annadana",
            name = "Kukke Sri Subramanya Swamy Annadana Mandiram",
            kannadaName = "ಕುಕ್ಕೆ ಶ್ರೀ ಸುಬ್ರಹ್ಮಣ್ಯ ಸ್ವಾಮಿ ಅನ್ನದಾನ ಮಂದಿರ",
            district = "Dakshina Kannada",
            highwayOrLocation = "Kukke Temple Car Street, Foothills of Kumara Parvatha",
            category = VegCategory.TEMPLE_ANNADANA,
            vegType = PureVegType.SATVIK_NO_ONION_GARLIC,
            approxCostPerPersonInr = 0,
            isFreeTempleAnnadana = true,
            cleanlinessRating = 4.8,
            signatureDishes = listOf("Sacred Subramanya Mahaprasadam", "Tamarind Gojju", "Fragrant Sambar", "Jackfruit / Sweet Payasam"),
            timings = "Lunch: 11:30 AM – 2:30 PM | Dinner: 7:30 PM – 9:30 PM",
            address = "Annadana Hall, Near Kumara Dhara River Road, Kukke",
            mapsSearchQuery = "Kukke Subramanya Annadana Hall",
            proTip = "Provides wholesome hot meals to trekkers descending from the arduous Kumara Parvatha trek as well as thousands of daily pilgrimage visitors.",
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Temple Board"
        ),

        // ----------------------------------------------------
        // 3. HERITAGE TIFFIN ROOMS & CITY LEGENDS
        // ----------------------------------------------------
        VegRestaurant(
            id = "mitra_samaj_udupi",
            name = "Mitra Samaj (Since 1949, Udupi Car Street)",
            kannadaName = "ಮಿತ್ರ ಸಮಾಜ (ಉಡುಪಿ ರಥಬೀದಿ)",
            district = "Udupi",
            highwayOrLocation = "Car Street, Beside Krishna Matha",
            category = VegCategory.HERITAGE_TIFFIN,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 120,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.8,
            signatureDishes = listOf("Original Mangalore Buns", "Goli Baje (Maida Fritters)", "Tuppa (Ghee) Dosa", "Degree Filter Coffee"),
            timings = "6:00 AM – 9:30 PM (Closed Wednesdays)",
            address = "Car Street, Near Temple Entrance, Udupi",
            mapsSearchQuery = "Mitra Samaj Udupi",
            proTip = "The birthplace of authentic Mangalore Buns (soft, mild-sweet banana dough fried to golden perfection). Pair with their spicy coconut chutney and steaming filter coffee.",
            imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Heritage Udupi"
        ),

        VegRestaurant(
            id = "vinayaka_mylari_mysore",
            name = "Original Hotel Vinayaka Mylari (Nazarbad, Mysuru)",
            kannadaName = "ಒರಿಜಿನಲ್ ಹೋಟೆಲ್ ವಿನಾಯಕ ಮೈಲಾರಿ (ಮೈಸೂರು)",
            district = "Mysuru",
            highwayOrLocation = "Nazarbad Main Road, Mysuru",
            category = VegCategory.HERITAGE_TIFFIN,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 150,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.7,
            signatureDishes = listOf("Mylari Benne Dosa", "Spicy Coriander-Coconut Chutney", "Soft Idli", "Mysore Filter Coffee"),
            timings = "6:30 AM – 1:30 PM & 3:00 PM – 8:30 PM (Closed Wednesdays)",
            address = "Shop No. 79, Nazarbad Main Road, Near Police Station, Mysuru",
            mapsSearchQuery = "Original Hotel Vinayaka Mylari Nazarbad",
            proTip = "Do not be fooled by copycats! Look for the tiny blue board in Nazarbad. The dosa is exceptionally airy, soft like a cloud, stuffed with a secret onion-sagoo butter filling.",
            imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Mylari Heritage"
        ),

        VegRestaurant(
            id = "vidyarthi_bhavan_bengaluru",
            name = "Vidyarthi Bhavan (Since 1943, Gandhi Bazaar)",
            kannadaName = "ವಿದ್ಯಾರ್ಥಿ ಭವನ (ಗಾಂಧಿ ಬಜಾರ್, ಬೆಂಗಳೂರು)",
            district = "Bengaluru Urban",
            highwayOrLocation = "Gandhi Bazaar Main Road, Basavanagudi",
            category = VegCategory.HERITAGE_TIFFIN,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 140,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.8,
            signatureDishes = listOf("Crispy Golden Benne Masala Dosa", "Crisp Uddina Vade", "Kesari Bath", "Pure Filter Kaapi"),
            timings = "6:30 AM – 11:30 AM & 2:00 PM – 8:00 PM (Closed Fridays)",
            address = "32, Gandhi Bazaar Main Rd, Basavanagudi, Bengaluru 560004",
            mapsSearchQuery = "Vidyarthi Bhavan Gandhi Bazaar",
            proTip = "An iconic Bangalore institution where writers, poets, and travellers gather. Watch the waiters skillfully balance 20 plates of dosas at once on a single forearm!",
            imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Vidyarthi Archives"
        ),

        VegRestaurant(
            id = "mtr_lalbagh_bengaluru",
            name = "Mavalli Tiffin Room - MTR 1924 (Lalbagh Road)",
            kannadaName = "ಎಂ.ಟಿ.ಆರ್ (ಮಾವಳ್ಳಿ ಟಿಫಿನ್ ರೂಮ್ - ಲಾಲ್‌ಬಾಗ್)",
            district = "Bengaluru Urban",
            highwayOrLocation = "Lalbagh Fort Road, Near Lalbagh Main Gate",
            category = VegCategory.HERITAGE_TIFFIN,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 280,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.9,
            signatureDishes = listOf("Original Rava Idli with Ghee & Chutney", "Chandrahara Sweet", "Bisi Bele Bath with Pure Ghee", "Filter Coffee in Silver Tumbler"),
            timings = "6:30 AM – 11:00 AM & 12:30 PM – 8:30 PM (Closed Mondays)",
            address = "14, Lalbagh Road, Mavalli, Bengaluru 560004",
            mapsSearchQuery = "MTR Lalbagh Road Bangalore",
            proTip = "Inventors of the world-famous Rava Idli during WWII rice shortages. Served with a small silver pot of steaming pure melted ghee.",
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "MTR Archive"
        ),

        VegRestaurant(
            id = "maddur_tiffanys_nh275",
            name = "Maddur Tiffany's (The Original Maddur Vada Stop)",
            kannadaName = "ಮದ್ದೂರು ಟಿಫನೀಸ್ (ಒರಿಜಿನಲ್ ಮದ್ದೂರು ವಡೆ)",
            district = "Mandya",
            highwayOrLocation = "Old Bangalore-Mysore Highway, Maddur Town",
            category = VegCategory.HERITAGE_TIFFIN,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 110,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.7,
            signatureDishes = listOf("Hot Fresh Maddur Vada", "Onion Pakoda", "Poori Sagoo", "Steaming Filter Coffee"),
            timings = "6:30 AM – 10:30 PM",
            address = "Bangalore-Mysore Road, Beside Maddur KSRTC Bus Stand, Maddur",
            mapsSearchQuery = "Maddur Tiffanys Maddur",
            proTip = "Created in 1917 at the Maddur Railway Station. Made with semolina (rava), rice flour, sliced onions, curry leaves, and green chillies fried fresh to crispy perfection.",
            imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Maddur Heritage"
        ),

        // ----------------------------------------------------
        // 4. NORTH KARNATAKA KHANAVALIS (Jolada Rotti Feast)
        // ----------------------------------------------------
        VegRestaurant(
            id = "basaveshwar_khanavali_hubballi",
            name = "Basaveshwar Khanavali (Near Old Bus Stand, Hubballi)",
            kannadaName = "ಬಸವೇಶ್ವರ ಖಾನಾವಳಿ (ಹುಬ್ಬಳ್ಳಿ)",
            district = "Dharwad",
            highwayOrLocation = "Old Bus Stand Road, Hubballi",
            category = VegCategory.NORTH_KA_KHANAVALI,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 160,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.8,
            signatureDishes = listOf("Kadak Jolada Rotti", "Soft Jolada Rotti with Ghee", "Badanekayi Ennegai (Stuffed Brinjal)", "Shenga (Peanut) Chutney Pudi", "Sajje Rotti"),
            timings = "11:30 AM – 4:00 PM & 7:00 PM – 10:30 PM",
            address = "Opp. Old Bus Stand, Lamington Road, Hubballi 580020",
            mapsSearchQuery = "Basaveshwar Khanavali Hubballi",
            proTip = "The gold standard of North Karnataka Lingayat vegetarian cuisine. Unlimited refills of authentic sprouted moong (kaalu pallya), zesty curd, and fiery stuffed brinjal curry.",
            imageUrl = "https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Khanavali Hub"
        ),

        VegRestaurant(
            id = "lingayat_bhojana_belagavi",
            name = "Annapoorna Khanavali (Khade Bazaar, Belagavi)",
            kannadaName = "ಅನ್ನಪೂರ್ಣ ಖಾನಾವಳಿ (ಖಡೆ ಬಜಾರ್, ಬೆಳಗಾವಿ)",
            district = "Belagavi",
            highwayOrLocation = "Khade Bazaar, Old City Belagavi",
            category = VegCategory.NORTH_KA_KHANAVALI,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 150,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.7,
            signatureDishes = listOf("Jolada Rotti Oota", "Gurellu (Niger Seed) Chutney", "Jhunka Vadi", "Fresh Butter & Mosaru (Curd)"),
            timings = "12:00 PM – 3:30 PM & 7:30 PM – 10:30 PM",
            address = "Khade Bazaar, Raviwar Peth, Belagavi 590001",
            mapsSearchQuery = "Annapoorna Khanavali Khade Bazaar Belagavi",
            proTip = "Finish your spicy jolada rotti meal with authentic Belagavi Kunda (slow-cooked milk and jaggery fudge) available right next door.",
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Belagavi Food Board"
        ),

        // ----------------------------------------------------
        // 5. LEGENDARY SWEETS & SNACKS
        // ----------------------------------------------------
        VegRestaurant(
            id = "guru_sweet_mart_mysuru",
            name = "Guru Sweet Mart (Original Creators of Mysore Pak)",
            kannadaName = "ಗುರು ಸ್ವೀಟ್ ಮಾರ್ಟ್ (ಒರಿಜಿನಲ್ ಮೈಸೂರು ಪಾಕ್)",
            district = "Mysuru",
            highwayOrLocation = "Devaraja Market, Near North Gate, Mysuru",
            category = VegCategory.SWEET_SNACK_LEGEND,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 120,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.8,
            signatureDishes = listOf("Authentic Ghee Mysore Pak (Melt-in-mouth)", "Badam Halwa", "Dumroot Halwa", "Karachi Halwa"),
            timings = "8:30 AM – 10:00 PM",
            address = "Shop No. 1, Devaraja Market Building, Sayyaji Rao Rd, Mysuru 570001",
            mapsSearchQuery = "Guru Sweet Mart Devaraja Market Mysore",
            proTip = "Run by the direct great-grandsons of Kakasura Madappa, the royal chef of Maharaja Nalwadi Krishnaraja Wadiyar who invented Mysore Pak in 1935. Made warm with pure desi cow ghee.",
            imageUrl = "https://images.unsplash.com/photo-1599488615731-7e5c2823ff28?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Heritage Mysore"
        ),

        VegRestaurant(
            id = "dharwad_mishra_peda",
            name = "Babu Singh's Thakur Mishra Peda (Line Bazaar)",
            kannadaName = "ಬಾಬು ಸಿಂಗ್ ಠಾಕೂರ್ ಮಿಶ್ರಾ ಪೇಡ (ಲೈನ್ ಬಜಾರ್, ಧಾರವಾಡ)",
            district = "Dharwad",
            highwayOrLocation = "Line Bazaar, Dharwad Heritage Center",
            category = VegCategory.SWEET_SNACK_LEGEND,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 130,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.9,
            signatureDishes = listOf("Authentic GI-Tagged Dharwad Peda", "Spicy Dharwad Girmit with Mirchi Bajji", "Kunda"),
            timings = "9:00 AM – 9:30 PM",
            address = "Line Bazaar, Near Gandhi Chowk, Dharwad 580001",
            mapsSearchQuery = "Babu Singh Mishra Peda Line Bazaar Dharwad",
            proTip = "Prepared exclusively from indigenous Dharwadi buffalo milk, slow caramelized to deep amber color and dusted with castor sugar. Awarded GI (Geographical Indication) tag.",
            imageUrl = "https://images.unsplash.com/photo-1599488615731-7e5c2823ff28?w=800&auto=format&fit=crop",
            licenseTag = "Unsplash Royalty-Free",
            authorCredit = "Dharwad Heritage"
        ),

        VegRestaurant(
            id = "gokak_karadantu_center",
            name = "Shree Mahalingeshwar Gokak Karadantu (Gokak)",
            kannadaName = "ಶ್ರೀ ಮಹಾಲಿಂಗೇಶ್ವರ ಗೋಕಾಕ್ ಕರದಂಟು",
            district = "Belagavi",
            highwayOrLocation = "Gokak Town (Near Gokak Waterfalls)",
            category = VegCategory.SWEET_SNACK_LEGEND,
            vegType = PureVegType.PURE_VEG_100,
            approxCostPerPersonInr = 150,
            isFreeTempleAnnadana = false,
            cleanlinessRating = 4.9,
            signatureDishes = listOf("Authentic Gokak Karadantu (Fried Edible Gum & Dry Fruits)", "Ladagi Ladu", "Besan Ladoo"),
            timings = "9:00 AM – 9:00 PM",
            address = "Main Market Road, Gokak, Belagavi District 591307",
            mapsSearchQuery = "Gokak Karadantu Centre",
            proTip = "Karadantu ('Fried Gum') is an energy powerhouse packed with pure ghee, acacia gum, pistachios, almonds, cashews, and organic jaggery from Ghataprabha river basin.",
            imageUrl = "https://images.unsplash.com/photo-1599488615731-7e5c2823ff28?w=800&auto=format&fit=crop",
            licenseTag = "CC BY-SA 4.0 / Commons",
            authorCredit = "Gokak Archives"
        )
    )

    val templeAnnadanaGuidelines: List<TempleAnnadanaGuidance> = listOf(
        TempleAnnadanaGuidance(
            templeName = "Dharmasthala Sri Manjunatha Swamy",
            diningHallName = "Annapoorna Mega Dining Complex",
            lunchTimings = "11:00 AM to 3:30 PM",
            dinnerTimings = "7:00 PM to 10:00 PM",
            dressCode = "Modest traditional attire. Footwear must be deposited at free shoe counter.",
            costNotes = "100% Free of Cost. Voluntary donations can be placed in temple hundi.",
            mealDescription = "Continuous multi-course meal: Steamed rice, authentic Karnataka sambar, rasam, freshly churned buttermilk, and sweet payasa served on eco-friendly plates.",
            etiquetteTip = "Do not waste any food on your plate. Eat peacefully and maintain the sanctity of Annapoorna."
        ),
        TempleAnnadanaGuidance(
            templeName = "Sringeri Sharada Peetham",
            diningHallName = "Sri Bharati Tirtha Bhojana Shala",
            lunchTimings = "12:15 PM to 2:30 PM",
            dinnerTimings = "7:15 PM to 8:30 PM",
            dressCode = "Traditional clothing; men remove shirts/vests before sitting in traditional row lines.",
            costNotes = "Free Divine Prasadam to all pilgrims without distinction.",
            mealDescription = "Traditional South Indian Satvik feast on plantain leaves with Tunga river water, ghee, temple dal, and seasonal sweet payasam.",
            etiquetteTip = "Strictly pure satvik (absolutely no onion, garlic, or outside food allowed inside)."
        ),
        TempleAnnadanaGuidance(
            templeName = "Horanadu Annapoorneshwari",
            diningHallName = "Nitya Annadana Mandira",
            lunchTimings = "12:00 PM to 3:00 PM",
            dinnerTimings = "7:30 PM to 9:30 PM",
            dressCode = "Modest clothing.",
            costNotes = "Always free. Goddess Annapoorneshwari blesses all with boundless food.",
            mealDescription = "Sumptuous 3-course mountain feast prepared with pristine Western Ghats spring water and pure ghee.",
            etiquetteTip = "Follow queue lanes patiently during festive Navratri periods."
        ),
        TempleAnnadanaGuidance(
            templeName = "Udupi Sri Krishna Matha",
            diningHallName = "Bhojana Shaale & Rajangana Chowki",
            lunchTimings = "11:45 AM to 2:30 PM",
            dinnerTimings = "8:00 PM to 9:30 PM",
            dressCode = "Traditional or modest clothing.",
            costNotes = "Free Anna Prasadam.",
            mealDescription = "Birthplace of authentic Udupi cooking: unique mild sambar, piping hot rasam, authentic pickle, and payasam.",
            etiquetteTip = "Sit in rows on clean raised marble benches; servers serve with traditional copper buckets."
        )
    )

    fun getRestaurantsByCategory(category: VegCategory): List<VegRestaurant> {
        return restaurants.filter { it.category == category }
    }

    fun searchRestaurants(query: String): List<VegRestaurant> {
        if (query.isBlank()) return restaurants
        val q = query.trim().lowercase()
        return restaurants.filter {
            it.name.lowercase().contains(q) ||
            it.district.lowercase().contains(q) ||
            it.highwayOrLocation.lowercase().contains(q) ||
            it.signatureDishes.any { dish -> dish.lowercase().contains(q) } ||
            it.category.label.lowercase().contains(q) ||
            it.proTip.lowercase().contains(q)
        }
    }
}
