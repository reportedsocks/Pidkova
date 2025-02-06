package com.antsyferov.ui.redux

import kotlinx.coroutines.CoroutineScope

abstract class Reducer<State: Reducer.ViewState, Event: Reducer.ViewEvent, Effect: Reducer.ViewEffect> {

    interface ViewState

    interface ViewEvent

    interface ViewEffect

    abstract fun reduce(previousState: State, event: Event): Pair<State, Effect?>

    lateinit var scope: CoroutineScope
}