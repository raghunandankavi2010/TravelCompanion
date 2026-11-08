plugins {
    id("namma.android.feature")
}

android {
    namespace = "com.namma.travelcompanion.feature.phrasebook"
}

dependencies {
    implementation(project(":core:network"))
    implementation("androidx.compose.material:material-icons-extended")
}
