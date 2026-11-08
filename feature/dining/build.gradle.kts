plugins {
    id("namma.android.feature")
}

android {
    namespace = "com.namma.travelcompanion.feature.dining"
}

dependencies {
    implementation("io.coil-kt.coil3:coil-compose:3.6.3")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.6.3")
    implementation("androidx.compose.material:material-icons-extended")
}
