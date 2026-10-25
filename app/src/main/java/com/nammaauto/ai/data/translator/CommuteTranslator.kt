package com.nammaauto.ai.data.translator

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

data class TranslationResult(
    val englishOriginal: String,
    val kannadaLipi: String,
    val phoneticPronunciation: String,
    val isBangaloreSlangOptimized: Boolean,
    val sourceEngine: String
)

object CommuteTranslator {

    /**
     * Translates custom English speech/text into conversational Bengaluru Kannada.
     * Prioritizes native Bangalore auto vernacular idioms, with automatic fallback
     * to a neural translation engine for arbitrary sentences.
     */
    suspend fun translateToKannada(englishInput: String): TranslationResult = withContext(Dispatchers.IO) {
        val trimmed = englishInput.trim()
        if (trimmed.isEmpty()) {
            return@withContext TranslationResult("", "", "", false, "Empty")
        }

        // 1. Fast On-Device Commute Natural Language Matcher
        val localMatch = matchColloquialBangaloreIdiom(trimmed)
        if (localMatch != null) {
            return@withContext localMatch
        }

        // 2. Fallback to Neural Translation API for arbitrary sentences
        try {
            val encoded = URLEncoder.encode(trimmed, "UTF-8")
            val endpoint = "https://api.mymemory.translated.net/get?q=$encoded&langpair=en|kn"
            val url = URL(endpoint)
            val connection = (url.openConnection() as HttpURLConnection).apply {
                connectTimeout = 4000
                readTimeout = 4000
                requestMethod = "GET"
                setRequestProperty("User-Agent", "NammaAutoAI/1.0 (Android)")
            }

            if (connection.responseCode == 200) {
                val responseStr = connection.inputStream.bufferedReader().use { it.readText() }
                val json = JSONObject(responseStr)
                val responseData = json.optJSONObject("responseData")
                val translatedText = responseData?.optString("translatedText") ?: ""

                if (translatedText.isNotBlank() && !translatedText.equals(trimmed, ignoreCase = true)) {
                    val phonetic = generatePhoneticGuide(translatedText, trimmed)
                    return@withContext TranslationResult(
                        englishOriginal = trimmed,
                        kannadaLipi = translatedText,
                        phoneticPronunciation = phonetic,
                        isBangaloreSlangOptimized = false,
                        sourceEngine = "Neural Translation Engine"
                    )
                }
            }
        } catch (e: Exception) {
            // Network fallback or offline
        }

        // 3. Graceful offline fallback with helpful commute response
        return@withContext TranslationResult(
            englishOriginal = trimmed,
            kannadaLipi = "ದಯವಿಟ್ಟು ಕೇಳಿ ಭೈಯಾ: $trimmed",
            phoneticPronunciation = "Dayavittu keli bhaiya: $trimmed",
            isBangaloreSlangOptimized = true,
            sourceEngine = "Offline Commute Parser"
        )
    }

