# Play Store release checklist

## 1. One-time setup
- [ ] **Upload key.** In Android Studio choose Build → Generate Signed Bundle, then create a keystore, kept
      **outside** the repo and backed up. Then create `keystore.properties` in the project root (git-ignored):
      ```
      storeFile=../keys/travelcompanion-upload.jks
      storePassword=…
      keyAlias=upload
      keyPassword=…
      ```
      Enrol in **Play App Signing** when creating the app. Google then holds the app signing key, and your key
      only signs uploads.
- [ ] **Application ID.** `com.namma.travelcompanion` is permanent once published. Change it in `app/build.gradle.kts`
      now if you want a different one.
- [ ] **Privacy policy URL.** Host `docs/PRIVACY_POLICY.md` publicly (e.g. GitLab Pages, or the file's GitLab
      URL in a public repo) after filling in your contact email.

## 2. Full Access product
- [ ] Play Console → Monetize → Products → **In-app products** → Create:
  - Product ID: `full_access` (must match `BillingManager.FULL_ACCESS_PRODUCT_ID`)
  - Type: one-time, non-consumable (the app never consumes it)
  - Price: e.g. ₹49–₹99 in India; let Play convert for other countries
- [ ] Products only appear after the app is uploaded to a testing track. Add yourself as a **licence tester**
      (Settings → License testing) to buy with test cards at no cost.
- [ ] Test the purchase, then a refund from the Order management page: on next launch the app should go back
      to the free tier.

## 3. App content declarations
- [ ] **Data safety form:**
  - Location (precise): collected? **No**. It is processed on the device and only shared by the user's own action
    in an SOS message. Under Play's definitions, user-initiated sharing is not "collection".
  - Audio: processed by the system speech service. Declare **"Voice or sound recordings: processed, not stored,
    not shared by the app"**, ephemeral.
  - App activity / user-generated content: text sent for translation and place lookups. Declare **shared with
    third parties for app functionality** (Google Translate API, Wikipedia), not stored by you, not used for ads.
  - Purchase history: handled by Google Play.
  - Encrypted in transit: Yes (HTTPS). Users can request deletion: by uninstalling or clearing storage (there is
    no server copy).
- [ ] **Foreground service declaration (location).** The app uses `FOREGROUND_SERVICE_LOCATION` while a trip
      runs. In App content → Foreground service permissions, choose "Location" and describe it: *"Measures ride
      distance for the fare meter and alerts the user if their ride moves away from the destination, while the
      user has an active trip."* Attach a short screen recording of starting a trip with the notification
      showing.
- [ ] **Permissions:** camera, microphone, location and notifications are all requested in context when a
      feature needs them. No background location permission is requested.
- [ ] **Target audience:** 18+ or 13+ (not children). **Content rating:** answer the questionnaire; expect
      "Everyone".
- [ ] **Ads:** No.
- [ ] **Government apps:** answer No. The app is not affiliated with BMTC, KSRTC or the Transport Department
      and only quotes their published fares. Keep the store listing free of their logos.

## 4. Store listing
- [ ] Short description (80 chars), e.g. *"Read Kannada bus boards, know the official fare, ride safer in Karnataka."*
- [ ] Screenshots: Lens reading a bus board, Official Fares, Meter, Night Guardian, Kannada phrasebook.
- [ ] Feature graphic 1024×500. The adaptive icon is in `res/drawable/ic_launcher_*`; export a 512×512 PNG for the
      listing.

## 5. Every release
- [ ] Bump `versionCode` / `versionName` in `app/build.gradle.kts`.
- [ ] Re-check fares in `OfficialFares.kt` / `BangaloreTariff.kt` against the latest notifications.
- [ ] `./gradlew :app:testDebugUnitTest`, then `./gradlew :app:bundleRelease`, then upload to the internal
      testing track first.
- [ ] On a real phone, smoke-test: the Lens on a Kannada board, the Meter with the screen off for 5 minutes,
      Night Guardian alert, SOS share, and the purchase flow.
