package com.example.financialportfolioapp.presentation.createBond

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financialportfolioapp.databinding.FragmentCreateBondBinding
import com.example.financialportfolioapp.domain.entities.AppCurrencies
import com.example.financialportfolioapp.domain.entities.Price
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class CreateBondFragment : Fragment() {
    private val viewModel: CreateBondViewModel by viewModels()
    private var _binding: FragmentCreateBondBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBondBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveBond() {
        val name = binding.editName.text.toString()
        val amount = binding.editAmount.text.toString().toDoubleOrNull() ?: 0.0
        val priceValue = binding.editPrice.text.toString().toDoubleOrNull() ?: 0.0
        val futurePriceValue = binding.editFuturePrice.text.toString().toDoubleOrNull() ?: 0.0
        val yieldToMaturity =
            binding.editYieldToMaturity.text.toString().toDoubleOrNull() ?: 0.0

        val price = Price(
            priceValue = priceValue,
            priceCurrency = AppCurrencies.USD,
            dateOfLastPriceUpdate = Calendar.getInstance()
        )
        val futurePrice = Price(
            priceValue = futurePriceValue,
            priceCurrency = AppCurrencies.USD,
            dateOfLastPriceUpdate = Calendar.getInstance()
        )

        viewModel.addBond(name, amount, price, futurePrice, yieldToMaturity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            saveBond()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
