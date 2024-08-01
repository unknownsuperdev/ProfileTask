package com.task.profile.ui

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.task.profile.ProfileElementUI

@Composable
fun ProfileLastName(
    modifier: Modifier = Modifier,
    lastName: ProfileElementUI.LastNameUI,
    onSetValue: (id: String, value: String) -> Unit
) {
    val value by remember(lastName.value) { mutableStateOf(lastName.value) }
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onSetValue(lastName.id, it)
        },
        label = {
            Text(text = lastName.label)
        },
        readOnly = lastName.disabled
    )
}
