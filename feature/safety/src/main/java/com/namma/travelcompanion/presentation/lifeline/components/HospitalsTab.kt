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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.domain.model.HospitalInfo

/** Ambulance banner plus the trauma hospital list. */
@Composable
internal fun HospitalsTab(
    hospitals: List<HospitalInfo>,
    onCallAmbulance: () -> Unit,
    onDialHospital: (HospitalInfo) -> Unit,
    onOpenMaps: (HospitalInfo) -> Unit
) {
    // 24x7 Ambulance Quick Banner
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = FastMeterAlert.copy(alpha = 0.15f),
        border = BorderStroke(1.dp, FastMeterAlert)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("FREE EMERGENCY AMBULANCE", color = FastMeterAlert, fontSize = 11.sp, fontWeight = FontWeight.Black)
                Text("Dial 108 (Arogya Kavacha)", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { onCallAmbulance() },
                colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Call 108", fontWeight = FontWeight.Black, color = Slate200)
            }
        }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
        text = "TOP 24x7 TRAUMA HOSPITALS BY ZONE",
        color = Slate400,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
    )
    Spacer(modifier = Modifier.height(8.dp))

    hospitals.forEach { hospital ->
        HospitalCard(
            hospital = hospital,
            onDial = { onDialHospital(hospital) },
            onOpenMaps = { onOpenMaps(hospital) }
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
internal fun HospitalCard(
    hospital: HospitalInfo,
    onDial: () -> Unit,
    onOpenMaps: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = hospital.name,
                        color = Slate200,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Zone: ${hospital.zone}",
                        color = MonsoonBlue,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(hospital.address, color = Slate400, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("🏥 ${hospital.specialty}", color = FairMeterGreen, fontSize = 11.sp)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onDial,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = Slate200, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Emergency", fontSize = 11.sp, color = Slate200, fontWeight = FontWeight.Bold)
                    }
                }

                OutlinedButton(
                    onClick = onOpenMaps,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, AutoYellow)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Map, contentDescription = null, tint = AutoYellow, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Google Maps", fontSize = 11.sp, color = AutoYellow, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
