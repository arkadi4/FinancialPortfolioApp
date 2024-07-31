package com.example.financialportfolioapp.presentation.assetList.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.financialportfolioapp.databinding.AssetListItemBinding
import com.example.financialportfolioapp.domain.entities.Asset

class AssetListViewHolder(
    private val binding: AssetListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Asset, onClick: (Asset) -> Unit, onDeleteClick: (Asset) -> Unit) {
        binding.tvAssetName.text = model.name
        binding.root.setOnClickListener { onClick(model) }
        binding.btnDelete.setOnClickListener { onDeleteClick(model) }
    }
}
