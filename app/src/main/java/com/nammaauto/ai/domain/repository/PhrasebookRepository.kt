package com.nammaauto.ai.domain.repository

import com.nammaauto.ai.domain.model.KannadaPhrase
import com.nammaauto.ai.domain.model.PhraseCategory

object PhrasebookRepository {

    val phrases: List<KannadaPhrase> = listOf(
        // Category 1: Catching Auto / Hailing
        KannadaPhrase(
            id = "hail_1",
            category = PhraseCategory.HAILING,
            kannadaLipi = "ಮೆಟ್ರೋ ಸ್ಟೇಷನ್ ಹತ್ತಿರ ಬರ್ತೀರಾ?",
            phoneticEnglish = "Metro station hatthira barthira?",
            englishMeaning = "Will you come near the Metro station?",
            contextTip = "Polite opening greeting when stopping an auto."
        ),
        KannadaPhrase(
            id = "hail_2",
            category = PhraseCategory.HAILING,
            kannadaLipi = "ಇಂದಿರಾನಗರ 100 ಫೀಟ್ ರೋಡ್‌ಗೆ ಹೋಗ್ಬೇಕು.",
            phoneticEnglish = "Indiranagar hundred feet road-ge hogbeku.",
            englishMeaning = "Need to go to Indiranagar 100 Feet Road.",
            contextTip = "Clear landmark assertion."
        ),
        KannadaPhrase(
            id = "hail_3",
            category = PhraseCategory.HAILING,
            kannadaLipi = "ಸೋನಿ ಸಿಗ್ನಲ್ ಕೊರಮಂಗಲ ಕಡೆ ಹೋಗ್ತೀರಾ?",
            phoneticEnglish = "Sony signal Koramangala kade hogthira?",
            englishMeaning = "Are you going towards Sony Signal, Koramangala?",
            contextTip = "Useful during peak hours when autos prefer specific directions."
        ),
        KannadaPhrase(
            id = "hail_4",
            category = PhraseCategory.HAILING,
            kannadaLipi = "ಅರ್ಜೆಂಟ್ ಆಗಿದೆ ಭೈಯಾ, ಬೇಗ ಬಿಡಿ.",
            phoneticEnglish = "Urgent aagide bhaiya, bega bidi.",
            englishMeaning = "It's urgent brother, please drop quickly.",
            contextTip = "Polite urgency appeal."
        ),

        // Category 2: Enforcing Lawful Meter
        KannadaPhrase(
            id = "meter_1",
            category = PhraseCategory.METER,
            kannadaLipi = "ಮೀಟರ್ ಹಾಕಿ ಬರ್ತೀರಾ?",
            phoneticEnglish = "Meter haaki barthira?",
            englishMeaning = "Will you come by meter?",
            contextTip = "Essential #1 Bangalore auto question."
        ),
        KannadaPhrase(
            id = "meter_2",
            category = PhraseCategory.METER,
            kannadaLipi = "ಭೈಯಾ, ಮೀಟರ್ ಆನ್ ಮಾಡಿ ಪ್ಲೀಸ್.",
            phoneticEnglish = "Bhaiya, meter on maadi please.",
            englishMeaning = "Brother, please turn on the meter.",
            contextTip = "Say immediately once seated if driver forgets to pull meter down."
        ),
        KannadaPhrase(
            id = "meter_3",
            category = PhraseCategory.METER,
            kannadaLipi = "ದಯವಿಟ್ಟು ಮೀಟರ್ ಹಾಕಿ, ಎಕ್ಸ್‌ಟ್ರಾ ಬೇಡ.",
            phoneticEnglish = "Dayavittu meter haaki, extra beda.",
            englishMeaning = "Please put meter, no extra arbitrary charges.",
            contextTip = "Firm and respectful refusal of fixed price demands."
        ),
        KannadaPhrase(
            id = "meter_4",
            category = PhraseCategory.METER,
            kannadaLipi = "ಟ್ರಾಫಿಕ್ ಪೊಲೀಸ್ ರೂಲ್ಸ್ ಪ್ರಕಾರ ಮೀಟರ್ ಹಾಕ್ಬೇಕು.",
            phoneticEnglish = "Traffic Police rules prakara meter haakbeku.",
            englishMeaning = "As per Traffic Police rules, meter must be run.",
            contextTip = "Lawful reference if driver refuses meter point blank."
        ),

        // Category 3: Smart Fair Haggling & Counter-Offers
        KannadaPhrase(
            id = "nego_1",
            category = PhraseCategory.NEGOTIATE,
            kannadaLipi = "ಭೈಯಾ, ಒನ್ ಅಂಡ್ ಹಾಫ್ ಯಾಕೆ? ಇದು ರೆಗ್ಯುಲರ್ ಟೈಮ್!",
            phoneticEnglish = "Bhaiya, one-and-half yaake? Idhu regular time!",
            englishMeaning = "Brother, why 1.5x? This is regular daytime!",
            contextTip = "Call out false night charge demands during daylight."
        ),
        KannadaPhrase(
            id = "nego_2",
            category = PhraseCategory.NEGOTIATE,
            kannadaLipi = "ಗವರ್ನಮೆಂಟ್ ರೇಟ್ ₹65 ಆಗುತ್ತೆ, ₹80 ಕೊಡ್ತೀನಿ, ಬರ್ತೀರಾ?",
            phoneticEnglish = "Govt rate arupattaydu aaguthe, embhattu kodthini, barthira?",
            englishMeaning = "Govt rate is ₹65, I will give ₹80, will you come?",
            contextTip = "Win-Win counter offer: shows you know the legal rate and are generous."
        ),
        KannadaPhrase(
            id = "nego_3",
            category = PhraseCategory.NEGOTIATE,
            kannadaLipi = "₹200 ತುಂಬಾ ಜಾಸ್ತಿ ಆಯ್ತು, ₹130 ಫಿಕ್ಸ್ ಮಾಡ್ಕೊಳ್ಳಿ.",
            phoneticEnglish = "Ernuru thumba jaasti aaythu, noora moovatthu fix maadkolli.",
            englishMeaning = "₹200 is too much, let's fix ₹130.",
            contextTip = "Brings excessive demands down to realistic rates."
        ),
        KannadaPhrase(
            id = "nego_4",
            category = PhraseCategory.NEGOTIATE,
            kannadaLipi = "ಡೈಲಿ ಇದೇ ರೂಟ್‌ಗೆ ಬರ್ತೀನಿ, ನನಗೆ ರೇಟ್ ಗೊತ್ತು.",
            phoneticEnglish = "Daily idhe route-ge barthini, nanage rate gothu.",
            englishMeaning = "I travel this route daily, I know the exact fare.",
            contextTip = "Signals that you are not a naive tourist or newcomer."
        ),
        KannadaPhrase(
            id = "nego_5",
            category = PhraseCategory.NEGOTIATE,
            kannadaLipi = "ರೌಂಡ್ ಆಫ್ ಮಾಡಿ ₹100 ತಗೊಳ್ಳಿ.",
            phoneticEnglish = "Round off maadi nooru rupayi thagoli.",
            englishMeaning = "Round it off and take ₹100.",
            contextTip = "Convenient change settlement."
        ),

        // Category 4: Directions & Drops
        KannadaPhrase(
            id = "dir_1",
            category = PhraseCategory.DIRECTIONS,
            kannadaLipi = "ಮುಂದೆ ರೈಟ್ ತಗೊಳ್ಳಿ.",
            phoneticEnglish = "Mundhe right thagoli.",
            englishMeaning = "Take the upcoming right.",
            contextTip = "Direction navigation."
        ),
        KannadaPhrase(
            id = "dir_2",
            category = PhraseCategory.DIRECTIONS,
            kannadaLipi = "ಲೆಫ್ಟ್ ಸೈಡ್‌ನಲ್ಲಿ ನಿಲ್ಲಿಸಿ ಭೈಯಾ.",
            phoneticEnglish = "Left side-nalli nillisi bhaiya.",
            englishMeaning = "Stop on the left side, brother.",
            contextTip = "Approaching destination."
        ),
        KannadaPhrase(
            id = "dir_3",
            category = PhraseCategory.DIRECTIONS,
            kannadaLipi = "ಸಿಗ್ನಲ್ ಕ್ರಾಸ್ ಮಾಡಿ ಡ್ರಾಪ್ ಮಾಡಿ.",
            phoneticEnglish = "Signal cross maadi drop maadi.",
            englishMeaning = "Cross the traffic signal and drop me.",
            contextTip = "Avoids crossing busy junction on foot."
        ),
        KannadaPhrase(
            id = "dir_4",
            category = PhraseCategory.DIRECTIONS,
            kannadaLipi = "ಯುಪಿಐ / ಗೂಗಲ್ ಪೇ ಮಾಡ್ತೀನಿ, ಕ್ಯೂಆರ್ ಕೋಡ್ ಇದೆಯಾ?",
            phoneticEnglish = "UPI / Google Pay maadthini, QR code ideya?",
            englishMeaning = "I will pay via UPI/Google Pay, do you have QR code?",
            contextTip = "Most Bangalore autos carry PhonePe/GPay QR stands."
        ),

        // Category 5: Rain & Night Surge
        KannadaPhrase(
            id = "rain_1",
            category = PhraseCategory.RAIN_SURGE,
            kannadaLipi = "ಮಳೆ ಬರ್ತಿದೆ ಅಂತ ಗೊತ್ತು, ಮೀಟರ್ ಮೇಲೆ ₹30 ಎಕ್ಸ್‌ಟ್ರಾ ಕೊಡ್ತೀನಿ.",
            phoneticEnglish = "Male barthide anta gothu, meter mele muvvatthu extra kodthini.",
            englishMeaning = "I know it's raining, I will pay ₹30 extra over meter.",
            contextTip = "Guaranteed way to get an auto during Bangalore monsoon rain."
        ),
        KannadaPhrase(
            id = "rain_2",
            category = PhraseCategory.RAIN_SURGE,
            kannadaLipi = "ನೈಟ್ ಚಾರ್ಜ್ ರಾತ್ರಿ 10 ಗಂಟೆ ಆಮೇಲೆ ಅಲ್ವಾ? ಈಗಷ್ಟೇ 8 ಗಂಟೆ!",
            phoneticEnglish = "Night charge raathri hatthu gante aamele alwa? Eegashtey entu gante!",
            englishMeaning = "Night charge applies after 10 PM right? It is only 8 PM now!",
            contextTip = "Refuting early night surcharge claims."
        ),

        // Category 6: Safety & Emergency
        KannadaPhrase(
            id = "safe_1",
            category = PhraseCategory.SAFETY,
            kannadaLipi = "ಇಲ್ಲೇ ನಿಲ್ಲಿಸಿ, ನಾನು ಇಲ್ಲೇ ಇಳಿತೀನಿ!",
            phoneticEnglish = "Ille nillisi, naanu ille ilithini!",
            englishMeaning = "Stop here immediately, I will alight right here!",
            contextTip = "Emergency exit command."
        ),
        KannadaPhrase(
            id = "safe_2",
            category = PhraseCategory.SAFETY,
            kannadaLipi = "ರಾಂಗ್ ರೂಟ್ ಹೋಗ್ತಿದ್ದೀರಾ, ಮೇನ್ ರೋಡ್ ತಗೊಳ್ಳಿ.",
            phoneticEnglish = "Wrong route hogthiddira, main road thagoli.",
            englishMeaning = "You are taking the wrong route, please stay on the main road.",
            contextTip = "Firm correction if driver veers off track."
        ),
        KannadaPhrase(
            id = "safe_3",
            category = PhraseCategory.SAFETY,
            kannadaLipi = "ಲೈವ್ ಲೊಕೇಶನ್ ಶೇರ್ ಮಾಡಿದ್ದೀನಿ ನನ್ನ ಫ್ಯಾಮಿಲಿ ಜೊತೆ.",
            phoneticEnglish = "Live location share maadiddini nanna family jothe.",
            englishMeaning = "I have shared my live location with my family.",
            contextTip = "Subtle safety deterrent for late night trips."
        )
    )

    fun getPhrasesByCategory(category: PhraseCategory): List<KannadaPhrase> {
        return phrases.filter { it.category == category }
    }
}
