package com.michael.restaurant.ui.screen.contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michael.restaurant.ui.screen.contact.footer.FooterSection
import com.michael.restaurant.ui.screen.contact.form.FormSection

@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FormSection(modifier = Modifier.fillMaxWidth(if (isExpanded) 0.5f else 1f))

        FooterSection(
            modifier = Modifier.fillMaxWidth(if (isExpanded) 0.5f else 1f),
            isExpanded = isExpanded
        )
    }
}

@Preview
@Composable
fun ContactSectionPreview() {
    ContactSection()
}