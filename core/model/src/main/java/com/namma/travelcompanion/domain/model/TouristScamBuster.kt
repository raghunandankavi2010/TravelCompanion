package com.namma.travelcompanion.domain.model

data class PriceBenchmarkItem(
    val id: String,
    val itemName: String,
    val kannadaName: String,
    val category: String,
    val fairPriceInr: String,
    val approxUsd: String,
    val ripOffWarning: String,
    val survivalKannada: String,
    val phoneticKannada: String
)

data class TouristGuideline(
    val id: String,
    val category: String,
    val title: String,
    val keyRule: String,
    val detailedAdvice: String,
    val tag: String
)

object TouristGuidanceData {

    val priceBenchmarks = listOf(
        PriceBenchmarkItem(
            id = "tender_coconut",
            itemName = "Fresh Tender Coconut (Elaneer)",
            kannadaName = "ಎಳನೀರು",
            category = "Refreshment",
            fairPriceInr = "₹40 – ₹50",
            approxUsd = "~$0.50 – $0.60",
            ripOffWarning = "Touts outside Mysore Palace / Hampi often ask ₹100+. Always confirm price before the vendor cuts it open!",
            survivalKannada = "ಎಳನೀರು ಒಂದಕ್ಕೆ ಎಷ್ಟು?",
            phoneticKannada = "Elaneeru ondakke eshtu?"
        ),
        PriceBenchmarkItem(
            id = "packaged_water",
            itemName = "1L Mineral Water (Bisleri/Kinley)",
            kannadaName = "ಕುಡಿಯುವ ನೀರು ಬಾಟಲ್",
            category = "Essentials",
            fairPriceInr = "₹20 (Strictly MRP)",
            approxUsd = "~$0.24",
            ripOffWarning = "By Indian Consumer Protection law, vendors CANNOT charge above printed MRP. Check the seal and the MRP ₹20 on the neck.",
            survivalKannada = "ಇಪ್ಪತ್ತು ರೂಪಾಯಿ MRP ಅಲ್ವಾ?",
            phoneticKannada = "Ippatthu rupayi MRP alwa?"
        ),
        PriceBenchmarkItem(
            id = "masala_dosa",
            itemName = "Classic Masala Dosa + Filter Coffee",
            kannadaName = "ಮಸಾಲೆ ದೋಸೆ & ಫಿಲ್ಟರ್ ಕಾಫಿ",
            category = "Iconic Food",
            fairPriceInr = "₹50 – ₹90 (Heritage Darshinis)",
            approxUsd = "~$0.60 – $1.05",
            ripOffWarning = "At traditional Darshinis (CTR, Vidyarthi Bhavan, MTR), pay at cashier token counter first. Five-star hotels charge 10x.",
            survivalKannada = "ಒಂದು ಮಸಾಲೆ ದೋಸೆ, ಒಂದು ಕಾಫಿ ಕೊಡಿ.",
            phoneticKannada = "Ondu masala dose, ondu coffee kodi."
        ),
        PriceBenchmarkItem(
            id = "auto_tariff",
            itemName = "City Auto Rickshaw Commute",
            kannadaName = "ಆಟೋ ಮೀಟರ್ ಬಾಡಿಗೆ",
            category = "Transport",
            fairPriceInr = "₹36 base (2km) + ₹18/km",
            approxUsd = "~$0.41 base (2km) + $0.20/km",
            ripOffWarning = "Drivers quoting flat ₹200 for 2km is common for foreigners. Turn on NammaAutoAI MeterCam or negotiate to fair rate.",
            survivalKannada = "ಮೀಟರ್ ಹಾಕಿ ಹೋಗಿ ಭೈಯಾ.",
            phoneticKannada = "Meter haaki hogi bhaiya."
        ),
        PriceBenchmarkItem(
            id = "monument_guide",
            itemName = "Official Certified ASI / KSTDC Guide",
            kannadaName = "ಸರ್ಕಾರಿ ಮಾನ್ಯತೆ ಪಡೆದ ಗೈಡ್",
            category = "Sightseeing",
            fairPriceInr = "₹500 – ₹1,500 / half day",
            approxUsd = "~$6 – $18 USD",
            ripOffWarning = "Never hire touts approaching you at car parks. Demand to see the official Ministry of Tourism green lanyard photo ID card.",
            survivalKannada = "ನಿಮ್ಮ ಗವರ್ನಮೆಂಟ್ ಗೈಡ್ ಐಡಿ ತೋರಿಸಿ.",
            phoneticKannada = "Nimma government guide ID thorisi."
        ),
        PriceBenchmarkItem(
            id = "mysore_silk",
            itemName = "Pure Mysore Silk Saree (KSIC)",
            kannadaName = "ಮೈಸೂರು ರೇಷ್ಮೆ ಸೀರೆ",
            category = "Handicrafts",
            fairPriceInr = "₹6,500 – ₹25,000+ (Govt Fixed)",
            approxUsd = "~$75 – $300+ USD",
            ripOffWarning = "Private shops in Mysore give commissions to taxi drivers to sell polyester fakes. Purchase ONLY from official KSIC Government showrooms.",
            survivalKannada = "ಇದು ಅಸಲಿ KSIC ಗವರ್ನಮೆಂಟ್ ಶೋರೂಮಾ?",
            phoneticKannada = "Idu asali KSIC government showroom-aa?"
        )
    )

