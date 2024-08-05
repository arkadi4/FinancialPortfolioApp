package com.example.financialportfolioapp.presentation.entitiespresentation

import android.view.View
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.presentation.portfoliolist.rv.BasePortfolioListViewHolder
import com.example.financialportfolioapp.presentation.portfoliolist.rv.BondViewHolder
import com.example.financialportfolioapp.presentation.portfoliolist.rv.CashViewHolder
import com.example.financialportfolioapp.presentation.portfoliolist.rv.StockViewHolder

object TypesFactory {
    fun holder(type: Int, view: View): BasePortfolioListViewHolder<*> {
        return when (type) {
            R.layout.item_cash_portfolio_list -> CashViewHolder(view)
            R.layout.item_stock_portfolio_list -> StockViewHolder(view)
            R.layout.item_bond_portfolio_list -> BondViewHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}

abstract class PortfolioItemUiModel() {
    abstract fun type(): Int
    abstract fun getIdForDiffUtils(): Int
}
data class CashUiModel(val cash: Cash) : PortfolioItemUiModel() {
    override fun type() = R.layout.item_cash_portfolio_list
    override fun getIdForDiffUtils(): Int = cash.id
}
data class StockUiModel(val stock: Stock) : PortfolioItemUiModel() {
    override fun type() = R.layout.item_stock_portfolio_list
    override fun getIdForDiffUtils(): Int = stock.id
}
data class BondUiModel(val bond: Bond) : PortfolioItemUiModel() {
    override fun type() = R.layout.item_bond_portfolio_list
    override fun getIdForDiffUtils(): Int = bond.id
}
