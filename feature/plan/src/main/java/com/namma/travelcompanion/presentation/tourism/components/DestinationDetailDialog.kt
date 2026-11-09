package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.namma.travelcompanion.core.theme.AutoGreenDark
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.KarnatakaSpot
import com.namma.travelcompanion.domain.model.VisitEssentials
import com.namma.travelcompanion.presentation.explore.components.VisitEssentialsCard

/** Full-screen destination detail dialog. */
@Composable
fun DestinationDetailDialog(
    spot: KarnatakaSpot,
    essentials: VisitEssentials?,
    onReadSign: () -> Unit,
    isForeignerMode: Boolean,
    isSpeaking: Boolean,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit,
    onDismiss: () -> Unit,
    onOpenBookingUrl: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            color = Slate900,
            border = BorderStroke(1.dp, Slate700)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                // Top Close Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = getCategoryColor(spot.category).copy(alpha = 0.2f),
                            border = BorderStroke(1.dp, getCategoryColor(spot.category))
                        ) {
                            Text(
                                text = spot.category.label,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = getCategoryColor(spot.category),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "${spot.region.iconTag} ${spot.region.label}",
                            fontSize = 12.sp,
                            color = Slate400
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(32.dp)
                            .background(Slate800, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Slate200,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    essentials?.let { item { VisitEssentialsCard(it, onReadSign) } }
                    destinationDetailItems(
                        spot = spot,
                        isSpeaking = isSpeaking,
                        onPlayAudio = onPlayAudio,
                        onStopAudio = onStopAudio
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom Action: Open Booking Portal
                val isAranya = spot.bookingInfo.badgeTag.contains("ARANYA")
                Button(
                    onClick = onOpenBookingUrl,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isAranya) AutoGreenDark else MonsoonBlue
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInBrowser,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Slate200
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Book on ${spot.bookingInfo.portalName}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }
            }
        }
    }
}
