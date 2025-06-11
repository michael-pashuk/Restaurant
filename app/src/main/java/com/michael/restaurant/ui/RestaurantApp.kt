package com.michael.restaurant.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.michael.restaurant.navigation.RestaurantNavGraph
import com.michael.restaurant.navigation.rememberNavigationState
import com.michael.restaurant.ui.adaptive.DeviceType

@Composable
fun RestaurantApp(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
) {
    val deviceType = remember { mutableStateOf(DeviceType.MOBILE) }
    val navState = rememberNavigationState()

    if (windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)) {
        deviceType.value = DeviceType.TABLET
    } else {
        deviceType.value = DeviceType.MOBILE
    }

    RestaurantNavGraph(
        navHostController = navState.navHostController,
        deviceType = deviceType.value
    )
}