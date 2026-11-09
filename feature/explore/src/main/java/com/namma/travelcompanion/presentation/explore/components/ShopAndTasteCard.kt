package com.namma.travelcompanion.presentation.explore.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.ui.components.GlassCard

/** What the district is known for buying and eating; "(GI)" items have a registered Geographical Indication. */
@Composable
internal fun ShopAndTasteCard(items: List<String>) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = AutoYellow, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Shop & taste", color = AutoYellow, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
            items.forEach { item ->
                Text("• $item", color = Slate200, fontSize = 13.sp, modifier = Modifier.padding(top = 4.dp))
            }
            if (items.any { "(GI)" in it }) {
                Text(
                    text = "GI = Geographical Indication: an officially registered product of this region. Look for the GI tag to avoid fakes.",
                    color = Slate400,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
