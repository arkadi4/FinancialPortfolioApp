package com.example.financialportfolioapp.presentation.assetList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.interactor.AssetListInteractor
import com.example.financialportfolioapp.domain.repository.AssetRepository
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AssetListViewModel @Inject constructor(
    private val assetRepository: AssetRepository,
    private val portfolioItemRepository: PortfolioItemRepository,
    private val interactor: AssetListInteractor
) : ViewModel() {
    private val _assets = MutableLiveData<List<Asset>>()
    val assets: LiveData<List<Asset>> get() = _assets

    private val _items = MutableLiveData<List<PortfolioItemInterface>>()
    val items: LiveData<List<PortfolioItemInterface>> get() = _items

    init {
        loadData()
    }

    private fun loadData() {
        _assets.value = assetRepository.getAssets()
    }

    fun deleteItem(asset: Asset) {
        interactor.deleteAssetById(asset.id)
        loadData()
    }
}
