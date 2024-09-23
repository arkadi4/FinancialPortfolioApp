package com.example.financialportfolioapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors

@Composable
fun HomeScreen(
    navigateToAssetListScreen: () -> Unit,
    navigateToPortfolioListScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = "Financial portfolio app",
                isBackButtonExists = false,
                navigateBack = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                navigateToAssetListScreen,
                modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
            ) {
                Text(text = stringResource(R.string.asset_list))
            }
            Button(
                navigateToPortfolioListScreen,
                modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
            ) {
                Text(text = stringResource(R.string.portfolio_list))
            }
            Button(
                navigateToSettingsScreen,
                modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.settings))
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToAssetListScreen = {},
        navigateToPortfolioListScreen = {},
        navigateToSettingsScreen = {},
    )
}
