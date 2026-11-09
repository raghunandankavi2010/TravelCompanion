package com.namma.travelcompanion.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.ScreenTopBar
import com.namma.travelcompanion.domain.model.KarnatakaDistricts
import com.namma.travelcompanion.presentation.explore.components.PlaceCategoryChips
import com.namma.travelcompanion.presentation.explore.components.ShopAndTasteCard
import com.namma.travelcompanion.presentation.explore.components.PlaceListItem
import com.namma.travelcompanion.presentation.explore.components.icon

/** Every tourist place in one district, grouped by type (temples, waterfalls, wildlife, …). */
@Composable
fun DistrictScreen(
    viewModel: DistrictViewModel,
    onBack: () -> Unit,
    onOpenPlace: (placeId: String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val district = state.district ?: return

    Column(modifier = Modifier.fillMaxSize().background(Slate900)) {
        ScreenTopBar(title = district.name, subtitle = "${district.kannadaName} · HQ ${district.headquarters}", onBack = onBack)
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { Text(district.summary, color = Slate200, fontSize = 14.sp) }
            KarnatakaDistricts.shopAndTaste[district.id]?.let { items ->
                item { ShopAndTasteCard(items) }
            }
            if (state.isLoading) {
                item { CircularProgressIndicator(color = AutoYellow) }
            } else if (state.totalPlaces == 0) {
                item { Text("No places listed for this district yet.", color = Slate400) }
            } else {
                item {
                    PlaceCategoryChips(
                        counts = state.categoryCounts,
                        total = state.totalPlaces,
                        selected = state.selectedCategory,
                        onSelect = viewModel::selectCategory
                    )
                }
            }
            state.sections.forEach { (category, places) ->
                item(key = "header_${category.name}") {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                        Icon(category.icon, contentDescription = null, tint = AutoYellow, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("${category.label} (${places.size})", color = AutoYellow, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                }
                items(places, key = { it.id }) { place ->
                    PlaceListItem(place, onClick = { onOpenPlace(place.id) })
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
