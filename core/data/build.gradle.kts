plugins {
    id("namma.android.library")
    id("namma.android.compose")
}

android {
    namespace = "com.namma.travelcompanion.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation("androidx.core:core-ktx:1.19.1")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")
    implementation("io.insert-koin:koin-android:4.0.2")
}
