package com.namma.travelcompanion.feature.lens.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.feature.lens.domain.model.BusRouteInfo
import com.namma.travelcompanion.feature.lens.domain.model.BusSignLensDatabase
import com.namma.travelcompanion.feature.lens.domain.model.BusSignLensScanResult
import com.namma.travelcompanion.feature.lens.domain.model.ConductorPhrase
import com.namma.travelcompanion.feature.lens.domain.model.DetectedSignboardInfo
import com.namma.travelcompanion.presentation.components.GlassCard
import java.util.concurrent.Executors

@Composable
fun BusSignboardLensScreen(
    viewModel: BusSignboardLensViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val scanResult by viewModel.scanResult.collectAsState()
    val isTorchEnabled by viewModel.isTorchEnabled.collectAsState()
    val isAutoAnnounceEnabled by viewModel.isAutoAnnounceEnabled.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()
    val viewMode by viewModel.viewMode.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedRouteDetails by viewModel.selectedRouteDetails.collectAsState()

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
        if (viewMode == LensViewMode.LIVE_CAMERA) {
            // Live CameraX Feed
            if (hasCameraPermission) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        val previewView = PreviewView(ctx)
                        val cameraExecutor = Executors.newSingleThreadExecutor()
                        val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

                        cameraProviderFuture.addListener({
                            val cameraProvider = cameraProviderFuture.get()
                            val preview = Preview.Builder().build().also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }

                            val imageAnalysis = ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build()
                                .also {
                                    it.setAnalyzer(cameraExecutor, viewModel.analyzer)
                                }

                            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                            try {
                                cameraProvider.unbindAll()
                                val camera = cameraProvider.bindToLifecycle(
                                    lifecycleOwner,
                                    cameraSelector,
                                    preview,
                                    imageAnalysis
                                )
                                cameraRef = camera
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }, ContextCompat.getMainExecutor(ctx))

                        previewView
                    }
                )

                // Viewfinder Reticle Overlay
                ViewfinderOverlay()
            } else {
                // Permission Fallback Screen
                CameraPermissionFallback(onGrantPermission = {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                })
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

@Composable
private fun TopLensActionBar(
    isTorchEnabled: Boolean,
    isAutoAnnounceEnabled: Boolean,
    isSpeaking: Boolean,
    onToggleTorch: () -> Unit,
    onToggleAutoAnnounce: () -> Unit,
    onStopAudio: () -> Unit,
    onSwitchMode: () -> Unit,
    onBack: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        shape = RoundedCornerShape(20.dp),
        color = Slate900.copy(alpha = 0.88f),
        border = BorderStroke(1.dp, Slate700.copy(alpha = 0.7f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Slate200
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Namma Vision Lens",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "🚌", fontSize = 14.sp)
                    }
                    Text(
                        text = "Real-Time Bus & Signboard AI",
                        fontSize = 11.sp,
                        color = AutoYellow
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                // Torch Button
                IconButton(
                    onClick = onToggleTorch,
                    modifier = Modifier
                        .size(36.dp)
                        .background(if (isTorchEnabled) AutoYellow else Slate800, CircleShape)
                ) {
                    Icon(
                        imageVector = if (isTorchEnabled) Icons.Default.FlashOn else Icons.Default.FlashOff,
                        contentDescription = "Torch",
                        tint = if (isTorchEnabled) Slate900 else Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Auto-Voice Announcer Button
                IconButton(
                    onClick = onToggleAutoAnnounce,
                    modifier = Modifier
                        .size(36.dp)
                        .background(if (isAutoAnnounceEnabled) AutoGreen else Slate800, CircleShape)
                ) {
                    Icon(
                        imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Voice Announce",
                        tint = if (isAutoAnnounceEnabled) Slate900 else Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Directory Switcher
                IconButton(
                    onClick = onSwitchMode,
                    modifier = Modifier
                        .size(36.dp)
                        .background(MonsoonBlue.copy(alpha = 0.25f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Route Directory",
                        tint = MonsoonBlue,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ViewfinderOverlay() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 310.dp, height = 210.dp)
                .border(BorderStroke(2.dp, AutoYellow.copy(alpha = 0.85f)), RoundedCornerShape(16.dp))
        ) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
                color = Slate900.copy(alpha = 0.75f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "AIM AT BUS BOARD OR KANNADA SIGN",
                    color = AutoYellow,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Composable
private fun BottomDetectionOverlay(
    scanResult: BusSignLensScanResult,
    isSpeaking: Boolean,
    onPlayRouteAnnouncement: (BusRouteInfo) -> Unit,
    onPlaySignboardAnnouncement: (DetectedSignboardInfo) -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit,
    onSimulateRoute: (String) -> Unit,
    onSimulateSignboard: (String) -> Unit,
    onClearDetection: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            val detectedRoute = scanResult.detectedRoute
            val detectedSign = scanResult.detectedSignboard

            AnimatedVisibility(
                visible = detectedRoute != null,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                detectedRoute?.let { route ->
                    BusRouteCard(
                        route = route,
                        isSpeaking = isSpeaking,
                        onPlayAnnouncement = { onPlayRouteAnnouncement(route) },
                        onSpeakPhrase = onSpeakPhrase,
                        onDismiss = onClearDetection
                    )
                }
            }

            AnimatedVisibility(
                visible = detectedSign != null && detectedRoute == null,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                detectedSign?.let { sign ->
                    SignboardDetailCard(
                        sign = sign,
                        isSpeaking = isSpeaking,
                        onPlayAnnouncement = { onPlaySignboardAnnouncement(sign) },
                        onDismiss = onClearDetection
                    )
                }
            }

            // If Idle (Nothing detected yet), show simulation pills and tips
            if (detectedRoute == null && detectedSign == null) {
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 18.dp
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    strokeWidth = 2.dp,
                                    color = AutoYellow
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Live On-Device Vision Scanner Active",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate200
                                )
                            }
                            Text(text = "0-Latency", fontSize = 10.sp, color = AutoGreen)
                        }

                        Text(
                            text = "Detects BMTC/KSRTC route numbers & Kannada signs (footwear, darshan, milestones).",
                            fontSize = 11.sp,
                            color = Slate400,
                            modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
                        )

                        Text(
                            text = "TEST SIMULATOR (TAP TO PREVIEW BOARDS):",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AutoYellow,
                            fontFamily = FontFamily.Monospace
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            FilterChip(
                                selected = false,
                                onClick = { onSimulateRoute("KIA-8") },
                                label = { Text("✈️ KIA-8 (Airport)", fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                            )
                            FilterChip(
                                selected = false,
                                onClick = { onSimulateRoute("335-E") },
                                label = { Text("🏢 335-E (ITPL)", fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                            )
                            FilterChip(
                                selected = false,
                                onClick = { onSimulateRoute("KSRTC MYS-EXP") },
                                label = { Text("🏰 KSRTC (Mysuru)", fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                            )
                            FilterChip(
                                selected = false,
                                onClick = { onSimulateSignboard("sign_padarakshe") },
                                label = { Text("🥿 Footwear Counter", fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                            )
                            FilterChip(
                                selected = false,
                                onClick = { onSimulateSignboard("sign_sheegra_darshana") },
                                label = { Text("⚡ Special Darshan", fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BusRouteCard(
    route: BusRouteInfo,
    isSpeaking: Boolean,
    onPlayAnnouncement: () -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit,
    onDismiss: () -> Unit
) {
    var showConductorPhrases by remember { mutableStateOf(false) }

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 20.dp,
        borderColor = Color(route.badgeColorHex)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Route Number & Dismiss
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(route.badgeColorHex)
                    ) {
                        Text(
                            text = route.routeNumber,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Slate900,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = route.serviceCategory,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Slate400
                    )
                }

                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss",
                        tint = Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Origin -> Destination in English & Kannada
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = route.originEn,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Slate200
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "➔", fontSize = 14.sp, color = AutoYellow)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = route.destinationEn,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }

                Text(
                    text = "${route.originKn} ➔ ${route.destinationKn}",
                    fontSize = 12.sp,
                    color = Slate400,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // VIA STOPS Ribbon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "VIA:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                route.viaStopsEn.forEach { stop ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Slate800,
                        border = BorderStroke(1.dp, Slate700)
                    ) {
                        Text(
                            text = stop,
                            fontSize = 10.sp,
                            color = Slate200,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fare & Frequency Meta
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fare: ${route.approxFareRange}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoGreen
                )
                Text(
                    text = route.frequencySummary,
                    fontSize = 10.sp,
                    color = Slate400
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons: Audio Announcement & Conductor Talk
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onPlayAnnouncement,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSpeaking) AutoGreen else AutoYellow,
                        contentColor = Slate900
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpeaking) "Speaking..." else "Announce",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = { showConductorPhrases = !showConductorPhrases },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, MonsoonBlue),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MonsoonBlue)
                ) {
                    Icon(
                        imageVector = Icons.Default.RecordVoiceOver,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (showConductorPhrases) "Hide Helper" else "Talk Conductor",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Expandable Conductor Phrases
            AnimatedVisibility(visible = showConductorPhrases) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    HorizontalDivider(color = Slate700)
                    Text(
                        text = "QUICK CONDUCTOR AUDIO PHRASES (TAP TO SPEAK):",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow,
                        fontFamily = FontFamily.Monospace
                    )

                    route.conductorSurvivalPhrases.forEach { phrase ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Slate800,
                            border = BorderStroke(1.dp, Slate700),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSpeakPhrase(phrase) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = phrase.englishMeaning,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Slate200
                                    )
                                    Text(
                                        text = phrase.kannadaLipi,
                                        fontSize = 12.sp,
                                        color = AutoYellow
                                    )
                                    Text(
                                        text = "\"${phrase.phoneticKannada}\"",
                                        fontSize = 10.sp,
                                        color = Slate400
                                    )
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Speak",
                                    tint = AutoYellow,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SignboardDetailCard(
    sign: DetectedSignboardInfo,
    isSpeaking: Boolean,
    onPlayAnnouncement: () -> Unit,
    onDismiss: () -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 20.dp,
        borderColor = MonsoonBlue
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MonsoonBlue.copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, MonsoonBlue)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = sign.category.iconTag, fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = sign.category.label,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MonsoonBlue
                        )
                    }
                }

                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss",
                        tint = Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = sign.kannadaOriginal,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow
            )

            Text(
                text = sign.englishTitle,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Slate200,
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = "Pronunciation: \"${sign.phoneticGuide}\"",
                fontSize = 11.sp,
                color = Slate400,
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Slate800,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "TRAVELER ACTION GUIDE:",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoGreen,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = sign.travelerAdvice,
                        fontSize = 11.sp,
                        color = Slate200,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onPlayAnnouncement,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue, contentColor = Slate900),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isSpeaking) "Speaking..." else "Listen Audio Explanation",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun RouteDirectoryView(
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
    onSelectRoute: (BusRouteInfo?) -> Unit,
    selectedRoute: BusRouteInfo?,
    onPlayRoute: (BusRouteInfo) -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit,
    onBackToCamera: () -> Unit
) {
    val allRoutes = BusSignLensDatabase.routes
    val filteredRoutes = remember(searchQuery) {
        if (searchQuery.isBlank()) allRoutes
        else {
            val q = searchQuery.trim().lowercase()
            allRoutes.filter { route ->
                route.routeNumber.lowercase().contains(q) ||
                        route.originEn.lowercase().contains(q) ||
                        route.destinationEn.lowercase().contains(q) ||
                        route.viaStopsEn.any { it.lowercase().contains(q) } ||
                        route.serviceCategory.lowercase().contains(q)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackToCamera) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Camera",
                        tint = AutoYellow
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = "Transit Route Directory",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                    Text(
                        text = "BMTC, Vayu Vajra & KSRTC Express",
                        fontSize = 11.sp,
                        color = Slate400
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = AutoYellow.copy(alpha = 0.2f),
                border = BorderStroke(1.dp, AutoYellow),
                modifier = Modifier.clickable { onBackToCamera() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null,
                        tint = AutoYellow,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Live Lens",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search by route (335-E, KIA-8) or stop (Airport, Silk Board)...", fontSize = 12.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Slate400
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Slate800,
                unfocusedContainerColor = Slate800,
                focusedBorderColor = AutoYellow,
                unfocusedBorderColor = Slate700,
                focusedTextColor = Slate200,
                unfocusedTextColor = Slate200
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredRoutes) { route ->
                RouteListItemCard(
                    route = route,
                    onPlayAnnouncement = { onPlayRoute(route) },
                    onSpeakPhrase = onSpeakPhrase
                )
            }
        }
    }
}

@Composable
private fun RouteListItemCard(
    route: BusRouteInfo,
    onPlayAnnouncement: () -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 16.dp,
        borderColor = Color(route.badgeColorHex)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(route.badgeColorHex)
                ) {
                    Text(
                        text = route.routeNumber,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Slate900,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontFamily = FontFamily.Monospace
                    )
                }

                Text(
                    text = route.approxFareRange,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoGreen
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${route.originEn} ➔ ${route.destinationEn}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )

            Text(
                text = "${route.originKn} ➔ ${route.destinationKn}",
                fontSize = 11.sp,
                color = Slate400
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Via: ${route.viaStopsEn.take(4).joinToString(", ")}...",
                fontSize = 10.sp,
                color = AutoYellow
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onPlayAnnouncement,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Slate700, contentColor = Slate200),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Announce", fontSize = 11.sp)
                }

                OutlinedButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MonsoonBlue),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MonsoonBlue)
                ) {
                    Text(if (expanded) "Hide Phrases" else "Conductor Helper", fontSize = 11.sp)
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    HorizontalDivider(color = Slate700)
                    route.conductorSurvivalPhrases.forEach { phrase ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Slate800,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSpeakPhrase(phrase) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(phrase.englishMeaning, fontSize = 11.sp, color = Slate200)
                                    Text(phrase.kannadaLipi, fontSize = 11.sp, color = AutoYellow)
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = null,
                                    tint = AutoYellow,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CameraPermissionFallback(onGrantPermission: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = null,
                tint = AutoYellow,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Camera Permission Required",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            Text(
                text = "Namma Vision Lens uses on-device camera processing to instantly read Kannada bus boards and signboards.",
                fontSize = 12.sp,
                color = Slate400,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Button(
                onClick = onGrantPermission,
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow, contentColor = Slate900)
            ) {
                Text("Grant Camera Access", fontWeight = FontWeight.Bold)
            }
        }
    }
}
