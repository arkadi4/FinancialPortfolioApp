package com.example.financialportfolioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.compose.AppTheme
import com.example.financialportfolioapp.presentation.assetDetails.AssetDetailsScreen
import com.example.financialportfolioapp.presentation.assetList.AssetListScreen
import com.example.financialportfolioapp.presentation.home.HomeScreen
import com.example.financialportfolioapp.presentation.portfoliodetails.PortfolioDetailsScreen
import com.example.financialportfolioapp.presentation.portfoliolist.PortfolioListScreen
import com.example.financialportfolioapp.presentation.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    AppNavigation()
                }
            }
        }
    }
}

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
                navigateToAssetListScreen = {
                    navController.navigate(AssetListScreenRoute)
                },
                navigateToPortfolioListScreen = {
                    navController.navigate(PortfolioListScreenRoute)
                },
                navigateToSettingsScreen = {
                    navController.navigate(SettingsScreenRoute)
                }
            )
        }
        composable<AssetListScreenRoute> {
            AssetListScreen(
                navController = navController,
            )
        }
        composable<PortfolioListScreenRoute> {
            PortfolioListScreen(
                navController = navController,
            )
        }
        composable<SettingsScreenRoute> {
            SettingsScreen(
                navigateBack = {
                    navController.popBackStack()
                },
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

//enum class Routes{
//    Home,
//    AssetList,
//    PortfolioList,
//    Settings
//}

//sealed class NavRoutes(val route: String) {
//    object HomeScreen : NavRoutes("home")
//    object SettingsScreen : NavRoutes("settings")
//}

@Preview
@Composable
fun AppPreview() {
    AppTheme {
        Surface {
            AppNavigation()
        }
    }
}
