plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly("com.android.tools.build:gradle:9.5.1")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.21")
    compileOnly("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.4.21")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "namma.android.application"
            implementationClass = "com.namma.travelcompanion.buildlogic.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "namma.android.library"
            implementationClass = "com.namma.travelcompanion.buildlogic.AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "namma.android.compose"
            implementationClass = "com.namma.travelcompanion.buildlogic.AndroidComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "namma.android.feature"
            implementationClass = "com.namma.travelcompanion.buildlogic.AndroidFeatureConventionPlugin"
        }
        register("androidKtor") {
            id = "namma.android.ktor"
            implementationClass = "com.namma.travelcompanion.buildlogic.AndroidKtorConventionPlugin"
        }
    }
}
