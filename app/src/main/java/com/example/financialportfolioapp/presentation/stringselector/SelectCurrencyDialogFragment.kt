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
import com.example.financialportfolioapp.databinding.FragmentBottomsheetBinding
import com.example.financialportfolioapp.presentation.settings.SettingsScreenViewmodel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectCurrencyDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomsheetBinding? = null
    private val binding get() = _binding!!
    private val currenciesList = AppCurrencies.entries.toTypedArray()

    private val myViewModel: SettingsScreenViewmodel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomsheetBinding.inflate(inflater, container, false)
        val adapter = ArrayAdapter(
            requireContext(), R.layout.item_bottom_sheet_list, currenciesList
        )
        binding.bottomSheet.adapter = adapter
        binding.bottomSheet.setOnItemClickListener { _, _, position, _ ->
            myViewModel.setDefaultCurrency(currenciesList[position].currencyName)
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val bottomSheetDialog = it as BottomSheetDialog
            val bottomSheet = bottomSheetDialog
                .findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "SelectCurrencyBottomSheetDialog"
    }
}
