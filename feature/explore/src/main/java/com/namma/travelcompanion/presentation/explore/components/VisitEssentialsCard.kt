package com.namma.travelcompanion.presentation.explore.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.data.repository.VisitEssentialsRepository
import com.namma.travelcompanion.domain.model.PaymentModes
import com.namma.travelcompanion.domain.model.VisitEssentials
import com.namma.travelcompanion.core.ui.components.openGeoCoordinates
import com.namma.travelcompanion.core.ui.components.openUrl

/** "Before you go" facts: how to pay, fees, special entry, annadanam, parking, what to carry. Empty sections hide. */
@Composable
fun VisitEssentialsCard(essentials: VisitEssentials, onReadSign: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val e = essentials
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Slate800,
        border = BorderStroke(1.dp, if (e.payment?.cashOnly == true) FastMeterAlert else Slate700)
    ) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("🧳 Before you go", color = AutoYellow, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            if (e.unverified) Line("⚠️ Not yet verified — please check at the counter on arrival.", SurgeWarningOrange)

            e.payment?.let { PaymentRow(it) }
            if (e.fees.isNotEmpty()) Section("🎟️ Entry fees", e.fees.map { "${it.label}: ${rupees(it.inr)}" })
            if (e.timings.isNotBlank() || e.closedOn.isNotEmpty()) Section(
                "🕘 Timings",
                listOfNotNull(e.timings.ifBlank { null }, e.closedOn.takeIf { it.isNotEmpty() }?.let { "Closed: ${it.joinToString()}" })
            )
            e.specialEntry.forEach { s ->
                Section("⚡ ${s.name}${s.inr?.let { " — ${rupees(it)}" }.orEmpty()}", listOf(s.note).filter { it.isNotBlank() })
                if (s.bookUrl.isNotBlank()) Link("Book on the official site ↗") { openUrl(context, s.bookUrl) }
            }
            e.annadanam?.takeIf { it.available }?.let {
                Section("🍛 Annadanam (free meal)", listOf(it.timings, it.note).filter(String::isNotBlank))
            }
            if (e.parking.isNotEmpty()) {
                Header("🅿️ Parking")
                e.parking.forEach { p ->
                    Link("${p.name}${p.distanceM?.let { " · $it m" }.orEmpty()}${if (p.fee.isNotBlank() && p.fee != "no" && p.fee != "yes") " · ${p.fee}" else ""} — Navigate") {
                        openGeoCoordinates(context, p.lat, p.lng, p.name)
                    }
                }
                if (e.parking.any { it.fromOsm }) Line("Parking © OpenStreetMap contributors (ODbL)", Slate400, 10)
            }
            val rules = listOf("👗 " to e.dressCode, "👟 " to e.footwear, "📷 " to e.photography).filter { it.second.isNotBlank() }
            if (rules.isNotEmpty()) Section("📋 Rules", rules.map { it.first + it.second })
            if (e.carry.isNotEmpty()) Section("✅ Carry", e.carry.map { "• $it" })
            if (e.warnings.isNotEmpty()) {
                Header("⚠️ Good to know")
                e.warnings.forEach { Line("• $it", SurgeWarningOrange) }
            }

            OutlinedButton(onClick = onReadSign, modifier = Modifier.fillMaxWidth()) {
                Text("📷 Read a Kannada sign here", color = MonsoonBlue)
            }
            FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                if (e.lastVerified.isNotBlank()) Line("Verified ${e.lastVerified}", Slate400, 11)
                if (e.sourceUrl.isNotBlank()) Link("Official source ↗", 11) { openUrl(context, e.sourceUrl) }
                Link("Report a change", 11) { openUrl(context, VisitEssentialsRepository.REPORT_URL) }
            }
        }
    }
}

@Composable
private fun PaymentRow(p: PaymentModes) {
    val text = if (p.cashOnly) "💵 CASH ONLY — no UPI or cards"
    else listOf("💵 Cash" to p.cash, "📱 UPI" to p.upi, "💳 Card" to p.card).filter { it.second }.joinToString("  ") { it.first }
    Column {
        Text(text, color = if (p.cashOnly) FastMeterAlert else FairMeterGreen, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        if (p.note.isNotBlank()) Line(p.note, Slate200)
    }
}

@Composable
private fun Section(title: String, lines: List<String>) {
    Column {
        Header(title)
        lines.forEach { Line(it, Slate200) }
    }
}

@Composable
private fun Header(text: String) = Text(text, color = Slate200, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

@Composable
private fun Line(text: String, color: Color, size: Int = 13) = Text(text, color = color, fontSize = size.sp, lineHeight = (size + 6).sp)

@Composable
private fun Link(text: String, size: Int = 13, onClick: () -> Unit) = Text(
    text,
    color = MonsoonBlue,
    fontSize = size.sp,
    textDecoration = TextDecoration.Underline,
    modifier = Modifier.clickable(onClick = onClick).padding(vertical = 2.dp)
)

private fun rupees(inr: Int) = if (inr == 0) "Free" else "₹$inr"
