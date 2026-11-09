package com.namma.travelcompanion.presentation.lifeline.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.EmergencyContact

/** Hotline row with a round call button. */
@Composable
internal fun EmergencyContactCard(
    contact: EmergencyContact,
    onDial: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, if (contact.isPrimarySos) FastMeterAlert.copy(alpha = 0.7f) else Slate700)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = contact.title,
                        color = if (contact.isPrimarySos) Slate200 else Slate200,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = contact.subtitle,
                    color = Slate400,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "📞 ${contact.phoneNumber}  •  ${contact.operatingHours}",
                    color = AutoYellow,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = onDial,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (contact.isPrimarySos) FastMeterAlert else AutoGreen
                ),
                shape = CircleShape,
                modifier = Modifier.size(46.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call",
                    tint = if (contact.isPrimarySos) Slate200 else Slate900,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
