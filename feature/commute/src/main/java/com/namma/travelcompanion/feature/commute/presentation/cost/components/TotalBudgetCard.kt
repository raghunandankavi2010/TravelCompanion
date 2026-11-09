package com.namma.travelcompanion.feature.commute.presentation.cost.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.TravelCostBreakdown

/** Big total budget card with the itemized breakdown. */
@Composable
internal fun TotalBudgetCard(breakdown: TravelCostBreakdown) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.5.dp, GoldPro)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ESTIMATED TOTAL BUDGET",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate400,
                    letterSpacing = 1.sp
                )
                Text(
                    text = breakdown.approxUsdEquivalent,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "₹${breakdown.totalEstimatedCostInr}",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Per Person", fontSize = 10.sp, color = Slate400)
                        Text("₹${breakdown.perPersonCostInr}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Per Day", fontSize = 10.sp, color = Slate400)
                        Text("₹${breakdown.perDayCostInr}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MonsoonBlue)
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Route Dist.", fontSize = 10.sp, color = Slate400)
                        Text("~${breakdown.roundTripDistanceKm} km", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Slate200)
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Slate700)

            // Itemized Breakdown
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CostPill(icon = "🚌", label = "Transport", amount = "₹${breakdown.transportCostInr}")
                CostPill(icon = "🏨", label = "Stay", amount = "₹${breakdown.stayCostInr}")
                CostPill(icon = "🍲", label = "Veg Dining", amount = "₹${breakdown.diningCostInr}")
                CostPill(icon = "🎟️", label = "Entry Fees", amount = "₹${breakdown.entryTicketsCostInr}")
            }
        }
    }
}

@Composable
fun CostPill(icon: String, label: String, amount: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = icon, fontSize = 16.sp)
        Text(text = label, fontSize = 9.sp, color = Slate400)
        Text(text = amount, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate200)
    }
}
