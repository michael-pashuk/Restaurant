package com.michael.restaurant.ui.screen.contact.footer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michael.restaurant.R
import com.michael.restaurant.ui.component.Title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FooterSection(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.contact_info_title),
            isExpanded = isExpanded
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactInfoBlock(
                title = stringResource(R.string.email_title),
                text = stringResource(R.string.email)
            )

            ContactInfoBlock(
                title = stringResource(R.string.phone_title),
                text = stringResource(R.string.phone)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactInfoBlock(
                title = stringResource(R.string.mon_fri_title),
                text = "09:00-20:00"
            )

            ContactInfoBlock(
                title = stringResource(R.string.saturday_title),
                text = "09:00-19:00"
            )

            ContactInfoBlock(
                title = stringResource(R.string.sunday_title),
                text = stringResource(R.string.weekday_title)
            )
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            SocialItem.socialList.forEach { it ->
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            onClick = { openLink(context, it.link) }
                        ),
                    imageVector = ImageVector.vectorResource(it.icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
private fun ContactInfoBlock(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    Row(modifier = modifier) {
        Text(modifier = Modifier.weight(0.35f), text = "$title:")
        Text(modifier = Modifier.weight(1f), text = text)
    }
}

@Preview
@Composable
fun FooterSectionPreview() {
    FooterSection()
}