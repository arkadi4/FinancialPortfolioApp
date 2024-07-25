package com.example.financialportfolioapp.presentation.assetDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.financialportfolioapp.databinding.FragmentAssetDetailsBinding
import com.example.financialportfolioapp.presentation.utils.DateTimeUtils
import dagger.hilt.android.AndroidEntryPoint

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

        assetDetailViewModel.loadItem(assetId)
        assetDetailViewModel.item.observe(viewLifecycleOwner) {
            val item = it ?: return@observe

            binding.apply {
                nameValue.text = item.name
                amountValue.text = item.amount.toString()
                priceValue.text = item.price.priceValue.toString()
                currencyValue.text = item.price.priceCurrency.toString()
                lastPriceUpdateValue.text = item.price.dateOfLastPriceUpdate.let {
                    DateTimeUtils.formatCalendar(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
