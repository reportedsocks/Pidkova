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
    val error: Color = Red,
    val textPrimary: Color = Black,
    val textSecondary: Color = Black40,
    val icon: Color = Green,
    val border: Color = PinkVariant,
    val buttonBackground: Color = Green,
    val buttonBackgroundPressed: Color = Green80,
    val buttonText: Color = White,
    val cardBackground: Color = White,
    val cardBorder: Color = Blue80
)