package com.task.profile.ui

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.task.profile.ProfileActionUI
import com.task.profile.ProfileElementUI

@Composable
fun ProfileButton(
    modifier: Modifier = Modifier,
    button: ProfileElementUI.ButtonUI,
    onButtonAction: (List<ProfileActionUI>) -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onButtonAction(button.actions) },
        enabled = !button.disabled
    ) {
        Text(text = button.label)
    }
}
