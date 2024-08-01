package com.task.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.task.designsystem.theme.ProfileTheme
import com.task.designsystem.ui.ProjectPreviews
import com.task.profile.mvi.ProfileIntent
import com.task.profile.ui.ProfileElement
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        viewModel.onIntent(ProfileIntent.OnGetData)
    }

    /*LaunchedEffect(Unit) {
        viewModel.navEvents.onEach {
            when (it) {
                is ProfileNavigationCommand -> {
                    goToMain("")
                }
            }
        }.launchIn(scope)
    }*/

    ProfileScreen(
        state = state,
        onButtonAction = { viewModel.onIntent(ProfileIntent.HandleButtonActions(it)) },
        onSetValue = { id, value -> viewModel.onIntent(ProfileIntent.SetElementValue(id, value)) }
    )
}

@Composable
internal fun ProfileScreen(
    state: ProfileState,
    onButtonAction: (List<ProfileActionUI>) -> Unit,
    onSetValue: (id: String, value: String) -> Unit
) {
    state.elements.forEach { element ->
        ProfileElement(
            element = element,
            onButtonAction = onButtonAction,
            onSetValue = onSetValue
        )
    }
}

@Composable
@ProjectPreviews
private fun ProfilePreview() {
    ProfileTheme {
        ProfileScreen(state = ProfileState(), onButtonAction = {}, onSetValue = {_, _ ->})
    }
}


