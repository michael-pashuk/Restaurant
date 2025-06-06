package com.michael.restaurant.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.michael.restaurant.ui.adaptive.DeviceType
import com.michael.restaurant.ui.screen.landing.LandingScreen

@Composable
fun RestaurantApp(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
) {
    val deviceType = remember { mutableStateOf(DeviceType.MOBILE) }

    if (windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)) {
        deviceType.value = DeviceType.TABLET
    } else {
        deviceType.value = DeviceType.MOBILE
    }

    LandingScreen(modifier = modifier, deviceType = deviceType.value)
}