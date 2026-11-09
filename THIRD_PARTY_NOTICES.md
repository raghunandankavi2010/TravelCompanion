# Third-party notices

Namma Travel Companion's own code is proprietary (see [LICENSE](LICENSE)). It includes or uses the following
third-party components, each under its own licence.

## Bundled in the app

| Component | Licence |
| --- | --- |
| Kotlin standard library, kotlinx.coroutines | Apache 2.0 |
| AndroidX (Compose, Lifecycle, Navigation, Activity, Core, CameraX) | Apache 2.0 |
| Coil 3, OkHttp | Apache 2.0 |
| Tesseract4Android (adaptech-cz), Tesseract OCR engine | Apache 2.0 |
| `kan.traineddata` from tesseract-ocr/tessdata_fast | Apache 2.0 |
| Leptonica (bundled with Tesseract4Android) | Leptonica licence (BSD-style) |
| libjpeg-turbo, libpng (bundled with Tesseract4Android) | IJG / BSD-style and libpng licence |
| Google ML Kit Text Recognition, Google Play services Location, Google Play Billing Library | Android Software Development Kit License / Google APIs Terms of Service |

Apache 2.0 licence text: https://www.apache.org/licenses/LICENSE-2.0

## Bundled data

| File | Source | Licence |
| --- | --- | --- |
| `assets/places/karnataka_places.json` | Wikidata (names, descriptions, coordinates, categories) | CC0 1.0 |
| | OpenStreetMap (entries with `"source":"osm"`) | ODbL 1.0, © OpenStreetMap contributors |
| `assets/maps/karnataka_districts.json` | geoBoundaries IND ADM2/ADM3 (Local Government Directory, Govt of India), simplified; Vijayanagara rebuilt from its taluks | ODbL 1.0 |
| `assets/youtube/channel_videos.json` | Titles of the author's own YouTube videos | Owner's content |

The two ODbL files are Derivative Databases and are themselves available under ODbL 1.0: anyone may extract and
reuse them under that licence, separately from this app's proprietary code. They are rebuilt by
`tools/fetch_places.py`, `tools/fetch_osm_places.py` and `tools/build_districts.py`.

## Online services used at runtime

| Service | Use | Terms |
| --- | --- | --- |
| Open-Meteo | Regional weather | Data under CC BY 4.0; the weather section is labelled with Open-Meteo as the source |
| Wikipedia REST API | Place summaries and photos | Text CC BY-SA 4.0; content is labelled "From Wikipedia" and links to the article |
| Google Translate API | English → Kannada phrase translation | Google Terms of Service |
| Images from Wikimedia Commons and Unsplash | Destination photos | Per-image licences on the source sites; every Commons photo links to its file page with author and licence |
| YouTube (thumbnails `i.ytimg.com`, links) | The author's own videos, opened in the YouTube app | YouTube Terms of Service |

Fare figures in the app are factual data taken from government notifications, cited with their sources.
