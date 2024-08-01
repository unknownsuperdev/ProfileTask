package com.task.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.designsystem.theme.ProfileTheme
import com.task.profile.ProfileActionUI
import com.task.profile.ProfileElementUI

@Composable
fun ProfileColumn(
    modifier: Modifier = Modifier,
    column: ProfileElementUI.ColumnUI,
    onButtonAction: (List<ProfileActionUI>) -> Unit,
    onSetValue: (id: String, value: String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        column.profileElements.forEach { element ->
            ProfileElement(
                element = element,
                onButtonAction = onButtonAction,
                onSetValue = onSetValue
            )
        }
    }
}
