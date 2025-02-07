package com.antsyferov.auth.sign_in.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import com.antsyferov.auth.sign_in.redux.SignInEvents
import com.antsyferov.auth.sign_in.redux.SignInState
import com.antsyferov.ui.components.TextField
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun SignInForm(
    state: SignInState,
    onEvent: (SignInEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(PidkovaTheme.dimensions.paddingLarge)
    ) {
        Text(
            text = "Welcome!",
            style = PidkovaTheme.typography.title
        )

        Text(
            text = "Enter your credentials\nto sign in.",
            style = PidkovaTheme.typography.subtitle
        )

        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingLarge))

        TextField(
            value = TextFieldValue(text = state.login, selection = TextRange(state.login.length)),
            onValueChange = {
                onEvent(SignInEvents.NewLoginValue(it.text))
            },
            isError = state.login.isNotEmpty() && !state.isLoginCorrect,
            hint = "Username",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingSmall))

        TextField(
            value = TextFieldValue(text = state.password, selection = TextRange(state.password.length)),
            onValueChange = {
                onEvent(SignInEvents.NewPasswordValue(it.text))
            },
            isError = state.password.isNotEmpty() && !state.isPasswordCorrect,
            hint = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (!state.isLoading && state.isLoginCorrect && state.isPasswordCorrect) {
                        onEvent(SignInEvents.SignInClicked)
                    }
                }
            )
        )
    }
}
