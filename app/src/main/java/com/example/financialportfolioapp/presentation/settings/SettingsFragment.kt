package com.example.financialportfolioapp.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financialportfolioapp.databinding.FragmentSettingsBinding
import com.example.financialportfolioapp.presentation.stringselector.SelectCurrencyDialogFragment

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val myViewModel: SettingsScreenViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        myViewModel.uiState.observe(viewLifecycleOwner) {
            binding.defaultCurrencyValue.text = it.defaultCurrencyValue
        }
        binding.selectButton.setOnClickListener {
            val modalDialogFragment = SelectCurrencyDialogFragment()
            modalDialogFragment.show(childFragmentManager, SelectCurrencyDialogFragment.TAG)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
