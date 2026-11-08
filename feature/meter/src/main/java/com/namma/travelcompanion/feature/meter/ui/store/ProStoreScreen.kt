package com.namma.travelcompanion.feature.meter.ui.store

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.PlanTier
import com.namma.travelcompanion.presentation.components.BangaloreBadge
import com.namma.travelcompanion.presentation.components.GlassCard

@Composable
fun ProStoreScreen(
    viewModel: ProStoreViewModel
) {
    val context = LocalContext.current
    val subscription by viewModel.subscriptionState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Namma Commuter Pro",
                    color = Slate200,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Play Store In-App Purchases & Commuter Pass",
                    color = Slate400,
                    fontSize = 12.sp
                )
            }
            BangaloreBadge(
                text = if (subscription.isProUser) "VIP UNLOCKED" else "FREE TIER",
                badgeColor = if (subscription.isProUser) GoldPro else Slate700,
                textColor = Slate900
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Hero Value Proposition Card
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            borderColor = GoldPro.copy(alpha = 0.5f)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = GoldPro,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Save ₹3,000+ Monthly on Bangalore Rides",
                        color = GoldPro,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Stop getting overcharged by tampered meters or arbitrary quotes. Unlock real-time on-device AI meter verification, full Kannada speech audio, and night route radar.",
                    color = Slate200,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subscription Cards
        Text(text = "SELECT COMMUTER PASS", color = Slate400, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        PlanTier.values().forEach { tier ->
            val isCurrentTier = subscription.isProUser && subscription.activeTier == tier
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(14.dp),
                color = if (isCurrentTier) Slate800 else Slate800.copy(alpha = 0.8f),
                border = BorderStroke(
                    if (isCurrentTier) 2.dp else 1.dp,
                    if (isCurrentTier) GoldPro else if (tier == PlanTier.MONTHLY_PRO) AutoYellow.copy(alpha = 0.6f) else Slate700
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = tier.title,
                                color = Slate200,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            tier.badge?.let { badge ->
                                Spacer(modifier = Modifier.width(8.dp))
                                BangaloreBadge(
                                    text = badge,
                                    badgeColor = if (tier == PlanTier.MONTHLY_PRO) AutoYellow else MetroPurple,
                                    textColor = Slate900
                                )
                            }
                        }
                        Text(
                            text = tier.period,
                            color = Slate400,
                            fontSize = 11.sp
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = tier.priceFormatted,
                            color = AutoYellow,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Button(
                            onClick = {
                                viewModel.purchaseTier(tier)
                                Toast.makeText(context, "${tier.title} Activated!", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isCurrentTier) FairMeterGreen else AutoYellow
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (isCurrentTier) "Active" else "Unlock",
                                color = Slate900,
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Feature Comparison Table
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Slate800,
            border = BorderStroke(1.dp, Slate700)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "FEATURE MATRIX",
                    color = AutoYellow,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                ComparisonRow(feature = "On-Device MeterCam AI OCR", freeVal = "3 / day", proVal = "Unlimited")
                ComparisonRow(feature = "Kannada Sathi Audio Speech", freeVal = "5 / day", proVal = "Unlimited")
                ComparisonRow(feature = "Night Safety Corridor Radar", freeVal = "Basic GPS", proVal = "Full AI Detour Alerts")
                ComparisonRow(feature = "BTP Complaint Evidence Report", freeVal = "✕", proVal = "✓ Instant PDF/Share")
                ComparisonRow(feature = "Ad-Free Commute Experience", freeVal = "✕", proVal = "✓ 100% Ad-Free")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Testing Reset Action
        OutlinedButton(
            onClick = {
                viewModel.resetForTesting()
                Toast.makeText(context, "Reset to Free Tier", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Slate700)
        ) {
            Text("Reset to Free Tier (For Play Store Testing)", color = Slate400, fontSize = 11.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ComparisonRow(
    feature: String,
    freeVal: String,
    proVal: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = feature, color = Slate200, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
        Text(text = freeVal, color = Slate400, fontSize = 11.sp, modifier = Modifier.weight(1f))
        Text(text = proVal, color = FairMeterGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
    }
}
