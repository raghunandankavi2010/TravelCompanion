package com.namma.travelcompanion.presentation.safety.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800

/** Emergency contact field, Send SOS and Call 112 buttons. */
@Composable
internal fun SosSection(
    contactInput: String,
    savedContact: String,
    onContactInputChange: (String) -> Unit,
    onSaveContact: () -> Unit,
    onSendSos: () -> Unit,
    onCall112: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Slate800,
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "EMERGENCY BROADCAST & BTP HELPLINE",
                color = FastMeterAlert,
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
            )
            Text(
                text = "Sends your GPS location to your emergency contact (or any app you pick), or call 112:",
                color = Slate400,
                fontSize = 11.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = contactInput,
                onValueChange = onContactInputChange,
                label = { Text("Emergency contact number (optional)", fontSize = 11.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                trailingIcon = {
                    if (contactInput != savedContact) {
                        TextButton(onClick = { onSaveContact() }) { Text("Save", color = AutoYellow) }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onSendSos,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = Slate200)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Send SOS", color = Slate200, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    }
                }

                Button(
                    onClick = { onCall112() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MetroPurple),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = Slate200)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Call 112", color = Slate200, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    }
                }
            }

        }
    }
}
