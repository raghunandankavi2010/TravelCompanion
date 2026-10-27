# Travel Companion: The #1 Karnataka Tourism & Real-Time Travel Guide Super App 🧭🌿

> **The Ultimate On-Device AI & Real-Time Travel Companion for Karnataka.**  
> Built for serious travelers, nature lovers, temple pilgrims, and road trippers. Powered by real-time web scraping, verified ground-truth travel intelligence, pure vegetarian dining guides, dynamic trip cost estimation, and 100% non-copyrighted media.

---

## 🌟 The Vision: Karnataka's Premier Travel Super App

Travelers exploring Karnataka face high friction:
1. **Scattered Information:** Travelers have to juggle dozens of government portals (`aranyavihaara.karnataka.gov.in`, `asi.nic.in`, `ksrtc.in`, `junglelodges.com`, temple trust sites) with outdated blogs.
2. **Real-Time Ground Reality Gaps:** Unplanned roadblocks, night traffic bans (e.g. strict 9 PM – 6 AM closure in Bandipur & Nagarhole), trekking permit caps (strict 50/day Aranya Vihaara limit for Kudremukh), and spot queue surprises.
3. **Vegetarian & Satvik Food Anxiety:** Finding reliable 100% pure vegetarian highway pitstops, heritage Darshinis, and sacred temple Annadana (free blessed sanctum meals) across Karnataka's highway corridors.
4. **Budget & Cost Opacity:** Unclear pricing for transport, fuel, safaris, and dual pricing for foreign tourists.
5. **Copyrighted Media Clutter:** Difficulty accessing genuine, high-definition, royalty-free photography and 4K aerial tours with clear Creative Commons attribution.

---

## 🚀 Key Engineering & Product Features

### 1. 🌐 Real-Time Net Scraper & Live Travel Intel
- **Live Web Streamer:** Asynchronously queries the Wikipedia/Wikimedia REST API in real time (`LiveTravelScraperService.kt`), streaming live extracts, descriptions, coordinates, and copyright-free thumbnails for any Karnataka town, temple, waterfall, or wildlife sanctuary with offline fail-safe caching.
- **Regional Live Weather Radar:** Instant climate and rain probability radar across 4 distinct climatic zones:
  - *Malnad & Western Ghats* (Chikkamagaluru, Coorg, Agumbe)
  - *Coastal Karavali* (Gokarna, Udupi, Mangaluru, Murudeshwar)
  - *Heritage North* (Hampi, Badami, Vijayapura)
  - *Royal South* (Mysuru, Kabini, Bandipur)
- **Live Gates & Permit Radar:**
  - Bandipur & Nagarhole Tiger Reserve mandatory 9:00 PM – 6:00 AM highway night closure countdown.
  - Aranya Vihaara Trekking Permits quota verification.
  - Netrani Scuba Island active sea season radar (October to May).
  - Mysuru Palace 100,000 golden bulbs illumination alert (Sundays 7:00 PM – 8:00 PM).

### 2. 🍲 Pure Veg Dining & Sacred Temple Annadana Directory
- **Highway Pure Veg Oases:**
  - *Kamath Lokaruchi (Ramanagara, NH 275)*: Famous Thatte Idli, Kadabu, Filter Coffee, Jowar Roti meals.
  - *Paakashala (Kunigal, NH 75)*: 100% Pure Veg, spotless amenities, EV fast chargers.
  - *Shivalli Restaurant (Channarayapatna, NH 75)*: Authentic South Kanara Udupi style dining.
  - *Swathi Delicacy (Yediyur)*: Rapid 5-minute highway breakfast service.
- **Sacred Temple Annadana (Free Divine Satvik Meals):**
  - *Dharmasthala Sri Manjunatha Annapoorna Dining Hall*: Serving 30,000+ pilgrims daily with zero charge.
  - *Sringeri Sharada Peetham Sri Bharati Tirtha Bhojanashala*: Pure satvik meals on plantain leaves by Tunga river.
  - *Horanadu Sri Annapoorneshwari Nitya Annadana*: Sumptuous 3-course sacred mountain feast.
  - *Udupi Sri Krishna Matha Chowki Bhojana*: Birthplace of authentic global Udupi cuisine.
  - *Kukke Subramanya Swamy Annadana Mandiram*: High-capacity pilgrim meal facility.
