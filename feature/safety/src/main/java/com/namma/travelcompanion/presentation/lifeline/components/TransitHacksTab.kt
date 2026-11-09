package com.namma.travelcompanion.presentation.lifeline.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.AirportBusRoute
import com.namma.travelcompanion.core.ui.components.BangaloreBadge
import com.namma.travelcompanion.core.ui.components.GlassCard

/** Vayu Vajra airport bus hacks and prepaid auto booth guide. */
@Composable
internal fun TransitHacksTab(routes: List<AirportBusRoute>) {
    // Airport Transit Hero Card
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = MonsoonBlue.copy(alpha = 0.6f)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.DirectionsBus, contentDescription = null, tint = MonsoonBlue, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Airport Vayu Vajra AC Bus Hacks",
                    color = MonsoonBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Save ₹1,500+! Never pay ₹1,800 for airport taxis. BMTC Vayu Vajra Volvos run 24x7 with luggage bays, AC, and high frequency directly into major tech hubs:",
                color = Slate200,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )
        }
    }

    Spacer(modifier = Modifier.height(14.dp))

    routes.forEach { route ->
        AirportBusCard(route = route)
        Spacer(modifier = Modifier.height(10.dp))
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Majestic & Yeshwanthpur Prepaid Counter Guide
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Slate800,
        border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.4f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "OFFICIAL PREPAID AUTO BOOTHS (NO HAGGLING)",
                color = AutoYellow,
                fontSize = 11.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "• Majestic Station: Exit Platform 1 gate. Traffic police booth gives a fixed receipt (Govt rate + ₹2 fee). Driver cannot refuse!\n• Yeshwanthpur Station: Counter located at main concourse entry.\n• Cantonment Station: Outside main portico.",
                color = Slate200,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
internal fun AirportBusCard(route: AirportBusRoute) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BangaloreBadge(
                        text = route.routeCode,
                        badgeColor = MonsoonBlue,
                        textColor = Slate900
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "to ${route.destination}",
                        color = Slate200,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "₹${route.fareRupees}",
                    color = FairMeterGreen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text("Stops: " + route.majorStops.joinToString(" ➔ "), color = Slate400, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Frequency: ${route.frequency}", color = AutoYellow, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("💰 ${route.comparisonWithCab}", color = FairMeterGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}
