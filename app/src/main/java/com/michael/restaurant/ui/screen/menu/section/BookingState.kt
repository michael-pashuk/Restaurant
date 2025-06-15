package com.michael.restaurant.ui.screen.menu.section

data class BookingState(
    val date: Long?,
    val count: Int = 0,
    val phone: String = "",
    val name: String = "",
    val email: String = "",
    val error: Pair<Boolean, Int?> = Pair(false, null),
    val message: String? = null
)