package com.namma.travelcompanion.presentation.store

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.core.ui.components.BangaloreBadge
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
fun ProStoreScreen(viewModel: ProStoreViewModel) {
    val state by viewModel.subscriptionState.collectAsState()
    val activity = LocalActivity.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Full Access", color = Slate200, fontSize = 24.sp, fontWeight = FontWeight.Black)
            BangaloreBadge(
                text = if (state.isProUser) "UNLOCKED" else "FREE",
                badgeColor = if (state.isProUser) GoldPro else Slate700,
                textColor = if (state.isProUser) Slate900 else Slate200
            )
        }
        Text(
            text = "Pay once, keep it forever. No subscription, no ads.",
            color = Slate400,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                FeatureRow("Meter Cam scans", "${BillingManager.DEFAULT_FREE_SCANS} / day", "Unlimited")
                FeatureRow("Kannada voice playback", "${BillingManager.DEFAULT_FREE_PHRASES} / day", "Unlimited")
                FeatureRow("Fare estimates, GPS meter, guides", "Free", "Free")
                FeatureRow("SOS, 112 & Night Guardian", "Always free", "Always free")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isProUser -> Text(
                text = "Thank you! Everything is unlocked on this Google account.",
                color = AutoGreen,
                fontWeight = FontWeight.Bold
            )
            state.isPurchasePending -> Text(
                text = "Payment pending. Access unlocks automatically once Google Play confirms it.",
                color = GoldPro
            )
            else -> Button(
                onClick = {
                    val started = activity != null && viewModel.buyFullAccess(activity)
                    if (!started) {
                        Toast.makeText(context, "Google Play is not reachable right now. Try again later.", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = state.fullAccessPrice != null,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = GoldPro),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = state.fullAccessPrice?.let { "Unlock everything for $it" } ?: "Connecting to Google Play…",
                    color = Slate900,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Already bought it on another phone? It restores automatically when you sign in with the same Google account.",
            color = Slate400,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun FeatureRow(feature: String, free: String, full: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(feature, color = Slate200, fontSize = 13.sp, modifier = Modifier.weight(1.4f))
        Text(free, color = Slate400, fontSize = 12.sp, modifier = Modifier.weight(1f))
        Text(full, color = GoldPro, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
    }
}
