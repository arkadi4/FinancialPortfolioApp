package com.example.financialportfolioapp.presentation.settings.stringselector

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCurrency() {
//    val sheetState = rememberModalBottomSheetState()
//    val scope = rememberCoroutineScope()
//
//    if (showBottomSheet) {
//        ModalBottomSheet(
//            onDismissRequest = {
//                showBottomSheet = false
//            },
//            sheetState = sheetState
//        ) {
//            Button(onClick = {
//                scope.launch { sheetState.hide() }.invokeOnCompletion {
//                    if (!sheetState.isVisible) {
//                        showBottomSheet = false
//                    }
//                }
//            }) {
//                Text("Hide bottom sheet")
//            }
//        }
//    }
}










//import android.app.Dialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import androidx.fragment.app.viewModels
//import com.example.financialportfolioapp.R
//import com.example.financialportfolioapp.data.AppCurrencies
//import com.example.financialportfolioapp.databinding.FragmentBottomSheetListBinding
//import com.example.financialportfolioapp.presentation.settings.SettingsScreenViewmodel
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//class SelectCurrencyDialogFragment : BottomSheetDialogFragment() {
//    private var _binding: FragmentBottomSheetListBinding? = null
//    private val binding get() = _binding!!
//    private val currenciesList = AppCurrencies.entries.toTypedArray()
//    private val settingsScreenViewModel: SettingsScreenViewmodel
//        by viewModels({ requireParentFragment() })
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentBottomSheetListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val adapter = ArrayAdapter(
//            requireContext(), R.layout.item_bottom_sheet_list, currenciesList
//        )
//        binding.bottomSheetList.adapter = adapter
//        binding.bottomSheetList.setOnItemClickListener { _, _, position, _ ->
//            settingsScreenViewModel.setDefaultCurrency(currenciesList[position].currencyName)
//            dialog?.dismiss()
//        }
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return BottomSheetDialog(requireContext(), theme).apply {
//            behavior.state = BottomSheetBehavior.STATE_EXPANDED
//        }
//    }
//
//    override fun onDestroy() {
//        _binding = null
//        super.onDestroy()
//    }
//
//    companion object {
//        const val TAG = "SelectCurrencyBottomSheetDialog"
//    }
//}
