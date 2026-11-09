# Tesseract4Android ships no consumer rules; its JNI code reads fields and calls methods by name.
-keep class com.googlecode.tesseract.android.** { *; }
-keep class com.googlecode.leptonica.android.** { *; }

# ML Kit, Play Billing, CameraX and Coil bring their own consumer rules. App models are built by hand
# (org.json, no reflection), so nothing of ours needs keeping.
