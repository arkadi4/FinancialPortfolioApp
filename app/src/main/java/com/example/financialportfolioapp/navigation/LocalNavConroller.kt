package com.example.financialportfolioapp.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("LocalNavController error")
}