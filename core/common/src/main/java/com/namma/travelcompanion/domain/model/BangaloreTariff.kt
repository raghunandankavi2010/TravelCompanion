package com.namma.travelcompanion.domain.model

import java.util.Calendar
import kotlin.math.max
import kotlin.math.roundToInt

data class AppMarketBenchmark(
    val distanceKm: Double,
    val govtLegalFare: Double,
    val nammaYatriEstimate: Double,
    val uberAutoEstimate: Double,
    val rapidoAutoEstimate: Double,
    val winWinDirectOffer: Double,
    val isShortRideFloor: Boolean,
    val recommendedKannadaLipi: String,
    val recommendedPhonetic: String
)

data class FareBreakdown(
    val distanceKm: Double,
    val baseFare: Double,
    val chargeableKmFare: Double,
    val isNight: Boolean,
    val nightSurcharge: Double,
    val waitingCharge: Double,
    val luggageCharge: Double,
    val totalLegalFare: Double
)

data class CommuteComparison(
    val distanceKm: Double,
    val legalGovtFare: Double,
    val fairNegotiationQuote: Double,
    val estimatedCabSurgePrice: Double,
    val potentialSavings: Double,
    val isNight: Boolean,
    val isRaining: Boolean,
    val trafficLevel: String
)

object BangaloreTariff {
    const val MINIMUM_BASE_FARE = 30.00          // Base charge covers up to 1.80 km
    const val MINIMUM_DISTANCE_KM = 1.80         // Included in base fare
    const val PER_KM_RATE = 15.00                // Rate per kilometer after 1.80 km
    const val NIGHT_MULTIPLIER = 1.50            // 50% extra between 10:00 PM and 5:00 AM
    const val FREE_WAITING_MINUTES = 5           // First 5 minutes waiting are free
    const val WAITING_SLAB_MINUTES = 15          // Waiting charged in 15-minute slabs
    const val WAITING_CHARGE_PER_SLAB = 5.00     // ₹5 per 15 minutes waiting
    const val FREE_LUGGAGE_KG = 20               // Free baggage up to 20 kg
    const val LUGGAGE_CHARGE_PER_SLAB = 5.00     // ₹5 per additional 20 kg

    const val APP_MINIMUM_SHORT_RIDE_FLOOR = 55.00 // Namma Yatri / Uber short-trip floor (1 to 2 km)

    fun isNightHours(calendar: Calendar = Calendar.getInstance()): Boolean {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour >= 22 || hour < 5
    }

    fun calculateLegalFare(
        distanceKm: Double,
        waitMinutes: Int = 0,
        isNight: Boolean = isNightHours(),
        luggageKg: Int = 0
    ): FareBreakdown {
        val safeDistance = max(0.0, distanceKm)
        val chargeableKm = max(0.0, safeDistance - MINIMUM_DISTANCE_KM)
        val distanceFare = chargeableKm * PER_KM_RATE
        val baseFare = MINIMUM_BASE_FARE

        val subtotalDayFare = baseFare + distanceFare
        val nightSurcharge = if (isNight) subtotalDayFare * 0.50 else 0.0

        val billableWait = max(0, waitMinutes - FREE_WAITING_MINUTES)
        val waitSlabs = if (billableWait > 0) ((billableWait + 14) / WAITING_SLAB_MINUTES) else 0
        val waitingCharge = waitSlabs * WAITING_CHARGE_PER_SLAB

        val billableLuggage = max(0, luggageKg - FREE_LUGGAGE_KG)
        val luggageSlabs = if (billableLuggage > 0) ((billableLuggage + 19) / 20) else 0
        val luggageCharge = luggageSlabs * LUGGAGE_CHARGE_PER_SLAB

        val totalFare = (subtotalDayFare + nightSurcharge + waitingCharge + luggageCharge).roundToNearestRupee()

        return FareBreakdown(
            distanceKm = safeDistance,
            baseFare = baseFare,
            chargeableKmFare = distanceFare,
            isNight = isNight,
            nightSurcharge = nightSurcharge,
            waitingCharge = waitingCharge,
            luggageCharge = luggageCharge,
            totalLegalFare = totalFare
        )
    }