    val touristGuidelines = listOf(
        TouristGuideline(
            id = "plastic_ban",
            category = "Forest & Trekking",
            title = "Zero Plastic in Western Ghats & Treks",
            keyRule = "Strict ₹500 fine for single-use plastic bottles.",
            detailedAdvice = "Karnataka Forest Department checkpoints (Kudremukh, Kumara Parvatha, Skandagiri) strictly prohibit disposable plastic water bottles and polythene bags. Carry reusable insulated stainless steel or copper bottles.",
            tag = "MANDATORY LAW"
        ),
        TouristGuideline(
            id = "aranya_portal",
            category = "Trekking Permits",
            title = "Official Aranya Vihaara Permit Booking",
            keyRule = "Book 1-30 days prior on aranyavihaara.karnataka.gov.in",
            detailedAdvice = "Physical tickets at checkposts are completely discontinued for peak treks. Every trekker must have their name registered with valid Passport / Govt ID. Groups capped at 10 trekkers.",
            tag = "OFFICIAL PORTAL"
        ),
        TouristGuideline(
            id = "temple_attire",
            category = "Cultural Etiquette",
            title = "Temple Dress Codes & Inner Sanctum Rules",
            keyRule = "Modest clothing (cover shoulders & knees). Footwear strictly removed.",
            detailedAdvice = "At major coastal and heritage temples (Murudeshwar, Kukke, Dharmasthala, Belur), men may be asked to remove shirts before entering the garbhagriha. Leather belts and wallets are barred in some sanctums.",
            tag = "CULTURAL ETIQUETTE"
        ),
        TouristGuideline(
            id = "cash_vs_upi",
            category = "Money & Payments",
            title = "UPI vs Cash in Ghats & Remote Areas",
            keyRule = "Keep ₹1,000 in physical cash notes for remote Ghats.",
            detailedAdvice = "While 99% of Bengaluru accepts UPI/GPay, mobile networks (Jio/Airtel) frequently drop to zero in Kudremukh, Dandeli forest, and Western Ghats. Keep physical cash for local guide fees and fruit stalls.",
            tag = "TRAVEL HACK"
        ),
        TouristGuideline(
            id = "asi_dual_pricing",
            category = "Monuments Ticketing",
            title = "ASI Monument Dual Pricing for Foreign Nationals",
            keyRule = "Foreigners: ₹300-₹600 | Domestic: ₹25-₹40",
            detailedAdvice = "Archaeological Survey of India (Hampi, Badami, Pattadakal) and Mysore Palace charge higher official ticket rates for foreign passport holders. Pay online via asi.nic.in for cashless entry and faster queues.",
            tag = "OFFICIAL TARIFF"
        )
    )
}
