package com.antsyferov.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.antsyferov.ui.theme.PidkovaTheme
import com.antsyferov.ui.theme.White

@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    statusBarColor: Color = White,
    snackBar: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(statusBarColor)
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxSize()
            .background(PidkovaTheme.colors.background)
    ) {
        content()

        Box(modifier = Modifier.align(Alignment.TopCenter)) {
            snackBar()
        }
    }
}