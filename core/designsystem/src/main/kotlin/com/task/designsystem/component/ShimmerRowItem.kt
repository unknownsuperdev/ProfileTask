package com.task.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRowItem(brush: Brush) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(6.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .height(200.dp)
                    .width(120.dp)
                    .padding(start = 12.dp, top = 12.dp, end = 4.dp, bottom = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush = brush)
            )
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(start = 8.dp, top = 12.dp, end = 12.dp, bottom = 4.dp)
                        .background(brush = brush)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(vertical = 8.dp)
                        .padding(start = 8.dp, end = 12.dp)
                        .background(brush = brush)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 8.dp, top = 8.dp, end = 12.dp, bottom = 8.dp)
                        .background(brush = brush)
                )
            }
        }
    }
}