plugins {
    id("com.namma.travelcompanion.android.library")
    id("com.namma.travelcompanion.android.compose")
}

android {
    namespace = "com.namma.travelcompanion.feature.commute"
    buildFeatures { compose = true; buildConfig = true }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.play.services.location)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.mlkit.text.recognition)
    implementation(libs.tesseract4android)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}
