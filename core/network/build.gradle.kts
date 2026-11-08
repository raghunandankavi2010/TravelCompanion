plugins {
    id("namma.android.library")
    id("namma.android.ktor")
}

android {
    namespace = "com.namma.travelcompanion.core.network"
}

dependencies {
    implementation(project(":core:common"))
    implementation("androidx.core:core-ktx:1.19.1")
    implementation("io.insert-koin:koin-android:4.0.2")
}
