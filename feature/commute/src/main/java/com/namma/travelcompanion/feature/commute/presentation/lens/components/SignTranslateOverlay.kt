package com.namma.travelcompanion.feature.commute.presentation.lens.components

import android.content.ClipData
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.data.translator.TranslationModelState
import com.namma.travelcompanion.feature.commute.presentation.lens.SignReadState
import com.namma.travelcompanion.feature.commute.presentation.lens.TranslatedSignBlock
import kotlinx.coroutines.launch

/** Sign mode, drawn over the live camera preview: tap to freeze, English drawn over each Kannada block. */
@Composable
internal fun SignTranslateOverlay(
    state: SignReadState,
    modelState: TranslationModelState,
    onFreeze: () -> Unit,
    onRetake: () -> Unit,
    onDownloadModel: () -> Unit,
    onSpeak: (TranslatedSignBlock) -> Unit,
    onBack: () -> Unit
) {
    var selected by remember(state) { mutableStateOf<TranslatedSignBlock?>(null) }
    var modelPromptDismissed by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            SignReadState.Aiming, is SignReadState.Failed ->
                Box(modifier = Modifier.fillMaxSize().clickable(onClickLabel = "Freeze and translate", onClick = onFreeze))
            SignReadState.Reading ->
                CircularProgressIndicator(color = AutoYellow, modifier = Modifier.align(Alignment.Center))
            is SignReadState.Frozen -> FrozenSign(state, onSelect = { selected = it })
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
                .background(Slate900.copy(alpha = 0.85f), RoundedCornerShape(20.dp))
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Slate200)
            }
            Text("Read a sign", color = Slate200, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            if (state is SignReadState.Frozen) TextButton(onClick = onRetake) { Text("Retake", color = AutoYellow) }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val needsModel = modelState == TranslationModelState.NOT_DOWNLOADED || modelState == TranslationModelState.FAILED
            when {
                modelState == TranslationModelState.DOWNLOADING ->
                    InfoCard("Downloading the Kannada language pack. It waits for Wi-Fi.")
                needsModel && !modelPromptDismissed -> InfoCard(
                    if (modelState == TranslationModelState.FAILED) "The language pack download failed. Try again on Wi-Fi?"
                    else "Translate signs offline? This downloads the Kannada language pack (about 30 MB) once, on Wi-Fi only."
                ) {
                    Button(
                        onClick = onDownloadModel,
                        colors = ButtonDefaults.buttonColors(containerColor = AutoYellow, contentColor = Slate900)
                    ) { Text("Download on Wi-Fi") }
                    TextButton(onClick = { modelPromptDismissed = true }) { Text("Not now", color = Slate400) }
                }
            }

            val block = selected
            when {
                block != null -> SelectedBlockCard(block, onSpeak)
                state is SignReadState.Failed -> InfoCard(state.message)
                state is SignReadState.Frozen && state.blocks.isEmpty() ->
                    InfoCard("No Kannada text found. Move closer and tap Retake.")
                state is SignReadState.Frozen -> InfoCard("Tap a translation to hear it or copy it.")
                state == SignReadState.Aiming -> InfoCard("Point at a Kannada sign and tap the screen.")
            }
        }
    }
}

@Composable
private fun FrozenSign(state: SignReadState.Frozen, onSelect: (TranslatedSignBlock) -> Unit) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(Slate900)) {
        val density = LocalDensity.current
        val frame = state.frame
        val viewW = constraints.maxWidth.toFloat()
        val viewH = constraints.maxHeight.toFloat()
        // ContentScale.Fit: one scale for both axes, letterboxed in the middle.
        val scale = minOf(viewW / frame.width, viewH / frame.height)
        val dx = (viewW - frame.width * scale) / 2
        val dy = (viewH - frame.height * scale) / 2

        Image(
            bitmap = remember(frame) { frame.asImageBitmap() },
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
        state.blocks.forEach { item ->
            val b = item.block
            Box(
                modifier = Modifier
                    .offset { IntOffset((dx + b.left * scale).toInt(), (dy + b.top * scale).toInt()) }
                    .size(
                        width = with(density) { (b.width * scale).toDp() },
                        height = with(density) { (b.height * scale).toDp() }.coerceAtLeast(18.dp)
                    )
                    .background(Slate900.copy(alpha = 0.82f), RoundedCornerShape(4.dp))
                    .clickable(onClickLabel = "Speak or copy") { onSelect(item) }
                    .padding(horizontal = 2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicText(
                    text = item.english ?: b.text,
                    style = TextStyle(color = if (item.english != null) AutoYellow else Slate200, fontWeight = FontWeight.Bold),
                    autoSize = TextAutoSize.StepBased(minFontSize = 7.sp, maxFontSize = 28.sp)
                )
            }
        }
    }
}

@Composable
private fun SelectedBlockCard(block: TranslatedSignBlock, onSpeak: (TranslatedSignBlock) -> Unit) {
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()
    val copyText = listOfNotNull(block.english, block.block.text).joinToString("\n")
    InfoCard(block.english ?: "Not translated yet", detail = block.block.text) {
        Button(
            onClick = { onSpeak(block) },
            colors = ButtonDefaults.buttonColors(containerColor = AutoYellow, contentColor = Slate900)
        ) { Text("Speak Kannada") }
        OutlinedButton(onClick = {
            scope.launch { clipboard.setClipEntry(ClipEntry(ClipData.newPlainText("Sign translation", copyText))) }
        }) { Text("Copy", color = Slate200) }
    }
}

@Composable
private fun InfoCard(text: String, detail: String? = null, actions: (@Composable () -> Unit)? = null) {
    Surface(color = Slate900.copy(alpha = 0.9f), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(text, color = Slate200, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            if (detail != null) Text(detail, color = Slate400, fontSize = 13.sp)
            if (actions != null) Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { actions() }
        }
    }
}
