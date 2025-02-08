package com.antsyferov.profile.profile_data.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.antsyferov.profile.profile_data.redux.ProfileEvents
import com.antsyferov.ui.components.Button
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun NavigateToAuthPrompt(
    onEvent: (ProfileEvents) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(PidkovaTheme.dimensions.paddingLarge)
    ) {
        Text(
            text = "Hi there!",
            style = PidkovaTheme.typography.title
        )
        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingMedium))
        Text(
            text = "You need to sign in\nbefore accessing your profile data",
            style = PidkovaTheme.typography.body
        )
        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingLarge))
        Button(
            text = "Go to authorization",
            onClick = {onEvent(ProfileEvents.AuthClicked)}
        )

    }
}