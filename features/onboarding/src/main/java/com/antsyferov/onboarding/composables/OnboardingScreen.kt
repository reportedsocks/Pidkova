package com.antsyferov.onboarding.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun OnboardingScreen(
    onOnboardingFinished: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        OnboardingPager(
            onOnboardingFinished = onOnboardingFinished
        )
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    PidkovaTheme {
        OnboardingScreen {  }
    }
}