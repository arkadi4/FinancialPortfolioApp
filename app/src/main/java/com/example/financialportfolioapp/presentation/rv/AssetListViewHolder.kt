package com.example.financialportfolioapp.presentation.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.financialportfolioapp.databinding.AssetListItemBinding
import com.example.financialportfolioapp.domain.entities.Asset

class AssetListViewHolder(
    private val binding: AssetListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Asset, onClick: (Asset) -> Unit) {
        binding.tvAssetName.text = model.name
        binding.root.setOnClickListener { onClick(model) }
    }
}
