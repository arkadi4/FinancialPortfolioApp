package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.financialportfolioapp.domain.entities.PortfolioItem

class PortfolioListAdapter(
    val typeFactory: TypeFactory
) : RecyclerView.Adapter<BasePortfolioListViewHolder<PortfolioItem>>() {
    private var items = emptyList<PortfolioItem>()
    private val differ = AsyncListDiffer(this, PortfolioListDiffCallBack)
    var onClick: (PortfolioItem) -> Unit = { }

    override fun getItemViewType(position: Int): Int {
        return items[position].type(TypeFactoryImpl())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePortfolioListViewHolder<PortfolioItem> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return typeFactory.holder(viewType, view) as BasePortfolioListViewHolder<PortfolioItem>
    }

    override fun onBindViewHolder(holder: BasePortfolioListViewHolder<PortfolioItem>, position: Int) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submit(newItems: List<PortfolioItem>) {
        items = newItems
        differ.submitList(newItems)
    }
}
