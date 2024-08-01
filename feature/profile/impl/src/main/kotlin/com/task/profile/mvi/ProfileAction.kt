package com.task.profile.mvi

import com.task.mvi.MviAction
import com.task.profile.ProfileElementUI

sealed class ProfileAction : MviAction {
    data class OnGetData(val elements: List<ProfileElementUI>) : ProfileAction()
    data class SetElementValue(val elementId: String, val value: String) : ProfileAction()
    data object ReloadDataAction : ProfileAction()
}