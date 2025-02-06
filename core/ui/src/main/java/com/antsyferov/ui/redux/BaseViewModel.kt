package com.antsyferov.ui.redux

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State: Reducer.ViewState, Event: Reducer.ViewEvent, Effect: Reducer.ViewEffect> (
    private val initialState: State,
    protected val reducer: Reducer<State, Event, Effect>
) : ViewModel() {

    init {
        reducer.scope = viewModelScope
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    private val _effects: Channel<Effect> = Channel(capacity = Channel.CONFLATED)
    val effect = _effects.receiveAsFlow()

    fun sendEffect(effect: Effect) {
        consumeEffect(effect)?.let { notConsumedEffect ->
            _effects.trySend(notConsumedEffect)
        }
    }

    fun sendEvent(event: Event) {
        val (newState, effect) = reducer.reduce(_state.value, event)

        _state.tryEmit(newState)

        effect?.let {
            sendEffect(effect)
        }
    }

    abstract fun consumeEffect(effect: Effect): Effect?

}