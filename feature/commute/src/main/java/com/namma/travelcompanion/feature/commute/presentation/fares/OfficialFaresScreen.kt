package com.namma.travelcompanion.feature.commute.presentation.fares

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.FareSource
import com.namma.travelcompanion.domain.model.MeteredTariff
import com.namma.travelcompanion.domain.model.OfficialFares
import com.namma.travelcompanion.core.ui.components.GlassCard
import com.namma.travelcompanion.core.ui.components.openUrl

/** Government-notified fares for a chosen distance, each with its effective date and source. */
@Composable
fun OfficialFaresScreen() {
    var km by rememberSaveable { mutableFloatStateOf(5f) }
    val distance = km.toDouble()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("Official fares", color = Slate200, fontSize = 24.sp, fontWeight = FontWeight.Black)
                Text("What the government says a ride should cost", color = Slate400, fontSize = 12.sp)
                Text(
                    text = "Distance: ${"%.1f".format(km)} km",
                    color = AutoYellow,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Slider(
                    value = km,
                    onValueChange = { km = (it * 2).toInt() / 2f },
                    valueRange = 1f..40f,
                    colors = SliderDefaults.colors(thumbColor = AutoYellow, activeTrackColor = AutoYellow)
                )
            }
        }

        item { SectionTitle("Auto-rickshaw meter (day fare)") }
        items(OfficialFares.autoRickshaw, key = { "auto_" + it.name }) { TariffCard(it, distance) }

        item { SectionTitle("City taxi & app cabs (no surge allowed)") }
        item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    OfficialFares.taxi.forEach { FareLine(it.name, it.fareFor(distance)) }
                    SourceLine(OfficialFares.taxi.first().source)
                }
            }
        }

        item { SectionTitle("Namma Metro (Bengaluru)") }
        item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    FareLine("Token fare for this distance", OfficialFares.metroFare(distance))
                    SourceLine(OfficialFares.metroSource)
                }
            }
        }

        item { SectionTitle("Buses (BMTC & KSRTC)") }
        items(OfficialFares.busNotes, key = { "bus_" + it.title }) { note ->
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(note.title, color = Slate200, fontWeight = FontWeight.Bold)
                    Text(note.detail, color = Slate400, fontSize = 12.sp)
                    SourceLine(note.source)
                }
            }
        }

        item {
            Text(
                text = "Fares are taken from government notifications as reported on the date shown; they change. " +
                    "Meter fare is for the road distance actually travelled.",
                color = Slate400,
                fontSize = 11.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, color = AutoYellow, fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 6.dp))
}

@Composable
private fun TariffCard(tariff: MeteredTariff, km: Double) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
            FareLine(tariff.name, tariff.fareFor(km))
            Text(
                text = "₹${tariff.baseFare.toInt()} for first ${tariff.baseKm} km, then ₹${tariff.perKm.toInt()}/km. ${tariff.extras}",
                color = Slate400,
                fontSize = 12.sp
            )
            SourceLine(tariff.source)
        }
    }
}

@Composable
private fun FareLine(label: String, rupees: Int) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Slate200, fontSize = 14.sp, modifier = Modifier.weight(1f))
        Text("₹$rupees", color = AutoGreen, fontSize = 18.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun SourceLine(source: FareSource) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(top = 6.dp)) {
        if (source.note.isNotBlank()) Text(source.note, color = Slate400, fontSize = 11.sp)
        Text(
            text = "Effective ${source.effectiveFrom} · Source",
            color = MonsoonBlue,
            fontSize = 11.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { openUrl(context, source.url) }
        )
    }
}
