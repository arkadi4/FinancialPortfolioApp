package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.financialportfolioapp.databinding.ItemBondPortfolioListBinding
import com.example.financialportfolioapp.databinding.ItemCashPortfolioListBinding
import com.example.financialportfolioapp.databinding.ItemStockPortfolioListBinding
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock

abstract class BasePortfolioListViewHolder<T>(
    view: View
) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, onClick: (T) -> Unit)
}

class CashHolder(view: View) : BasePortfolioListViewHolder<Cash>(view) {
    private val binding = ItemCashPortfolioListBinding.bind(view)
    override fun bind(item: Cash, onClick: (Cash) -> Unit) {
        binding.apply {
            itemPortfolioName.text = item.name
            cashCurrentPrice.text = item.price.getPriceString()
            cashDetailsButton.setOnClickListener { onClick(item) }
        }
    }
}

class StockHolder(view: View) : BasePortfolioListViewHolder<Stock>(view) {
    private val binding = ItemStockPortfolioListBinding.bind(view)
    override fun bind(item: Stock, onClick: (Stock) -> Unit) {
        binding.apply {
            itemPortfolioName.text = item.name
            dividendsValue.text = item.dividends.toString()
            stockDetailsButton.setOnClickListener { onClick(item) }
        }
    }
}

class BondHolder(view: View) : BasePortfolioListViewHolder<Bond>(view) {
    private val binding = ItemBondPortfolioListBinding.bind(view)
    override fun bind(item: Bond, onClick: (Bond) -> Unit) {
        binding.apply {
            itemPortfolioName.text = item.name
            bondFuturePrice.text = item.futurePrice.toString()
            bondDetailsButton.setOnClickListener { onClick(item) }
        }
    }
}
