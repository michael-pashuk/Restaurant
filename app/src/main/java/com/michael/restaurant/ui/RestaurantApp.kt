package com.michael.restaurant.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import com.michael.restaurant.ui.landing.LandingScreen

@Composable
fun RestaurantApp(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
) {
    LandingScreen(modifier = modifier)
}