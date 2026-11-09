package com.namma.travelcompanion.presentation.explore.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.DistrictShape
import com.namma.travelcompanion.domain.model.GeoBounds
import com.namma.travelcompanion.domain.model.GeoPoint
import com.namma.travelcompanion.domain.model.KarnatakaDistricts

private val districtColors = listOf(
    Color(0xFF1E3A5F), Color(0xFF234E52), Color(0xFF3B3355), Color(0xFF4A3B2A), Color(0xFF2F4F3A)
)

/** Tappable outline map of Karnataka's districts. Labels are drawn only where the district is wide enough. */
@Composable
internal fun KarnatakaMap(
    shapes: List<DistrictShape>,
    onDistrictClick: (districtId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (shapes.isEmpty()) return
    val bounds = remember(shapes) { GeoBounds.of(shapes) }
    val textMeasurer = rememberTextMeasurer()
    val lngSpan = bounds.maxLng - bounds.minLng
    val latSpan = bounds.maxLat - bounds.minLat

    fun toScreen(point: GeoPoint, size: Size) = Offset(
        ((point.lng - bounds.minLng) / lngSpan * size.width).toFloat(),
        ((bounds.maxLat - point.lat) / latSpan * size.height).toFloat()
    )

    fun toGeo(offset: Offset, width: Int, height: Int) = GeoPoint(
        bounds.minLng + offset.x / width * lngSpan,
        bounds.maxLat - offset.y / height * latSpan
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio((lngSpan / latSpan).toFloat())
            .semantics { contentDescription = "Map of Karnataka districts. Tap a district to open it." }
            .pointerInput(shapes) {
                detectTapGestures { offset ->
                    val point = toGeo(offset, size.width, size.height)
                    shapes.firstOrNull { it.contains(point) }?.let { onDistrictClick(it.districtId) }
                }
            }
    ) {
        shapes.forEachIndexed { index, shape ->
            val path = Path()
            shape.polygons.forEach { ring ->
                ring.forEachIndexed { i, point ->
                    val p = toScreen(point, size)
                    if (i == 0) path.moveTo(p.x, p.y) else path.lineTo(p.x, p.y)
                }
                path.close()
            }
            drawPath(path, districtColors[index % districtColors.size])
            drawPath(path, Slate900, style = Stroke(width = 1.dp.toPx()))
        }

        shapes.forEach { shape ->
            val name = KarnatakaDistricts.byId(shape.districtId)?.name?.substringBefore(" (") ?: return@forEach
            val layout = textMeasurer.measure(name, TextStyle(color = Slate200, fontSize = 8.sp, fontWeight = FontWeight.Bold))
            val ring = shape.polygons.maxBy { it.size }.map { toScreen(it, size) }
            val widthPx = ring.maxOf { it.x } - ring.minOf { it.x }
            if (widthPx > layout.size.width * 0.9f) {
                val center = toScreen(shape.label, size)
                drawText(layout, topLeft = Offset(center.x - layout.size.width / 2, center.y - layout.size.height / 2))
            }
        }
    }
}
