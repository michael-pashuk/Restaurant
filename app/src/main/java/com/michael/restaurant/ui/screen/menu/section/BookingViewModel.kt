package com.michael.restaurant.ui.screen.menu.section

import android.content.Context
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.restaurant.R
import com.michael.restaurant.data.database.booking.model.Booking
import com.michael.restaurant.data.repository.BookingRepository
import com.michael.restaurant.service.helper.EmailHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BookingViewModel
@Inject constructor(
    private val helper: EmailHelper,
    private val repository: BookingRepository
) : ViewModel() {

    private val initialState = BookingState(Date().time)

    private val _bookingState = MutableStateFlow<BookingState>(initialState)
    val bookingState: StateFlow<BookingState> = _bookingState.asStateFlow()

    fun changeDate(date: Long?) {
        _bookingState.value = _bookingState.value.copy(date = date)
    }

    fun changeCount(count: Int) {
        _bookingState.value = _bookingState.value.copy(count = count)
    }

    fun changeName(name: String) {
        _bookingState.value = _bookingState.value.copy(name = name)
    }

    fun changeEmail(email: String) {
        _bookingState.value = _bookingState.value.copy(email = email)
    }

    fun changePhone(phone: String) {
        _bookingState.value = _bookingState.value.copy(phone = phone)
    }

    fun sendEmail(
        context: Context
    ) {
        validateData()

        if (bookingState.value.error.first) {
            return
        }

        val state = bookingState.value

        if (state.date == null) throw NullPointerException()

        val intent = helper.createIntent(
            state.email,
            state.date,
            state.count,
            state.name,
            state.phone
        )

        context.startActivity(intent)

        viewModelScope.launch {
            repository.insert(
                Booking(
                    timestamp = state.date,
                    name = state.name,
                    email = state.email,
                    phone = state.phone,
                    guest = state.count
                )
            )
        }

        _bookingState.value = _bookingState.value.copy(
            message = "Message sent successfully"
        )
    }

    fun clearError() {
        _bookingState.value = _bookingState.value.copy(
            error = Pair(false, null)
        )
    }

    fun clearMessage() {
        _bookingState.value = _bookingState.value.copy(
            message = null
        )
    }

    private fun validateData() {
        val state = bookingState.value

        when {
            (state.count <= 0) -> {
                _bookingState.value = _bookingState.value.copy(
                    error = Pair(
                        true,
                        R.string.count_is_not_positive_error
                    )
                )
            }

            (state.date == null) -> {
                _bookingState.value =
                    _bookingState.value.copy(error = Pair(true, R.string.date_selected_error))
            }

            (state.phone.isEmpty() || state.email.isEmpty() || state.name.isEmpty()) -> {
                _bookingState.value = _bookingState.value.copy(
                    error = Pair(true, R.string.empty_required_error)
                )
            }

            (!validateDate(state.date)) -> {
                _bookingState.value = _bookingState.value.copy(
                    error = Pair(
                        true,
                        R.string.date_in_past_error
                    )
                )
            }

            (!validateEmail(state.email)) -> {
                _bookingState.value = _bookingState.value.copy(
                    error = Pair(
                        true,
                        R.string.invalid_email_error
                    )
                )
            }

            (!validatePhone(state.phone)) -> {
                _bookingState.value = _bookingState.value.copy(
                    error = Pair(
                        true,
                        R.string.invalid_phone_error
                    )
                )
            }

            else -> clearError()
        }
    }

    private fun validateDate(date: Long): Boolean {
        val selectedDate = Instant
            .ofEpochMilli(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        return !selectedDate.isBefore(LocalDate.now())
    }

    private fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
        return emailRegex.matches(email)
    }

    private fun validatePhone(phone: String): Boolean {
        return if (phone.isDigitsOnly()) {
            phone.length == 10
        } else {
            false
        }
    }
}