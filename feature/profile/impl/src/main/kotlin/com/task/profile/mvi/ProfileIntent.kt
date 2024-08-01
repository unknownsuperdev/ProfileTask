package com.task.profile.mvi

import com.task.mvi.MviIntent
import com.task.profile.ProfileActionUI

sealed class ProfileIntent : MviIntent {
    data object OnGetData : ProfileIntent()
    data class SetElementValue(val elementId: String, val value: String) : ProfileIntent()
    data class HandleButtonActions(val actions: List<ProfileActionUI>) : ProfileIntent()
}