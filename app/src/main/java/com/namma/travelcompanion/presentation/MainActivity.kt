package com.namma.travelcompanion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.namma.travelcompanion.core.theme.NammaAutoAITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // Permissions are requested in context by the features that need them (camera, location, mic).
        setContent {
            NammaAutoAITheme {
                NammaAutoNavHost()
            }
        }
    }
}
