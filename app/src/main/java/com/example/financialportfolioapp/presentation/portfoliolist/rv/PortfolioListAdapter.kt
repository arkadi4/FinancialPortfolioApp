package com.example.financialportfolioapp.presentation.portfoliolist.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.financialportfolioapp.presentation.entitiespresentation.PortfolioItemUiModel
import com.example.financialportfolioapp.presentation.entitiespresentation.TypesFactory

class PortfolioListAdapter :
    RecyclerView.Adapter<BasePortfolioListViewHolder<PortfolioItemUiModel>>() {
    private var items = emptyList<PortfolioItemUiModel>()
    private val differ = AsyncListDiffer(this, PortfolioListDiffCallBack)
    var onClick: (PortfolioItemUiModel) -> Unit = { }

    override fun getItemViewType(position: Int): Int {
        return items[position].type()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePortfolioListViewHolder<PortfolioItemUiModel> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return TypesFactory.holder(viewType, view)
            as BasePortfolioListViewHolder<PortfolioItemUiModel>
    }

    override fun onBindViewHolder(
        holder: BasePortfolioListViewHolder<PortfolioItemUiModel>,
        position: Int
    ) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submit(newItems: List<PortfolioItemUiModel>) {
        items = newItems
        differ.submitList(newItems)
    }
}
