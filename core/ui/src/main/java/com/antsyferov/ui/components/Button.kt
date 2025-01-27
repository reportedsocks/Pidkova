package com.antsyferov.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antsyferov.ui.theme.PidkovaTheme
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var touchedDown by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        if (touchedDown) PidkovaTheme.colors.cardBorder else PidkovaTheme.colors.primary,
        label = ""
    )

    val containerColor by animateColorAsState(
        if (touchedDown) PidkovaTheme.colors.buttonBackgroundPressed else PidkovaTheme.colors.buttonBackground,
        label = ""
    )

    val textColor by animateColorAsState(
        if (touchedDown) PidkovaTheme.colors.buttonText else PidkovaTheme.colors.textPrimary,
        label = ""
    )

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        touchedDown = true
                        val released = try {
                            tryAwaitRelease()
                        } catch (c: CancellationException) { false }
                        if (released) { onClick() }
                        touchedDown = false
                    }
                )
            }
            .background(borderColor)
            .padding(2.dp)
            .shadow(10.dp, RectangleShape)
    ) {
        Row(
            modifier = Modifier
                .background(color = containerColor, shape = PidkovaTheme.shapes.buttonShape)
                .padding(horizontal = PidkovaTheme.dimensions.paddingMedium, vertical = PidkovaTheme.dimensions.paddingSmall)
        ) {
            Text(
                text = text,
                style = PidkovaTheme.typography.button,
                color = textColor
            )
        }
    }
}



@Composable
@Preview
fun ButtonPreview() {
    PidkovaTheme {
        Button(
            text = "Click me!",
            onClick = {}
        )
    }
}