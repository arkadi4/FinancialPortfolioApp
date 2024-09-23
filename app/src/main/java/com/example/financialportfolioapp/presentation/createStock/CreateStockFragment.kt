//package com.example.financialportfolioapp.presentation.createStock
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.example.financialportfolioapp.databinding.FragmentCreateStockBinding
//import com.example.financialportfolioapp.domain.entities.AppCurrencies
//import com.example.financialportfolioapp.domain.entities.Price
//import dagger.hilt.android.AndroidEntryPoint
//import java.util.Calendar
//
//@AndroidEntryPoint
//class CreateStockFragment : Fragment() {
//    private val viewModel: CreateStockViewModel by viewModels()
//    private var _binding: FragmentCreateStockBinding? = null
//    private val binding get() = _binding!!
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentCreateStockBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    private fun saveStock() {
//        val name = binding.editName.text.toString()
//        val amount = binding.editAmount.text.toString().toDoubleOrNull() ?: 0.0
//        val priceValue = binding.editPrice.text.toString().toDoubleOrNull() ?: 0.0
//        val price = Price(
//            priceValue = priceValue,
//            priceCurrency = AppCurrencies.USD,
//            dateOfLastPriceUpdate = Calendar.getInstance()
//        )
//        val dividends = binding.editDividends.text.toString().toDoubleOrNull() ?: 0.0
//
//        viewModel.addStock(name, amount, price, dividends)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.btnSaveStock.setOnClickListener {
//            saveStock()
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
