package com.michael.restaurant.ui.screen.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michael.restaurant.R
import com.michael.restaurant.ui.screen.menu.section.BookingSection

@Composable
fun MenuSection(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    isExpanded: Boolean = false
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.menu_title),
            textAlign = if (isExpanded) TextAlign.Start else TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        BookingSection(
            modifier = Modifier.fillMaxWidth(if (isExpanded) 0.5f else 1f),
            snackBarHostState = snackBarHostState
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MenuSectionPreview() {
    MenuSection(snackBarHostState = SnackbarHostState())
}