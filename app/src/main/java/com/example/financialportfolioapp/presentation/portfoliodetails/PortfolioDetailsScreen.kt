package com.example.financialportfolioapp.presentation.portfoliodetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors

@Composable
fun PortfolioDetailsScreen(
    navigateBack: () -> Unit,
    portfolioItemId: Int
) {
    val portfolioDetailsViewModel = hiltViewModel<PortfolioDetailsViewModel>()
    portfolioDetailsViewModel.loadItem(portfolioItemId.toLong())
    val item by portfolioDetailsViewModel.item.collectAsState()
    Scaffold(
        topBar = {
            TopBarWithThemeColors (
                screenName = "Portfolio item details",
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            item?.let { notNullableItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = itemPropertyRowModifier
                ) {
                    Text(text = "Name:", modifier = Modifier.weight(1f))
                    Text(text = notNullableItem.name, modifier = Modifier.weight(1f))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = itemPropertyRowModifier
                ) {
                    Text(text = "Amount:", modifier = Modifier.weight(1f))
                    Text(text = notNullableItem.amount.toString(), modifier = Modifier.weight(1f))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = itemPropertyRowModifier
                ) {
                    Text(text = "Current price:", modifier = Modifier.weight(1f))
                    Text(text = notNullableItem.price.getPriceString(), modifier = Modifier.weight(1f))
                }
                when (notNullableItem) {
                    is Cash -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = itemPropertyRowModifier
                    ) {
                        Text(text = "Exchange ratio to USD:", modifier = Modifier.weight(1f))
                        Text(text = notNullableItem.exchangeRatioToUSD.toString(), modifier = Modifier.weight(1f))
                    }
                    is Stock -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = itemPropertyRowModifier
                    ) {
                        Text(text = "Future price:", modifier = Modifier.weight(1f))
                        Text(text = notNullableItem.dividends.toString(), modifier = Modifier.weight(1f))
                    }
                    is Bond -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = itemPropertyRowModifier
                    ) {
                        Text(text = "Future price:", modifier = Modifier.weight(1f))
                        Text(text = notNullableItem.futurePrice.getPriceString(), modifier = Modifier.weight(1f))
                    }
                }
            } ?: run {
                Text(text = "Something went wrong")
            }
        }
    }
}

val itemPropertyRowModifier = Modifier
    .fillMaxWidth()
    .fillMaxHeight(0.2f)
    .padding(8.dp)
