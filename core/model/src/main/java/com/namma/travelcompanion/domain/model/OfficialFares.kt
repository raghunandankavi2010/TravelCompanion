package com.namma.travelcompanion.domain.model

import kotlin.math.ceil
import kotlin.math.max

/** Where a fare comes from. Government fares change often, so every number carries its date and source. */
data class FareSource(val effectiveFrom: String, val url: String, val note: String = "")

/** A distance-metered tariff: [baseFare] covers the first [baseKm], then [perKm] for each further km. */
data class MeteredTariff(
    val name: String,
    val baseFare: Double,
    val baseKm: Double,
    val perKm: Double,
    val extras: String,
    val source: FareSource
) {
    /** Day fare for [km], rounded up to the next rupee. Waiting, luggage and night charges are listed in [extras]. */
    fun fareFor(km: Double): Int = ceil(baseFare + max(0.0, km - baseKm) * perKm).toInt()
}

data class FareSlab(val upToKm: Double, val fare: Int)

data class FareNote(val title: String, val detail: String, val source: FareSource)

/**
 * Government-notified fares in Karnataka, checked 6 Nov 2026. Update this file when a new notification lands.
 *
 * ponytail: bundled with the app, so a fare revision needs an app update. If revisions keep landing between
 * releases, serve this as JSON from the repo and cache it.
 */
object OfficialFares {

    val autoRickshaw = listOf(
        MeteredTariff(
            name = "Bengaluru",
            baseFare = BangaloreTariff.MINIMUM_BASE_FARE,
            baseKm = BangaloreTariff.MINIMUM_DISTANCE_KM,
            perKm = BangaloreTariff.PER_KM_RATE,
            extras = "Night 10 pm–5 am: 1.5×. Waiting: 5 min free, then ₹10 per 15 min. Luggage over 20 kg: ₹10 per 20 kg.",
            source = FareSource("1 Aug 2025", "https://thesouthfirst.com/karnataka/auto-fares-revised-in-bengaluru-effective-from-august-1/")
        ),
        MeteredTariff(
            name = "Mysuru",
            baseFare = 36.0,
            baseKm = 2.0,
            perKm = 18.0,
            extras = "Up to 3 passengers. Waiting: first 5 min free.",
            source = FareSource("1 Jan 2026", "https://starofmysore.com/new-auto-fares-in-mysuru-from-new-year/")
        ),
        MeteredTariff(
            name = "Mangaluru (Dakshina Kannada)",
            baseFare = 40.0,
            baseKm = 1.5,
            perKm = 23.0,
            extras = "Night charge 10 pm–5 am. Many meters not yet recalibrated, so agree the fare first.",
            source = FareSource("1 Nov 2026", "https://newskarnataka.com/mangaluru/mangaluru-auto-fares-revised-rs-40-minimum-from-nov-1/06102026/")
        ),
        MeteredTariff(
            name = "Belagavi",
            baseFare = 37.0,
            baseKm = 1.6,
            perKm = 15.0,
            extras = "Meters are rarely used yet; agree the fare before boarding.",
            source = FareSource("Jul 2026", "https://allaboutbelgaum.com/news/15-day-deadline-for-auto-meters-in-belagavi-rapido-bike-taxi-services-to-face-crackdown-auto-fares-fixed-at-min-37/")
        )
    )

    private val taxiSource = FareSource(
        "3 Feb 2024",
        "https://www.medianama.com/2024/02/223-karnataka-transport-dept-uniform-fare-cab-aggregators/",
        "Applies to city taxis and app cabs. Waiting: 5 min free, then ₹1/min. Night (midnight–6 am): +10%. GST and tolls extra."
    )

    /** Uniform city taxi / app cab fares, by the vehicle's price. */
    val taxi = listOf(
        MeteredTariff("Cab under ₹10 lakh", 100.0, 4.0, 24.0, "", taxiSource),
        MeteredTariff("Cab ₹10–15 lakh", 115.0, 4.0, 28.0, "", taxiSource),
        MeteredTariff("Cab above ₹15 lakh", 130.0, 4.0, 32.0, "", taxiSource)
    )

    val metroSource = FareSource(
        "9 Feb 2025",
        "https://www.dnaindia.com/india/report-namma-metro-fare-update-travel-rides-get-costlier-by-50-per-cent-from-february-9-bengaluru-metro-bmrcl-3131507",
        "Smart card / QR: 5% off, plus 5% more off-peak."
    )

    /** Namma Metro (BMRCL) token fares by journey distance. */
    val metroSlabs = listOf(
        FareSlab(2.0, 10), FareSlab(4.0, 20), FareSlab(6.0, 30), FareSlab(8.0, 40), FareSlab(10.0, 50),
        FareSlab(15.0, 60), FareSlab(20.0, 70), FareSlab(25.0, 80), FareSlab(Double.MAX_VALUE, 90)
    )

    fun metroFare(km: Double): Int = metroSlabs.first { km <= it.upToKm }.fare

    private val busRevision = FareSource(
        "4 Nov 2026",
        "https://www.theweek.in/news/biz-tech/2026/11/04/karnataka-bus-fare-hike-check-revised-rates-for-state-run-ksrtc-bmtc-buses-from-tonight.html"
    )

    val busNotes = listOf(
        FareNote("BMTC Ordinary", "₹10 minimum, up to ₹45. Day pass ₹100, monthly pass ₹1,500.", busRevision),
        FareNote("BMTC Vajra (AC)", "Up to ₹90. Volvo day pass ₹175.", busRevision),
        FareNote("Vayu Vajra (airport)", "Up to ₹425. KIA-9 to Majestic ₹300.", busRevision),
        FareNote(
            "KSRTC Express from Bengaluru",
            "Mysuru ₹197 · Mandya ₹136 · Hassan ₹270 · Chikkamagaluru ₹363 · Tumakuru / Kolar ₹100. " +
                "Rajahamsa, AC and sleeper buses are priced by KSRTC; check ksrtc.in.",
            busRevision
        ),
        FareNote(
            "Shakti scheme",
            "Free travel for women domiciled in Karnataka on non-AC government buses (show Shakti smart card or ID).",
            busRevision
        )
    )
}
