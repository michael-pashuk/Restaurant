package com.michael.restaurant.ui.screen.about

import com.michael.restaurant.data.database.about.model.About

sealed class AboutState {
    data object Loading : AboutState()
    data class Success(val about: About) : AboutState()
    data class Error(val message: String) : AboutState()
}