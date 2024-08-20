package com.example.financialportfolioapp.presentation.portfoliodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.databinding.FragmentPortfolioDetailsBinding
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.presentation.utils.DateTimeUtils
import com.example.financialportfolioapp.presentation.utils.DateTimeUtils.dateTimeFormatter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioDetailsFragment : Fragment() {

    private var _binding: FragmentPortfolioDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: PortfolioDetailsFragmentArgs by navArgs()
    private val portfolioDetailsViewModel: PortfolioDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemId = args.id
        portfolioDetailsViewModel.loadItem(itemId.toLong())

        portfolioDetailsViewModel.item.observe(viewLifecycleOwner) {
            val item = it ?: return@observe
            binding.apply {
                itemName.text = getString(R.string.item_name, item.name)
                itemQuantity.text = getString(R.string.item_quantity, item.amount.toString())
                itemCurrentPrice.text = getString(
                    R.string.item_current_price,
                    item.price.priceValue.toString()
                )
                itemCurrency.text = getString(
                    R.string.item_currency,
                    item.price.priceCurrency.toString()
                )
                itemLastPriceUpdate.text = getString(
                    R.string.item_last_price_update,
                    item.price.dateOfLastPriceUpdate.let {
                        DateTimeUtils.formatCalendar(it, dateTimeFormatter)
                    }
                )
            }
            when (item) {
                is Cash -> {
                    binding.apply {
                        itemExchangeRatioToUsd.text = getString(
                            R.string.item_exchange_ratio_to_usd,
                            item.exchangeRatioToUSD.toString()
                        )
                        itemDividends.visibility = View.GONE
                        itemFuturePrice.visibility = View.GONE
                        itemYieldToMaturity.visibility = View.GONE
                    }
                }
                is Stock -> {
                    binding.apply {
                        itemExchangeRatioToUsd.visibility = View.GONE
                        itemDividends.text = getString(
                            R.string.item_dividends,
                            item.dividends.toString()
                        )
                        itemFuturePrice.visibility = View.GONE
                        itemYieldToMaturity.visibility = View.GONE
                    }
                }
                is Bond -> {
                    binding.apply {
                        itemExchangeRatioToUsd.visibility = View.GONE
                        itemDividends.visibility = View.GONE
                        itemFuturePrice.text = getString(
                            R.string.future_price,
                            item.futurePrice.priceValue.toString()
                        )
                        itemYieldToMaturity.text = getString(
                            R.string.item_yield_to_maturity,
                            item.yieldToMaturity.toString()
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
