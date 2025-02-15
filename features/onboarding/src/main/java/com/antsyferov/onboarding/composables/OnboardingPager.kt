package com.antsyferov.onboarding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.lerp
import coil3.compose.rememberAsyncImagePainter
import com.antsyferov.onboarding.calculatePageOffset
import com.antsyferov.onboarding.urlToBitmap

@Composable
fun OnboardingPager(
    onOnboardingFinished: () -> Unit
) {
    val backgroundPagerState = rememberPagerState(pageCount = { 3 })
    val frontCardPagerState = rememberPagerState(pageCount = { 3 })

    LaunchedEffect(frontCardPagerState.isScrollInProgress) {
        if (frontCardPagerState.isScrollInProgress) {
            snapshotFlow { frontCardPagerState.currentPageOffsetFraction }
                .collect { offset ->
                    backgroundPagerState.requestScrollToPage(frontCardPagerState.currentPage, offset)
                }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackgroundPager(backgroundPagerState)
        GradientOverlay()
        FrontCardsPager(
            frontCardPagerState,
            onOnboardingFinished
        )
    }
}

@Composable
private fun BackgroundPager(state: PagerState) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = state
    ) { currentPage ->

        val currentPageOffset = calculatePageOffset(state, currentPage)
        val translationX = lerp(30f, 0f, 1f - currentPageOffset)

        Box(Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter("file:///android_asset/background_${currentPage+1}.jpg"),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { this.translationX = translationX }
            )
        }
    }
}

@Composable
private fun FrontCardsPager(
    state: PagerState,
    onOnboardingFinished: () -> Unit
) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = state,
        verticalAlignment = Alignment.Bottom
    ) { currentPage ->
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(currentPage) {
            urlToBitmap(
                scope = coroutineScope,
                url = "file:///android_asset/background_${currentPage+1}.jpg",
                context = context,
                onSuccess = { bitmap ->
                    imageBitmap = bitmap.asImageBitmap()
                },
                onError = {}
            )
        }

        val currentPageOffset = calculatePageOffset(state, currentPage)
        OnboardingCard(currentPage, imageBitmap, currentPageOffset, onOnboardingFinished)
    }
}

