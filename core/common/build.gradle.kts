plugins {
    id("namma.android.library")
    id("namma.android.compose")
}

android {
    namespace = "com.namma.travelcompanion.core.common"
}

dependencies {
    implementation("androidx.core:core-ktx:1.19.1")
}
