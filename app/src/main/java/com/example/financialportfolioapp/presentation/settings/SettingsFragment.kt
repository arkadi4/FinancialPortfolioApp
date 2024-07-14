package com.example.financialportfolioapp.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.financialportfolioapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: SettingsScreenViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(this).get(SettingsScreenViewmodel::class.java)
        myViewModel.uiState.observe(viewLifecycleOwner) {
            binding.defaultCurrencyValue.text = it.defaultCurrencyValue
        }
        binding.stringSelectorButton.setOnClickListener {
            myViewModel.setDefaultCurrency("USD")
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
