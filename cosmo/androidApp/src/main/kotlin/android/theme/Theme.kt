// Theme.kt
package com.yourcompany.cosmo.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SpaceBlack = Color(0xFF02030A)
private val StarWhite  = Color(0xFFEAEAEA)
private val NebulaBlue = Color(0xFF5BA3F7)
private val WarpViolet = Color(0xFF7A5CF5)

private val DarkColors = darkColorScheme(
    primary = NebulaBlue,
    secondary = WarpViolet,
    background = SpaceBlack,
    surface = SpaceBlack,
    onPrimary = Color.White,
    onBackground = StarWhite,
    onSurface = StarWhite
)

@Composable
fun CosmoTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColors else DarkColors
    MaterialTheme(colorScheme = colors, content = content)
}
