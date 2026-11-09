pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = java.net.URI("https://jitpack.io") }
    }
}

rootProject.name = "TravelCompanion"

includeBuild("build-logic")

include(":app")
include(":core:model")
include(":core:network")
include(":core:data")
include(":core:ui")
include(":feature:explore")
include(":feature:commute")
include(":feature:safety")
include(":feature:phrasebook")
include(":feature:plan")