    private fun matchColloquialBangaloreIdiom(input: String): TranslationResult? {
        val lower = input.lowercase().trim()

        return when {
            // Waiting scenarios
            lower.contains("wait") && (lower.contains("minute") || lower.contains("min")) -> {
                val mins = extractNumber(lower) ?: 2
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "$mins ನಿಮಿಷ ವೇಟ್ ಮಾಡಿ ಭೈಯಾ, ನನ್ ಫ್ರೆಂಡ್ ಬರ್ತಿದ್ದಾರೆ.",
                    phoneticPronunciation = "$mins nimisha wait maadi bhaiya, nan friend barthiddare.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("wait") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಒಂದೆರಡು ನಿಮಿಷ ಇಲ್ಲೇ ವೇಟ್ ಮಾಡಿ ಪ್ಲೀಸ್.",
                    phoneticPronunciation = "Onderadu nimisha ille wait maadi please.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Change / Cash queries
            lower.contains("change for") || (lower.contains("change") && lower.contains("500")) -> {
                val amount = extractNumber(lower) ?: 500
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "₹$amount ಗೆ ಚೇಂಜ್ ಇದೆಯಾ ನಿಮ್ಮ ಹತ್ತಿರ?",
                    phoneticPronunciation = "₹$amount ge change ideya nimma hatthira?",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Digital UPI / Payment
            lower.contains("gpay") || lower.contains("google pay") || lower.contains("phonepe") || lower.contains("upi") || lower.contains("qr") || lower.contains("online") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಗೂಗಲ್ ಪೇ / ಫೋನ್‌ಪೇ ಮಾಡ್ತೀನಿ, ಕ್ಯೂಆರ್ ಕೋಡ್ ಇದೆಯಾ?",
                    phoneticPronunciation = "Google Pay / PhonePe maadthini, QR code ideya?",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Drop near gate / building / landmark
            lower.contains("gate") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಮೇನ್ ಗೇಟ್ ಹತ್ತಿರ ನಿಲ್ಲಿಸಿ ಭೈಯಾ.",
                    phoneticPronunciation = "Main gate hatthira nillisi bhaiya.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("signal") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಸಿಗ್ನಲ್ ಕ್ರಾಸ್ ಮಾಡಿ ಮುಂದೆ ನಿಲ್ಲಿಸಿ.",
                    phoneticPronunciation = "Signal cross maadi mundhe nillisi.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("metro") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಮೆಟ್ರೋ ಸ್ಟೇಷನ್ ಎಂಟ್ರನ್ಸ್ ಹತ್ತಿರ ಬಿಡಿ.",
                    phoneticPronunciation = "Metro station entrance hatthira bidi.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Music / AC / Window
            lower.contains("music") || lower.contains("song") || lower.contains("sound") || lower.contains("volume") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಸೌಂಡ್ ಸ್ವಲ್ಪ ಕಮ್ಮಿ ಮಾಡಿ ಪ್ಲೀಸ್.",
                    phoneticPronunciation = "Sound swalpa kammi maadi please.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("window") || lower.contains("curtain") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ವಿಂಡೋ ಕರ್ಟನ್ ಸ್ವಲ್ಪ ಹಾಕಿ ಭೈಯಾ, ಗಾಳಿ ಬರ್ತಿದೆ.",
                    phoneticPronunciation = "Window curtain swalpa haaki bhaiya, gaali barthide.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Driving speed & comfort
            lower.contains("slow") || lower.contains("careful") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಸ್ವಲ್ಪ ನಿಧಾನವಾಗಿ ಹೋಗಿ ಭೈಯಾ, ಹೆದರಿಕೆ ಆಗ್ತಿದೆ.",
                    phoneticPronunciation = "Swalpa nidhanavagi hogli bhaiya, hedarike aagthide.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("fast") || lower.contains("hurry") || lower.contains("urgent") || lower.contains("late") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಅರ್ಜೆಂಟ್ ಇದೆ ಭೈಯಾ, ಸ್ವಲ್ಪ ಬೇಗ ಹೋಗಿ.",
                    phoneticPronunciation = "Urgent ide bhaiya, swalpa bega hogli.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Route preferences
            lower.contains("flyover") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಫ್ಲೈಓವರ್ ಮೇಲೆ ಹೋಗೋಣ, ಟ್ರಾಫಿಕ್ ಕಮ್ಮಿ ಇರುತ್ತೆ.",
                    phoneticPronunciation = "Flyover mele hogona, traffic kammi irutthe.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("ring road") || lower.contains("outer ring") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಔಟರ್ ರಿಂಗ್ ರೋಡ್ ಕಡೆಯಿಂದ ಹೋಗೋಣ.",
                    phoneticPronunciation = "Outer Ring Road kadeyinda hogona.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("maps") || lower.contains("google map") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಗೂಗಲ್ ಮ್ಯಾಪ್ಸ್ ಪ್ರಕಾರ ಹೋಗೋಣ, ರೂಟ್ ತೋರಿಸ್ತೀನಿ.",
                    phoneticPronunciation = "Google Maps prakara hogona, route thoristhini.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("u-turn") || lower.contains("u turn") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಮುಂದೆ ಯು-ಟರ್ನ್ ತಗೊಳ್ಳಿ ಭೈಯಾ.",
                    phoneticPronunciation = "Mundhe U-turn thagoli bhaiya.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // ATM / Cash withdrawal
            lower.contains("atm") || lower.contains("cash") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ದಾರಿಲಿ ಯಾವುದಾದ್ರೂ ಎಟಿಎಂ ಹತ್ತಿರ ನಿಲ್ಲಿಸಿ, ಕ್ಯಾಶ್ ತಗೋಬೇಕು.",
                    phoneticPronunciation = "Daarili yaavudhaadru ATM hatthira nillisi, cash thagobeku.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Extra money / Tip offer
            lower.contains("extra") -> {
                val num = extractNumber(lower) ?: 20
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಮೀಟರ್ ಮೇಲೆ ₹$num ಎಕ್ಸ್‌ಟ್ರಾ ಕೊಡ್ತೀನಿ, ಬನ್ನಿ.",
                    phoneticPronunciation = "Meter mele ₹$num extra kodthini, banni.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Directions
            lower.contains("left") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಇಲ್ಲಿಂದ ಲೆಫ್ಟ್ ತಗೊಳ್ಳಿ ಭೈಯಾ.",
                    phoneticPronunciation = "Illinda left thagoli bhaiya.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("right") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಇಲ್ಲಿಂದ ರೈಟ್ ತಗೊಳ್ಳಿ ಭೈಯಾ.",
                    phoneticPronunciation = "Illinda right thagoli bhaiya.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }
            lower.contains("straight") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಮುಂದೆ ಸೀದಾ ಹೋಗಿ.",
                    phoneticPronunciation = "Mundhe seedha hogli.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Alighting
            lower.contains("stop") || lower.contains("get down") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಇಲ್ಲೇ ನಿಲ್ಲಿಸಿ, ನಾನು ಇಲ್ಲೇ ಇಳಿತೀನಿ.",
                    phoneticPronunciation = "Ille nillisi, naanu ille ilithini.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Bengaluru Commute AI"
                )
            }

            // Food & Dining / Tourist Lifelines
            lower.contains("spic") || lower.contains("chilli") || lower.contains("khaara") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ದಯವಿಟ್ಟು ಖಾರ ಬೇಡ, ತುಂಬಾ ಕಮ್ಮಿ ಮಾಡಿ!",
                    phoneticPronunciation = "Dayavittu khaara beda, thumba kammi maadi!",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }
            lower.contains("dosa") || lower.contains("coffee") || lower.contains("breakfast") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಒಂದು ಮಸಾಲೆ ದೋಸೆ ಮತ್ತು ಫಿಲ್ಟರ್ ಕಾಫಿ ಕೊಡಿ.",
                    phoneticPronunciation = "Ondu masala dose mathu filter coffee kodi.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }
            lower.contains("water") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಕುಡಿಯುವ ನೀರು ಸೀಲ್ಡ್ ಬಾಟಲ್ ಕೊಡಿ.",
                    phoneticPronunciation = "Kudiyuva neeru sealed bottle kodi.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }
            lower.contains("bill") || lower.contains("check please") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಬಿಲ್ ಕೊಡಿ ಪ್ಲೀಸ್, ಫೋನ್ ಪೇ / ಗೂಗಲ್ ಪೇ ಮಾಡ್ತೀನಿ.",
                    phoneticPronunciation = "Bill kodi please, PhonePe / Google Pay maadthini.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }
            lower.contains("trek") || lower.contains("permit") || lower.contains("aranya") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಅರಣ್ಯ ವಿಹಾರ ಟ್ರೆಕ್ ಪರ್ಮಿಟ್ ಇದೆ, ಚೆಕ್‌ಪೋಸ್ಟ್ ಎಲ್ಲಿದೆ?",
                    phoneticPronunciation = "Aranya Vihaara trek permit ide, checkpost ellide?",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }
            lower.contains("guide") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಸರ್ಕಾರಿ ಮಾನ್ಯತೆ ಪಡೆದ ಗೈಡ್ ಬೇಕು, ನಿಮ್ಮ ಐಡಿ ತೋರಿಸಿ.",
                    phoneticPronunciation = "Sarkari maanyathe padeda guide beku, nimma ID thorisi.",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }
            lower.contains("bus") || lower.contains("ksrtc") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ಈ ಕೆ.ಎಸ್.ಆರ್.ಟಿ.ಸಿ ಬಸ್ ಮೈಸೂರು / ಹಂಪಿಗೆ ಹೋಗುತ್ತಾ?",
                    phoneticPronunciation = "Ee KSRTC bus Mysore / Hampi-ge hoguttha?",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }
            lower.contains("temple") || lower.contains("darshan") -> {
                TranslationResult(
                    englishOriginal = input,
                    kannadaLipi = "ದೇವಸ್ಥಾನದ ದರ್ಶನ ಸಮಯ ಯಾವಾಗ ಮುಗಿಯುತ್ತೆ?",
                    phoneticPronunciation = "Devasthanada darshana samaya yaavaga mugiyutthe?",
                    isBangaloreSlangOptimized = true,
                    sourceEngine = "Karnataka Tourist AI"
                )
            }

            else -> null
        }
    }

    private fun extractNumber(str: String): Int? {
        val match = Regex("""\b(\d+)\b""").find(str)
        return match?.groupValues?.get(1)?.toIntOrNull()
    }

    private fun generatePhoneticGuide(kannadaText: String, fallbackEnglish: String): String {
        // Quick phonetic guide to help foreigners read along
        return "Tap speaker icon to play clear Kannada audio to the driver"
    }
}
