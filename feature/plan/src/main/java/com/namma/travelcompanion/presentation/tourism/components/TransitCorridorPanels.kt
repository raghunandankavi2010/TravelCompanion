package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.domain.model.GovtBusCorridorInfo
import com.namma.travelcompanion.domain.model.PrivateBusCorridorInfo
import com.namma.travelcompanion.domain.model.RailCorridorInfo

@Composable
internal fun CorridorGovtBusPanel(
    bus: GovtBusCorridorInfo,
    onOpenUrl: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Departure Hub: ${bus.departureHub}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            if (bus.shaktiSchemeApplicable) {
                Surface(shape = RoundedCornerShape(4.dp), color = AutoGreen.copy(alpha = 0.2f)) {
                    Text("Shakti Free for Women", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = AutoGreen, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                }
            }
        }

        Text(text = "Frequency: ${bus.frequency}", fontSize = 11.sp, color = Slate400)

        // Fleet Breakdown Table
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Slate900,
            border = BorderStroke(1.dp, Slate700),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                bus.fleetOptions.forEach { opt ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "• ${opt.first} (${opt.second})", fontSize = 11.sp, color = Slate200)
                        Text(text = opt.third, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                    }
                }
            }
        }

        Text(text = "💡 Advice: ${bus.travelerTip}", fontSize = 10.sp, color = Slate400, lineHeight = 14.sp)

        Button(
            onClick = { onOpenUrl(bus.portalUrl) },
            colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.OpenInBrowser, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Official KSRTC Booking & Live Awatar GPS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
        }
    }
}

@Composable
internal fun CorridorRailPanel(
    rail: RailCorridorInfo,
    onOpenUrl: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "Stations: ${rail.stationNames}",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Slate200
        )

        // Train Options List
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Slate900,
            border = BorderStroke(1.dp, Slate700),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                rail.keyTrains.forEach { train ->
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "#${train.trainNumber} ${train.trainName}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (train.isVandeBharatOrVistadome) AutoYellow else Slate200)
                                if (train.isVandeBharatOrVistadome) {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Surface(shape = RoundedCornerShape(4.dp), color = AutoYellow.copy(alpha = 0.2f)) {
                                        Text("PREMIUM", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = AutoYellow, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                                    }
                                }
                            }
                            Text(text = train.travelDuration, fontSize = 10.sp, color = Slate400)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Dep: ${train.departureTime} ➔ Arr: ${train.arrivalTime}", fontSize = 10.sp, color = Slate400)
                            Text(text = train.classFares, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                        }
                        HorizontalDivider(color = Slate800, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }

        Text(text = "📱 UTS Mobile: ${rail.utsMobileAdvice}", fontSize = 10.sp, color = AutoGreen)
        Text(text = "💡 Tip: ${rail.travelerTip}", fontSize = 10.sp, color = Slate400, lineHeight = 14.sp)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Button(
                onClick = { onOpenUrl(rail.liveTrackingUrl) },
                colors = ButtonDefaults.buttonColors(containerColor = MetroPurple),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("NTES Live Status", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
            }
            Button(
                onClick = { onOpenUrl(rail.irctcBookingUrl) },
                colors = ButtonDefaults.buttonColors(containerColor = Slate700),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("IRCTC Tickets", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate200)
            }
        }
    }
}

@Composable
internal fun CorridorPrivateBusPanel(
    priv: PrivateBusCorridorInfo,
    onOpenUrl: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "Operators: ${priv.majorOperators.joinToString(", ")}",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Slate200
        )

        // Fare Comparison Radar Box
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Slate900,
            border = BorderStroke(1.dp, Slate700),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Regular Weekday Fare:", fontSize = 11.sp, color = Slate400)
                    Text(priv.nonPeakFareRange, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Surge Weekend / Festival:", fontSize = 11.sp, color = SurgeWarningOrange)
                    Text(priv.surgeWeekendFareRange, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SurgeWarningOrange)
                }
            }
        }

        Text(text = "⚠️ Boarding Warning: ${priv.boardingPointsAdvisory}", fontSize = 10.sp, color = SurgeWarningOrange, lineHeight = 14.sp)
        Text(text = "💡 Tip: ${priv.travelerTip}", fontSize = 10.sp, color = Slate400, lineHeight = 14.sp)

        Button(
            onClick = { onOpenUrl(priv.bookingUrl) },
            colors = ButtonDefaults.buttonColors(containerColor = SurgeWarningOrange),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.OpenInBrowser, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Check Live Seats & Fares on RedBus", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
        }
    }
}
