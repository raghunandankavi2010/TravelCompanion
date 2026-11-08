package com.namma.travelcompanion.feature.lens.domain.model

data class BusRouteInfo(
    val routeNumber: String,
    val serviceCategory: String, // "BMTC Ordinary", "Vajra AC Volvo", "Vayu Vajra Airport", "KSRTC Rajahamsa", "KSRTC Airavat", "KSRTC Non-AC"
    val originEn: String,
    val originKn: String,
    val destinationEn: String,
    val destinationKn: String,
    val viaStopsEn: List<String>,
    val approxFareRange: String,
    val frequencySummary: String,
    val voiceAnnouncementEn: String,
    val voiceAnnouncementKn: String,
    val conductorSurvivalPhrases: List<ConductorPhrase>,
    val badgeColorHex: Long = 0xFFFACC15
)

data class ConductorPhrase(
    val id: String,
    val englishMeaning: String,
    val kannadaLipi: String,
    val phoneticKannada: String
)

enum class SignCategory(val label: String, val iconTag: String) {
    TEMPLE_ETIQUETTE("Temple Etiquette", "🛕"),
    TRANSIT_PLATFORM("Transit & Platform", "🚌"),
    HIGHWAY_MILESTONE("Highway & Navigation", "🛣️"),
    SAFETY_REGULATION("Rule & Warning", "⚠️"),
    GENERAL_GUIDE("Public Guide", "ℹ️")
}

data class DetectedSignboardInfo(
    val id: String,
    val kannadaOriginal: String,
    val englishTitle: String,
    val category: SignCategory,
    val travelerAdvice: String,
    val phoneticGuide: String,
    val commonLocation: String
)

data class BusSignLensScanResult(
    val detectedRoute: BusRouteInfo? = null,
    val detectedSignboard: DetectedSignboardInfo? = null,
    val rawExtractedText: String = "",
    val detectionConfidence: Float = 0f,
    val scanTimestampMs: Long = 0L
)

object BusSignLensDatabase {

    val routes = listOf(
        // Airport Vayu Vajra Routes
        BusRouteInfo(
            routeNumber = "KIA-8",
            serviceCategory = "Vayu Vajra AC (Airport Express)",
            originEn = "Kempegowda International Airport (KIAL)",
            originKn = "ಕೆಂಪೇಗೌಡ ಅಂತಾರಾಷ್ಟ್ರೀಯ ವಿಮಾನ ನಿಲ್ದಾಣ",
            destinationEn = "Electronic City (Wipro Gate / Infosys)",
            destinationKn = "ಎಲೆಕ್ಟ್ರಾನಿಕ್ ಸಿಟಿ",
            viaStopsEn = listOf("Hebbal", "Manyata Tech Park", "KR Puram Railway Station", "Marathahalli", "Bellandur", "Central Silk Board", "Bommanahalli"),
            approxFareRange = "₹280 – ₹340 (Toll Included)",
            frequencySummary = "Every 25–30 mins (24x7 Schedule)",
            voiceAnnouncementEn = "Bus KIA-8 going to Electronic City via Marathahalli and Silk Board.",
            voiceAnnouncementKn = "ಬಸ್ ಕೆ.ಐ.ಎ-8, ಮಾರತ್‌ಹಳ್ಳಿ ಮತ್ತು ಸಿಲ್ಕ್ ಬೋರ್ಡ್ ಮಾರ್ಗವಾಗಿ ಎಲೆಕ್ಟ್ರಾನಿಕ್ ಸಿಟಿಗೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("kia8_1", "One ticket to Silk Board please", "ಸಿಲ್ಕ್ ಬೋರ್ಡ್‌ಗೆ ಒಂದು ಟಿಕೆಟ್ ಕೊಡಿ", "Silk Board-ge ondu ticket kodi"),
                ConductorPhrase("kia8_2", "Does this bus go to Electronic City Phase 1?", "ಈ ಬಸ್ ಎಲೆಕ್ಟ್ರಾನಿಕ್ ಸಿಟಿ ಫೇಸ್ 1 ಗೆ ಹೋಗುತ್ತಾ?", "Ee bus Electronic City Phase 1-ge hoguttha?"),
                ConductorPhrase("kia8_3", "Can I pay by UPI / GPay?", "ಗೂಗಲ್ ಪೇ ಅಥವಾ ಯುಪಿಐ ಆಗುತ್ತಾ?", "Google Pay athava UPI aaguttha?")
            ),
            badgeColorHex = 0xFF0284C7 // Sky Blue
        ),

