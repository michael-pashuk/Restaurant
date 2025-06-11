package com.michael.restaurant.ui.screen.event.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.michael.restaurant.R

@Composable
fun EventsEmpty(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.defaultMinSize(minHeight = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.event_empty_title),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}