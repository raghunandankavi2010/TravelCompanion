import java.util.Properties

plugins {
    id("com.namma.travelcompanion.android.application")
    id("com.namma.travelcompanion.android.compose")
}

android {
    namespace = "com.namma.travelcompanion"

    defaultConfig {
        applicationId = "com.namma.travelcompanion"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // Upload key lives outside git: create keystore.properties (storeFile, storePassword, keyAlias, keyPassword).
    val keystoreProps = rootProject.file("keystore.properties").takeIf { it.exists() }
        ?.let { file -> Properties().apply { file.inputStream().use(::load) } }

    signingConfigs {
        if (keystoreProps != null) {
            create("release") {
                storeFile = rootProject.file(keystoreProps.getProperty("storeFile"))
                storePassword = keystoreProps.getProperty("storePassword")
                keyAlias = keystoreProps.getProperty("keyAlias")
                keyPassword = keystoreProps.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.findByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    androidResources {
        noCompress += "traineddata"
    }
    buildFeatures {
        buildConfig = true
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:commute"))
    implementation(project(":feature:safety"))
    implementation(project(":feature:phrasebook"))
    implementation(project(":feature:plan"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

    implementation(libs.play.services.location)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    testImplementation(libs.json)
}
