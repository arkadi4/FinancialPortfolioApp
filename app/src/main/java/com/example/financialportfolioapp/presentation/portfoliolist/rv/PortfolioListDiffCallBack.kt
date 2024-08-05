package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.financialportfolioapp.presentation.entitiespresentation.PortfolioItemUiModel

object PortfolioListDiffCallBack : DiffUtil.ItemCallback<PortfolioItemUiModel>() {
    override fun areItemsTheSame(
        oldItem: PortfolioItemUiModel,
        newItem: PortfolioItemUiModel
    ): Boolean {
        return oldItem.getIdForDiffUtils() == newItem.getIdForDiffUtils()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: PortfolioItemUiModel,
        newItem: PortfolioItemUiModel
    ): Boolean {
        return oldItem == newItem
    }
}
