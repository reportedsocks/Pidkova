package com.antsyferov.onboarding.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.antsyferov.ui.components.Button
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.theme.PidkovaTheme
import kotlin.math.absoluteValue

@Composable
fun OnboardingCard(
    currentPage: Int,
    imageBitmap: ImageBitmap?,
    currentPageOffset: Float,
    onOnboardingFinished: () -> Unit
) {
    val cardTranslationX = lerp(100f, 0f, 1f - currentPageOffset)
    val cardScaleX = lerp(0.8f, 1f, 1f - currentPageOffset.absoluteValue.coerceIn(0f, 1f))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .graphicsLayer {
                scaleX = cardScaleX
                translationX = cardTranslationX
            }
            .background(Color.Black, shape = PidkovaTheme.shapes.onboardingCardShape)
    ) {
        imageBitmap?.let {
            ParallaxImage(imageBitmap, currentPageOffset)
        }
        OnboardingCardOverlay(
            currentPage = currentPage,
            onOnboardingFinished = onOnboardingFinished,
            offset = currentPageOffset
        )
    }
}

@Composable
private fun OnboardingCardOverlay(
    currentPage: Int,
    onOnboardingFinished: () -> Unit,
    offset: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
    ) {

        val highlight = PidkovaTheme.colors.textGradientHighlight
        val brush = remember(offset) {
            val gradientOffset = offset * 1000
            Brush.linearGradient(
                0.0f to Color.Black,
                0.5f to highlight,
                1f to Color.Black,
                start = Offset(gradientOffset, 0f),
                end = Offset(gradientOffset + 200f,50f)
            )
        }

        when(currentPage) {
            0 -> {
                Text(
                    text = "Welcome!",
                    style = PidkovaTheme.typography.title.copy(
                        brush = brush
                    ),
                    color = PidkovaTheme.colors.buttonText
                )
                Text(
                    text = "Please follow this quick tutorial",
                    style = PidkovaTheme.typography.subtitle.copy(
                        brush = brush
                    ),
                    color = PidkovaTheme.colors.buttonText
                )
            }
            1 -> {
                Text(
                    text = "Almost there...",
                    style = PidkovaTheme.typography.title.copy(
                        brush = brush
                    ),
                    color = PidkovaTheme.colors.buttonText
                )
                Text(
                    text = "Keep scrolling",
                    style = PidkovaTheme.typography.subtitle.copy(
                        brush = brush
                    ),
                    color = PidkovaTheme.colors.buttonText
                )
            }
            2 -> {
                Text(
                    text = "Done!",
                    style = PidkovaTheme.typography.title.copy(
                        brush = brush
                    ),
                    color = PidkovaTheme.colors.buttonText
                )
                Spacer(Modifier.height(4.dp))
                Button(
                    text = "Go to the app",
                    onClick = onOnboardingFinished
                )
            }
        }
    }
}