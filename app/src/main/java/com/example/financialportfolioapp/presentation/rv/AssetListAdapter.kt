package com.example.financialportfolioapp.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.financialportfolioapp.databinding.AssetListItemBinding
import com.example.financialportfolioapp.domain.entities.Asset

class AssetListAdapter : RecyclerView.Adapter<AssetListViewHolder>() {
    private var items = emptyList<Asset>()
    private val differ = AsyncListDiffer(this, DiffCallBack)
    var onClick: (Asset) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetListViewHolder {
        val view = AssetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetListViewHolder(view)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: AssetListViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    fun submit(newItems: List<Asset>) {
        items = newItems
        differ.submitList(newItems)
    }
}
