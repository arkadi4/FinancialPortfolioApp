package com.example.financialportfolioapp.presentation.assetList.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.financialportfolioapp.domain.entities.Asset

object DiffCallBack : DiffUtil.ItemCallback<Asset>() {
    override fun areItemsTheSame(oldItem: Asset, newItem: Asset) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Asset, newItem: Asset) =
        oldItem == newItem
}