        BusRouteInfo(
            routeNumber = "KIA-9",
            serviceCategory = "Vayu Vajra AC (Airport Express)",
            originEn = "Kempegowda International Airport (KIAL)",
            originKn = "ಕೆಂಪೇಗೌಡ ಅಂತಾರಾಷ್ಟ್ರೀಯ ವಿಮಾನ ನಿಲ್ದಾಣ",
            destinationEn = "Kempegowda Bus Station (Majestic)",
            destinationKn = "ಕೆಂಪೇಗೌಡ ಬಸ್ ನಿಲ್ದಾಣ (ಮೆಜೆಸ್ಟಿಕ್)",
            viaStopsEn = listOf("Kogilu Cross", "Yelahanka Bypass", "Hebbal Flyover", "Mekhri Circle", "Malleshwaram", "Bengaluru City Rly Stn"),
            approxFareRange = "₹250 – ₹270",
            frequencySummary = "Every 15–20 mins (24x7 Schedule)",
            voiceAnnouncementEn = "Bus KIA-9 going to Majestic via Hebbal and Mekhri Circle.",
            voiceAnnouncementKn = "ಬಸ್ ಕೆ.ಐ.ಎ-9, ಹೆಬ್ಬಾಳ ಮತ್ತು ಮೇಖ್ರಿ ಸರ್ಕಲ್ ಮಾರ್ಗವಾಗಿ ಮೆಜೆಸ್ಟಿಕ್‌ಗೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("kia9_1", "One ticket to Majestic", "ಮೆಜೆಸ್ಟಿಕ್‌ಗೆ ಒಂದು ಟಿಕೆಟ್", "Majestic-ge ondu ticket"),
                ConductorPhrase("kia9_2", "Will you drop at City Railway Station?", "ಸಿಟಿ ರೈಲ್ವೆ ಸ್ಟೇಷನ್ ಹತ್ತಿರ ಇಳಿಸ್ತೀರಾ?", "City railway station hathira ilistheera?"),
                ConductorPhrase("kia9_3", "Is luggage allowed inside?", "ಲಗೇಜ್ ಒಳಗೆ ಇಡಬಹುದಾ?", "Luggage olage idabahuda?")
            ),
            badgeColorHex = 0xFF0284C7
        ),

        BusRouteInfo(
            routeNumber = "KIA-4",
            serviceCategory = "Vayu Vajra AC (Airport Express)",
            originEn = "Kempegowda International Airport (KIAL)",
            originKn = "ಕೆಂಪೇಗೌಡ ಅಂತಾರಾಷ್ಟ್ರೀಯ ವಿಮಾನ ನಿಲ್ದಾಣ",
            destinationEn = "HAL Main Gate / Old Airport Road",
            destinationKn = "ಹೆಚ್.ಎ.ಎಲ್ ಮೇನ್ ಗೇಟ್",
            viaStopsEn = listOf("Hebbal", "Nagavara", "Kalyan Nagar", "Banaswadi", "Indiranagar 100 Ft Rd", "Domlur Flyover"),
            approxFareRange = "₹260 – ₹310",
            frequencySummary = "Every 35 mins",
            voiceAnnouncementEn = "Bus KIA-4 going to HAL Old Airport Road via Indiranagar.",
            voiceAnnouncementKn = "ಬಸ್ ಕೆ.ಐ.ಎ-4, ಇಂದಿರಾನಗರ ಮಾರ್ಗವಾಗಿ ಹೆಚ್.ಎ.ಎಲ್‌ಗೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("kia4_1", "Drop me at Domlur Flyover please", "ದೊಮ್ಮಲೂರು ಫ್ಲೈಓವರ್ ಹತ್ತಿರ ಇಳಿಸಿ", "Domlur flyover hathira ilisi")
            ),
            badgeColorHex = 0xFF0284C7
        ),

        // High Volume BMTC Corridors
        BusRouteInfo(
            routeNumber = "335-E",
            serviceCategory = "Vajra AC Volvo (IT Corridor Express)",
            originEn = "Kempegowda Bus Station (Majestic)",
            originKn = "ಮೆಜೆಸ್ಟಿಕ್ (ಕೆ.ಬಿ.ಎಸ್)",
            destinationEn = "Kadugodi Bus Stand / Whitefield ITPL",
            destinationKn = "ಕಾಡುಗೋಡಿ ಬಸ್ ನಿಲ್ದಾಣ / ವೈಟ್‌ಫೀಲ್ಡ್",
            viaStopsEn = listOf("Corporation", "Richmond Circle", "Domlur", "Manipal Hospital", "HAL Main Gate", "Marathahalli Bridge", "Kundalahalli Gate", "Hope Farm"),
            approxFareRange = "₹25 – ₹45 (AC Volvo) / ₹15 – ₹25 (Ordinary)",
            frequencySummary = "High Frequency (Every 8–10 mins)",
            voiceAnnouncementEn = "Bus 335-E going towards Kadugodi and Whitefield via Marathahalli.",
            voiceAnnouncementKn = "ಬಸ್ 335-ಇ, ಮಾರತ್‌ಹಳ್ಳಿ ಮಾರ್ಗವಾಗಿ ಕಾಡುಗೋಡಿ ಮತ್ತು ವೈಟ್‌ಫೀಲ್ಡ್‌ಗೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("335e_1", "Does this bus go to Marathahalli Bridge?", "ಈ ಬಸ್ ಮಾರತ್‌ಹಳ್ಳಿ ಬ್ರಿಡ್ಜ್‌ಗೆ ಹೋಗುತ್ತಾ?", "Ee bus Marathahalli bridge-ge hoguttha?"),
                ConductorPhrase("335e_2", "One ticket to ITPL Hope Farm please", "ಐ.ಟಿ.ಪಿ.ಎಲ್ ಹೋಪ್ ಫಾರ್ಮ್‌ಗೆ ಒಂದು ಟಿಕೆಟ್", "ITPL Hope Farm-ge ondu ticket"),
                ConductorPhrase("335e_3", "Please tell me when the stop comes", "ಸ್ಟಾಪ್ ಬಂದಾಗ ದಯವಿಟ್ಟು ತಿಳಿಸಿ", "Stop bandaga dayavittu thilisi")
            ),
            badgeColorHex = 0xFF8B5CF6 // Metro Purple
        ),

        BusRouteInfo(
            routeNumber = "500-D",
            serviceCategory = "BMTC Outer Ring Road Express",
            originEn = "Central Silk Board",
            originKn = "ಸೆಂಟ್ರಲ್ ಸಿಲ್ಕ್ ಬೋರ್ಡ್",
            destinationEn = "Hebbal Flyover / Kemapura",
            destinationKn = "ಹೆಬ್ಬಾಳ ಫ್ಲೈಓವರ್",
            viaStopsEn = listOf("HSR Layout", "Bellandur EcoSpace", "Kadubeesanahalli", "Marathahalli", "Mahadevapura", "KR Puram Rly Stn", "Kalyan Nagar", "Nagavara"),
            approxFareRange = "₹20 – ₹35",
            frequencySummary = "Every 5–8 mins (Busiest Tech Corridor)",
            voiceAnnouncementEn = "Bus 500-D running along Outer Ring Road to Hebbal via Bellandur.",
            voiceAnnouncementKn = "ಬಸ್ 500-ಡಿ, ಹೊರವರ್ತುಲ ರಸ್ತೆ ಮಾರ್ಗವಾಗಿ ಬೆಳ್ಳಂದೂರು ಮತ್ತು ಹೆಬ್ಬಾಳಕ್ಕೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("500d_1", "Does it stop at Bellandur EcoSpace?", "ಬೆಳ್ಳಂದೂರು ಇಕೋಸ್ಪೇಸ್ ಹತ್ತಿರ ನಿಲ್ಲುತ್ತಾ?", "Bellandur EcoSpace hathira nilluttha?"),
                ConductorPhrase("500d_2", "Ticket to KR Puram Railway Station", "ಕೆ.ಆರ್ ಪುರಂ ರೈಲ್ವೆ ಸ್ಟೇಷನ್‌ಗೆ ಟಿಕೆಟ್", "KR Puram railway station-ge ticket")
            ),
            badgeColorHex = 0xFF10B981 // Emerald Green
        ),

        BusRouteInfo(
            routeNumber = "201-G",
            serviceCategory = "BMTC City Crosstown",
            originEn = "Banashankari TTMC",
            originKn = "ಬನಶಂಕರಿ ಟಿ.ಟಿ.ಎಂ.ಸಿ",
            destinationEn = "CV Raman Nagar / Domlur",
            destinationKn = "ಸಿ.ವಿ ರಾಮನ್ ನಗರ / ದೊಮ್ಮಲೂರು",
            viaStopsEn = listOf("Jayanagar 4th Block", "Dairy Circle", "Koramangala Sony World", "Central Silk Board", "Indiranagar", "Doopanahalli"),
            approxFareRange = "₹18 – ₹28",
            frequencySummary = "Every 15 mins",
            voiceAnnouncementEn = "Bus 201-G to Domlur and CV Raman Nagar via Koramangala.",
            voiceAnnouncementKn = "ಬಸ್ 201-ಜಿ, ಕೋರಮಂಗಲ ಮಾರ್ಗವಾಗಿ ದೊಮ್ಮಲೂರಿಗೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("201g_1", "Will this go to Koramangala Sony World signal?", "ಕೋರಮಂಗಲ ಸೋನಿ ವರ್ಲ್ಡ್ ಸಿಗ್ನಲ್‌ಗೆ ಹೋಗುತ್ತಾ?", "Koramangala Sony World signal-ge hoguttha?")
            ),
            badgeColorHex = 0xFFF59E0B // Amber
        ),

        BusRouteInfo(
            routeNumber = "G-4",
            serviceCategory = "Big-10 Radial City Express",
            originEn = "Brigade Road / MG Road Metro",
            originKn = "ಬ್ರಿಗೇಡ್ ರಸ್ತೆ / ಎಂ.ಜಿ ರಸ್ತೆ",
            destinationEn = "Bannerghatta National Park (Safari Gate)",
            destinationKn = "ಬನ್ನೇರುಘಟ್ಟ ರಾಷ್ಟ್ರೀಯ ಉದ್ಯಾನವನ",
            viaStopsEn = listOf("Shanthinagar", "Dairy Circle", "Jayadeva Hospital", "Vega City Mall", "Gottigere", "Kallubalu"),
            approxFareRange = "₹25 – ₹35",
            frequencySummary = "Every 20 mins",
            voiceAnnouncementEn = "Bus G-4 direct to Bannerghatta National Park & Safari.",
            voiceAnnouncementKn = "ಬಸ್ ಜಿ-4, ಬನ್ನೇರುಘಟ್ಟ ನ್ಯಾಷನಲ್ ಪಾರ್ಕ್ ಸಫಾರಿಗೆ ನೇರವಾಗಿ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("g4_1", "One ticket to Bannerghatta Zoo", "ಬನ್ನೇರುಘಟ್ಟ ಪ್ರಾಣಿ ಸಂಗ್ರಹಾಲಯಕ್ಕೆ ಒಂದು ಟಿಕೆಟ್", "Bannerghatta zoo-ge ondu ticket"),
                ConductorPhrase("g4_2", "Is the safari gate within walking distance?", "ಸಫಾರಿ ಗೇಟ್ ಹತ್ತಿರದಲ್ಲೇ ಇದೆಯಾ?", "Safari gate hathiradalle ideya?")
            ),
            badgeColorHex = 0xFFEC4899 // Pink
        ),

        // Statewide KSRTC Intercity Buses
        BusRouteInfo(
            routeNumber = "KSRTC MYS-EXP",
            serviceCategory = "KSRTC Airavat / Karnataka Sarige",
            originEn = "Bengaluru (Satellite Bus Stand Mysore Road)",
            originKn = "ಬೆಂಗಳೂರು (ಸ್ಯಾಟಲೈಟ್ ಬಸ್ ನಿಲ್ದಾಣ)",
            destinationEn = "Mysuru Suburb Bus Stand (KSRTC Central)",
            destinationKn = "ಮೈಸೂರು ಬಸ್ ನಿಲ್ದಾಣ",
            viaStopsEn = listOf("Kengeri", "Bidadi", "Ramanagara", "Channapatna", "Maddur", "Mandya", "Srirangapatna"),
            approxFareRange = "₹170 (Sarige) / ₹240 (Rajahamsa) / ₹360 (Airavat AC)",
            frequencySummary = "Every 5 mins (Round the Clock)",
            voiceAnnouncementEn = "KSRTC Express bus to Mysuru via Mandya and Srirangapatna.",
            voiceAnnouncementKn = "ಕೆ.ಎಸ್.ಆರ್.ಟಿ.ಸಿ ಎಕ್ಸ್‌ಪ್ರೆಸ್ ಬಸ್, ಮಂಡ್ಯ ಮತ್ತು ಶ್ರೀರಂಗಪಟ್ಟಣ ಮಾರ್ಗವಾಗಿ ಮೈಸೂರಿಗೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("mys_1", "Does this bus go via expressway non-stop?", "ಈ ಬಸ್ ಎಕ್ಸ್‌ಪ್ರೆಸ್‌ವೇ ಮೇಲೆ ನಾನ್‌ಸ್ಟಾಪ್ ಹೋಗುತ್ತಾ?", "Ee bus expressway mele non-stop hoguttha?"),
                ConductorPhrase("mys_2", "Drop me at Srirangapatna bypass please", "ಶ್ರೀರಂಗಪಟ್ಟಣ ಬೈಪಾಸ್ ಹತ್ತಿರ ಇಳಿಸಿ", "Srirangapatna bypass hathira ilisi"),
                ConductorPhrase("mys_3", "Two tickets to Mysuru Palace", "ಮೈಸೂರಿಗೆ ಎರಡು ಟಿಕೆಟ್ ಕೊಡಿ", "Mysurige eradu ticket kodi")
            ),
            badgeColorHex = 0xFFDC2626 // KSRTC Red
        ),

        BusRouteInfo(
            routeNumber = "KSRTC DTL-EXP",
            serviceCategory = "KSRTC Rajahamsa / Sleeper",
            originEn = "Bengaluru (Kempegowda Bus Station Majestic)",
            originKn = "ಬೆಂಗಳೂರು (ಮೆಜೆಸ್ಟಿಕ್)",
            destinationEn = "Dharmasthala Manjunatha Swamy Temple",
            destinationKn = "ಧರ್ಮಸ್ಥಳ ಮಂಜುನಾಥ ಸ್ವಾಮಿ ಕ್ಷೇತ್ರ",
            viaStopsEn = listOf("Nelamangala", "Kunigal", "Hassan", "Sakleshpur", "Shiradi Ghat", "Gundya", "Kokkada"),
            approxFareRange = "₹350 – ₹550",
            frequencySummary = "Multiple Daily & Night Sleeper departures",
            voiceAnnouncementEn = "KSRTC bus to Dharmasthala via Sakleshpur and Shiradi Ghat.",
            voiceAnnouncementKn = "ಕೆ.ಎಸ್.ಆರ್.ಟಿ.ಸಿ ಬಸ್, ಹಾಸನ ಮತ್ತು ಸಕಲೇಶಪುರ ಮಾರ್ಗವಾಗಿ ಧರ್ಮಸ್ಥಳಕ್ಕೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("dtl_1", "Will the bus stop for breakfast on the highway?", "ಹೈವೇಯಲ್ಲಿ ತಿಂಡಿಗೆ ನಿಲ್ಲಿಸುತ್ತಾರಾ?", "Highway-nalli thindige nillisutthaara?"),
                ConductorPhrase("dtl_2", "Are seats available for Dharmasthala?", "ಧರ್ಮಸ್ಥಳಕ್ಕೆ ಸೀಟ್ ಖಾಲಿ ಇದೆಯಾ?", "Dharmasthalakke seat khaali ideya?")
            ),
            badgeColorHex = 0xFFDC2626
        ),

        BusRouteInfo(
            routeNumber = "KSRTC HMP-EXP",
            serviceCategory = "KSRTC Non-AC Sleeper & Rajahamsa",
            originEn = "Bengaluru (Majestic)",
            originKn = "ಬೆಂಗಳೂರು (ಮೆಜೆಸ್ಟಿಕ್)",
            destinationEn = "Hampi / Hospet KSRTC Bus Stand",
            destinationKn = "ಹೊಸಪೇಟೆ / ಹಂಪಿ",
            viaStopsEn = listOf("Tumakuru", "Sira", "Chitradurga Fort", "Kudligi", "Hospet Central"),
            approxFareRange = "₹420 – ₹650",
            frequencySummary = "Frequent Day & Overnight Services",
            voiceAnnouncementEn = "KSRTC bus to Hospet and Hampi UNESCO Heritage ruins.",
            voiceAnnouncementKn = "ಕೆ.ಎಸ್.ಆರ್.ಟಿ.ಸಿ ಬಸ್, ಚಿತ್ರದುರ್ಗ ಮಾರ್ಗವಾಗಿ ಹೊಸಪೇಟೆ ಮತ್ತು ಹಂಪಿಗೆ ಹೋಗುತ್ತದೆ.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("hmp_1", "Does this bus go up to Hampi Bazaar or stop at Hospet?", "ಈ ಬಸ್ ಹಂಪಿ ಬಜಾರ್ ವರೆಗೂ ಹೋಗುತ್ತಾ ಅಥವಾ ಹೊಸಪೇಟೆಯಲ್ಲೇ ನಿಲ್ಲುತ್ತಾ?", "Ee bus Hampi bazaar varegoo hoguttha athava Hospet-yalle nilluttha?"),
                ConductorPhrase("hmp_2", "Wake me up when Hospet arrives", "ಹೊಸಪೇಟೆ ಬಂದಾಗ ಎಬ್ಬಿಸಿ ಅಣ್ಣಾ", "Hospet bandaga ebbisi anna")
            ),
            badgeColorHex = 0xFFDC2626
        ),

        BusRouteInfo(
            routeNumber = "KSRTC MNG-EXP",
            serviceCategory = "Ambari Dream Class Volvo Sleeper",
            originEn = "Bengaluru (Majestic / Satellite)",
            originKn = "ಬೆಂಗಳೂರು (ಮೆಜೆಸ್ಟಿಕ್)",
            destinationEn = "Mangaluru (KSRTC Bejai Bus Terminal)",
            destinationKn = "ಮಂಗಳೂರು (ಕೆ.ಎಸ್.ಆರ್.ಟಿ.ಸಿ ಬಿಜೈ)",
            viaStopsEn = listOf("Hassan", "Sakleshpur", "Shiradi Ghat", "Uppinangady", "Bantwal", "BC Road"),
            approxFareRange = "₹450 (Sarige) to ₹950 (Volvo AC Sleeper)",
            frequencySummary = "Hourly Service 24x7",
            voiceAnnouncementEn = "KSRTC Volvo coach to coastal Mangaluru via Shiradi Ghat.",
            voiceAnnouncementKn = "ಕರಾವಳಿ ಮಂಗಳೂರಿಗೆ ಹೋಗುವ ಕೆ.ಎಸ್.ಆರ್.ಟಿ.ಸಿ ಬಸ್.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("mng_1", "Will this drop at Mangalore Bejai Bus Stand?", "ಮಂಗಳೂರು ಬಿಜೈ ಬಸ್ ಸ್ಟ್ಯಾಂಡ್‌ಗೆ ಹೋಗುತ್ತಾ?", "Mangaluru Bejai bus stand-ge hoguttha?")
            ),
            badgeColorHex = 0xFFDC2626
        ),

        BusRouteInfo(
            routeNumber = "KSRTC COG-EXP",
            serviceCategory = "KSRTC Rajahamsa / Airavat",
            originEn = "Bengaluru (Satellite Bus Stand)",
            originKn = "ಬೆಂಗಳೂರು",
            destinationEn = "Madikeri (Coorg)",
            destinationKn = "ಮಡಿಕೇರಿ (ಕೊಡಗು)",
            viaStopsEn = listOf("Mysuru Bypass", "Hunsur", "Periyapatna", "Bylakuppe (Golden Temple)", "Kushalnagar", "Suntikoppa"),
            approxFareRange = "₹320 – ₹550",
            frequencySummary = "Every 45 mins",
            voiceAnnouncementEn = "KSRTC coach to Madikeri, Coorg via Kushalnagar and Tibetan Monastery.",
            voiceAnnouncementKn = "ಕುಶಾಲನಗರ ಮಾರ್ಗವಾಗಿ ಮಡಿಕೇರಿಗೆ ಹೋಗುವ ಬಸ್.",
            conductorSurvivalPhrases = listOf(
                ConductorPhrase("cog_1", "Can you drop me at Kushalnagar Golden Temple gate?", "ಕುಶಾಲನಗರ ಗೋಲ್ಡನ್ ಟೆಂಪಲ್ ಹತ್ತಿರ ಇಳಿಸ್ತೀರಾ?", "Kushalnagar Golden Temple hathira ilistheera?")
            ),
            badgeColorHex = 0xFFDC2626
        )
    )

    val signboards = listOf(
        DetectedSignboardInfo(
            id = "sign_padarakshe",
            kannadaOriginal = "ಪಾದರಕ್ಷೆ ಕೌಂಟರ್",
            englishTitle = "Free Footwear / Shoe Stand",
            category = SignCategory.TEMPLE_ETIQUETTE,
            travelerAdvice = "Leave your shoes/sandals here before entering temple premises. Most temples provide a numbered metal token for free or a nominal ₹2–₹5 maintenance fee. Keep the token safe!",
            phoneticGuide = "Paadarakk-she Counter",
            commonLocation = "All Karnataka Temples (Chamundi, Kukke, Dharmasthala, Belur)"
        ),
        DetectedSignboardInfo(
            id = "sign_sheegra_darshana",
            kannadaOriginal = "ವಿಶೇಷ ದರ್ಶನ / ಶೀಘ್ರ ದರ್ಶನ",
            englishTitle = "Special / Fast-Track Darshan Queue",
            category = SignCategory.TEMPLE_ETIQUETTE,
            travelerAdvice = "Paid express entry line for skipping the 2–3 hour general line. Tickets typically cost ₹50–₹100 per person and are purchased at the adjacent official Muzrai department counter.",
            phoneticGuide = "Vishesha Darshana / Sheegra Darshana",
            commonLocation = "Muzrai & Trust Temples (Kollur Mookambika, Kateel, Srikanteshwara)"
        ),
        DetectedSignboardInfo(
            id = "sign_dharma_darshana",
            kannadaOriginal = "ಧರ್ಮ ದರ್ಶನ",
            englishTitle = "Free General Public Darshan Queue",
            category = SignCategory.TEMPLE_ETIQUETTE,
            travelerAdvice = "The regular, no-ticket queue. It is completely free of charge. Be prepared for longer wait times during weekends, Tuesdays, Fridays, and festival holidays.",
            phoneticGuide = "Dharma Darshana",
            commonLocation = "Every temple in Karnataka"
        ),
        DetectedSignboardInfo(
            id = "sign_prasada",
            kannadaOriginal = "ಪ್ರಸಾದ ಕೌಂಟರ್ / ಅನ್ನದಾಸೋಹ",
            englishTitle = "Prasada Distribution / Free Community Meal Hall",
            category = SignCategory.TEMPLE_ETIQUETTE,
            travelerAdvice = "Annasantharpane (free sanctified lunch/dinner) is served daily from 12:00 PM to 2:30 PM and 7:30 PM to 9:30 PM. Everyone is welcome regardless of nationality, caste, or background.",
            phoneticGuide = "Prasaada Counter / Anna-daasoha",
            commonLocation = "Dharmasthala, Sringeri, Horanadu, Udupi Krishna Matha"
        ),
        DetectedSignboardInfo(
            id = "sign_amanathu",
            kannadaOriginal = "ಅಮಾನತು ಕೊಠಡಿ / ಲಗೇಜ್ ಕೌಂಟರ್",
            englishTitle = "Luggage Cloakroom / Bag Deposit",
            category = SignCategory.TRANSIT_PLATFORM,
            travelerAdvice = "Safe government locker facility to deposit backpacks, heavy luggage, and electronics before entering the sanctum or museum. Keep the printed receipt.",
            phoneticGuide = "Amaanathu Kothadi / Luggage Counter",
            commonLocation = "Railway stations, major KSRTC bus stands, Chamundeshwari hill"
        ),
        DetectedSignboardInfo(
            id = "sign_mobile_nisheeda",
            kannadaOriginal = "ಮೊಬೈಲ್ ಫೋನ್ ನಿಷೇಧಿಸಲಾಗಿದೆ",
            englishTitle = "Mobile Phones & Photography Prohibited",
            category = SignCategory.SAFETY_REGULATION,
            travelerAdvice = "Turn off or stow your mobile phone in your bag. Taking photos or talking on the phone in this area carries a penalty and may cause confiscation by temple marshals.",
            phoneticGuide = "Mobile phone nisheedhisalaagide",
            commonLocation = "Inner sanctums, Bandipur Safari Core, High Court corridors"
        ),
        DetectedSignboardInfo(
            id = "sign_pravesha",
            kannadaOriginal = "ಪ್ರವೇಶ",
            englishTitle = "Entry / In Gate",
            category = SignCategory.GENERAL_GUIDE,
            travelerAdvice = "Designated entrance gate. Follow single-file queue for security bag check.",
            phoneticGuide = "Prav-esha",
            commonLocation = "Metro stations, monuments, KSRTC platforms"
        ),
        DetectedSignboardInfo(
            id = "sign_nirgamana",
            kannadaOriginal = "ನಿರ್ಗಮನ",
            englishTitle = "Exit / Way Out",
            category = SignCategory.GENERAL_GUIDE,
            travelerAdvice = "Designated way out towards public transport and parking areas.",
            phoneticGuide = "Nir-gamana",
            commonLocation = "All public transit & monuments"
        ),
        DetectedSignboardInfo(
            id = "sign_platform",
            kannadaOriginal = "ಪ್ಲಾಟ್‌ಫಾರ್ಮ್ ಸಂಖ್ಯೆ / ನಿಲ್ದಾಣ",
            englishTitle = "Bus Platform / Departure Bay",
            category = SignCategory.TRANSIT_PLATFORM,
            travelerAdvice = "Verify the platform number on your KSRTC ticket or Awatar SMS. Buses pull into bays 15 minutes before scheduled departure.",
            phoneticGuide = "Platform sankhye / Nildaana",
            commonLocation = "Majestic KBS, Mysuru Suburb, Mangalore Bejai"
        ),
        DetectedSignboardInfo(
            id = "sign_nh_milestone",
            kannadaOriginal = "ರಾಷ್ಟ್ರೀಯ ಹೆದ್ದಾರಿ / ಸುಂಕ ವಸೂಲಾತಿ ಕೇಂದ್ರ",
            englishTitle = "National Highway Toll Plaza (FASTag Only)",
            category = SignCategory.HIGHWAY_MILESTONE,
            travelerAdvice = "Keep FASTag recharged with at least ₹250 before entering the express corridor. Cash lane carries double toll penalty.",
            phoneticGuide = "Raashtreeya Heddaari / Sunka Vasoolathi",
            commonLocation = "NH-48 (Pune-Bengaluru), NH-75 (Mangaluru), Bengaluru-Mysuru Expressway"
        )
    )

    /**
     * Converts Kannada numerals to Arabic digits.
     * ೧ ➔ 1, ೨ ➔ 2, ೩ ➔ 3, etc.
     */
    fun convertKannadaNumeralsToArabic(input: String): String {
        val kannadaDigits = charArrayOf('೦', '೧', '೨', '೩', '೪', '೫', '೬', '೭', '೮', '೯')
        var output = input
        for (i in kannadaDigits.indices) {
            output = output.replace(kannadaDigits[i], ('0' + i))
        }
        return output
    }
}
