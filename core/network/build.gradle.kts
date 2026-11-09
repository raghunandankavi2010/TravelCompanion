plugins {
    id("com.namma.travelcompanion.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.namma.travelcompanion.core.network"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.koin.android)

    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.mockito.kotlin)
    testImplementation("junit:junit:4.13.2")
}

