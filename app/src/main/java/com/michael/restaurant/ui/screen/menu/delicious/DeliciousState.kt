package com.michael.restaurant.ui.screen.menu.delicious

import com.michael.restaurant.data.database.menu.model.Category
import com.michael.restaurant.data.database.menu.model.Dish

sealed class DeliciousState {
    object Loading : DeliciousState()

    data class Success(
        val category: List<Category>,
        val dishes: List<Dish>,
        val selectedCategoryId: Int? = null
    ): DeliciousState()

    data class Error(val message: String?) : DeliciousState()
}