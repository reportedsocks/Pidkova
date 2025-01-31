package com.antsyferov.ui.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun SnackBar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier
) {
    Snackbar(
        snackbarData = snackbarData,
        containerColor = PidkovaTheme.colors.primary,
        contentColor = PidkovaTheme.colors.buttonText,
        shape = PidkovaTheme.shapes.buttonShape
    )
}

@Composable
fun SnackbarHost(
    snackbarHostState: SnackbarHostState
) {
    androidx.compose.material3.SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { SnackBar(it) }
    )
}