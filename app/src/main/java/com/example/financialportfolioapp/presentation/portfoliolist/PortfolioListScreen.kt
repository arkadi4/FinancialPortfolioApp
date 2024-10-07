package com.example.financialportfolioapp.presentation.portfoliolist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.financialportfolioapp.AppNavigation
import com.example.financialportfolioapp.PortfolioDetailsScreenRoute
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.domain.entities.AppCurrencies
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.DomainItemType
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.navigation.LocalNavController
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
//        super.onCreate(savedInstanceState)
//        setContent {
//            AppTheme {
//                Surface {
//                    AppNavigation()
//                }
//            }
//        }
//    }
//}



@Composable
fun PortfolioListScreen(
//    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navController = LocalNavController.current
    val portfolioListViewModel = hiltViewModel<PortfolioListViewModel>()
    val portfolioListUiState by portfolioListViewModel.portfolioListUiState.collectAsState()
    Scaffold(
        topBar = {
            excte(navController)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    portfolioListViewModel.showAlertDialog()
                },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        stringResource(id = R.string.portfolio_list_screen_fab_description)
                    )
                },
                text = { Text(text = stringResource(id = R.string.portfolio_list_screen_fab_text)) }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Button(onClick = { portfolioListViewModel.addSamplesWithClick() }) {
                    Text(
                        text = stringResource(
                            id = R.string.portfolio_list_screen_add_samples_button
                        )
                    )
                }
            }
            items(portfolioListUiState.portfolioList) { item ->
                when (item) {
                    is Cash -> CashItem(
                        cash = item,
                        navController = navController,
                        modifier = itemModifier
                    )

                    is Stock -> StockItem(
                        stock = item,
                        navController = navController,
                        modifier = itemModifier
                    )

                    is Bond -> BondItem(
                        bond = item,
                        navController = navController,
                        modifier = itemModifier
                    )
                }
            }
            item {
                Button(onClick = { portfolioListViewModel.deleteAll() }) {
                    Text(
                        text = stringResource(id = R.string.portfolio_list_screen_delete_all_button)
                    )
                }
            }
        }
        if (portfolioListUiState.isAlertDialogOnScreen) {
            AlertDialog(
                onDismissRequest = { portfolioListViewModel.hideAlertDialog() },
                dismissButton = {
                    Button(onClick = { portfolioListViewModel.hideAlertDialog() }) {
                        Text(
                            text = stringResource(
                                id = R.string.portfolio_list_screen_dialog_dismiss_button_text
                            )
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            /* TODO */
                        }
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.portfolio_list_screen_dialog_confirm_button_text
                            )
                        )
                    }
                },
                text = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        SelectOptionOutlinedTextField(
                            options = DomainItemType.entries.toList().map {
                                it.toString().lowercase().replaceFirstChar {
                                    if (it.isLowerCase()) {
                                        it.titlecase(Locale.ROOT)
                                    } else { it.toString() }
                                }
                            },
                            selectedValue = portfolioListUiState.selectedType,
                            label = "Item type",
                            onValueChangedEvent = {
                                portfolioListViewModel.selectTypeFromDropDownMenu(
                                    it
                                )
                            }
                        )
                        when (portfolioListUiState.selectedType) {
                            "Cash" -> {
                                var newCashName by rememberSaveable { mutableStateOf("") }
                                var newCashAmount by remember { mutableStateOf("") }
                                var newCashPriceValue by remember { mutableStateOf("") }
                                var newCashPriceCurrency by remember { mutableStateOf("") }
                                var newCashExchangeRateToUSD by remember { mutableStateOf("") }
                                OutlinedTextField(
                                    value = newCashName,
                                    onValueChange = { newCashName = it },
                                    label = { Text(text = "Name") },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Done
                                    )
                                )
                                OutlinedTextField(
                                    value = newCashAmount,
                                    onValueChange = { newCashAmount = it },
                                    label = { Text(text = "Amount") },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done
                                    )
                                )
                                OutlinedTextField(
                                    value = newCashPriceValue,
                                    onValueChange = { newCashPriceValue = it },
                                    label = { Text(text = "Price value") },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done
                                    )
                                )
                                SelectOptionOutlinedTextField(
                                    AppCurrencies.entries.toList()
                                        .map { it.currencyName },
                                    newCashPriceCurrency,
                                    { newCashPriceCurrency = it },
                                    "Currency"
                                )
                                OutlinedTextField(
                                    value = newCashExchangeRateToUSD,
                                    onValueChange = { newCashExchangeRateToUSD = it },
                                    label = { Text(text = "Exchange rate to USD") }
                                )
                            }

                            "Stock" -> {
                                Text(text = "stock")
                            }

                            "Bond" -> {
                                Text(text = "Bond")
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun excte(navController: NavController) {
    TopBarWithThemeColors(
        screenName = stringResource(id = R.string.portfolio_list_screen_title),
        navigateBack = { navController.popBackStack() }
    )
}

@Composable
private fun SelectItemTypeOutlinedTextField(
    portfolioListUiState: PortfolioListUiState,
    portfolioListViewModel: PortfolioListViewModel,
    modifier: Modifier = Modifier
) {

    val itemTypes = listOf("Cash", "Stock", "Bond")
    with (itemTypes) {

    }
    Box(
        modifier = Modifier.clickable {
            portfolioListViewModel.toggleExpanded()
        }
    ) {
        OutlinedTextField(
            value = portfolioListUiState.selectedType,
            onValueChange = {
                portfolioListViewModel.selectTypeFromDropDownMenu(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Item type") },
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            trailingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    "drop down icon",
                    modifier = Modifier.size(48.dp)
                )
            }
        )
        DropdownMenu(
            expanded = portfolioListUiState.isDropDownMenuExpanded,
            onDismissRequest = { portfolioListViewModel.hideDropDownMenu() }
        ) {
            itemTypes.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        portfolioListViewModel.selectTypeFromDropDownMenu(label)
                        portfolioListViewModel.hideDropDownMenu()
                    }
                )
            }
        }
    return
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectOptionOutlinedTextField(
    options: List<String>,
    selectedValue: String,
    onValueChangedEvent: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(
                    type = MenuAnchorType.PrimaryNotEditable,
                )
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) } ,
                    onClick = {
                        expanded = false
                        onValueChangedEvent(it)
                    }
                )
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
                Text(stringResource(id = R.string.portfolio_list_screen_details_button))
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
                Text(stringResource(id = R.string.portfolio_list_screen_details_button))
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
                Text(stringResource(id = R.string.portfolio_list_screen_details_button))
            }
        }
    }
}
