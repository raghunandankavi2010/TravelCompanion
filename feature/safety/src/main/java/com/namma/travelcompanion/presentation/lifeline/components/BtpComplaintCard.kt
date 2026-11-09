package com.namma.travelcompanion.presentation.lifeline.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.ui.components.GlassCard

/** BTP auto overcharging complaint drafter. */
@Composable
internal fun BtpComplaintCard(
    autoNumber: String,
    onAutoNumberChange: (String) -> Unit,
    incidentLocation: String,
    onIncidentLocationChange: (String) -> Unit,
    violationType: String,
    onViolationTypeChange: (String) -> Unit,
    onSend: () -> Unit
) {
    // Official BTP Auto Grievance Drafter
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = FastMeterAlert.copy(alpha = 0.6f)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Report, contentDescription = null, tint = FastMeterAlert, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lodge BTP Auto Overcharging Complaint",
                    color = FastMeterAlert,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Auto driver demanded double meter, refused to ply, or harassed you? Submit direct evidence to Bengaluru Traffic Police WhatsApp helpline (9480801000):",
                color = Slate200,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = autoNumber,
                onValueChange = onAutoNumberChange,
                label = { Text("Auto Registration No. (e.g., KA-04-AB-1234)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AutoYellow,
                    unfocusedBorderColor = Slate700,
                    focusedTextColor = Slate200,
                    unfocusedTextColor = Slate200
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = incidentLocation,
                onValueChange = onIncidentLocationChange,
                label = { Text("Incident Location (e.g., Near Indiranagar Metro)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AutoYellow,
                    unfocusedBorderColor = Slate700,
                    focusedTextColor = Slate200,
                    unfocusedTextColor = Slate200
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Select Violation Type:", color = Slate400, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))

            val violationOptions = listOf(
                "Demanded excess fare / refused meter",
                "Refused to go to destination",
                "Fast / tampered meter detected by AI",
                "Rude behavior or harassment"
            )

            violationOptions.forEach { option ->
                FilterChip(
                    selected = violationType == option,
                    onClick = { onViolationTypeChange(option) },
                    label = { Text(option, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = FastMeterAlert.copy(alpha = 0.3f),
                        selectedLabelColor = Slate200
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(2.dp))
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onSend,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null, tint = Slate200)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Send to BTP WhatsApp (9480801000)", color = Slate200, fontWeight = FontWeight.Black)
                }
            }
        }
    }
}
