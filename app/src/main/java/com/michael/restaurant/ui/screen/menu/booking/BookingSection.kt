package com.michael.restaurant.ui.screen.menu.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.michael.restaurant.R
import com.michael.restaurant.service.transformation.PhoneNumberVisualTransformation
import com.michael.restaurant.ui.component.FormTextField
import com.michael.restaurant.ui.screen.menu.component.ModalDatePicker
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingSection(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    viewModel: BookingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModel.bookingState.collectAsState()
    val booking = state.value

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val bookingFieldModifier = Modifier.fillMaxWidth()

        Text(
            text = stringResource(R.string.booking_title),
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ModalDatePicker(
                modifier = Modifier.weight(1f),
                selectedDate = booking.date,
                onDateSelected = viewModel::changeDate,
                label = stringResource(R.string.date_title),
                confirmText = stringResource(R.string.confirm_button),
                dismissText = stringResource(R.string.dismiss_button)
            )

            Spacer(modifier = Modifier.weight(0.15f))

            FormTextField(
                modifier = Modifier.weight(1f),
                value = booking.count.toString(),
                onValueChange = { it ->
                    when {
                        it.isEmpty() -> viewModel.changeCount(0)
                        it.isNotEmpty() -> {
                            val count = it.filter { it.isDigit() }.toInt()

                            if (count > 0) {
                                viewModel.changeCount(count)
                            }
                        }

                        else -> viewModel.changeCount(0)
                    }
                },
                label = stringResource(R.string.guest_count_title),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        FormTextField(
            modifier = bookingFieldModifier,
            value = booking.name,
            onValueChange = viewModel::changeName,
            label = stringResource(R.string.name_title),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        FormTextField(
            modifier = bookingFieldModifier,
            value = booking.phone,
            onValueChange = {
                val raw = it.filter { it -> it.isDigit() }.take(10)
                viewModel.changePhone(raw)
            },
            label = stringResource(R.string.phone_title),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            visualTransformation = PhoneNumberVisualTransformation()
        )

        FormTextField(
            modifier = bookingFieldModifier,
            value = booking.email,
            onValueChange = viewModel::changeEmail,
            label = stringResource(R.string.email_title),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                viewModel.sendEmail(context)
            }
        ) {
            Text(text = stringResource(R.string.send_button))
        }
    }

    if (booking.error.first) {
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

    LaunchedEffect(booking.message) {
        if (booking.message != null) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = booking.message,
                    duration = SnackbarDuration.Long
                )
            }
            viewModel.clearMessage()
        }

    }
}
