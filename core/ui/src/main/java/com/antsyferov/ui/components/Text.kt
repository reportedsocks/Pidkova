package com.antsyferov.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.antsyferov.ui.theme.PidkovaTheme
import com.antsyferov.ui.theme.PidkovaTypography

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = PidkovaTheme.typography.body,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = PidkovaTheme.colors.textPrimary
) {
    BasicText(
        text = text,
        style = style,
        maxLines = maxLines,
        overflow = overflow,
        color = { color },
        modifier = modifier
    )
}

@Composable
@Preview
fun TextPreview(
    @PreviewParameter(TypographyPreviewProvider::class) style: TextStyle
) {
    PidkovaTheme {
        Box(
            modifier = Modifier.background(color = PidkovaTheme.colors.surface)
        ) { Text(
            text = "Hello World!",
            style = style
        ) }

    }
}

class TypographyPreviewProvider: PreviewParameterProvider<TextStyle> {
    override val values = sequenceOf(
        PidkovaTypography().title,
        PidkovaTypography().subtitle,
        PidkovaTypography().body,
        PidkovaTypography().button,
        PidkovaTypography().caption,
        PidkovaTypography().overline,
    )
}