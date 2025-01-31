package com.antsyferov.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antsyferov.ui.theme.PidkovaTheme
import com.antsyferov.ui.theme.Pink

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = Pink)
            .padding(PidkovaTheme.dimensions.border)
    ) {
        Row(
            modifier = Modifier
                .background(color = PidkovaTheme.colors.primary, shape = PidkovaTheme.shapes.chipShape)
                .padding(horizontal = PidkovaTheme.dimensions.paddingMedium, vertical = PidkovaTheme.dimensions.paddingSmall)
        ) {
            Text(
                text = text,
                style = PidkovaTheme.typography.overline,
                color = PidkovaTheme.colors.buttonText
            )
        }
    }
}

@Composable
@Preview
fun ChipPreview(){
    PidkovaTheme {
        Chip("Chip")
    }
}