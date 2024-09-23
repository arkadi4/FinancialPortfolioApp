package com.example.financialportfolioapp.presentation.settings

//import com.example.financialportfolioapp.NavRoutes
//import com.example.financialportfolioapp.databinding.FragmentSettingsBinding
//import com.example.financialportfolioapp.presentation.settings.stringselector.SelectCurrencyDialogFragment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.presentation.TopBarWithThemeColors
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financialportfolioapp.domain.entities.AppCurrencies
import com.example.financialportfolioapp.presentation.settings.stringselector.SelectCurrency
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {
//    private var _binding: FragmentSettingsBinding? = null
//    private val binding get() = _binding!!
//    private val settingsScreenViewmodel: SettingsScreenViewmodel by viewModels()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext())
////            .apply {
//////            setContent {
////////                SettingsScreen(
////////                    navigateBack = {}
////////                )
//////            }
////        }
//
////        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
////        return binding.root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        settingsScreenViewmodel.uiState.observe(viewLifecycleOwner) {
////            binding.defaultCurrencyValue.text = it.defaultCurrencyValue
//        }
////        binding.selectButton.setOnClickListener {
////            SelectCurrencyDialogFragment().show(
////                childFragmentManager, SelectCurrencyDialogFragment.TAG
////            )
////        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
////        _binding = null
//    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
) {
    val settingsScreenViewModel = hiltViewModel<SettingsScreenViewmodel>()
    val settingsScreenUiState by settingsScreenViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBarWithThemeColors(
                screenName = "Settings",
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text(text = stringResource(id = R.string.settings_fragment_text))
            Text(text = settingsScreenUiState.defaultCurrencyValue)
            Button(onClick = {
                showBottomSheet = true
            }) {
                Text(text = "change")
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                modifier = Modifier.fillMaxHeight(0.4f)
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
                                .clickable(onClick = {
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            settingsScreenViewModel.setDefaultCurrency(item.name)
                                            showBottomSheet = false
                                        }
                                    }
                                })
                                .padding(8.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSettings() {
    SettingsScreen(
        navigateBack = {},
    )
}
