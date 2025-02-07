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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun TextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    isError: Boolean = false,
    hint: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {

    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

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
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused },
        decorationBox = @Composable { innerTextField ->
            Box(
                modifier = modifier
                    .background(borderColor)
                    .padding(if (isFocused) 3.dp else 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
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