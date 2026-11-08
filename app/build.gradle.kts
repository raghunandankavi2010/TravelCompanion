plugins {
    id("namma.android.application")
    id("namma.android.compose")
}

android {
    namespace = "com.namma.travelcompanion"

    defaultConfig {
        applicationId = "com.namma.travelcompanion"
        versionCode = 1
        versionName = "1.0.0"
    }
}

dependencies {
    // Core Infrastructure
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))

    // Features
    implementation(project(":feature:lens"))
    implementation(project(":feature:transit"))
    implementation(project(":feature:dining"))
    implementation(project(":feature:phrasebook"))
    implementation(project(":feature:tourism"))
    implementation(project(":feature:meter"))

    // Dependency Injection
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // AndroidX & Architecture
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
