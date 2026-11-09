package com.namma.travelcompanion.feature.commute.presentation.cost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.openUrl
import com.namma.travelcompanion.feature.commute.presentation.cost.components.BookingButtons
import com.namma.travelcompanion.feature.commute.presentation.cost.components.CostHeader
import com.namma.travelcompanion.feature.commute.presentation.cost.components.DestinationChips
import com.namma.travelcompanion.feature.commute.presentation.cost.components.DiningBudgetSelector
import com.namma.travelcompanion.feature.commute.presentation.cost.components.MoneySavingTipsCard
import com.namma.travelcompanion.feature.commute.presentation.cost.components.StayTierSelector
import com.namma.travelcompanion.feature.commute.presentation.cost.components.TotalBudgetCard
import com.namma.travelcompanion.feature.commute.presentation.cost.components.TransportModeSelector
import com.namma.travelcompanion.feature.commute.presentation.cost.components.TripStepperCard

@Composable
fun TravelCostCalculatorScreen(viewModel: TravelCostViewModel) {
    val context = LocalContext.current
    val selectedSpotIds by viewModel.selectedSpotIds.collectAsStateWithLifecycle()
    val tripDays by viewModel.tripDays.collectAsStateWithLifecycle()
    val adultsCount by viewModel.adultsCount.collectAsStateWithLifecycle()
    val childrenCount by viewModel.childrenCount.collectAsStateWithLifecycle()
    val isForeigner by viewModel.isForeigner.collectAsStateWithLifecycle()
    val transportMode by viewModel.transportMode.collectAsStateWithLifecycle()
    val stayType by viewModel.stayType.collectAsStateWithLifecycle()
    val diningPreference by viewModel.diningPreference.collectAsStateWithLifecycle()
    val breakdown by viewModel.breakdown.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                CostHeader(
                    isForeigner = isForeigner,
                    onToggleForeigner = viewModel::toggleForeigner
                )
            }

            item { TotalBudgetCard(breakdown = breakdown) }

            item {
                DestinationChips(
                    destinations = viewModel.availableDestinations,
                    selectedSpotIds = selectedSpotIds,
                    onToggle = viewModel::toggleDestination
                )
            }

            item {
                TripStepperCard(
                    tripDays = tripDays,
                    adultsCount = adultsCount,
                    childrenCount = childrenCount,
                    onTripDaysChange = viewModel::setTripDays,
                    onAdultsChange = viewModel::setAdults,
                    onChildrenChange = viewModel::setChildren
                )
            }

            item {
                TransportModeSelector(
                    selected = transportMode,
                    onSelected = viewModel::setTransportMode
                )
            }

            item {
                StayTierSelector(
                    selected = stayType,
                    onSelected = viewModel::setStayType
                )
            }

            item {
                DiningBudgetSelector(
                    selected = diningPreference,
                    onSelected = viewModel::setDiningPreference
                )
            }

            item { MoneySavingTipsCard(tips = breakdown.moneySavingTips) }

            item {
                BookingButtons(
                    onBookBus = { openUrl(context, "https://ksrtc.in") },
                    onBookMonuments = { openUrl(context, "https://asi.nic.in") }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
