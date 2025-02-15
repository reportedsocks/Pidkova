package com.antsyferov.onboarding.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toIntSize
import androidx.compose.ui.util.lerp
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun ParallaxImage(imageBitmap: ImageBitmap, currentPageOffset: Float) {
    val drawSize = IntSize(imageBitmap.width, imageBitmap.height)
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val parallaxOffset = currentPageOffset * screenWidth * 2f

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clip(PidkovaTheme.shapes.onboardingCardShape)
            .border(2.dp, androidx.compose.ui.graphics.Color.White, PidkovaTheme.shapes.onboardingCardShape)
            .graphicsLayer { translationX = lerp(10f, 0f, 1f - currentPageOffset) }
            .blur(20.dp)
    ) {
        translate(left = parallaxOffset) {
            drawImage(
                image = imageBitmap,
                srcSize = drawSize,
                dstSize = size.toIntSize(),
            )
        }
    }
}