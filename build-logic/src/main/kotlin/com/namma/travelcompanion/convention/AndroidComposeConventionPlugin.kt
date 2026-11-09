package com.namma.travelcompanion.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.findByType(ApplicationExtension::class.java)?.apply {
                buildFeatures.compose = true
            }
            extensions.findByType(LibraryExtension::class.java)?.apply {
                buildFeatures.compose = true
            }

            dependencies {
                val bom = "androidx.compose:compose-bom:2026.09.00"
                "implementation"(platform(bom))
                "androidTestImplementation"(platform(bom))
                "implementation"("androidx.compose.ui:ui")
                "implementation"("androidx.compose.ui:ui-graphics")
                "implementation"("androidx.compose.ui:ui-tooling-preview")
                "implementation"("androidx.compose.material3:material3")
                "debugImplementation"("androidx.compose.ui:ui-tooling")
            }
        }
    }
}
