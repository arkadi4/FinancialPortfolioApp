package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.view.View
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock

class TypeFactoryImpl : TypeFactory {
    override fun type(cash: Cash) = R.layout.item_cash_portfolio_list
    override fun type(stock: Stock) = R.layout.item_stock_portfolio_list
    override fun type(bond: Bond) = R.layout.item_bond_portfolio_list

    override fun holder(type: Int, view: View): BasePortfolioListViewHolder<*> {
        return when (type) {
            R.layout.item_cash_portfolio_list -> CashHolder(view)
            R.layout.item_stock_portfolio_list -> StockHolder(view)
            R.layout.item_bond_portfolio_list -> BondHolder(view)
            else -> CashHolder(view)
        }
    }
}
