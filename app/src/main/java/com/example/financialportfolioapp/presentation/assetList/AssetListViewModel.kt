package com.example.financialportfolioapp.presentation.assetList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.interactor.AssetListInteractor
import com.example.financialportfolioapp.domain.repository.AssetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AssetListScreenState(
    val assetList: List<Asset>
)

@HiltViewModel
class AssetListViewModel @Inject constructor(
    private val assetRepository: AssetRepository,
//    private val portfolioItemRepository: PortfolioItemRepository,
    private val interactor: AssetListInteractor
) : ViewModel() {
    private val _assets = MutableStateFlow(AssetListScreenState(emptyList()))
    val assets = _assets.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
//            assetRepository.addSamplesToDb()
            _assets.value = _assets.value.copy(assetRepository.getAssets())
        }
    }

    fun deleteAllAssets() {
        viewModelScope.launch {
            assetRepository.deleteAllAssets()
            loadData()
        }
    }

    fun deleteItem(asset: Asset) {
        viewModelScope.launch {
            interactor.deleteAssetById(asset.id.toLong())
            loadData()
        }
    }
}
