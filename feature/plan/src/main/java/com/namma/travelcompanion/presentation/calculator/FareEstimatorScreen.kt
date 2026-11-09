package com.namma.travelcompanion.presentation.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.presentation.calculator.components.AppOptionRow
import com.namma.travelcompanion.presentation.calculator.components.BestOptionCard
import com.namma.travelcompanion.presentation.calculator.components.ConditionChips
import com.namma.travelcompanion.presentation.calculator.components.EstimateDisclaimer
import com.namma.travelcompanion.presentation.calculator.components.HubPickerCard
import com.namma.travelcompanion.presentation.calculator.components.RadarHeader
import com.namma.travelcompanion.presentation.calculator.components.StreetAutoCard
import com.namma.travelcompanion.core.ui.components.openAppOrStore

@Composable
fun FareEstimatorScreen(
    viewModel: FareEstimatorViewModel,
    onOpenKannadaNegotiator: () -> Unit
) {
    val context = LocalContext.current
    val fromHub by viewModel.fromHub.collectAsStateWithLifecycle()
    val toHub by viewModel.toHub.collectAsStateWithLifecycle()
    val traffic by viewModel.trafficMultiplier.collectAsStateWithLifecycle()
    val isRaining by viewModel.isRaining.collectAsStateWithLifecycle()
    val report by viewModel.radarReport.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        RadarHeader()

        Spacer(modifier = Modifier.height(14.dp))

        HubPickerCard(
            hubs = viewModel.hubs,
            fromHub = fromHub,
            toHub = toHub,
            onFromSelected = viewModel::setFromHub,
            onToSelected = viewModel::setToHub
        )

        Spacer(modifier = Modifier.height(12.dp))

        ConditionChips(
            traffic = traffic,
            isRaining = isRaining,
            onTrafficSelected = viewModel::setTrafficMultiplier,
            onToggleRain = viewModel::toggleRain
        )

        Spacer(modifier = Modifier.height(14.dp))

        val best = report.bestAppRecommendation
        BestOptionCard(
            best = best,
            onOpenApp = { openAppOrStore(context, best.packageName) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "MULTI-APP ACCEPTANCE & FARE MATRIX",
            color = Slate400,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        report.options.forEach { option ->
            AppOptionRow(
                option = option,
                onLaunch = { openAppOrStore(context, option.packageName) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        EstimateDisclaimer()

        Spacer(modifier = Modifier.height(14.dp))

        StreetAutoCard(
            fare = report.streetAutoAlternativeFare,
            onOpenKannadaNegotiator = onOpenKannadaNegotiator
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
