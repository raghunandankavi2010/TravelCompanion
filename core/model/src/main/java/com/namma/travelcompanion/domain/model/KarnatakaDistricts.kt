package com.namma.travelcompanion.domain.model

/** One of Karnataka's 31 districts (Vijayanagara formed 2021; Ramanagara renamed Bengaluru South in 2025). */
data class District(
    val id: String,
    val name: String,
    val kannadaName: String,
    val headquarters: String,
    /** Two to four highlights shown on the map and district cards. */
    val famousFor: List<String>,
    val summary: String
)

/** Districts listed south to north, the way a traveller moves up the state from Chamarajanagar to Bidar. */
object KarnatakaDistricts {

    val all: List<District> = listOf(
        District("chamarajanagar", "Chamarajanagar", "ಚಾಮರಾಜನಗರ", "Chamarajanagar",
            listOf("Bandipur", "BR Hills", "MM Hills"),
            "Tiger country: Bandipur National Park, Biligiri Rangana Hills and the Male Mahadeshwara hill temple."),
        District("mysuru", "Mysuru", "ಮೈಸೂರು", "Mysuru",
            listOf("Mysuru Palace", "Dasara", "Chamundi Hills"),
            "The royal city: Mysuru Palace, the Dasara festival, Chamundi Hills, silk and Mysore Pak."),
        District("kodagu", "Kodagu (Coorg)", "ಕೊಡಗು", "Madikeri",
            listOf("Coffee estates", "Abbey & Iruppu Falls", "Talakaveri"),
            "Misty hills and coffee: Abbey and Iruppu falls, Raja's Seat, Talakaveri and Dubare elephant camp."),
        District("mandya", "Mandya", "ಮಂಡ್ಯ", "Mandya",
            listOf("KRS & Brindavan Gardens", "Srirangapatna", "Ranganathittu"),
            "Sugarcane country with KRS Dam, Srirangapatna's history and the Ranganathittu bird sanctuary."),
        District("bengaluru_south", "Bengaluru South (Ramanagara)", "ಬೆಂಗಳೂರು ದಕ್ಷಿಣ", "Ramanagara",
            listOf("Ramadevara Betta", "Savandurga", "Channapatna toys"),
            "Granite hills for treks and climbs, and Channapatna's lacquered wooden toys."),
        District("bengaluru_urban", "Bengaluru Urban", "ಬೆಂಗಳೂರು ನಗರ", "Bengaluru",
            listOf("Lalbagh", "Cubbon Park", "Bangalore Palace"),
            "The capital: gardens, palaces, museums, food streets and nightlife."),
        District("bengaluru_rural", "Bengaluru Rural", "ಬೆಂಗಳೂರು ಗ್ರಾಮಾಂತರ", "Bengaluru",
            listOf("Devanahalli Fort", "Ghati Subramanya", "Makalidurga"),
            "Tipu Sultan's birthplace at Devanahalli, hill temples and weekend treks near the airport."),
        District("kolar", "Kolar", "ಕೋಲಾರ", "Kolar",
            listOf("Kolar Gold Fields", "Kotilingeshwara", "Antaragange"),
            "Old gold-mining country with the giant Kotilingeshwara lingas and Antaragange's caves."),
        District("chikkaballapura", "Chikkaballapura", "ಚಿಕ್ಕಬಳ್ಳಾಪುರ", "Chikkaballapura",
            listOf("Nandi Hills", "Bhoga Nandeeshwara", "Skandagiri"),
            "Sunrise above the clouds at Nandi Hills, ancient temples and night treks."),
        District("dakshina_kannada", "Dakshina Kannada", "ದಕ್ಷಿಣ ಕನ್ನಡ", "Mangaluru",
            listOf("Beaches", "Dharmasthala", "Kukke Subramanya"),
            "Coastal Mangaluru: beaches, temple towns, Jain heritage at Moodbidri and fiery seafood."),
        District("hassan", "Hassan", "ಹಾಸನ", "Hassan",
            listOf("Belur & Halebidu", "Shravanabelagola", "Sakleshpur"),
            "Hoysala temple art (UNESCO), the Gommateshwara monolith and Western Ghats coffee hills."),
        District("tumakuru", "Tumakuru", "ತುಮಕೂರು", "Tumakuru",
            listOf("Madhugiri", "Devarayanadurga", "Shivagange"),
            "Huge granite monoliths and hill forts, popular for sunrise treks from Bengaluru."),
        District("udupi", "Udupi", "ಉಡುಪಿ", "Udupi",
            listOf("Krishna Temple", "Malpe & St Mary's Island", "Kollur"),
            "Temple town and beaches: Udupi Krishna Matha, Malpe, St Mary's Island and Udupi cuisine."),
        District("chikkamagaluru", "Chikkamagaluru", "ಚಿಕ್ಕಮಗಳೂರು", "Chikkamagaluru",
            listOf("Mullayanagiri", "Coffee", "Kudremukh"),
            "Karnataka's highest peak, the birthplace of Indian coffee, waterfalls and Sringeri."),
        District("chitradurga", "Chitradurga", "ಚಿತ್ರದುರ್ಗ", "Chitradurga",
            listOf("Chitradurga Fort", "Onake Obavva", "Chandravalli"),
            "The seven-walled stone fort (Kallina Kote) and the legend of Onake Obavva."),
        District("davanagere", "Davanagere", "ದಾವಣಗೆರೆ", "Davanagere",
            listOf("Benne dose", "Kunduvada Kere", "Shanthi Sagara"),
            "Famous for butter dosa, with lakes like Kunduvada Kere and Shanthi Sagara (Sulekere)."),
        District("shivamogga", "Shivamogga", "ಶಿವಮೊಗ್ಗ", "Shivamogga",
            listOf("Jog Falls", "Agumbe", "Sakrebailu"),
            "Malnad rainforest: Jog Falls, Agumbe's sunsets, Kodachadri and the Sakrebailu elephant camp."),
        District("uttara_kannada", "Uttara Kannada", "ಉತ್ತರ ಕನ್ನಡ", "Karwar",
            listOf("Gokarna", "Murudeshwar", "Dandeli"),
            "Beaches and temples at Gokarna and Murudeshwar, rafting in Dandeli and the Yana rocks."),
        District("vijayanagara", "Vijayanagara", "ವಿಜಯನಗರ", "Hosapete",
            listOf("Hampi", "Tungabhadra Dam", "Virupaksha"),
            "Hampi, the ruined capital of the Vijayanagara Empire (UNESCO), and the Tungabhadra Dam."),
        District("ballari", "Ballari", "ಬಳ್ಳಾರಿ", "Ballari",
            listOf("Ballari Fort", "Sandur hills", "Kumaraswamy Temple"),
            "Rocky Ballari Fort and the green Sandur hills with the ancient Kumaraswamy temple."),
        District("haveri", "Haveri", "ಹಾವೇರಿ", "Haveri",
            listOf("Byadgi chillies", "Ranebennur blackbuck", "Galageshwara"),
            "Home of Byadgi chillies, the Ranebennur blackbuck sanctuary and Chalukyan temples."),
        District("gadag", "Gadag", "ಗದಗ", "Gadag",
            listOf("Lakkundi temples", "Trikuteshwara", "Kappatagudda"),
            "Kalyani Chalukya temples and stepwells at Lakkundi, and the Kappatagudda hills."),
        District("dharwad", "Dharwad", "ಧಾರವಾಡ", "Dharwad",
            listOf("Dharwad peda", "Hindustani music", "Unkal Lake"),
            "Hubballi–Dharwad: the famous peda, a great Hindustani music tradition and Unkal Lake."),
        District("koppal", "Koppal", "ಕೊಪ್ಪಳ", "Koppal",
            listOf("Anjanadri Hill", "Anegundi", "Itagi temple"),
            "Across the river from Hampi: Anjanadri Hill, Anegundi and the Mahadeva temple at Itagi."),
        District("belagavi", "Belagavi", "ಬೆಳಗಾವಿ", "Belagavi",
            listOf("Gokak Falls", "Kittur", "Belagavi Fort"),
            "Gokak Falls, Rani Chennamma's Kittur, Belagavi Fort and the famous kunda sweet."),
        District("bagalkote", "Bagalkote", "ಬಾಗಲಕೋಟೆ", "Bagalkote",
            listOf("Badami caves", "Pattadakal", "Aihole"),
            "The cradle of Chalukyan architecture: Badami's cave temples, Aihole and Pattadakal (UNESCO)."),
        District("raichur", "Raichur", "ರಾಯಚೂರು", "Raichur",
            listOf("Raichur Fort", "Mudgal Fort", "Maski"),
            "Between the Krishna and Tungabhadra: Raichur and Mudgal forts and the Ashokan edict at Maski."),
        District("vijayapura", "Vijayapura", "ವಿಜಯಪುರ", "Vijayapura",
            listOf("Gol Gumbaz", "Ibrahim Rauza", "Almatti Dam"),
            "Adil Shahi monuments: the Gol Gumbaz whispering gallery, Ibrahim Rauza and Almatti Dam."),
        District("yadgir", "Yadgir", "ಯಾದಗಿರಿ", "Yadgir",
            listOf("Yadgir Fort", "Surapura", "Bonal sanctuary"),
            "Hill fort of Yadgir, the old Surapura kingdom and Bonal bird sanctuary."),
        District("kalaburagi", "Kalaburagi", "ಕಲಬುರಗಿ", "Kalaburagi",
            listOf("Kalaburagi Fort", "Khwaja Bande Nawaz", "Sannati"),
            "Bahmani capital: Kalaburagi Fort, the Bande Nawaz dargah and the Buddhist site at Sannati."),
        District("bidar", "Bidar", "ಬೀದರ್", "Bidar",
            listOf("Bidar Fort", "Bidriware", "Nanak Jhira"),
            "Karnataka's northern tip: Bidar Fort, Bahmani tombs, Bidriware craft and Gurudwara Nanak Jhira.")
    )

