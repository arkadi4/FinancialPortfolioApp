package com.example.financialportfolioapp.presentation.settings

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.domain.entities.AppCurrencies
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors
import kotlinx.coroutines.launch

const val BOTTOM_SHEET_HEIGHT = 0.4f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navigateBack: () -> Unit
) {
    val settingsScreenViewModel = hiltViewModel<SettingsScreenViewmodel>()
    val settingsScreenUiState by settingsScreenViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = stringResource(id = R.string.settings_screen_title),
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by rememberSaveable { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.settings_fragment_text))
            Text(text = settingsScreenUiState.defaultCurrencyValue)
            Button(onClick = {
                showBottomSheet = true
            }) {
                Text(text = stringResource(id = R.string.settings_screen_change_button))
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                modifier = Modifier
//                    .fillMaxHeight(0.4f)


            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(AppCurrencies.entries) { item ->
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        scope
                                            .launch { sheetState.hide() }
                                            .invokeOnCompletion {
                                                if (!sheetState.isVisible) {
                                                    settingsScreenViewModel
                                                        .setDefaultCurrency(item.name)
                                                    showBottomSheet = false
                                                }
                                            }
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = ripple(),
                                )
                                .padding(16.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
