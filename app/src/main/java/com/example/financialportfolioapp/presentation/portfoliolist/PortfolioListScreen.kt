package com.example.financialportfolioapp.presentation.portfoliolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financialportfolioapp.PortfolioDetailsScreenRoute
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors

@Composable
fun PortfolioListScreen(
    navController: NavController,
    portfolioListViewModel: PortfolioListViewModel = hiltViewModel<PortfolioListViewModel>()
) {
    val portfolioListUiState by portfolioListViewModel.portfolioListUiState.collectAsState()
    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = "Portfolio items",
                navigateBack = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Button(onClick = { portfolioListViewModel.addSamplesWithClick() }) {
                    Text(text = "add samples")
                }
            }
            items(portfolioListUiState.portfolioList) { item ->
                when (item) {
                    is Cash -> CashItem(cash = item, navController = navController, modifier = itemModifier)
                    is Stock -> StockItem(stock = item, navController = navController, modifier = itemModifier)
                    is Bond -> BondItem(bond = item, navController = navController, modifier = itemModifier)
                }
            }
            item {
                Button(onClick = { portfolioListViewModel.deleteAll() }) {
                    Text(text = "delete all")
                }
            }
        }

    }
}

val itemModifier = Modifier
    .padding(8.dp)
    .fillMaxWidth()
    .fillMaxHeight(0.2f)

@Composable
fun CashItem(
    cash: Cash,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(text = cash.name)
            Button(onClick = { navController.navigate(PortfolioDetailsScreenRoute(cash.id)) }) {
                Text("details")
            }
        }
    }
}

@Composable
fun StockItem(
    stock: Stock,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(text = stock.name)
            Button(onClick = { navController.navigate(PortfolioDetailsScreenRoute(stock.id)) }) {
                Text("details")
            }
        }
    }
}

@Composable
fun BondItem(
    bond: Bond,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(text = bond.name)
            Button(onClick = { navController.navigate(PortfolioDetailsScreenRoute(bond.id)) }) {
                Text("details")
            }
        }
    }
}