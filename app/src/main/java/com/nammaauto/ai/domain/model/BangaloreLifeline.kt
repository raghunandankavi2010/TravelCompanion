package com.nammaauto.ai.domain.model

enum class HelplineCategory(val label: String) {
    POLICE_SAFETY("Police & Safety"),
    HOSPITALS("Hospitals & Trauma"),
    CIVIC_UTILITIES("Civic (BESCOM/Water)"),
    TRANSIT_HACKS("Airport & Station Hacks"),
    BTP_COMPLAINT("Lodge Auto Complaint")
}

data class EmergencyContact(
    val id: String,
    val title: String,
    val phoneNumber: String,
    val category: HelplineCategory,
    val subtitle: String,
    val operatingHours: String = "24x7 Available",
    val isPrimarySos: Boolean = false
)

data class HospitalInfo(
    val name: String,
    val zone: String,
    val emergencyPhone: String,
    val address: String,
    val specialty: String,
    val mapsQuery: String
)

data class AirportBusRoute(
    val routeCode: String,
    val destination: String,
    val majorStops: List<String>,
    val frequency: String,
    val fareRupees: Int,
    val comparisonWithCab: String
)

data class CitySlangGuide(
    val kannadaLipi: String,
    val phonetic: String,
    val meaning: String,
    val culturalContext: String
)

object BangaloreLifelineDirectory {

    val emergencyContacts: List<EmergencyContact> = listOf(
        EmergencyContact(
            id = "sos_112",
            title = "All-in-One Emergency (Police / Fire / Medical)",
            phoneNumber = "112",
            category = HelplineCategory.POLICE_SAFETY,
            subtitle = "National Unified Emergency Response Support System (ERSS)",
            isPrimarySos = true
        ),
        EmergencyContact(
            id = "btp_wa",
            title = "BTP Traffic Police WhatsApp Helpline",
            phoneNumber = "9480801000",
            category = HelplineCategory.POLICE_SAFETY,
            subtitle = "Report auto overcharging, refusal, or traffic harassment",
            isPrimarySos = true
        ),
        EmergencyContact(
            id = "ambulance_108",
            title = "Emergency Ambulance Service (Arogya Kavacha)",
            phoneNumber = "108",
            category = HelplineCategory.HOSPITALS,
            subtitle = "Government free emergency trauma ambulance",
            isPrimarySos = true
        ),
        EmergencyContact(
            id = "women_1091",
            title = "Women Helpline (Vanitha Sahayavani)",
            phoneNumber = "1091",
            category = HelplineCategory.POLICE_SAFETY,
            subtitle = "Bengaluru City Police Dedicated Women Assistance & Safety",
            isPrimarySos = true
        ),
        EmergencyContact(
            id = "auto_complaint",
            title = "BTP Auto Grievance Control Room",
            phoneNumber = "08022868550",
            category = HelplineCategory.POLICE_SAFETY,
            subtitle = "Dedicated line to register complaints against errant auto drivers"
        ),
        EmergencyContact(
            id = "bescom_1912",
            title = "BESCOM Electricity & Power Outage",
            phoneNumber = "1912",
            category = HelplineCategory.CIVIC_UTILITIES,
            subtitle = "24x7 helpline for Bangalore power cuts, line faults, and bill queries"
        ),
        EmergencyContact(
            id = "bwssb_1916",
            title = "BWSSB Water Supply & Tanker Helpline",
            phoneNumber = "1916",
            category = HelplineCategory.CIVIC_UTILITIES,
            subtitle = "Report water shortages, pipeline leakage, or tanker grievances"
        ),
        EmergencyContact(
            id = "bbmp_1533",
            title = "BBMP Citizen Helpline (Potholes & Garbage)",
            phoneNumber = "1533",
            category = HelplineCategory.CIVIC_UTILITIES,
            subtitle = "Bruhat Bengaluru Mahanagara Palike civic helpline"
        ),
        EmergencyContact(
            id = "senior_14567",
            title = "Senior Citizens Helpline (Elderline)",
            phoneNumber = "14567",
            category = HelplineCategory.POLICE_SAFETY,
            subtitle = "Toll-free national elder assistance helpline"
        )
    )

