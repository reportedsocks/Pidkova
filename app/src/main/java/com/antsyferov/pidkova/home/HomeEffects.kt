package com.antsyferov.pidkova.home

import androidx.compose.runtime.Immutable
import com.antsyferov.pidkova.mvi.Reducer

@Immutable
sealed class HomeEffects: Reducer.ViewEffect {
    data object ShowToast: HomeEffects()
    data object LoadProducts: HomeEffects()
}