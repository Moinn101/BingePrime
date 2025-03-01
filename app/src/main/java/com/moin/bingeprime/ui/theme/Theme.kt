package com.moin.bingeprime.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = androidx.compose.ui.graphics.Color(0xFFBB86FC),
    primaryVariant = androidx.compose.ui.graphics.Color(0xFF3700B3),
    secondary = androidx.compose.ui.graphics.Color(0xFF03DAC6),
    background = androidx.compose.ui.graphics.Color.Black,
    surface = androidx.compose.ui.graphics.Color.Black,
    onBackground = androidx.compose.ui.graphics.Color.White,
    onSurface = androidx.compose.ui.graphics.Color.White
)

@Composable
fun BingePrimeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = DarkColorPalette, typography = MaterialTheme.typography, shapes = MaterialTheme.shapes, content = content)
}