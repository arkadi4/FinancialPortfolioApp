package com.example.financialportfolioapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.financialportfolioapp.AssetDetailsScreenRoute
import com.example.financialportfolioapp.AssetListScreenRoute
import com.example.financialportfolioapp.HomeScreenRoute
import com.example.financialportfolioapp.PortfolioDetailsScreenRoute
import com.example.financialportfolioapp.PortfolioListScreenRoute
import com.example.financialportfolioapp.SettingsScreenRoute
import com.example.financialportfolioapp.presentation.assetDetails.AssetDetailsScreen
import com.example.financialportfolioapp.presentation.assetList.AssetListScreen
import com.example.financialportfolioapp.presentation.home.HomeScreen
import com.example.financialportfolioapp.presentation.portfoliodetails.PortfolioDetailsScreen
import com.example.financialportfolioapp.presentation.portfoliolist.PortfolioListScreen
import com.example.financialportfolioapp.presentation.settings.SettingsScreen
import kotlinx.serialization.Serializable


@Serializable
data object HomeScreenRoute

@Serializable
data object AssetListScreenRoute

@Serializable
data object PortfolioListScreenRoute

@Serializable
data object SettingsScreenRoute

@Serializable
data class AssetDetailsScreenRoute(val assetId: Int)

@Serializable
data class PortfolioDetailsScreenRoute(val portfolioItemId: Int)


@Composable
fun AppNavWithLocalNavController(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeScreenRoute
        ) {
            composable<HomeScreenRoute> {
                HomeScreen()
            }

            composable<AssetListScreenRoute> {
                AssetListScreen(
//                    navController = navController
                )
            }
            composable<PortfolioListScreenRoute> {
                PortfolioListScreen(
//                    navController = navController
                )
            }
            composable<SettingsScreenRoute> {
                SettingsScreen(
                    navigateBack = {
                        navController.popBackStack()
                    }
                )
            }
            composable<AssetDetailsScreenRoute> {
                val args = it.toRoute<AssetDetailsScreenRoute>()
                AssetDetailsScreen(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    assetId = args.assetId
                )
            }
            composable<PortfolioDetailsScreenRoute> {
                val args = it.toRoute<PortfolioDetailsScreenRoute>()
                PortfolioDetailsScreen(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    portfolioItemId = args.portfolioItemId
                )
            }
        }
    }
}