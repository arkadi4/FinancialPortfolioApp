package com.example.financialportfolioapp.presentation.assetDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors

@Composable
fun AssetDetailsScreen(
    navigateBack: () -> Unit,
    assetId: Int
) {
    val assetDetailsViewModel = hiltViewModel<AssetDetailsViewModel>()
    assetDetailsViewModel.loadItem(assetId)
    val item by assetDetailsViewModel.item.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = stringResource(id = R.string.asset_details_screen_title),
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            item?.let { notNullableItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = propertyRowModifier
                ) {
                    Text(
                        text = stringResource(id = R.string.asset_details_screen_asset_name),
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = notNullableItem.name, modifier = Modifier.weight(1f))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = propertyRowModifier
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.asset_details_screen_asset_current_price
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = notNullableItem.price.getPriceString(),
                        modifier = Modifier.weight(1f)
                    )
                }
            } ?: run {
                Text(text = stringResource(id = R.string.asset_details_screen_error_text))
            }
        }
    }
}

val propertyRowModifier = Modifier
    .fillMaxWidth()
    .fillMaxHeight(0.2f)
    .padding(8.dp)
