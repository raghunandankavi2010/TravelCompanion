package com.nammaauto.ai.domain.model

import kotlin.math.max
import kotlin.math.roundToInt

object RidePulseEngine {

    fun generateRadarReport(
        distanceKm: Double,
        fromName: String,
        toName: String,
        trafficMultiplier: Double = 1.0,
        isRaining: Boolean = false,
        isNight: Boolean = BangaloreTariff.isNightHours()
    ): MultiAppRadarReport {
        val legalFare = BangaloreTariff.calculateLegalFare(distanceKm, isNight = isNight).totalLegalFare
        val isShortTrip = distanceKm <= 2.5
        val isLongTrip = distanceKm >= 8.0

        // 1. Namma Yatri
        // Commission: 0% to driver -> High driver loyalty in core Bangalore
        val nyBase = max(55.0, legalFare + 18.0 + (if (isRaining) 25.0 else 0.0) + (if (trafficMultiplier > 1.2) 15.0 else 0.0))
        val nyFare = (if (isNight) nyBase * 1.35 else nyBase).roundToNearest()
        val nyAcceptance = when {
            isRaining -> 60
            isShortTrip -> 88
            isLongTrip -> 82
            else -> 85
        }
        val nyTip = if (isRaining) 20 else if (trafficMultiplier >= 1.4) 15 else 0

        val nyOption = RideAppOption(
            id = "namma_yatri",
            appName = "Namma Yatri",
            fareRupees = nyFare,
            etaMinutes = if (trafficMultiplier > 1.2) 6 else 3,
            acceptancePercentage = nyAcceptance,
            acceptanceChance = getChance(nyAcceptance),
            recommendedTipRupees = nyTip,
            proCommuteInsight = "0% driver commission means highest driver pickup loyalty in central Bangalore. Minimal cancellations.",
            packageName = "in.juspay.nammayatri",
            deepLinkUri = "market://details?id=in.juspay.nammayatri"
        )

        // 2. Rapido Auto
        // Aggressive on short hops & college hubs
        val rapidoBase = max(58.0, (legalFare * 1.15) + 15.0 + (if (isRaining) 30.0 else 0.0) + (if (trafficMultiplier > 1.2) 20.0 else 0.0))
        val rapidoFare = (if (isNight) rapidoBase * 1.35 else rapidoBase).roundToNearest()
        val rapidoAcceptance = when {
            isShortTrip -> 89 // Rapido dominates short hops
            isRaining -> 55
            isLongTrip -> 70
            else -> 78
        }
        val rapidoTip = if (isRaining) 20 else 0

        val rapidoOption = RideAppOption(
            id = "rapido_auto",
            appName = "Rapido Auto",
            fareRupees = rapidoFare,
            etaMinutes = if (trafficMultiplier > 1.2) 5 else 3,
            acceptancePercentage = rapidoAcceptance,
            acceptanceChance = getChance(rapidoAcceptance),
            recommendedTipRupees = rapidoTip,
            proCommuteInsight = "Fastest pickup for short hops under 3km. Driver fleet is dense near metro stations.",
            packageName = "com.rapido.passenger",
            deepLinkUri = "market://details?id=com.rapido.passenger"
        )

        // 3. Uber Auto
        // Known for cancellation on short rides, reliable on longer distances
        val uberBase = max(65.0, (legalFare * 1.25) + 22.0 + (if (isRaining) 40.0 else 0.0) + (if (trafficMultiplier > 1.2) 30.0 else 0.0))
        val uberFare = (if (isNight) uberBase * 1.4 else uberBase).roundToNearest()
        val uberAcceptance = when {
            isShortTrip -> 42 // High rejection on Uber for < 2.5km (drivers dislike small fares)
            isRaining -> 45
            isLongTrip -> 76 // Good on long airport/tech park rides
            else -> 64
        }
        val uberTip = 0

        val uberOption = RideAppOption(
            id = "uber_auto",
            appName = "Uber Auto",
            fareRupees = uberFare,
            etaMinutes = if (isShortTrip) 9 else 5,
            acceptancePercentage = uberAcceptance,
            acceptanceChance = getChance(uberAcceptance),
            recommendedTipRupees = uberTip,
            proCommuteInsight = if (isShortTrip)
                "Warning: 58% cancellation risk for rides under 2.5km. Drivers often reject small fares."
            else "Reliable for long tech park commutes > 7km.",
            packageName = "com.ubercab",
            deepLinkUri = "uber://"
        )

        // 4. Ola Auto
        // High driver churn and cancellations
        val olaBase = max(62.0, (legalFare * 1.22) + 20.0 + (if (isRaining) 35.0 else 0.0) + (if (trafficMultiplier > 1.2) 25.0 else 0.0))
        val olaFare = (if (isNight) olaBase * 1.38 else olaBase).roundToNearest()
        val olaAcceptance = when {
            isRaining -> 38
            isShortTrip -> 40
            else -> 48
        }
        val olaTip = 0

        val olaOption = RideAppOption(
            id = "ola_auto",
            appName = "Ola Auto",
            fareRupees = olaFare,
            etaMinutes = 8,
            acceptancePercentage = olaAcceptance,
            acceptanceChance = getChance(olaAcceptance),
            recommendedTipRupees = olaTip,
            proCommuteInsight = "Frequent cancellation risk. Drivers often call to ask destination or payment mode.",
            packageName = "com.olacabs.customer",
            deepLinkUri = "market://details?id=com.olacabs.customer"
        )

        val allOptions = listOf(nyOption, rapidoOption, uberOption, olaOption)

        // Best recommendation: Balances lowest price AND highest acceptance probability
        val bestOption = allOptions.maxByOrNull { option ->
            (option.acceptancePercentage * 2.0) - (option.fareRupees * 0.4)
        }?.copy(isBestChoiceOverall = true) ?: nyOption.copy(isBestChoiceOverall = true)

        val updatedOptions = allOptions.map {
            if (it.id == bestOption.id) bestOption else it
        }

        // Direct street auto fair offer alternative
        val streetAlternative = (nyFare * 0.92).roundToNearest()

        return MultiAppRadarReport(
            distanceKm = distanceKm,
            fromLocation = fromName,
            toLocation = toName,
            bestAppRecommendation = bestOption,
            options = updatedOptions,
            streetAutoAlternativeFare = streetAlternative,
            weatherCondition = if (isRaining) "Bengaluru Monsoon (High App Surges)" else "Clear Skies",
            peakTrafficNotice = when {
                trafficMultiplier >= 1.4 -> "Silkboard / ORR Gridlock: Add ₹15-₹20 tip for quick dispatch"
                trafficMultiplier >= 1.2 -> "Moderate Peak Traffic"
                else -> "Smooth Traffic"
            }
        )
    }

    private fun getChance(pct: Int): AcceptanceChance = when {
        pct >= 85 -> AcceptanceChance.VERY_HIGH
        pct >= 75 -> AcceptanceChance.HIGH
        pct >= 50 -> AcceptanceChance.MODERATE
        else -> AcceptanceChance.LOW_REJECTION_RISK
    }

    private fun Double.roundToNearest(): Double = (this * 1.0).roundToInt().toDouble()
}
