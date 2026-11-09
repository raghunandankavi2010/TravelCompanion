package com.namma.travelcompanion.presentation.hub

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.GlassCard

data class HubTile(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

/** A tab's landing page: one card per feature in that area. */
@Composable
fun HubScreen(title: String, subtitle: String, tiles: List<HubTile>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)) {
                Text(title, color = Slate200, fontSize = 24.sp, fontWeight = FontWeight.Black)
                Text(subtitle, color = Slate400, fontSize = 12.sp)
            }
        }
        items(tiles, key = { it.title }) { tile ->
            GlassCard(modifier = Modifier.fillMaxWidth().clickable(onClick = tile.onClick)) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(tile.icon, contentDescription = null, tint = AutoYellow, modifier = Modifier.size(28.dp))
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(tile.title, color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(tile.subtitle, color = Slate400, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
