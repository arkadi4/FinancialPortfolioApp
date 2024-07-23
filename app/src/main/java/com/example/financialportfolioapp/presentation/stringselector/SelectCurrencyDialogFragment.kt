package com.example.financialportfolioapp.presentation.stringselector

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.data.AppCurrencies
import com.example.financialportfolioapp.databinding.FragmentBottomSheetListBinding
import com.example.financialportfolioapp.presentation.settings.SettingsScreenViewmodel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectCurrencyDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetListBinding? = null
    private val binding get() = _binding!!
    private val currenciesList = AppCurrencies.entries.toTypedArray()
    private val settingsScreenViewModel: SettingsScreenViewmodel
        by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(
            requireContext(), R.layout.item_bottom_sheet_list, currenciesList
        )
        binding.bottomSheetList.adapter = adapter
        binding.bottomSheetList.setOnItemClickListener { _, _, position, _ ->
            settingsScreenViewModel.setDefaultCurrency(currenciesList[position].currencyName)
            dialog?.dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val TAG = "SelectCurrencyBottomSheetDialog"
    }
}
