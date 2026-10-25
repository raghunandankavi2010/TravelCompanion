package com.nammaauto.ai.domain.repository

import com.nammaauto.ai.domain.model.KannadaPhrase
import com.nammaauto.ai.domain.model.PhraseCategory

object PhrasebookRepository {

    val phrases: List<KannadaPhrase> = listOf(
        // Category 0: No-Meter Rides / App-Rate Matcher (#1 Bengaluru Pain Point)
        KannadaPhrase(
            id = "nometer_1",
            category = PhraseCategory.NO_METER,
            kannadaLipi = "ಮೀಟರ್ ಬೇಡ, ಆಪ್ ರೇಟ್ ₹60 ಕೊಡ್ತೀನಿ, ಬರ್ತೀರಾ?",
            phoneticEnglish = "Meter beda, app rate aravattu rupayi kodthini, barthira?",
            englishMeaning = "No meter needed, I will pay app rate ₹60, will you come?",
            contextTip = "The ultimate icebreaker when driver flatly says 'Meter illa'."
        ),
        KannadaPhrase(
            id = "nometer_2",
            category = PhraseCategory.NO_METER,
            kannadaLipi = "ನಮ್ಮ ಯಾತ್ರಿ ರೇಟ್ ₹55 ಇದೆ, ಡೈರೆಕ್ಟ್ ಕ್ಯಾಶ್ ಕೊಡ್ತೀನಿ.",
            phoneticEnglish = "Namma Yatri rate eevatthaydu ide, direct cash kodthini.",
            englishMeaning = "Namma Yatri rate is ₹55, I will pay you direct in cash.",
            contextTip = "Anchors the price to Namma Yatri rather than driver's arbitrary quote."
        ),
        KannadaPhrase(
            id = "nometer_3",
            category = PhraseCategory.NO_METER,
            kannadaLipi = "1.5 ಕಿ.ಮೀ ಗೆ ₹120 ಯಾಕೆ ಭೈಯಾ? ₹60 ಫಿಕ್ಸ್ ಮಾಡಿ.",
            phoneticEnglish = "Onduvare km ge noora ippatthu yaake bhaiya? Aravattu fix maadi.",
            englishMeaning = "Why ₹120 for just 1.5 km brother? Let's fix ₹60.",
            contextTip = "Firm polite pushback when driver quotes 2x the app rate."
        ),
        KannadaPhrase(
            id = "nometer_4",
            category = PhraseCategory.NO_METER,
            kannadaLipi = "ಆಪ್ ಕಮಿಷನ್ ಕಟ್ ಆಗಲ್ಲ, ಪೂರ್ತಿ ₹60 ನಿಮಗೇ ಸಿಗುತ್ತೆ!",
            phoneticEnglish = "App commission cut aagalla, poorthi aravattu nimagey sigutthe!",
            englishMeaning = "No app commission cut, you get the full ₹60 in hand!",
            contextTip = "Key psychological lever: drivers love cash without platform cuts."
        ),
        KannadaPhrase(
            id = "nometer_5",
            category = PhraseCategory.NO_METER,
            kannadaLipi = "ಮೀಟರ್ ಆನ್ ಮಾಡಲ್ಲ ಅಂದ್ರೆ ಸೀದಾ ₹50 ಕೊಡ್ತೀನಿ, ಬನ್ನಿ.",
            phoneticEnglish = "Meter on maadalla andre seedha eevatthu kodthini, banni.",
            englishMeaning = "If you won't turn on meter, I will pay flat ₹50, let's go.",
            contextTip = "Quick short-distance fixed rate agreement."
        ),

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
        ),

