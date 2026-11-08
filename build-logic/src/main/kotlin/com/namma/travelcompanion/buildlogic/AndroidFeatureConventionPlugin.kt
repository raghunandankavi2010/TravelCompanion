package com.namma.travelcompanion.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("namma.android.library")
                apply("namma.android.compose")
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))
                add("implementation", "androidx.core:core-ktx:1.19.1")
                add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:2.11.0")
                add("implementation", "androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0")
                add("implementation", "androidx.activity:activity-compose:1.13.0")
                add("implementation", "androidx.navigation:navigation-compose:2.10.2")
                add("implementation", "io.insert-koin:koin-android:4.0.2")
                add("implementation", "io.insert-koin:koin-androidx-compose:4.0.2")
            }
        }
    }
}
