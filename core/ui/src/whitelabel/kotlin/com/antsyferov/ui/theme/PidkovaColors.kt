package com.antsyferov.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalPidkovaColors = staticCompositionLocalOf { PidkovaColors() }

data class PidkovaColors(
    val primary: Color = Blue,
    val primaryVariant: Color = Blue80,
    val secondary: Color = Red,
    val secondaryVariant: Color = Red80,
    val background: Color = White,
    val surface: Color = White,
    val error: Color = Pink,
    val textPrimary: Color = Blue,
    val textSecondary: Color = Green,
    val icon: Color = Green,
    val border: Color = Pink40,
    val buttonBackground: Color = Blue,
    val buttonText: Color = White,
    val cardBackground: Color = White,
    val cardBorder: Color = Blue40
)