package com.nammaauto.ai.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = AutoYellow,
    onPrimary = Slate900,
    primaryContainer = AutoYellowDark,
    onPrimaryContainer = Slate200,
    secondary = AutoGreen,
    onSecondary = Slate900,
    tertiary = MetroPurple,
    background = Slate900,
    surface = Slate800,
    onBackground = Slate200,
    onSurface = Slate200,
    surfaceVariant = Slate700,
    onSurfaceVariant = Slate400,
    error = FastMeterAlert
)

private val LightColorScheme = lightColorScheme(
    primary = AutoYellowDark,
    onPrimary = Slate900,
    primaryContainer = AutoYellow,
    secondary = AutoGreenDark,
    tertiary = MetroPurpleDark,
    background = Slate900, // Bangalore tech aesthetic looks best in deep dark slate mode
    surface = Slate800,
    onBackground = Slate200,
    onSurface = Slate200,
    surfaceVariant = Slate700,
    onSurfaceVariant = Slate400,
    error = FastMeterAlert
)

@Composable
fun NammaAutoAITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            window?.let {
                it.statusBarColor = Slate900.toArgb()
                it.navigationBarColor = Slate900.toArgb()
                WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
