package com.example.financialportfolioapp.presentation.portfoliolist.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface

object PortfolioListDiffCallBack : DiffUtil.ItemCallback<PortfolioItemInterface>() {
    override fun areItemsTheSame(oldItem: PortfolioItemInterface, newItem: PortfolioItemInterface) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PortfolioItemInterface, newItem: PortfolioItemInterface) =
        oldItem.name == newItem.name
}
