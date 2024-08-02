package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.financialportfolioapp.domain.entities.PortfolioItem

object PortfolioListDiffCallBack : DiffUtil.ItemCallback<PortfolioItem>() {
    override fun areItemsTheSame(oldItem: PortfolioItem, newItem: PortfolioItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: PortfolioItem, newItem: PortfolioItem): Boolean {
        return oldItem == newItem
    }
}
