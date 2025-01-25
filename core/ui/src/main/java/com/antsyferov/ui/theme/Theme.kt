package com.antsyferov.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun PidkovaTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPidkovaColors provides PidkovaColors(),
        LocalPidkovaTypography provides PidkovaTypography(),
        LocalPidkovaShapes provides PidkovaShapes(),
        LocalPidkovaDimensions provides PidkovaDimensions(),
    ) {
        content()
    }
}

object PidkovaTheme {

    val colors: PidkovaColors
        @Composable
        get() = LocalPidkovaColors.current

    val typography: PidkovaTypography
        @Composable
        get() = LocalPidkovaTypography.current

    val shapes: PidkovaShapes
        @Composable
        get() = LocalPidkovaShapes.current

    val dimensions: PidkovaDimensions
        @Composable
        get() = LocalPidkovaDimensions.current


}