package com.namma.travelcompanion.presentation.food.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.TempleAnnadanaGuidance

/** Temple annadana meal rules dialog. */
@Composable
fun AnnadanaDetailDialog(
    guidance: TempleAnnadanaGuidance,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .clip(RoundedCornerShape(20.dp)),
            color = Slate900,
            border = BorderStroke(1.dp, AutoGreen)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = guidance.templeName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AutoGreen
                        )
                        Text(
                            text = guidance.diningHallName,
                            fontSize = 12.sp,
                            color = AutoYellow
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, null, tint = Slate400)
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = Slate700)

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    AnnadanaFactRow(label = "🕒 Lunch Hours", value = guidance.lunchTimings)
                    AnnadanaFactRow(label = "🌙 Dinner Hours", value = guidance.dinnerTimings)
                    AnnadanaFactRow(label = "👗 Dress Code", value = guidance.dressCode)
                    AnnadanaFactRow(label = "💰 Cost", value = guidance.costNotes)
                    AnnadanaFactRow(label = "🍲 Prasada Menu", value = guidance.mealDescription)
                    AnnadanaFactRow(label = "🙏 Sacred Etiquette", value = guidance.etiquetteTip)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = AutoGreen),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Understood & Close", color = Slate900, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun AnnadanaFactRow(label: String, value: String) {
    Column {
        Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, fontSize = 12.sp, color = Slate200, lineHeight = 16.sp)
    }
}
