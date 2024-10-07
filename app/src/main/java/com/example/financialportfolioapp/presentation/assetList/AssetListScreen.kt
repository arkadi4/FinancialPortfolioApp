package com.example.financialportfolioapp.presentation.assetList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financialportfolioapp.AssetDetailsScreenRoute
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.navigation.LocalNavController
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors

const val CARD_HEIGHT_FRACTION = 0.2F
const val TEXT_IN_ROW_WIDTH_FRACTION = 0.2F

@Composable
fun AssetListScreen(
//    navController: NavController
) {
    val localNav = LocalNavController.current
    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = stringResource(id = R.string.asset_list_screen_title),
                navigateBack = { localNav.popBackStack() }
            )
        }
    ) { innerPadding ->
        val assetListViewModel = hiltViewModel<AssetListViewModel>()
        val assetListUiState by assetListViewModel.assets.collectAsState()
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(assetListUiState.assetList) { item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .fillParentMaxHeight(CARD_HEIGHT_FRACTION)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = item.name,
                            modifier = Modifier.weight(TEXT_IN_ROW_WIDTH_FRACTION)
                        )
                        Button(onClick = { assetListViewModel.deleteItem(item) }) {
                            Text(text = stringResource(id = R.string.asset_list_delete_item_button))
                        }
                        Button(onClick = {
                            localNav.navigate(AssetDetailsScreenRoute(item.id))
                        }) {
                            Text(stringResource(id = R.string.asset_list_details_button))
                        }
                    }
                }
            }
            item {
                Button(
                    onClick = { assetListViewModel.deleteAllAssets() }
                ) {
                    Text(text = stringResource(id = R.string.asset_list_delete_all_button))
                }
            }
        }
    }
}
