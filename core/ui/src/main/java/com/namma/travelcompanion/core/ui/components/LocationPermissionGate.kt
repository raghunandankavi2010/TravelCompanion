package com.namma.travelcompanion.core.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

private val LOCATION_PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

/**
 * Returns a function that runs an action once location permission is granted, asking at that moment
 * (in context, as Play's location policy expects) instead of at app launch.
 */
@Composable
fun rememberLocationPermissionGate(): (action: () -> Unit) -> Unit {
    val context = LocalContext.current
    var pendingAction by remember { mutableStateOf<(() -> Unit)?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grants ->
        if (grants.values.any { it }) {
            pendingAction?.invoke()
        } else {
            Toast.makeText(context, "Location is needed to measure the ride. Enable it in Settings.", Toast.LENGTH_LONG).show()
        }
        pendingAction = null
    }
    return { action ->
        val granted = LOCATION_PERMISSIONS.any {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
        if (granted) {
            action()
        } else {
            pendingAction = action
            launcher.launch(LOCATION_PERMISSIONS)
        }
    }
}
