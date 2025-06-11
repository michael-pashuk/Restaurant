package com.michael.restaurant.navigation

sealed class Screen(val route: String) {
    object Landing : Screen("landing")
}