    fun calculateAppMarketBenchmark(
        distanceKm: Double,
        isRaining: Boolean = false,
        isNight: Boolean = isNightHours()
    ): AppMarketBenchmark {
        val legalFare = calculateLegalFare(distanceKm, isNight = isNight).totalLegalFare

        val nyBase = max(APP_MINIMUM_SHORT_RIDE_FLOOR, legalFare + 20.0 + (if (isRaining) 25.0 else 0.0))
        val nammaYatri = if (isNight) nyBase * 1.35 else nyBase

        val uberBase = max(65.0, (legalFare * 1.25) + 20.0 + (if (isRaining) 35.0 else 0.0))
        val uberAuto = if (isNight) uberBase * 1.4 else uberBase

        val rapidoBase = max(58.0, (legalFare * 1.18) + 15.0 + (if (isRaining) 25.0 else 0.0))
        val rapidoAuto = if (isNight) rapidoBase * 1.35 else rapidoBase

        val winWinOffer = (nammaYatri * 0.92).roundToNearestRupee()
        val isShortFloor = distanceKm <= 2.2

        val offerInt = winWinOffer.toInt()
        val kannadaLipi = if (isShortFloor) {
            "ಮೀಟರ್ ಬೇಡ, ಆಪ್ ರೇಟ್ ₹$offerInt ಕೊಡ್ತೀನಿ, ಬರ್ತೀರಾ?"
        } else {
            "ಆಪ್‌ನಲ್ಲಿ ₹$offerInt ಇದೆ, ಡೈರೆಕ್ಟ್ ಕ್ಯಾಶ್ ಕೊಡ್ತೀನಿ, ಬನ್ನಿ!"
        }

        val phonetic = if (isShortFloor) {
            "Meter beda, app rate ₹$offerInt kodthini, barthira?"
        } else {
            "App-nalli ₹$offerInt ide, direct cash kodthini, banni!"
        }

        return AppMarketBenchmark(
            distanceKm = distanceKm,
            govtLegalFare = legalFare,
            nammaYatriEstimate = nammaYatri.roundToNearestRupee(),
            uberAutoEstimate = uberAuto.roundToNearestRupee(),
            rapidoAutoEstimate = rapidoAuto.roundToNearestRupee(),
            winWinDirectOffer = winWinOffer,
            isShortRideFloor = isShortFloor,
            recommendedKannadaLipi = kannadaLipi,
            recommendedPhonetic = phonetic
        )
    }

    fun computeCommuteComparison(
        distanceKm: Double,
        trafficMultiplier: Double = 1.0,
        isRaining: Boolean = false,
        isNight: Boolean = isNightHours()
    ): CommuteComparison {
        val legalBreakdown = calculateLegalFare(distanceKm, isNight = isNight)
        val baseLegal = legalBreakdown.totalLegalFare

        val trafficBuffer = if (trafficMultiplier > 1.0) (baseLegal * (trafficMultiplier - 1.0) * 0.5) else 0.0
        val rainBuffer = if (isRaining) 30.0 else 0.0
        val fairHagglingOffer = max(
            APP_MINIMUM_SHORT_RIDE_FLOOR,
            (baseLegal + trafficBuffer + rainBuffer)
        ).roundToNearestRupee()

        val cabBase = 80.0
        val cabKmRate = 24.0
        val cabTrafficSurge = if (isRaining) 2.2 else if (trafficMultiplier > 1.2) 1.8 else 1.3
        val estimatedCabPrice = ((cabBase + (distanceKm * cabKmRate)) * cabTrafficSurge).roundToNearestRupee()

        val netSavings = max(0.0, estimatedCabPrice - fairHagglingOffer)

        return CommuteComparison(
            distanceKm = distanceKm,
            legalGovtFare = baseLegal,
            fairNegotiationQuote = fairHagglingOffer,
            estimatedCabSurgePrice = estimatedCabPrice,
            potentialSavings = netSavings,
            isNight = isNight,
            isRaining = isRaining,
            trafficLevel = when {
                trafficMultiplier >= 1.4 -> "Silkboard Gridlock"
                trafficMultiplier >= 1.2 -> "Heavy ORR Traffic"
                else -> "Smooth"
            }
        )
    }

    private fun Double.roundToNearestRupee(): Double = (this * 1.0).roundToInt().toDouble()
}
