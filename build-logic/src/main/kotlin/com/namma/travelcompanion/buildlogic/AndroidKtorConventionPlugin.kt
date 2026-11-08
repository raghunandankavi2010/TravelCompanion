package com.namma.travelcompanion.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKtorConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                add("implementation", "io.ktor:ktor-client-core:3.1.1")
                add("implementation", "io.ktor:ktor-client-okhttp:3.1.1")
                add("implementation", "io.ktor:ktor-client-content-negotiation:3.1.1")
                add("implementation", "io.ktor:ktor-serialization-kotlinx-json:3.1.1")
                add("implementation", "io.ktor:ktor-client-logging:3.1.1")
                add("implementation", "org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
                add("testImplementation", "io.ktor:ktor-client-mock:3.1.1")
            }
        }
    }
}
