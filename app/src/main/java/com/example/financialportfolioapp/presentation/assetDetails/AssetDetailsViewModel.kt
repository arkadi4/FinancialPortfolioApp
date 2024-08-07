package com.example.financialportfolioapp.presentation.assetDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AssetDetailsViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository
) : ViewModel() {
    private val _item = MutableLiveData<PortfolioItemInterface?>()
    val item: LiveData<PortfolioItemInterface?> get() = _item

    fun loadItem(assetId: Int) {
        val item = portfolioItemRepository.getItemById(assetId)
        _item.value = item
    }
}
