package com.example.financialportfolioapp.presentation.assetList

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financialportfolioapp.AssetDetailsScreenRoute
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AssetListScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = "Assets",
                navigateBack = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        val assetListViewModel = hiltViewModel<AssetListViewModel>()
        val assetListUiState by assetListViewModel.assets.collectAsState()
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item { Button(onClick = { assetListViewModel.deleteAllAssets() }) {
                Text(text = "deleteAll")
            } }
            items(assetListUiState.assetList) { item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .fillParentMaxHeight(0.2f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(text = item.name, modifier = Modifier.weight(0.8f))
                        Button(onClick = { assetListViewModel.deleteItem(item) }) {
                            Text(text = "delete")
                        }
                        Button(onClick = { navController.navigate(AssetDetailsScreenRoute(item.id)) } ) {
                            Text("details")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AssetListScreenPreview() {
    val itemsList = listOf("1","2","3")
    LazyColumn(
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        items(itemsList) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillParentMaxHeight(0.1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(text = it)
                    Button(onClick = { /*TODO*/ }) {
                        Text("details")
                    }
                }
            }
        }
    }
}