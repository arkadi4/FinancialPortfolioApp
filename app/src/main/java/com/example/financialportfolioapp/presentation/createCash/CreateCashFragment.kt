//package com.example.financialportfolioapp.presentation.createCash
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.example.financialportfolioapp.databinding.FragmentCreateCashBinding
//import com.example.financialportfolioapp.domain.entities.AppCurrencies
//import com.example.financialportfolioapp.domain.entities.Price
//import dagger.hilt.android.AndroidEntryPoint
//import java.util.Calendar
//
//@AndroidEntryPoint
//class CreateCashFragment : Fragment() {
//
//    private val viewModel: CreateCashViewModel by viewModels()
//    private var _binding: FragmentCreateCashBinding? = null
//    private val binding get() = _binding!!
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentCreateCashBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    private fun saveCash() {
//        val name = binding.editName.text.toString()
//        val amount = binding.editAmount.text.toString().toDoubleOrNull() ?: 0.0
//        val priceValue = binding.editPrice.text.toString().toDoubleOrNull() ?: 0.0
//        val price = Price(
//            priceValue = priceValue,
//            priceCurrency = AppCurrencies.USD,
//            dateOfLastPriceUpdate = Calendar.getInstance()
//        )
//        val exchangeRatioToUSD = 1.0
//
//        viewModel.addCash(name, amount, price, exchangeRatioToUSD)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.btnSave.setOnClickListener {
//            saveCash()
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
