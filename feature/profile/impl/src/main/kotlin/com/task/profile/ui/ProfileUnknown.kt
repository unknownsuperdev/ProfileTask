package com.task.profile.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.task.feature.profile.R

@Composable
fun ProfileUnknown(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(id = R.string.unknown_element_label))
}
