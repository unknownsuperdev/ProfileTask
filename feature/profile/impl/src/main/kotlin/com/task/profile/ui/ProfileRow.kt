package com.task.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.task.profile.ProfileActionUI
import com.task.profile.ProfileElementUI

@Composable
fun ProfileRow(
    modifier: Modifier = Modifier,
    row: ProfileElementUI.RowUI,
    onButtonAction: (List<ProfileActionUI>) -> Unit,
    onSetValue: (id: String, value: String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        row.profileElements.forEach { element ->
            ProfileElement(
                element = element,
                onButtonAction = onButtonAction,
                onSetValue = onSetValue
            )
        }
    }
}