    private val byId = all.associateBy { it.id }

    fun byId(id: String): District? = byId[id]

    /**
     * What to buy and taste, by district id. "(GI)" marks products with a Geographical Indication tag, a
     * government-registered guarantee of origin worth looking for on the label.
     */
    val shopAndTaste: Map<String, List<String>> = mapOf(
        "mysuru" to listOf(
            "Mysore Silk sarees (GI): buy from KSIC showrooms", "Mysore Sandal Soap (GI)", "Mysore Agarbathi (GI)",
            "Mysore Pak", "Mysore traditional paintings (GI)", "Rosewood inlay (GI)", "Mysore Mallige jasmine (GI)",
            "Nanjanagud Rasabale banana (GI)"
        ),
        "kodagu" to listOf("Coorg Arabica coffee (GI)", "Coorg orange (GI)", "Coorg green cardamom (GI)", "Forest honey and spices"),
        "mandya" to listOf("Maddur vada", "Organic jaggery (bella)"),
        "bengaluru_south" to listOf("Channapatna wooden toys (GI)", "Ramanagara silk cocoon market"),
        "bengaluru_urban" to listOf("Bangalore Blue grapes (GI)", "Bangalore rose onion (GI)", "Darshini breakfasts: idli, vada, filter coffee"),
        "bengaluru_rural" to listOf("Devanahalli pomelo (GI)"),
        "chikkaballapura" to listOf("Sidlaghatta silk", "Grapes"),
        "dakshina_kannada" to listOf("Mangalore buns and fish curry", "Cashews", "Mangalore tiles"),
        "hassan" to listOf("Sakleshpur coffee and pepper"),
        "tumakuru" to listOf("Tiptur coconuts"),
        "udupi" to listOf("Udupi sarees (GI)", "Mattu Gulla brinjal (GI)", "Udupi temple-style meals"),
        "chikkamagaluru" to listOf("Chikmagalur Arabica coffee (GI)", "Baba Budangiri Arabica coffee (GI)"),
        "chitradurga" to listOf("Molakalmuru silk sarees (GI)"),
        "davanagere" to listOf("Benne dose (butter dosa)"),
        "uttara_kannada" to listOf("Sirsi supari (GI)", "Karwari fish thali"),
        "vijayanagara" to listOf("Hadagali jasmine (GI)"),
        "ballari" to listOf("Sandur Lambani embroidery (GI)"),
        "haveri" to listOf("Byadgi chillies (GI)"),
        "dharwad" to listOf("Dharwad peda (GI)", "Navalgund durries (GI)", "Kasuti embroidery (GI)"),
        "koppal" to listOf("Kinhal wooden toys (GI)"),
        "belagavi" to listOf("Belagavi kunda", "Gokak karadantu", "Kolhapuri chappals (GI)", "Kasuti embroidery (GI)"),
        "bagalkote" to listOf("Ilkal sarees (GI)", "Guledgudd khana fabric (GI)"),
        "vijayapura" to listOf("Grapes and raisins"),
        "kalaburagi" to listOf("Kalaburagi tur dal (GI)", "Kamalapur red banana (GI)"),
        "bidar" to listOf("Bidriware (GI)")
    )
}

