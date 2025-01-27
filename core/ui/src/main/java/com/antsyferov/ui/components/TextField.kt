package com.antsyferov.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun TextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    isError: Boolean = false,
    hint: String? = null
) {

    val borderColor by animateColorAsState(
        if (isError) PidkovaTheme.colors.error else PidkovaTheme.colors.primary,
        label = ""
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = PidkovaTheme.typography.body,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        decorationBox = @Composable { innerTextField ->
            Box(
                modifier = modifier
                    .background(borderColor)
                    .padding(PidkovaTheme.dimensions.border)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = PidkovaTheme.colors.surface,
                            shape = PidkovaTheme.shapes.inputFieldShape
                        )
                        .padding(
                            horizontal = PidkovaTheme.dimensions.paddingMedium,
                            vertical = PidkovaTheme.dimensions.paddingSmall
                        )

                ) {
                    if (value.text.isBlank() && !hint.isNullOrBlank()) {
                        Text(
                            text = hint,
                            style = PidkovaTheme.typography.body,
                            color = PidkovaTheme.colors.textSecondary,
                            maxLines = 1
                        )
                    } else {
                        innerTextField()
                    }

                    if (isError) {
                        Spacer(Modifier.weight(1f))

                        Image(
                            imageVector = Icons.Default.Error,
                            colorFilter = ColorFilter.tint(PidkovaTheme.colors.error),
                            contentDescription = "Error icon",
                            modifier = Modifier.size(PidkovaTheme.dimensions.icon)
                        )
                    }

                }
            }
        }
    )
}

@Composable
@Preview
fun TextFieldPreview() {
    PidkovaTheme {
        TextField(
            value = TextFieldValue("Hello world"),
            onValueChange = {},
            hint = "Your text goes here"
        )
    }
}

@Composable
@Preview
fun TextFieldPreviewEmpty() {
    PidkovaTheme {
        TextField(
            value = TextFieldValue(""),
            onValueChange = {},
            hint = "Your text goes here"
        )
    }
}

@Composable
@Preview
fun TextFieldPreviewError() {
    PidkovaTheme {
        TextField(
            value = TextFieldValue("Hello world"),
            onValueChange = {},
            hint = "Your text goes here",
            isError = true
        )
    }
}