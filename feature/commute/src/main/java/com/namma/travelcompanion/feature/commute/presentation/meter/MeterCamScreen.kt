package com.namma.travelcompanion.feature.commute.presentation.meter

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.namma.travelcompanion.feature.commute.BuildConfig
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.rememberLocationPermissionGate
import com.namma.travelcompanion.feature.commute.presentation.meter.components.AppBenchmarkCard
import com.namma.travelcompanion.feature.commute.presentation.meter.components.DistanceSimulatorBar
import com.namma.travelcompanion.feature.commute.presentation.meter.components.FastMeterAlertBanner
import com.namma.travelcompanion.feature.commute.presentation.meter.components.MeterComparisonCards
import com.namma.travelcompanion.feature.commute.presentation.meter.components.MeterHeader
import com.namma.travelcompanion.feature.commute.presentation.meter.components.MeterModeSwitcher
import com.namma.travelcompanion.feature.commute.presentation.meter.components.MeterScannerView
import com.namma.travelcompanion.feature.commute.presentation.meter.components.PhoneMeterHud

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun MeterCamScreen(
    viewModel: MeterViewModel,
    onNavigateToProStore: () -> Unit
) {
    val context = LocalContext.current

    val reading by viewModel.meterReading.collectAsStateWithLifecycle()
    val trip by viewModel.tripState.collectAsStateWithLifecycle()
    val subscription by viewModel.subscriptionState.collectAsStateWithLifecycle()
    val withLocationPermission = rememberLocationPermissionGate()
    val isPhoneMeterMode by viewModel.isPhoneMeterMode.collectAsStateWithLifecycle()
    val appBenchmark by viewModel.appBenchmark.collectAsStateWithLifecycle()

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
        if (!hasCameraPermission && !isPhoneMeterMode) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Header
        MeterHeader(isPhoneMeterMode = isPhoneMeterMode, subscription = subscription)

        Spacer(modifier = Modifier.height(12.dp))

        // Dual Mode Switcher: "Phone Is Meter (No-Meter Mode)" vs "Scan Physical Meter"
        MeterModeSwitcher(
            isPhoneMeterMode = isPhoneMeterMode,
            onSelectPhoneMeter = { viewModel.setPhoneMeterMode(true) },
            onSelectOcrCam = { if (!viewModel.setPhoneMeterMode(false)) onNavigateToProStore() }
        )

        Spacer(modifier = Modifier.height(14.dp))

        if (isPhoneMeterMode) {
            // MODE A: PHONE IS THE METER (HIGH-CONTRAST DIGITAL HUD METER)
            PhoneMeterHud(
                trip = trip,
                appBenchmark = appBenchmark,
                onToggleTrip = {
                    if (trip.isRunning) viewModel.stopTrip() else withLocationPermission { viewModel.startTrip() }
                },
                onResetTrip = { viewModel.resetTrip() }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // THE "#1 NO-METER PROBLEM SOLVER": APP-RATE BENCHMARK CARD
            AppBenchmarkCard(
                appBenchmark = appBenchmark,
                onPlayKannadaOffer = { viewModel.playNoMeterKannadaOffer() }
            )

        } else {
            // MODE B: CAMERA OCR METER SCANNER
            MeterScannerView(
                hasCameraPermission = hasCameraPermission,
                reading = reading,
                analyzer = viewModel.analyzer,
                onRequestPermission = { permissionLauncher.launch(Manifest.permission.CAMERA) }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Meter Anomaly Alert Banner
            FastMeterAlertBanner(reading = reading)

            Spacer(modifier = Modifier.height(12.dp))

            // Side-by-Side Comparison Cards
            MeterComparisonCards(reading = reading, trip = trip)
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Simulation Bar
        if (BuildConfig.DEBUG) {
            DistanceSimulatorBar(onSimulate = { viewModel.simulateDistance(it) })
        }

        if (!subscription.isProUser) {
            Spacer(modifier = Modifier.height(14.dp))
            Button(
                onClick = onNavigateToProStore,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Get Full Access: unlimited meter scans & voice", color = Slate900, fontWeight = FontWeight.Black)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
