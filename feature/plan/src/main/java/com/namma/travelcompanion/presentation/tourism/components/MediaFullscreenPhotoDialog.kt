package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.KarnatakaSpot

@Composable
internal fun FullscreenPhotoDialog(
    spot: KarnatakaSpot,
    onDismiss: () -> Unit,
    onWatchVideo: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Slate900.copy(alpha = 0.96f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Top close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = spot.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Slate200)
                        Text(text = "License: Creative Commons CC BY-SA 4.0 / Wikimedia", fontSize = 11.sp, color = AutoGreen)
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, null, tint = Slate200, modifier = Modifier.size(28.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Image in Center
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    AsyncImage(
                        model = spot.imageUrl,
                        contentDescription = spot.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = spot.overview,
                    fontSize = 12.sp,
                    color = Slate400,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onWatchVideo,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.PlayArrow, null, tint = Color.White)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Watch 4K Cinematic Tour", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
