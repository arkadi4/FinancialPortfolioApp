package com.example.financialportfolioapp.presentation.assetDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.financialportfolioapp.databinding.FragmentAssetDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class AssetDetailsFragment : Fragment() {

    private var _binding: FragmentAssetDetailsBinding? = null
    private val binding get() = _binding!!

    private val assetDetailViewModel: AssetDetailsViewModel by viewModels()
    private val args: AssetDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val assetId = args.assetId

        assetDetailViewModel.loadItem(assetId).observe(viewLifecycleOwner) { item ->

            val calendar = item.price.dateOfLastPriceUpdate
            val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            val dateString = dateFormat.format(calendar.time)
            binding.nameValue.text = item.name
            binding.amountValue.text = item.amount.toString()
            binding.priceValue.text = item.price.priceValue.toString()
            binding.currencyValue.text = item.price.priceCurrency.toString()
            binding.lastPriceUpdateValue.text = dateString
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
