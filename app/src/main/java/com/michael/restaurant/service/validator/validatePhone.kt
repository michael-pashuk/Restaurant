package com.michael.restaurant.service.validator

import androidx.core.text.isDigitsOnly

fun validatePhone(phone: String): Boolean {
    return if (phone.isDigitsOnly()) {
        phone.length == 10
    } else {
        false
    }
}