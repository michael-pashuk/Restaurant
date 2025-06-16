package com.michael.restaurant.ui.screen.contact.form

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.restaurant.R
import com.michael.restaurant.data.database.contact.model.Message
import com.michael.restaurant.data.repository.ContactRepository
import com.michael.restaurant.service.helper.EmailHelper
import com.michael.restaurant.service.validator.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FormViewModel
@Inject constructor(
    private val helper: EmailHelper,
    private val repository: ContactRepository
) : ViewModel() {
    private val initial = FormState(email = "", name = "", message = "")

    private val _formState = MutableStateFlow<FormState>(initial)
    val formState = _formState.asStateFlow()

    fun updateEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    fun updateName(name: String) {
        _formState.value = _formState.value.copy(name = name)
    }

    fun updateMessage(message: String) {
        _formState.value = _formState.value.copy(message = message)
    }

    fun clearError() {
        _formState.value = _formState.value.copy(error = Pair(false, null))
    }

    fun sendMessage(
        context: Context
    ) {
        val state = formState.value

        when {
            state.error.first -> return
            state.name.isEmpty() || state.message.isEmpty() || state.email.isEmpty() -> {
                _formState.value = _formState.value.copy(
                    error = Pair(true, R.string.empty_required_error)
                )
                return
            }

            !validateEmail(state.email) -> {
                _formState.value = _formState.value.copy(
                    error = Pair(true, R.string.invalid_email_error)
                )
                return
            }
        }

        val message = helper.createContactMessage(
            email = state.email,
            name = state.name,
            message = state.message
        )

        val intent = helper.createIntent(
            theme = "Contact message",
            message = message
        )
        val date = Date().time

        context.startActivity(intent)

        viewModelScope.launch {
            repository.insert(
                Message(
                    timestamp = date,
                    name = state.name,
                    email = state.email,
                    message = state.message
                )
            )
        }
    }
}