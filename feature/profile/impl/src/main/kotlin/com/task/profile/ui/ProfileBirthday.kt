package com.task.profile.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.task.profile.ProfileElementUI
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBirthday(
    modifier: Modifier = Modifier,
    birthday: ProfileElementUI.BirthdayUI,
    onSetValue: (id: String, value: String) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val pickerState = rememberDatePickerState()

    LaunchedEffect(key1 = pickerState.selectedDateMillis) {
        val format = SimpleDateFormat.getDateInstance()
        pickerState.selectedDateMillis?.let { onSetValue(birthday.id, format.format(Date(it))) }
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))
            .clickable(enabled = !birthday.disabled) {
                showDatePicker = true
            }
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
    ) {
        Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription = null)
        Spacer(modifier = Modifier.width(12.dp))

        Text(text = birthday.value.takeIf { it.isNotEmpty() } ?: birthday.label)
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Text(
                    text = "OK",
                    modifier = Modifier.clickable { showDatePicker = false }
                )
            }
        ) {
            DatePicker(state = pickerState)
        }
    }
}
