package com.namma.travelcompanion.feature.commute.presentation.meter.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.data.location.LiveTripState
import com.namma.travelcompanion.feature.commute.domain.model.MeterIntegrityStatus
import com.namma.travelcompanion.feature.commute.domain.model.MeterReading
import com.namma.travelcompanion.core.ui.components.FareMetricCard
import java.util.concurrent.Executors

/** Camera preview with the OCR reticle and status pill, or a permission prompt. */
@Composable
internal fun MeterScannerView(
    hasCameraPermission: Boolean,
    reading: MeterReading,
    analyzer: ImageAnalysis.Analyzer,
    onRequestPermission: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
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
                                it.setAnalyzer(cameraExecutor, analyzer)
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
                            // Camera binding failed
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
                Text("Camera Permission Required", color = Slate200, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onRequestPermission() },
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
}

/** Banner shown when the OCR reading looks like a fast meter. */
@Composable
internal fun FastMeterAlertBanner(reading: MeterReading) {
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
}

/** Driver's OCR reading next to the lawful BTP fare. */
@Composable
internal fun MeterComparisonCards(reading: MeterReading, trip: LiveTripState) {
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
}
