package com.example.financialportfolioapp.presentation.assetList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.repository.AssetRepository
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AssetListViewModel @Inject constructor(
    private val assetRepository: AssetRepository,
    private val portfolioItemRepository: PortfolioItemRepository
) : ViewModel() {
    private val _assets = MutableLiveData<List<Asset>>()

    private val _items = MutableLiveData<List<PortfolioItemInterface>>()
    val assets: LiveData<List<Asset>> get() = _assets

    val items: LiveData<List<PortfolioItemInterface>> get() = _items

    init {
        _assets.value = loadSampleData()
    }

    private fun loadSampleData(): List<Asset> {
        return assetRepository.getAssets()
    }

    fun assetExists(assetId: Int): Boolean {
        return portfolioItemRepository.getItems().any { it.id == assetId }
    }
}
