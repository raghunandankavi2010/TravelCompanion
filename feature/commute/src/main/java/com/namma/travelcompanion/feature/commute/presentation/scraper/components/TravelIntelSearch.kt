package com.namma.travelcompanion.feature.commute.presentation.scraper.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900

/** Title row with the weather refresh button. */
@Composable
internal fun TravelIntelHeader(
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Travel Intel",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "🌐", fontSize = 22.sp)
            }
            Text(
                text = "Wikipedia lookup • Live weather • Forest & permit alerts",
                fontSize = 12.sp,
                color = MonsoonBlue
            )
        }

        // Refresh Button
        IconButton(
            onClick = onRefresh,
            enabled = !isRefreshing
        ) {
            if (isRefreshing) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = AutoYellow,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh weather",
                    tint = AutoYellow
                )
            }
        }
    }
}

/** Wikipedia scrape search card with quick-lookup preset chips. */
@Composable
internal fun ScrapeSearchCard(
    query: String,
    onQueryChange: (String) -> Unit,
    isScraping: Boolean,
    presetChips: List<String>,
    onScrape: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, MonsoonBlue.copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "🔍 Scrape Real-Time Travel Info from the Net",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MonsoonBlue
            )
            Text(
                text = "Looks up any Karnataka place on Wikipedia: summary, photo and map location.",
                fontSize = 11.sp,
                color = Slate400
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    placeholder = { Text("Enter town, temple or peak...", fontSize = 12.sp, color = Slate400) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Slate900,
                        unfocusedContainerColor = Slate900,
                        focusedBorderColor = MonsoonBlue,
                        unfocusedBorderColor = Slate700,
                        focusedTextColor = Slate200,
                        unfocusedTextColor = Slate200
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { onScrape(query) },
                    enabled = !isScraping && query.isNotBlank(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue)
                ) {
                    if (isScraping) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = Slate900,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Scrape", fontWeight = FontWeight.Bold, color = Slate900, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Preset Quick Chips
            Text(text = "Quick lookups:", fontSize = 11.sp, color = Slate400)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                presetChips.forEach { chip ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900,
                        border = BorderStroke(1.dp, Slate700),
                        modifier = Modifier.clickable { onScrape(chip) }
                    ) {
                        Text(
                            text = chip,
                            fontSize = 10.sp,
                            color = AutoYellow,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