        // Category 7: Tourist & Sightseeing
        KannadaPhrase(
            id = "tour_1",
            category = PhraseCategory.TOURIST_SIGHTSEEING,
            kannadaLipi = "ಅರಣ್ಯ ವಿಹಾರ ಟ್ರೆಕ್ ಪರ್ಮಿಟ್ ಇದೆ, ಚೆಕ್‌ಪೋಸ್ಟ್ ಎಲ್ಲಿದೆ?",
            phoneticEnglish = "Aranya Vihaara trek permit ide, checkpost ellide?",
            englishMeaning = "I have an Aranya Vihaara trek permit, where is the checkpost?",
            contextTip = "Essential question when arriving at Kudremukh / Skandagiri / Kumara Parvatha bases."
        ),
        KannadaPhrase(
            id = "tour_2",
            category = PhraseCategory.TOURIST_SIGHTSEEING,
            kannadaLipi = "ಸರ್ಕಾರಿ ಮಾನ್ಯತೆ ಪಡೆದ ಗೈಡ್ ಬೇಕು, ನಿಮ್ಮ ಐಡಿ ತೋರಿಸಿ.",
            phoneticEnglish = "Sarkari maanyathe padeda guide beku, nimma ID thorisi.",
            englishMeaning = "We want a government authorized guide, please show your ID card.",
            contextTip = "Safeguard against unauthorized touts at Hampi, Belur, or Mysore Palace."
        ),
        KannadaPhrase(
            id = "tour_3",
            category = PhraseCategory.TOURIST_SIGHTSEEING,
            kannadaLipi = "ಎಂಟ್ರಿ ಟಿಕೆಟ್‌ಗೆ ಗೂಗಲ್ ಪೇ ಅಥವಾ ಕಾರ್ಡ್ ಆಗ್ತದಾ?",
            phoneticEnglish = "Entry ticket-ge Google Pay athava card aagthada?",
            englishMeaning = "Do you accept Google Pay or card for the entry ticket?",
            contextTip = "Verifying digital payment at monument or museum counters."
        ),
        KannadaPhrase(
            id = "tour_4",
            category = PhraseCategory.TOURIST_SIGHTSEEING,
            kannadaLipi = "ಒಳಗಡೆ ಕ್ಯಾಮೆರಾ ಮತ್ತು ಫೋಟೋ ತೆಗೆಯೋಕೆ ಅನುಮತಿ ಇದೆಯಾ?",
            phoneticEnglish = "Olagade camera mathu photo thegeyoke anumathi ideya?",
            englishMeaning = "Is camera and photography permitted inside?",
            contextTip = "Always confirm before photographing inside temple sanctums or ASI monuments."
        ),
        KannadaPhrase(
            id = "tour_5",
            category = PhraseCategory.TOURIST_SIGHTSEEING,
            kannadaLipi = "ದೇವಸ್ಥಾನದ ದರ್ಶನ ಸಮಯ ಯಾವಾಗ ಮುಗಿಯುತ್ತೆ?",
            phoneticEnglish = "Devasthanada darshana samaya yaavaga mugiyutthe?",
            englishMeaning = "When do the temple darshan timings close?",
            contextTip = "Helpful when planning afternoon temple visits (most close 1:00 PM – 4:00 PM)."
        ),

        // Category 8: Food & Darshini Dining
        KannadaPhrase(
            id = "food_1",
            category = PhraseCategory.FOOD_DINING,
            kannadaLipi = "ದಯವಿಟ್ಟು ಖಾರ ಬೇಡ, ತುಂಬಾ ಕಮ್ಮಿ ಮಾಡಿ!",
            phoneticEnglish = "Dayavittu khaara beda, thumba kammi maadi!",
            englishMeaning = "Please no spicy, make it very mild / not spicy!",
            contextTip = "Number one lifeline phrase for foreign travelers and sensitive eaters."
        ),
        KannadaPhrase(
            id = "food_2",
            category = PhraseCategory.FOOD_DINING,
            kannadaLipi = "ಒಂದು ಮಸಾಲೆ ದೋಸೆ ಮತ್ತು ಫಿಲ್ಟರ್ ಕಾಫಿ ಕೊಡಿ.",
            phoneticEnglish = "Ondu masala dose mathu filter coffee kodi.",
            englishMeaning = "Please give one Masala Dosa and one Filter Coffee.",
            contextTip = "The iconic breakfast order at any Bangalore Darshini (CTR, Vidyarthi Bhavan, MTR)."
        ),
        KannadaPhrase(
            id = "food_3",
            category = PhraseCategory.FOOD_DINING,
            kannadaLipi = "ಕುಡಿಯುವ ನೀರು ಸೀಲ್ಡ್ ಬಾಟಲ್ ಇದೆಯಾ?",
            phoneticEnglish = "Kudiyuva neeru sealed bottle ideya?",
            englishMeaning = "Do you have a sealed bottle of drinking water?",
            contextTip = "Ensures safe, hygienically packaged water."
        ),
        KannadaPhrase(
            id = "food_4",
            category = PhraseCategory.FOOD_DINING,
            kannadaLipi = "ಬಿಲ್ ಕೊಡಿ ಪ್ಲೀಸ್, ಫೋನ್ ಪೇ ಮಾಡ್ತೀನಿ.",
            phoneticEnglish = "Bill kodi please, PhonePe maadthini.",
            englishMeaning = "Please give the bill, I will pay via PhonePe.",
            contextTip = "Quick settlement at restaurants and cafe tables."
        ),
        KannadaPhrase(
            id = "food_5",
            category = PhraseCategory.FOOD_DINING,
            kannadaLipi = "ಇದು ಪ್ಯೂರ್ ವೆಜಿಟೇರಿಯನ್ ಹೋಟೆಲ್ ಅಲ್ವಾ?",
            phoneticEnglish = "Idu pure vegetarian hotel alwa?",
            englishMeaning = "This is a pure vegetarian restaurant right?",
            contextTip = "Confirmation for strict vegetarians and vegans."
        ),

