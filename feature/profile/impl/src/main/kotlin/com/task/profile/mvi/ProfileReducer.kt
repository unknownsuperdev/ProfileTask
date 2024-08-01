package com.task.profile.mvi

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.task.mvi.Reducer
import com.task.profile.ProfileElementUI
import com.task.profile.ProfileState
import com.task.profile.mvi.ProfileAction
import org.koin.core.annotation.Single

@Single
class ProfileReducer : Reducer<ProfileAction, ProfileState> {
    override fun reduce(action: ProfileAction, state: ProfileState): ProfileState =
        when (action) {
            is ProfileAction.OnGetData -> state.copy(
                elements = action.elements.toMutableStateList(),
                oldElements = action.elements.toMutableStateList()
            )

            is ProfileAction.SetElementValue -> state.copy(
                elements = state.elements.setElementValue(action.elementId, action.value)
            )

            is ProfileAction.ReloadDataAction -> state.copy(
                elements = state.oldElements
            )
        }

    private fun SnapshotStateList<ProfileElementUI>.setElementValue(
        elementId: String,
        value: String
    ): SnapshotStateList<ProfileElementUI> = map { element ->
        if (element.id == elementId) {
            element.setValueWithCopy(value = value)
        } else when (element) {
            is ProfileElementUI.ColumnUI -> element.copy(
                profileElements = element.profileElements.setElementValue(
                    elementId,
                    value
                )
            )
            is ProfileElementUI.RowUI -> element.copy(
                profileElements = element.profileElements.setElementValue(
                    elementId,
                    value
                )
            )
            else -> element
        }
    }.toMutableStateList()
}