package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.domain.model.CityTransportTariff
import com.namma.travelcompanion.domain.model.RegionalDialectInfo
import com.namma.travelcompanion.domain.model.TouristGuidanceData
import com.namma.travelcompanion.core.ui.components.GlassCard

/** Expandable info panels toggled from the info hub. */
@Composable
internal fun TransportTariffsPanel(
    visible: Boolean,
    tariffs: List<CityTransportTariff>,
    onClose: () -> Unit,
    onOpenUrl: (String) -> Unit
) {
    AnimatedVisibility(visible = visible) {
        GlassCard(
            backgroundColor = Slate800.copy(alpha = 0.95f),
            borderColor = MonsoonBlue.copy(alpha = 0.5f)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🛺 All-Karnataka Auto Rickshaw & Transit Benchmarks",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                    IconButton(
                        onClick = { onClose() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate400)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                tariffs.forEach { tariff ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900,
                        border = BorderStroke(1.dp, Slate700)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = tariff.cityName,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate200
                                )
                                Text(
                                    text = "${tariff.baseFare} | ${tariff.perKmRate}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AutoGreen
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = tariff.localAdvice, fontSize = 11.sp, color = Slate400)
                            Text(
                                text = "🚌 Transit Option: ${tariff.sharedAutoOption}",
                                fontSize = 11.sp,
                                color = MonsoonBlue
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { onOpenUrl("https://ksrtc.in") },
                        colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = Slate900, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Book KSRTC Buses", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
                    }
                    OutlinedButton(
                        onClick = { onOpenUrl("https://karnatakatourism.org") },
                        border = BorderStroke(1.dp, AutoYellow),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("KSTDC Tours", fontSize = 11.sp, color = AutoYellow)
                    }
                }
            }
        }
    }
}

@Composable
internal fun DialectsPanel(
    visible: Boolean,
    dialects: List<RegionalDialectInfo>,
    onClose: () -> Unit
) {
    AnimatedVisibility(visible = visible) {
        GlassCard(
            backgroundColor = Slate800.copy(alpha = 0.95f),
            borderColor = AutoGreen.copy(alpha = 0.5f)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🍛 Regional Dialects & Iconic Cuisines of Karnataka",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                    IconButton(
                        onClick = { onClose() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate400)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                dialects.forEach { dialect ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900,
                        border = BorderStroke(1.dp, Slate700)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = dialect.dialectName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoGreen
                            )
                            Text(
                                text = "Greeting: ${dialect.commonGreeting}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Slate200
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Famous Cuisines: ${dialect.regionalCuisines.joinToString(", ")}",
                                fontSize = 11.sp,
                                color = MonsoonBlue
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "💡 Local Tip: ${dialect.localTip}",
                                fontSize = 11.sp,
                                color = Slate400
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun ScamBusterPanel(
    visible: Boolean,
    onClose: () -> Unit,
    onPlayKannada: (text: String, id: String) -> Unit
) {
    AnimatedVisibility(visible = visible) {
        GlassCard(
            backgroundColor = Slate800.copy(alpha = 0.95f),
            borderColor = SurgeWarningOrange.copy(alpha = 0.5f)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🛡️ Karnataka Tourist Scam Buster & Fair Prices",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                    IconButton(
                        onClick = { onClose() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate400)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                TouristGuidanceData.priceBenchmarks.forEach { item ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900,
                        border = BorderStroke(1.dp, Slate700)
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = item.itemName, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Slate200)
                                Text(text = item.ripOffWarning, fontSize = 10.sp, color = SurgeWarningOrange)
                                Text(text = "Say: '" + item.phoneticKannada + "'", fontSize = 10.sp, color = MonsoonBlue)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(text = item.fairPriceInr, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                                Text(text = item.approxUsd, fontSize = 10.sp, color = Slate400)
                                IconButton(
                                    onClick = { onPlayKannada(item.survivalKannada, "bench_" + item.id) },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Play", tint = AutoYellow, modifier = Modifier.size(16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