- **Heritage Tiffin Legends & Sweet Creators:**
  - *Mitra Samaj (Udupi Car Street, Est. 1949)*: Creators of authentic Mangalore Buns & Goli Baje.
  - *Hotel Vinayaka Mylari (Nazarbad, Mysuru)*: World-renowned cloud-soft butter Mylari Dosa.
  - *Guru Sweet Mart (Devaraja Market, Mysuru)*: Direct descendants of royal chef Kakasura Madappa, creators of Mysore Pak.
  - *Vidyarthi Bhavan & MTR (Bengaluru)*: Iconic 1924/1943 heritage institutions.
  - *Babu Singh Mishra Peda (Dharwad)*: GI-tagged authentic caramelized buffalo-milk Dharwad Peda.
  - *Basaveshwar Khanavali (Hubballi)*: North Karnataka Lingayat Jolada Rotti, Ennegai & Shenga Chutney.
- **1-Tap Food Voice:** Native Kannada TTS speech for ordering ("Idu pure veg alwa?", "Swalpa tuppa haaki", "Filter coffee ondu").

### 3. 💰 Dynamic Real-Time Trip Cost Estimator
- **Itemized Multi-Category Budget Planner:**
  - Transport (KSRTC Airavat / EV Bus, Indian Railways, Self-Drive fuel+toll, Outstation taxi).
  - Stay (Malnad/Coastal Homestay, KSTDC Govt Mayura Hotels, Luxury Jungle Lodges).
  - Dining (Satvik Temple Annadana, Pure Veg Darshinis, Premium Fine Dine).
  - Official monument & forest safari entry tickets (Domestic INR vs International Foreigner USD).
- **Live Output:** Calculates Total Estimated Cost, Per-Person Cost, Per-Day Cost, and round-trip highway distance.
- **Smart Money-Saving Tips:** Direct links to book KSRTC (10% return discount) and ASI monuments online.

### 4. 📸 Non-Copyrighted Media Showcase & 4K Video Tours
- **100% Royalty-Free Guarantee:** Every photo is sourced from Creative Commons CC BY-SA 4.0 (Wikimedia Commons) or Unsplash Free License, verified free from copyright infringement.
- **4K Cinematic Aerial Tours:** 1-tap launch for authorized Department of Tourism 4K aerial walkthroughs and documentaries.
- **Interactive Filters:** Filter by Temples, Wildlife, Trekking, Coastal Beaches, Islands, and Vegetarian Cuisines.

### 5. 🗣️ On-Location Kannada Voice Assistant & Dialect Explorer
- Real-time on-device Kannada Text-To-Speech synthesis (`Locale("kn", "IN")`).
- Authentic dialect phrases for *Uttara Karnataka (Dharwad/Belagavi)*, *Coastal Karavali (Kundagannada/Tulu)*, *Malnad*, and *Royal Mysuru*.

---

## 🛠️ Tech Stack & Clean Architecture

- **Language:** Kotlin 2.4.21
- **UI Framework:** Jetpack Compose with Material 3 & Modern Travel Palette
- **Asynchronous Flow:** Kotlin Coroutines & StateFlow
- **Image Pipeline:** Coil 3.6.3 (`coil-compose` + `coil-network-okhttp`)
- **Networking:** Native `java.net.HttpURLConnection` with Wikipedia/Wikimedia REST API & JSON streaming
- **On-Device Audio:** Android TextToSpeech (`Locale("kn", "IN")`)
- **Build System:** Gradle 9.7.1 + Android Gradle Plugin 9.4.1 (Kotlin DSL)

---

## 📲 How to Run in Android Studio

1. Android Studio is already opened at `/home/raghunandan/StudioProjects/NammaAutoAI`.
2. Notice the project has been renamed to **Travel Companion** (`app_name` and `rootProject.name = "TravelCompanion"`).
3. In Android Studio, click **Sync Project with Gradle Files** (or hit the green Hammer/Run button).
4. Run on your connected Android device or emulator (`Medium_Phone`).