        // Category 9: Hotels & Shopping
        KannadaPhrase(
            id = "shop_1",
            category = PhraseCategory.HOTEL_SHOPPING,
            kannadaLipi = "ಇದಕ್ಕೆ ರೀಸನೇಬಲ್ ರೇಟ್ ಹೇಳಿ, ಸ್ವಲ್ಪ ಕಮ್ಮಿ ಮಾಡಿ.",
            phoneticEnglish = "Idakke reasonable rate heli, swalpa kammi maadi.",
            englishMeaning = "Tell a reasonable price for this, please give a discount.",
            contextTip = "Polite bargaining at handicraft, spice, and souvenir markets."
        ),
        KannadaPhrase(
            id = "shop_2",
            category = PhraseCategory.HOTEL_SHOPPING,
            kannadaLipi = "ಇದು ಅಸಲಿ ಮೈಸೂರು ರೇಷ್ಮೆ ಸೀರೆನಾ? ಗವರ್ನಮೆಂಟ್ ಸಿಲ್ಕ್ ಮಾರ್ಕ್ ಇದೆಯಾ?",
            phoneticEnglish = "Idu asali Mysore Reshme Sareena? Govt silk mark ideya?",
            englishMeaning = "Is this authentic Mysore Silk? Does it have the govt Silk Mark?",
            contextTip = "Crucial test to avoid synthetic knockoffs in tourist bazaars."
        ),
        KannadaPhrase(
            id = "shop_3",
            category = PhraseCategory.HOTEL_SHOPPING,
            kannadaLipi = "ರೂಮ್‌ನಲ್ಲಿ 24 ಗಂಟೆ ಬಿಸಿ ನೀರು ಬರುತ್ತಾ?",
            phoneticEnglish = "Room-nalli 24 gante bisi neeru baruttha?",
            englishMeaning = "Does the room have 24-hour hot water?",
            contextTip = "Important check for homestays and budget hotels in hill stations (Coorg, Chikkamagaluru)."
        ),

        // Category 10: Bus & Intercity Transit
        KannadaPhrase(
            id = "bus_1",
            category = PhraseCategory.TRANSIT_BUS,
            kannadaLipi = "ಈ ಕೆ.ಎಸ್.ಆರ್.ಟಿ.ಸಿ ಬಸ್ ಮೈಸೂರು / ಹಂಪಿಗೆ ಹೋಗುತ್ತಾ?",
            phoneticEnglish = "Ee KSRTC bus Mysore / Hampi-ge hoguttha?",
            englishMeaning = "Does this KSRTC bus go to Mysore / Hampi?",
            contextTip = "Verifying long-distance bus destination at Majestic / Satellite bus stand."
        ),
        KannadaPhrase(
            id = "bus_2",
            category = PhraseCategory.TRANSIT_BUS,
            kannadaLipi = "ಏರ್‌ಪೋರ್ಟ್ ಫ್ಲೈಬಸ್ (Flybus) ಪ್ಲಾಟ್‌ಫಾರ್ಮ್ ಎಲ್ಲಿದೆ?",
            phoneticEnglish = "Airport Flybus platform ellide?",
            englishMeaning = "Where is the platform for the Airport Flybus?",
            contextTip = "Direction query for direct airport AC buses from Mysore/Bangalore."
        ),
        KannadaPhrase(
            id = "bus_3",
            category = PhraseCategory.TRANSIT_BUS,
            kannadaLipi = "ಮುಂದಿನ ಸ್ಟಾಪ್‌ನಲ್ಲಿ ಇಳಿತೀನಿ, ದಯವಿಟ್ಟು ನಿಲ್ಲಿಸಿ.",
            phoneticEnglish = "Mundina stop-nalli ilithini, dayavittu nillisi.",
            englishMeaning = "I will get down at the next stop, please stop.",
            contextTip = "Alerting the bus conductor and driver before arriving at your stop."
        )
    )

    fun getPhrasesByCategory(category: PhraseCategory): List<KannadaPhrase> {
        return phrases.filter { it.category == category }
    }
}
