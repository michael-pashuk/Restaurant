package com.michael.restaurant.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.michael.restaurant.ui.adaptive.DeviceType
import com.michael.restaurant.ui.screen.landing.LandingScreen

@Composable
fun RestaurantNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    deviceType: DeviceType = DeviceType.MOBILE
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Landing.route
    ) {
        composable(Screen.Landing.route) {
            LandingScreen(modifier = modifier, deviceType = deviceType)
        }
    }
}