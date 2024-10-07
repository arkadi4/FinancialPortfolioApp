package com.example.financialportfolioapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.financialportfolioapp.navigation.LocalNavController
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
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreen(
//                navigateToAssetListScreen = {
//                    navController.navigate(AssetListScreenRoute)
//                },
//                navigateToPortfolioListScreen = {
//                    navController.navigate(PortfolioListScreenRoute)
//                },
//                navigateToSettingsScreen = {
//                    navController.navigate(SettingsScreenRoute)
//                }
            )
        }
        composable<AssetListScreenRoute> {
//            AssetListScreen(
//                navController = navController
//            )
        }
        composable<PortfolioListScreenRoute> {
            PortfolioListScreen(
//                navController = navController
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
