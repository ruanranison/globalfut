package com.example.globalfut.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = GFPrimary,
    onPrimary = GFWhite,
    secondary = GFSecondary,
    onSecondary = GFWhite,
    background = GFBackground,
    onBackground = GFText,
    surface = GFWhite,
    onSurface = GFText
)

private val DarkColors = darkColorScheme(
    primary = GFPrimary,
    onPrimary = GFWhite,
    secondary = GFSecondary,
    onSecondary = GFWhite,
    background = Color(0xFF121212),
    onBackground = GFWhite,
    surface = Color(0xFF1E1E1E),
    onSurface = GFWhite
)

@Composable
fun GlobalFutTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = GlobalFutShapes,
        content = content
    )
}
