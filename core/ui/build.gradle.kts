plugins {
    id("com.namma.travelcompanion.android.library")
    id("com.namma.travelcompanion.android.compose")
}

android {
    namespace = "com.namma.travelcompanion.core.ui"
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.icons.extended)
    api("io.coil-kt.coil3:coil-compose:3.6.3")
    api("io.coil-kt.coil3:coil-network-okhttp:3.6.3")
}
