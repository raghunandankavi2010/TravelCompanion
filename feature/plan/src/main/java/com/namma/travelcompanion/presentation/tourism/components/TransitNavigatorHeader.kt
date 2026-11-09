package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
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
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900

enum class NavigatorTab(val label: String, val iconTag: String) {
    LIVE_TRANSIT("Live Bus, Train & Private", "⚡"),
    COMPARE_TRANSPORT("Fleet Comparison", "🚆"),
    TICKET_PORTALS("Where to Buy", "🎟️"),
    BEST_SEASONS("Best Seasons", "🌤️"),
    GUIDES_INFO("Guides & Audio", "👨‍🏫")
}

@Composable
internal fun NavigatorHeader() {
    Spacer(modifier = Modifier.height(16.dp))
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Karnataka Travel Navigator",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "🚆", fontSize = 22.sp)
        }
        Text(
            text = "Real-Time KSRTC • Indian Railways • Private Sleepers • Fares",
            fontSize = 12.sp,
            color = AutoYellow
        )
    }
}

@Composable
internal fun BusLensActionCard(onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Slate800,
        border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.6f)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = AutoYellow.copy(alpha = 0.2f),
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "📷", fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Namma Vision Lens",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = AutoGreen.copy(alpha = 0.25f)
                        ) {
                            Text(
                                text = "ON-DEVICE AI",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoGreen,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Text(
                        text = "Scan Kannada bus boards, route numbers & platform signs",
                        fontSize = 11.sp,
                        color = Slate400
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.DirectionsBus,
                contentDescription = "Open Lens",
                tint = AutoYellow,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

@Composable
internal fun NavigatorTabBar(
    selectedTab: NavigatorTab,
    onSelect: (NavigatorTab) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NavigatorTab.values().forEach { tab ->
            val isSelected = selectedTab == tab
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) AutoYellow else Slate800,
                border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700),
                modifier = Modifier.clickable { onSelect(tab) }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = tab.iconTag, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = tab.label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Slate900 else Slate200
                    )
                }
            }
        }
    }
}
