package com.task.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.task.mvi.MviBaseViewModel
import com.task.profile.mvi.ProfileAction
import com.task.profile.mvi.ProfileIntent
import com.task.profile.mvi.ProfileReducer
import com.task.utils.subscribe
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProfileViewModel(
    private val getProfileContent: GetProfileContentUseCase,
    private val handleButtonActions: HandleButtonActionsUseCase,
    reducer: ProfileReducer
) :
    MviBaseViewModel<ProfileState, ProfileAction, ProfileIntent>(reducer) {

    override fun initState(): ProfileState = ProfileState()

    override fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.OnGetData -> getContent()
            is ProfileIntent.HandleButtonActions -> handleButtonAction(intent.actions)

            is ProfileIntent.SetElementValue -> onAction(
                ProfileAction.SetElementValue(
                    intent.elementId,
                    intent.value
                )
            )
        }
    }

    private fun getContent() {
        getProfileContent().subscribe(viewModelScope,
            success = {
                onAction(ProfileAction.OnGetData(it))
            },
            error = {}
        )
    }

    private fun handleButtonAction(actions: List<ProfileActionUI>) = viewModelScope.launch {
        handleButtonActions(
            actions.map { action ->
                when (action) {
                    is ProfileActionUI.ReloadDataAction -> {
                        action.copy(elements = viewState.value.oldElements)
                    }
                    is ProfileActionUI.SaveCustomerAction -> {
                        action.copy(
                            birthdayValue = viewState.value
                                .findElementById(action.birthdayId)?.value.orEmpty(),
                            genderValue = viewState.value
                                .findElementById(action.genderId)?.value.orEmpty(),
                            lastNameValue = viewState.value
                                .findElementById(action.lastNameId)?.value.orEmpty()
                        )
                    }
                    else -> action
                }
            }
        )
    }
}

