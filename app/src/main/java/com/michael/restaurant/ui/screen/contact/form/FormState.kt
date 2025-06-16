package com.michael.restaurant.ui.screen.contact.form

data class FormState(
    val email: String,
    val name: String,
    val message: String,
    val error: Pair<Boolean, Int?> = Pair(false, null)
)
