package com.michael.restaurant.ui.screen.contact.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.michael.restaurant.R
import com.michael.restaurant.ui.component.FormTextField

@Composable
fun FormSection(
    modifier: Modifier = Modifier,
    viewModel: FormViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.formState.collectAsState()
    val formData = state.value

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val formFieldModifier = Modifier.fillMaxWidth()

        Text(
            text = "Contact Form",
            style = MaterialTheme.typography.titleMedium
        )

        FormTextField(
            modifier = formFieldModifier,
            value = formData.name,
            onValueChange = viewModel::updateName,
            label = stringResource(R.string.name_title)
        )

        FormTextField(
            modifier = formFieldModifier,
            value = formData.email,
            onValueChange = viewModel::updateEmail,
            label = stringResource(R.string.email_title)
        )

        OutlinedTextField(
            modifier = formFieldModifier,
            value = formData.message,
            onValueChange = viewModel::updateMessage,
            label = {
                Text(text = stringResource(R.string.message_title))
            },
            minLines = 5,
            maxLines = 5
        )

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { viewModel.sendMessage(context) }
        ) {
            Text(text = stringResource(R.string.send_button))
        }
    }

    if (formData.error.first) {
        AlertDialog(
            onDismissRequest = viewModel::clearError,
            confirmButton = {
                TextButton(onClick = viewModel::clearError) {
                    Text(stringResource(R.string.confirm_button))
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = ""
                )
            },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(
                    text = stringResource(
                        state.value.error.second ?: R.string.something_wrong_title
                    )
                )
            }
        )
    }

}

@Preview
@Composable
fun FormSectionPreview() {
    FormSection()
}