package com.michael.restaurant.ui.screen.event.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun DateEvent(
    date: Date
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Log.d("DATE", date.toString())
        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = ""
        )
        Text(
            text = SimpleDateFormat("dd-MM-yyyy").format(date),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}