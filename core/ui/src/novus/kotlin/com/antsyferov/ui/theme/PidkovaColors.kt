package com.antsyferov.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalPidkovaColors = staticCompositionLocalOf { PidkovaColors() }

data class PidkovaColors(
    val primary: Color = Blue,
    val primaryVariant: Color = Blue80,
    val secondary: Color = Red,
    val secondaryVariant: Color = Red80,
    val background: Color = Green,
    val surface: Color = White,
    val error: Color = Red,
    val textPrimary: Color = White,
    val textSecondary: Color = Gray,
    val icon: Color = Green,
    val border: Color = Red40,
    val buttonBackground: Color = Blue,
    val buttonBackgroundPressed: Color = Blue,
    val buttonText: Color = White,
    val cardBackground: Color = White,
    val cardBorder: Color = Blue40,
    val chipBorder: Color = Blue,
    val textGradientHighlight: Color = White
)