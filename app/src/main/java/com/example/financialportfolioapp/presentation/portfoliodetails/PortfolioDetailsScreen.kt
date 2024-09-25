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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financialportfolioapp.R
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
            TopBarWithThemeColors(
                screenName = stringResource(id = R.string.portfolio_details_screen_title),
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            item?.let { notNullableItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = itemPropertyRowModifier
                ) {
                    Text(
                        text = stringResource(id = R.string.portfolio_details_screen_item_name),
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = notNullableItem.name, modifier = Modifier.weight(1f))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = itemPropertyRowModifier
                ) {
                    Text(
                        text = stringResource(id = R.string.portfolio_details_screen_item_amount),
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = notNullableItem.amount.toString(), modifier = Modifier.weight(1f))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = itemPropertyRowModifier
                ) {
                    Text(
                        text = stringResource(id = R.string.portfolio_details_screen_item_current_price),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = notNullableItem.price.getPriceString(),
                        modifier = Modifier.weight(1f)
                    )
                }
                when (notNullableItem) {
                    is Cash -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = itemPropertyRowModifier
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.portfolio_details_screen_cash_exchange_rate
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = notNullableItem.exchangeRatioToUSD.toString(),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    is Stock -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = itemPropertyRowModifier
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.portfolio_details_screen_stock_dividends
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = notNullableItem.dividends.toString(),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    is Bond -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = itemPropertyRowModifier
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.portfolio_details_screen_bond_future_price
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = notNullableItem.futurePrice.getPriceString(),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            } ?: run {
                Text(text = stringResource(id = R.string.portfolio_details_screen_error_text))
            }
        }
    }
}

val itemPropertyRowModifier = Modifier
    .fillMaxWidth()
    .fillMaxHeight(0.2f)
    .padding(8.dp)
