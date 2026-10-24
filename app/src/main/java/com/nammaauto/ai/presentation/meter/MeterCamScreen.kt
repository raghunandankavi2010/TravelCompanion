package com.nammaauto.ai.presentation.meter

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.FairMeterGreen
import com.nammaauto.ai.core.theme.FastMeterAlert
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.domain.model.MeterIntegrityStatus
import com.nammaauto.ai.presentation.components.BangaloreBadge
import com.nammaauto.ai.presentation.components.FareMetricCard
import com.nammaauto.ai.presentation.components.GlassCard
import java.util.concurrent.Executors

@Composable
fun MeterCamScreen(
    viewModel: MeterViewModel,
    onNavigateToProStore: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val reading by viewModel.meterReading.collectAsState()
    val trip by viewModel.tripState.collectAsState()
    val subscription by viewModel.subscriptionState.collectAsState()

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "MeterCam AI",
                    color = Slate200,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Live Bengaluru Auto Fare & Meter Verifier",
                    color = Slate400,
                    fontSize = 12.sp
                )
            }
            BangaloreBadge(
                text = if (subscription.isProUser) "PRO ACTIVE" else "${subscription.freeDailyScansRemaining} Scans Left",
                badgeColor = if (subscription.isProUser) AutoYellow else Slate700,
                textColor = if (subscription.isProUser) Slate900 else AutoYellow
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Camera Viewfinder Box with HUD Overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(2.dp, Slate700, RoundedCornerShape(20.dp))
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            if (hasCameraPermission) {
                val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
                DisposableEffect(Unit) {
                    onDispose {
                        cameraExecutor.shutdown()
                    }
                }

                AndroidView(
                    factory = { ctx ->
                        val previewView = PreviewView(ctx)
                        val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                        cameraProviderFuture.addListener({
                            val cameraProvider = cameraProviderFuture.get()
                            val preview = Preview.Builder().build().also {
                                it.surfaceProvider = previewView.surfaceProvider
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
                                cameraProvider.bindToLifecycle(
                                    lifecycleOwner,
                                    cameraSelector,
                                    preview,
                                    imageAnalysis
                                )
                            } catch (e: Exception) {
                                // Camera binding failed (e.g. running in headless emulator)
                            }
                        }, ContextCompat.getMainExecutor(ctx))
                        previewView
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Camera Permission",
                        tint = AutoYellow,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Camera Permission Required",
                        color = Slate200,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) },
                        colors = ButtonDefaults.buttonColors(containerColor = AutoYellow)
                    ) {
                        Text("Grant Camera Access", color = Slate900, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // HUD Viewfinder Bounding Reticle
            Box(
                modifier = Modifier
                    .size(width = 230.dp, height = 110.dp)
                    .border(
                        BorderStroke(
                            2.dp,
                            when (reading.integrityStatus) {
                                MeterIntegrityStatus.FAST_METER_ALERT -> FastMeterAlert
                                MeterIntegrityStatus.FAIR_ACCURATE -> FairMeterGreen
                                else -> AutoYellow.copy(alpha = 0.8f)
                            }
                        ),
                        RoundedCornerShape(12.dp)
                    )
            ) {
                Text(
                    text = "ALIGN AUTO METER DISPLAY",
                    color = AutoYellow,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 4.dp)
                )
            }

            // Real-Time OCR Status Pill Overlay
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(20.dp),
                color = Slate900.copy(alpha = 0.85f),
                border = BorderStroke(1.dp, Slate700)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                when (reading.integrityStatus) {
                                    MeterIntegrityStatus.FAST_METER_ALERT -> FastMeterAlert
                                    MeterIntegrityStatus.FAIR_ACCURATE -> FairMeterGreen
                                    else -> AutoYellow
                                },
                                CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (reading.detectedFareRupees != null)
                            "Scanned: ₹${reading.detectedFareRupees?.toInt()}"
                        else "Scanning Meter...",
                        color = Slate200,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Meter Anomaly Alert Banner
        AnimatedVisibility(
            visible = reading.integrityStatus == MeterIntegrityStatus.FAST_METER_ALERT,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = FastMeterAlert.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, FastMeterAlert)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Alert",
                        tint = FastMeterAlert,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "FAST METER ALERT",
                            color = FastMeterAlert,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = reading.anomalyReason,
                            color = Slate200,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Side-by-Side Fare Comparison Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FareMetricCard(
                title = "Driver's Meter",
                amount = if (reading.detectedFareRupees != null) "₹${reading.detectedFareRupees?.toInt()}" else "₹--",
                subtitle = "On-device OCR readout",
                accentColor = if (reading.integrityStatus == MeterIntegrityStatus.FAST_METER_ALERT) FastMeterAlert else AutoYellow,
                modifier = Modifier.weight(1f)
            )
            FareMetricCard(
                title = "Lawful BTP Fare",
                amount = "₹${trip.currentLegalFare.toInt()}",
                subtitle = "Govt tariff (${trip.distanceKm} km)",
                accentColor = FairMeterGreen,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Live GPS Ride Tracker Card
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Live GPS Telemetry",
                            color = Slate200,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (trip.isRunning) "Actively tracking trip distance..." else "Trip paused / not started",
                            color = if (trip.isRunning) AutoGreen else Slate400,
                            fontSize = 11.sp
                        )
                    }

                    Row {
                        IconButton(
                            onClick = {
                                if (trip.isRunning) viewModel.stopTrip() else viewModel.startTrip()
                            }
                        ) {
                            Icon(
                                imageVector = if (trip.isRunning) Icons.Default.Stop else Icons.Default.PlayArrow,
                                contentDescription = "Toggle Trip",
                                tint = if (trip.isRunning) FastMeterAlert else AutoGreen,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = { viewModel.resetTrip() }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reset Trip",
                                tint = Slate400
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "DISTANCE", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = "${trip.distanceKm} km", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text(text = "SPEED", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = "${trip.currentSpeedKmh.toInt()} km/h", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text(text = "WAITING", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = "${trip.waitingMinutes} min", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text(text = "TARIFF BASE", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = if (trip.fareBreakdown.isNight) "1.5x Night" else "Regular", color = AutoYellow, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Demo Simulation Bar (for instant testing inside Android Studio without needing a moving auto)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Slate800,
            border = BorderStroke(1.dp, Slate700)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "QUICK SIMULATION CONTROLS",
                    color = AutoYellow,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Test on-device AI algorithms without a moving vehicle:",
                    color = Slate400,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { viewModel.simulateDistance(1.0) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("+1 km GPS", fontSize = 11.sp, color = Slate200)
                    }
                    OutlinedButton(
                        onClick = { viewModel.simulateMeterScan(viewModel.tripState.value.currentLegalFare) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Fair Meter", fontSize = 11.sp, color = FairMeterGreen)
                    }
                    OutlinedButton(
                        onClick = { viewModel.simulateMeterScan(viewModel.tripState.value.currentLegalFare * 1.45) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Fast Meter", fontSize = 11.sp, color = FastMeterAlert)
                    }
                }
            }
        }

        if (!subscription.isProUser) {
            Spacer(modifier = Modifier.height(14.dp))
            Button(
                onClick = onNavigateToProStore,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Upgrade to Pro: Unlimited AI Scans & Night SOS", color = Slate900, fontWeight = FontWeight.Black)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
