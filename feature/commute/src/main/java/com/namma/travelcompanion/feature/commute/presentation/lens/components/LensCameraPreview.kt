package com.namma.travelcompanion.feature.commute.presentation.lens.components

import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import java.util.concurrent.Executors

/** Live CameraX preview bound to the lifecycle; reports the bound [Camera] so the caller can drive the torch. */
@Composable
internal fun LensCameraPreview(
    analyzer: ImageAnalysis.Analyzer,
    onCameraBound: (Camera) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraExecutor = Executors.newSingleThreadExecutor()
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, analyzer)
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    val camera = cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                    onCameraBound(camera)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        }
    )
}

@Composable
internal fun ViewfinderOverlay() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Scanning HUD Box
        Box(
            modifier = Modifier
                .size(width = 310.dp, height = 210.dp)
                .border(BorderStroke(2.dp, AutoYellow.copy(alpha = 0.85f)), RoundedCornerShape(16.dp))
        ) {
            // Target corners
            Surface(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
                color = Slate900.copy(alpha = 0.75f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "AIM AT BUS BOARD OR KANNADA SIGN",
                    color = AutoYellow,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Composable
internal fun CameraPermissionFallback(onGrantPermission: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = null,
                tint = AutoYellow,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Camera Permission Required",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            Text(
                text = "Namma Vision Lens uses on-device camera processing to instantly read Kannada bus boards and signboards.",
                fontSize = 12.sp,
                color = Slate400,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Button(
                onClick = onGrantPermission,
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow, contentColor = Slate900)
            ) {
                Text("Grant Camera Access", fontWeight = FontWeight.Bold)
            }
        }
    }
}
