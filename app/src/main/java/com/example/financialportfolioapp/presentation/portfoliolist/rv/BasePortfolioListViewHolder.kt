package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.financialportfolioapp.databinding.ItemBondPortfolioListBinding
import com.example.financialportfolioapp.databinding.ItemCashPortfolioListBinding
import com.example.financialportfolioapp.databinding.ItemStockPortfolioListBinding
import com.example.financialportfolioapp.presentation.entitiespresentation.BondUiModel
import com.example.financialportfolioapp.presentation.entitiespresentation.CashUiModel
import com.example.financialportfolioapp.presentation.entitiespresentation.StockUiModel

abstract class BasePortfolioListViewHolder<T>(
    view: View
) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, onClick: (T) -> Unit)
}

class CashViewHolder(view: View) : BasePortfolioListViewHolder<CashUiModel>(view) {
    private val binding = ItemCashPortfolioListBinding.bind(view)
    override fun bind(item: CashUiModel, onClick: (CashUiModel) -> Unit) {
        binding.apply {
            itemPortfolioName.text = item.cash.name
            cashCurrentPrice.text = item.cash.price.getPriceString()
            cashDetailsButton.setOnClickListener { onClick(item) }
        }
    }
}

class StockViewHolder(view: View) : BasePortfolioListViewHolder<StockUiModel>(view) {
    private val binding = ItemStockPortfolioListBinding.bind(view)
    override fun bind(item: StockUiModel, onClick: (StockUiModel) -> Unit) {
        binding.apply {
            itemPortfolioName.text = item.stock.name
            dividendsValue.text = item.stock.dividends.toString()
            stockDetailsButton.setOnClickListener { onClick(item) }
        }
    }
}

class BondViewHolder(view: View) : BasePortfolioListViewHolder<BondUiModel>(view) {
    private val binding = ItemBondPortfolioListBinding.bind(view)
    override fun bind(item: BondUiModel, onClick: (BondUiModel) -> Unit) {
        binding.apply {
            itemPortfolioName.text = item.bond.name
            bondFuturePrice.text = item.bond.futurePrice.toString()
            bondDetailsButton.setOnClickListener { onClick(item) }
        }
    }
}
