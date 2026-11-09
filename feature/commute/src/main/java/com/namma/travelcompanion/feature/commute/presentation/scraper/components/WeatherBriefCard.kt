package com.namma.travelcompanion.feature.commute.presentation.scraper.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.network.api.LiveWeatherBrief

/** "Weather now" section title. */
@Composable
internal fun WeatherTitle() {
    Text(
        text = "⛅ Weather now (Open-Meteo)",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = AutoYellow
    )
}

/** Horizontally scrolling row of weather cards. */
@Composable
internal fun WeatherBriefRow(weatherBriefs: List<LiveWeatherBrief>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        weatherBriefs.forEach { weather ->
            WeatherBriefCard(weather = weather)
        }
    }
}

@Composable
internal fun WeatherBriefCard(weather: LiveWeatherBrief) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(150.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = weather.zoneName,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow,
                    maxLines = 1
                )
                Text(
                    text = weather.tempCelsius,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
            }

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.WbSunny, null, tint = AutoYellow, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = weather.condition, fontSize = 11.sp, color = Slate200)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CloudQueue, null, tint = MonsoonBlue, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (weather.isLive) "Rain today: ${weather.rainProbability}" else "Typical season (offline)",
                        fontSize = 10.sp,
                        color = MonsoonBlue
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Text(
                    text = "💡 ${weather.advisoryTag}",
                    fontSize = 9.sp,
                    color = Slate400,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                )
            }
        }
    }
}
