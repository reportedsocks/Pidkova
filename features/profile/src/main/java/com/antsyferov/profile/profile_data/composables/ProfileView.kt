package com.antsyferov.profile.profile_data.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.antsyferov.profile.models.ProfileUi
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun ProfileView(
    profile: ProfileUi
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(PidkovaTheme.dimensions.paddingLarge)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
        ) {
            Text(
                text = "Name:",
                style = PidkovaTheme.typography.caption,
            )
            Text(
                text = profile.name,
                style = PidkovaTheme.typography.body,
            )
        }

        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingMedium))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
        ) {
            Text(
                text = "Email:",
                style = PidkovaTheme.typography.caption,
            )
            Text(
                text = profile.email,
                style = PidkovaTheme.typography.body,
            )
        }

        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingMedium))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
        ) {
            Text(
                text = "Phone:",
                style = PidkovaTheme.typography.caption,
            )
            Text(
                text = profile.phone,
                style = PidkovaTheme.typography.body,
            )
        }

        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingMedium))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
        ) {
            Text(
                text = "Membership:",
                style = PidkovaTheme.typography.caption,
            )
            Text(
                text = profile.membership,
                style = PidkovaTheme.typography.body,
            )
        }

    }
}