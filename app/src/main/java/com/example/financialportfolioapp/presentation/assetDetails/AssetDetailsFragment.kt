package com.example.financialportfolioapp.presentation.assetDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.financialportfolioapp.databinding.FragmentAssetDetailsBinding
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.presentation.utils.DateTimeUtils
import com.example.financialportfolioapp.presentation.utils.DateTimeUtils.dateTimeFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        fun populateScreen(
            nameValue: String,
            amountValue: String,
            priceValue: String,
            currencyValue: String,
            lastPriceUpdateValue: String,
            dividendsValue: String? = null,
            futurePriceValue: String? = null
        ) {
            binding.apply {
                this.nameValue.text = nameValue
                this.amountValue.text = amountValue
                this.priceValue.text = priceValue
                this.currencyValue.text = currencyValue
                this.lastPriceUpdateValue.text = lastPriceUpdateValue

                dividendsValue?.let {
                    this.dividendsValue.text = it
                    this.dividendsValue.visibility = View.VISIBLE
                    this.dividends.visibility = View.VISIBLE
                } ?: run {
                    this.dividendsValue.visibility = View.GONE
                    this.dividends.visibility = View.GONE
                }

                futurePriceValue?.let {
                    this.futurePriceValue.text = it
                    this.futurePriceValue.visibility = View.VISIBLE
                    this.futurePrice.visibility = View.VISIBLE
                } ?: run {
                    this.futurePriceValue.visibility = View.GONE
                    this.futurePrice.visibility = View.GONE
                }
            }
        }

        assetDetailViewModel.item.observe(viewLifecycleOwner) {
            val item = it ?: return@observe

            binding.apply {
                when (item) {
                    is Stock -> {
                        populateScreen(
                            nameValue = item.name,
                            amountValue = item.amount.toString(),
                            priceValue = item.price.priceValue.toString(),
                            currencyValue = item.price.priceCurrency.toString(),
                            lastPriceUpdateValue = DateTimeUtils.formatCalendar(
                                item.price.dateOfLastPriceUpdate,
                                dateTimeFormatter
                            ),
                            dividendsValue = item.dividends.toString()
                        )
                    }

                    is Bond -> {
                        populateScreen(
                            nameValue = item.name,
                            amountValue = item.amount.toString(),
                            priceValue = item.price.priceValue.toString(),
                            currencyValue = item.price.priceCurrency.toString(),
                            lastPriceUpdateValue = DateTimeUtils.formatCalendar(
                                item.price.dateOfLastPriceUpdate,
                                dateTimeFormatter
                            ),
                            futurePriceValue = item.futurePrice.priceValue.toString()
                        )
                    }

                    is Cash -> {
                        populateScreen(
                            nameValue = item.name,
                            amountValue = item.amount.toString(),
                            priceValue = item.price.priceValue.toString(),
                            currencyValue = item.price.priceCurrency.toString(),
                            lastPriceUpdateValue = DateTimeUtils.formatCalendar(
                                item.price.dateOfLastPriceUpdate,
                                dateTimeFormatter
                            )
                        )
                    }
                }
            }
            binding.btnAdd.setOnClickListener {
                lifecycleScope.launch {
                    assetDetailViewModel.addItem(item.id.toLong())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
