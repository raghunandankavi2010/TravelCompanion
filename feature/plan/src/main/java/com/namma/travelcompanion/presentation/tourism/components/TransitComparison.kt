package com.namma.travelcompanion.presentation.tourism.components

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
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.domain.model.TransportModeComparison
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun TransportVerdictCard() {
        GlassCard(
            backgroundColor = Slate800.copy(alpha = 0.95f),
            borderColor = MonsoonBlue.copy(alpha = 0.5f)
        ) {
            Column {
                Text(
                    text = "💡 Statewide Transit Master Verdict:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = """• For Western Ghats & Mountain safety: Choose KSRTC (trained drivers, no breakdown abandonment).
• For Coastal & Nature scenery: Choose South Western Railway Vistadome train (Sakleshpur glass coach).
• For Wildlife Safaris: Choose Govt Jungle Lodges (exclusive core-zone tiger permits).
• For Spontaneous night trips: Choose Private Sleepers (VRL/SRS) via RedBus.""",
                    fontSize = 11.sp,
                    color = Slate200,
                    lineHeight = 16.sp
                )
            }
        }
}

@Composable
internal fun TransportComparisonCard(
    item: TransportModeComparison,
    onOpenBooking: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Operator Tag & Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(item.badgeColorHex).copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, Color(item.badgeColorHex))
                ) {
                    Text(
                        text = item.operatorType,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(item.badgeColorHex),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Text(
                    text = item.approxPriceRange,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )

            Text(
                text = item.headline,
                fontSize = 12.sp,
                color = MonsoonBlue,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.howItHelpsTravelers,
                fontSize = 12.sp,
                color = Slate400,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Pros & Cons
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "✅ Advantages (Why choose this):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                    item.pros.forEach { pro ->
                        Text(text = "• $pro", fontSize = 11.sp, color = Slate200, modifier = Modifier.padding(vertical = 1.dp))
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = "⚠️ Watch Out (Limitations):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SurgeWarningOrange)
                    item.cons.forEach { con ->
                        Text(text = "• $con", fontSize = 11.sp, color = Slate400, modifier = Modifier.padding(vertical = 1.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Best suited for
            Text(
                text = "🎯 Best Suited For: " + item.bestSuitedFor,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = AutoYellow
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Action Button: Book Tickets
            Button(
                onClick = onOpenBooking,
                colors = ButtonDefaults.buttonColors(containerColor = Color(item.badgeColorHex)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.OpenInBrowser,
                    contentDescription = null,
                    tint = Slate900,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Book on Official Portal (${item.bookingUrl.replace("https://", "").replace("www.", "").substringBefore("/")})",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
            }
        }
    }
}

@Composable
internal fun TicketPortalCard(
    item: TransportModeComparison,
    onOpenUrl: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Text(
                    text = item.whereToBuyTickets,
                    fontSize = 11.sp,
                    color = Slate400,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onOpenUrl,
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Open Portal",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
            }
        }
    }
}
