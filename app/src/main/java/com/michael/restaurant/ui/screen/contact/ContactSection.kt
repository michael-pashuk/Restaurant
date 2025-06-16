package com.michael.restaurant.ui.screen.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.michael.restaurant.ui.screen.contact.form.FormSection

@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSection(modifier = Modifier.fillMaxWidth(if (isExpanded) 0.5f else 1f))
    }
}

@Preview
@Composable
fun ContactSectionPreview() {
    ContactSection()
}