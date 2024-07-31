package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.view.View
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.Stock

interface TypeFactory {
    fun type(cash: Cash): Int
    fun type(stock: Stock): Int
    fun type(bond: Bond): Int
    fun holder(type: Int, view: View): BasePortfolioListViewHolder<*>
}
