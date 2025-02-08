package com.antsyferov.profile.profile_data

import androidx.lifecycle.viewModelScope
import com.antsyferov.domain.interfaces.Datastore
import com.antsyferov.domain.use_cases.LoadProfileUseCase
import com.antsyferov.profile.profile_data.redux.ProfileEffects
import com.antsyferov.profile.profile_data.redux.ProfileEvents
import com.antsyferov.profile.profile_data.redux.ProfileReducer
import com.antsyferov.profile.profile_data.redux.ProfileState
import com.antsyferov.ui.redux.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProfileViewModel(
    private val datastore: Datastore,
    private val loadProfileUseCase: LoadProfileUseCase
): BaseViewModel<ProfileState, ProfileEvents, ProfileEffects>(
    initialState = ProfileState.initial(),
    reducer = ProfileReducer()
) {

    init {
        datastore
            .getAccessToken()
            .onEach {
                sendEvent(ProfileEvents.AuthChanged(it != null))
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)

        viewModelScope.launch {
            val result = loadProfileUseCase()
            sendEvent(ProfileEvents.ProfileLoaded(result))
        }
    }

    override fun consumeEffect(effect: ProfileEffects): ProfileEffects? {
        return effect
    }

}