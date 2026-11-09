package com.namma.travelcompanion.presentation.explore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.domain.model.District
import com.namma.travelcompanion.core.ui.components.GlassCard

/** One district on the home list: name, Kannada name, place count and what it is famous for. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DistrictCard(district: District, placeCount: Int, onClick: () -> Unit) {
    GlassCard(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(district.name, color = Slate200, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(district.kannadaName, color = Slate400, fontSize = 13.sp)
                }
                Text(
                    text = if (placeCount > 0) "$placeCount places to visit" else district.headquarters,
                    color = AutoYellow,
                    fontSize = 12.sp
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    district.famousFor.forEach { highlight ->
                        Surface(color = Slate700, shape = RoundedCornerShape(50)) {
                            Text(highlight, color = Slate200, fontSize = 11.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                        }
                    }
                }
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Slate400)
        }
    }
}
