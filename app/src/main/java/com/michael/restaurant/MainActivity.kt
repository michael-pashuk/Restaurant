package com.michael.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import com.michael.restaurant.ui.RestaurantApp
import com.michael.restaurant.ui.theme.RestaurantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestaurantTheme {
                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
                RestaurantApp(windowSizeClass = windowSizeClass)
            }
        }
    }
}