/**
 * Traveller-friendly groups over the raw Wikidata categories written by tools/fetch_places.py.
 * Order is the order chips and sections appear in.
 */
enum class PlaceCategory(val label: String, val rawCategories: Set<String>) {
    TEMPLES("Temples & shrines", setOf("Temple")),
    WATERFALLS("Waterfalls", setOf("Waterfall")),
    WILDLIFE("Wildlife, zoos & sanctuaries", setOf("Wildlife")),
    AMUSEMENT("Theme parks & fun", setOf("Amusement")),
    HILLS("Hills & nature walks", setOf("Hill")),
    FORTS("Forts & palaces", setOf("Fort", "Palace")),
    HERITAGE("Heritage & caves", setOf("Heritage")),
    LAKES("Lakes & dams", setOf("Lake")),
    BEACHES("Beaches & islands", setOf("Beach", "Island")),
    GARDENS("Gardens & parks", setOf("Garden")),
    MUSEUMS("Museums", setOf("Museum")),
    OTHER("Other sights", setOf("Attraction"));

    companion object {
        fun of(place: OpenPlace): PlaceCategory? = entries.firstOrNull { place.category in it.rawCategories }

        /** Non-empty groups for [places], in display order, each with its places. */
        fun group(places: List<OpenPlace>): List<Pair<PlaceCategory, List<OpenPlace>>> {
            val byCategory = places.groupBy { of(it) }
            return entries.mapNotNull { category -> byCategory[category]?.let { category to it } }
        }
    }
}
