package com.namma.travelcompanion.feature.tourism.domain.repository

import com.namma.travelcompanion.feature.tourism.domain.model.CityTransportTariff
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.model.RegionalDialectInfo
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory

object KarnatakaTourismRepository {

    val spots: List<KarnatakaSpot> = northKarnatakaSpots +
        coastalKaravaliSpots +
        malnadSpots +
        southKarnatakaSpots +
        deccanSpots

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