    val topTraumaHospitals: List<HospitalInfo> = listOf(
        HospitalInfo(
            name = "St. John's Medical College Hospital",
            zone = "South (Koramangala / HSR / Sarjapur)",
            emergencyPhone = "08022065000",
            address = "Sarjapur Main Road, John Nagar, Koramangala",
            specialty = "Level 1 Trauma, Multi-specialty, 24x7 Emergency",
            mapsQuery = "St Johns Hospital Koramangala Bangalore"
        ),
        HospitalInfo(
            name = "Manipal Hospital (Old Airport Road)",
            zone = "East (Indiranagar / Domlur / HAL)",
            emergencyPhone = "08025024444",
            address = "98, HAL Old Airport Rd, Kodihalli",
            specialty = "Cardiac Emergencies, Stroke & Trauma Care",
            mapsQuery = "Manipal Hospital Old Airport Road Bangalore"
        ),
        HospitalInfo(
            name = "Aster CMI Hospital",
            zone = "North (Hebbal / Sahakara Nagar / Manyata)",
            emergencyPhone = "08043420100",
            address = "No. 43/2, New Airport Road, NH 44, Sahakar Nagar",
            specialty = "Trauma & Critical Care on Airport Highway",
            mapsQuery = "Aster CMI Hospital Hebbal Bangalore"
        ),
        HospitalInfo(
            name = "Victoria Hospital & Bowring Hospital",
            zone = "Central (Majestic / City Market / Shivaji Nagar)",
            emergencyPhone = "08026701150",
            address = "Fort Road, Near City Market / Shivaji Nagar",
            specialty = "Premier Govt Emergency Medical & Burn Institute",
            mapsQuery = "Victoria Hospital Bangalore"
        ),
        HospitalInfo(
            name = "Manipal Hospital Yeshwanthpur (Columbia Asia)",
            zone = "West (Malleshwaram / Rajajinagar / Yeshwanthpur)",
            emergencyPhone = "08061656262",
            address = "26/4, Brigade Gateway, Beside Metro, Yeshwanthpur",
            specialty = "24x7 Trauma, Stroke & Pediatric Emergency",
            mapsQuery = "Manipal Hospital Yeshwanthpur Bangalore"
        ),
        HospitalInfo(
            name = "Jayadeva Institute of Cardiovascular Sciences",
            zone = "South (Jayanagar / Bannerghatta Road)",
            emergencyPhone = "08022977400",
            address = "Bannerghatta Main Road, 9th Block, Jayanagar",
            specialty = "Asia's Premier 24x7 Dedicated Heart Care Institute",
            mapsQuery = "Jayadeva Hospital Bannerghatta Road Bangalore"
        )
    )

    val airportBusRoutes: List<AirportBusRoute> = listOf(
        AirportBusRoute(
            routeCode = "KIAS-4",
            destination = "Electronic City / HSR Layout",
            majorStops = listOf("Hebbal", "Tin Factory", "Marathahalli", "Silk Board", "HSR Layout", "Electronic City Phase 1"),
            frequency = "Every 15–20 minutes (24 hours)",
            fareRupees = 310,
            comparisonWithCab = "Save ~₹1,500 compared to ₹1,850 airport cab"
        ),
        AirportBusRoute(
            routeCode = "KIAS-7A",
            destination = "Koramangala (Sony World Signal)",
            majorStops = listOf("Hebbal", "Mekhri Circle", "Domlur", "Indiranagar 100ft Rd", "Koramangala Sony World"),
            frequency = "Every 30 minutes",
            fareRupees = 270,
            comparisonWithCab = "Save ~₹1,400 compared to ₹1,700 airport cab"
        ),
        AirportBusRoute(
            routeCode = "KIAS-8",
            destination = "Whitefield (ITPL Main Gate)",
            majorStops = listOf("Hebbal", "Tin Factory", "K.R. Puram", "Marathahalli", "Hope Farm", "ITPL Whitefield"),
            frequency = "Every 25 minutes",
            fareRupees = 320,
            comparisonWithCab = "Save ~₹1,600 compared to ₹1,950 airport cab"
        ),
        AirportBusRoute(
            routeCode = "KIAS-9",
            destination = "Kempegowda Bus Station (Majestic)",
            majorStops = listOf("Hebbal", "Mekhri Circle", "Cantonment Station", "Shivaji Nagar", "Majestic Station"),
            frequency = "Every 10–15 minutes (High frequency)",
            fareRupees = 250,
            comparisonWithCab = "Save ~₹1,350 compared to ₹1,600 airport cab"
        )
    )

    val citySlangGuides: List<CitySlangGuide> = listOf(
        CitySlangGuide(
            kannadaLipi = "ಸ್ವಲ್ಪ ಅಡ್ಜಸ್ಟ್ ಮಾಡಿ",
            phonetic = "Swalpa adjust maadi",
            meaning = "Please adjust a little bit",
            culturalContext = "The unofficial motto of Bengaluru. Used in traffic, auto haggling, crowded buses, or when asking for a small favor. Instantly diffuses tension!"
        ),
        CitySlangGuide(
            kannadaLipi = "ಗುರು / ಮಚ್ಚಾ",
            phonetic = "Guru / Macha",
            meaning = "Friend, Bro, Mate",
            culturalContext = "'Guru' is respectful camaraderie with auto drivers and shopkeepers. 'Macha' is for close friends."
        ),
        CitySlangGuide(
            kannadaLipi = "ಹೌದಾ? / ಸರಿ ಆಯ್ತು",
            phonetic = "Hauda? / Sari aaythu",
            meaning = "Is it so? / Okay, done deal",
            culturalContext = "Perfect responses when an auto driver confirms the route or price."
        ),
        CitySlangGuide(
            kannadaLipi = "ಬೇಡ / ಬೇಕು",
            phonetic = "Beda / Bekku",
            meaning = "Don't want / Want",
            culturalContext = "'Beda' is the polite magic word to decline high quotes or persistent touts at railway stations."
        )
    )
}
