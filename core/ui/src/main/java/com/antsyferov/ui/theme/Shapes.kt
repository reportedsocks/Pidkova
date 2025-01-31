package com.antsyferov.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val LocalPidkovaShapes = staticCompositionLocalOf { PidkovaShapes() }

data class PidkovaShapes(
    val cardShape: CornerBasedShape = CutCornerShape(topEnd = 14.dp),
    val buttonShape: CornerBasedShape = CutCornerShape(topEnd = 10.dp),
    val dialogShape: CornerBasedShape = RoundedCornerShape(20.dp),
    val inputFieldShape: CornerBasedShape = CutCornerShape(topEnd = 10.dp),
    val chipShape: CornerBasedShape = CutCornerShape(bottomStart = 6.dp)
)