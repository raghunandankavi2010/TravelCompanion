plugins {
    id("namma.android.feature")
}

android {
    namespace = "com.namma.travelcompanion.feature.lens"
}

dependencies {
    implementation("androidx.camera:camera-core:1.5.1")
    implementation("androidx.camera:camera-camera2:1.5.1")
    implementation("androidx.camera:camera-lifecycle:1.5.1")
    implementation("androidx.camera:camera-view:1.5.1")
    implementation("com.google.mlkit:text-recognition:16.0.1")
    implementation("androidx.compose.material:material-icons-extended")
}
