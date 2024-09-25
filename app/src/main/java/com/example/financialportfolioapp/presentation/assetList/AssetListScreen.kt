package com.example.financialportfolioapp.presentation.assetList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financialportfolioapp.AssetDetailsScreenRoute
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors

const val CARD_HEIGHT_FRACTION = 0.2F
const val TEXT_IN_ROW_WIDTH_FRACTION = 0.2F

@Composable
fun AssetListScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = stringResource(id = R.string.asset_list_screen_title),
                navigateBack = { navController.popBackStack() }
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
                            navController.navigate(AssetDetailsScreenRoute(item.id))
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

@Preview
@Composable
fun AssetListPreview() {
    val samples = listOf(1, 2, 3)
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },
                icon = { Icon(Icons.Filled.Add, "Floating action button") },
                text = { Text(text = "create asset") }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Button(
                    onClick = {  }
                ) {
                    Text(text = "deleteAll")
                }
            }
            items(samples) { item ->
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
                            text = item.toString(),
                            modifier = Modifier.weight(TEXT_IN_ROW_WIDTH_FRACTION)
                        )
                        Button(onClick = {  }) {
                            Text(text = "delete")
                        }
                        Button(onClick = {

                        }) {
                            Text("details")
                        }
                    }
                }
            }
        }
    }
}
