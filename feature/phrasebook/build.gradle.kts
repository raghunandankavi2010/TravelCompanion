plugins {
    id("com.namma.travelcompanion.android.library")
    id("com.namma.travelcompanion.android.compose")
}

android {
    namespace = "com.namma.travelcompanion.feature.phrasebook"
    buildFeatures { compose = true }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}
