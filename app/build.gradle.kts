plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.namma.travelcompanion"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.namma.travelcompanion"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
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
    implementation("io.insert-koin:koin-android:4.0.2")
    implementation("io.insert-koin:koin-androidx-compose:4.0.2")

    // AndroidX & Architecture
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
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
