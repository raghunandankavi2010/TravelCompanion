package com.namma.travelcompanion.presentation.lifeline.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.domain.model.CitySlangGuide

/** Bengaluru street etiquette and slang guide. */
@Composable
internal fun StreetEtiquetteCard(slangGuides: List<CitySlangGuide>) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Slate800,
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "BENGALURU STREET SURVIVAL ETIQUETTE",
                color = GoldPro,
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            slangGuides.forEach { slang ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(slang.kannadaLipi, color = AutoYellow, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("(${slang.phonetic})", color = Slate200, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                    Text("“${slang.meaning}” — ${slang.culturalContext}", color = Slate400, fontSize = 11.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun LegalDisclaimerCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "LEGAL & FAIR USE DISCLAIMER",
                color = Slate400,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "NammaAuto AI is an independent utility. All trademarks, service marks, and brand names (Uber, Ola, Rapido, Namma Yatri, BTP) are property of their respective owners. Their reference herein is strictly for descriptive nominative comparison under Section 30 of the Trade Marks Act, 1999 and does not imply endorsement or affiliation.",
                color = Slate400,
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }
    }
}
