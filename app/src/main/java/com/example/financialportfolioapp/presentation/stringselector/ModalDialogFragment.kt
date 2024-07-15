package com.example.financialportfolioapp.presentation.stringselector

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.data.AppCurrencies
import com.example.financialportfolioapp.databinding.FragmentBottomsheetBinding
import com.example.financialportfolioapp.presentation.settings.SettingsScreenViewmodel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalDialogFragment(viewmodel: SettingsScreenViewmodel) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomsheetBinding? = null
    private val binding get() = _binding!!
    var myViewModel = viewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomsheetBinding.inflate(inflater, container, false)

        val listView = binding.bottomSheet
        val list = AppCurrencies.entries.toTypedArray()
        val adapter = ArrayAdapter(requireContext(), R.layout.item_bottom_sheet_list, list)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                requireContext(),
                "Clicked item : $position  ${list[position]} ",
                Toast.LENGTH_SHORT
            ).show()
            myViewModel.setDefaultCurrency(list[position].currencyName)
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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
        const val TAG = "ModalBottomSheetDialog"
    }
}
