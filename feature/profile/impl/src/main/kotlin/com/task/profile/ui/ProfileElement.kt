package com.task.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.task.profile.ProfileActionUI
import com.task.profile.ProfileElementUI

@Composable
fun ProfileElement(
    modifier: Modifier = Modifier,
    element: ProfileElementUI,
    onButtonAction: (List<ProfileActionUI>) -> Unit,
    onSetValue: (id: String, value: String) -> Unit
) {
    if (!element.hide) {
        when (element) {
            is ProfileElementUI.BirthdayUI -> ProfileBirthday(
                modifier = modifier,
                birthday = element,
                onSetValue = onSetValue
            )

            is ProfileElementUI.ButtonUI -> ProfileButton(
                modifier = modifier,
                button = element,
                onButtonAction = onButtonAction
            )

            is ProfileElementUI.ColumnUI -> ProfileColumn(
                modifier = modifier,
                column = element,
                onButtonAction = onButtonAction,
                onSetValue = onSetValue
            )

            is ProfileElementUI.GenderUI -> ProfileGender(
                modifier = modifier,
                gender = element,
                onSetValue = onSetValue
            )
            is ProfileElementUI.LastNameUI -> ProfileLastName(
                modifier = modifier,
                lastName = element,
                onSetValue = onSetValue
            )

            is ProfileElementUI.RowUI -> ProfileRow(
                modifier = modifier,
                row = element,
                onButtonAction = onButtonAction,
                onSetValue = onSetValue
            )

            is ProfileElementUI.UnknownUI -> ProfileUnknown(modifier = modifier)
        }
    }
}
