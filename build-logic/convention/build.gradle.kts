plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.namma.travelcompanion.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidApplication") {
            id = "com.namma.travelcompanion.android.application"
            implementationClass = "com.namma.travelcompanion.convention.AndroidApplicationConventionPlugin"
        }
        create("androidLibrary") {
            id = "com.namma.travelcompanion.android.library"
            implementationClass = "com.namma.travelcompanion.convention.AndroidLibraryConventionPlugin"
        }
        create("androidCompose") {
            id = "com.namma.travelcompanion.android.compose"
            implementationClass = "com.namma.travelcompanion.convention.AndroidComposeConventionPlugin"
        }
    }
}
