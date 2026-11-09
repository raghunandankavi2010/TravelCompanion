package com.namma.travelcompanion.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            dependencies.apply {
                add("testImplementation", "junit:junit:4.13.2")
                add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 37
                defaultConfig {
                    minSdk = 26
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }
        }
    }
}
