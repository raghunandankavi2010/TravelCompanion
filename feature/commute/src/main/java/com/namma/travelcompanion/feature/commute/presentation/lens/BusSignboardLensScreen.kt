package com.namma.travelcompanion.feature.commute.presentation.lens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.feature.commute.presentation.lens.components.BottomDetectionOverlay
import com.namma.travelcompanion.feature.commute.presentation.lens.components.CameraPermissionFallback
import com.namma.travelcompanion.feature.commute.presentation.lens.components.LensCameraPreview
import com.namma.travelcompanion.feature.commute.presentation.lens.components.TopLensActionBar
import com.namma.travelcompanion.feature.commute.presentation.lens.components.RouteDirectoryView
import com.namma.travelcompanion.feature.commute.presentation.lens.components.SignTranslateOverlay
import com.namma.travelcompanion.feature.commute.presentation.lens.components.ViewfinderOverlay

@Composable
fun BusSignboardLensScreen(
    viewModel: BusSignboardLensViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val scanResult by viewModel.scanResult.collectAsStateWithLifecycle()
    val isTorchEnabled by viewModel.isTorchEnabled.collectAsStateWithLifecycle()
    val isAutoAnnounceEnabled by viewModel.isAutoAnnounceEnabled.collectAsStateWithLifecycle()
    val isSpeaking by viewModel.isSpeaking.collectAsStateWithLifecycle()
    val viewMode by viewModel.viewMode.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedRouteDetails by viewModel.selectedRouteDetails.collectAsStateWithLifecycle()
    val signState by viewModel.signState.collectAsStateWithLifecycle()
    val translationModelState by viewModel.translationModelState.collectAsStateWithLifecycle()

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    var cameraRef by remember { mutableStateOf<Camera?>(null) }

    LaunchedEffect(isTorchEnabled, cameraRef) {
        cameraRef?.let { cam ->
            if (cam.cameraInfo.hasFlashUnit()) {
                cam.cameraControl.enableTorch(isTorchEnabled)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
    ) {
        if (viewMode != LensViewMode.ROUTE_DIRECTORY) {
            // Live CameraX Feed, shared by bus lens and sign mode
            if (hasCameraPermission) {
                LensCameraPreview(
                    analyzer = viewModel.analyzer,
                    onCameraBound = { cameraRef = it }
                )
            } else {
                // Permission Fallback Screen
                CameraPermissionFallback(onGrantPermission = {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                })
            }
        }

        if (viewMode == LensViewMode.SIGN_TRANSLATE) {
            val exitSign = { if (viewModel.openedInSignMode) onBack() else viewModel.setViewMode(LensViewMode.LIVE_CAMERA) }
            BackHandler(enabled = !viewModel.openedInSignMode || signState is SignReadState.Frozen) {
                if (signState is SignReadState.Frozen) viewModel.resumeSignCamera() else exitSign()
            }
            if (hasCameraPermission) {
                SignTranslateOverlay(
                    state = signState,
                    modelState = translationModelState,
                    onFreeze = { viewModel.freezeSign() },
                    onRetake = { viewModel.resumeSignCamera() },
                    onDownloadModel = { viewModel.downloadTranslationModel() },
                    onSpeak = { viewModel.speakSignBlock(it) },
                    onBack = exitSign
                )
            }
        } else if (viewMode == LensViewMode.LIVE_CAMERA) {
            if (hasCameraPermission) {
                // Viewfinder Reticle Overlay
                ViewfinderOverlay()
            }

            // Top HUD Bar
            TopLensActionBar(
                isTorchEnabled = isTorchEnabled,
                isAutoAnnounceEnabled = isAutoAnnounceEnabled,
                isSpeaking = isSpeaking,
                onToggleTorch = { viewModel.toggleTorch() },
                onToggleAutoAnnounce = { viewModel.toggleAutoAnnounce() },
                onStopAudio = { viewModel.stopAudio() },
                onSwitchMode = { viewModel.setViewMode(LensViewMode.ROUTE_DIRECTORY) },
                onReadSign = { viewModel.setViewMode(LensViewMode.SIGN_TRANSLATE) },
                onBack = onBack
            )

            // Bottom Results & Control Deck
            BottomDetectionOverlay(
                scanResult = scanResult,
                isSpeaking = isSpeaking,
                onPlayRouteAnnouncement = { viewModel.playRouteAnnouncement(it) },
                onPlaySignboardAnnouncement = { viewModel.playSignboardAnnouncement(it) },
                onSpeakPhrase = { viewModel.speakConductorPhrase(it) },
                onSimulateRoute = { viewModel.simulateRoute(it) },
                onSimulateSignboard = { viewModel.simulateSignboard(it) },
                onClearDetection = { viewModel.clearDetection() }
            )
        } else {
            // Route Directory & Search Mode
            RouteDirectoryView(
                searchQuery = searchQuery,
                onSearchChanged = { viewModel.updateSearchQuery(it) },
                onSelectRoute = { viewModel.selectRoute(it) },
                selectedRoute = selectedRouteDetails,
                onPlayRoute = { viewModel.playRouteAnnouncement(it) },
                onSpeakPhrase = { viewModel.speakConductorPhrase(it) },
                onBackToCamera = { viewModel.setViewMode(LensViewMode.LIVE_CAMERA) }
            )
        }
    }
}
