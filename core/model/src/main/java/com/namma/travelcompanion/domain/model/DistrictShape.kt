package com.namma.travelcompanion.domain.model

/** A point in degrees: x = longitude, y = latitude. */
data class GeoPoint(val lng: Double, val lat: Double)

/** A district's outline (one or more polygons) and where to put its label, from assets/maps. */
data class DistrictShape(
    val districtId: String,
    val polygons: List<List<GeoPoint>>,
    val label: GeoPoint
) {
    fun contains(point: GeoPoint): Boolean = polygons.any { it.containsPoint(point) }
}

/** Ray casting: count edge crossings of a ray going east from [point]; odd means inside. */
fun List<GeoPoint>.containsPoint(point: GeoPoint): Boolean {
    var inside = false
    var j = lastIndex
    for (i in indices) {
        val a = this[i]
        val b = this[j]
        if ((a.lat > point.lat) != (b.lat > point.lat) &&
            point.lng < (b.lng - a.lng) * (point.lat - a.lat) / (b.lat - a.lat) + a.lng
        ) {
            inside = !inside
        }
        j = i
    }
    return inside
}

/** Bounding box of all shapes, used to fit the map to the screen. */
data class GeoBounds(val minLng: Double, val minLat: Double, val maxLng: Double, val maxLat: Double) {
    companion object {
        fun of(shapes: List<DistrictShape>): GeoBounds {
            val points = shapes.flatMap { it.polygons.flatten() }
            return GeoBounds(points.minOf { it.lng }, points.minOf { it.lat }, points.maxOf { it.lng }, points.maxOf { it.lat })
        }
    }
}
