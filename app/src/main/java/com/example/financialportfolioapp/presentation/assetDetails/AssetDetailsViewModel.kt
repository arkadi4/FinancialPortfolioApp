package com.example.financialportfolioapp.presentation.assetDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.data.DataSample.portfolioItemsList
import com.example.financialportfolioapp.data.local.dao.AssetDao
import com.example.financialportfolioapp.data.local.dao.BondDao
import com.example.financialportfolioapp.data.local.dao.CashDao
import com.example.financialportfolioapp.data.local.dao.StockDao
import com.example.financialportfolioapp.domain.entities.DomainItemType
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetDetailsViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository,
    private val assetDao: AssetDao,
    private val stockDao: StockDao,
    private val cashDao: CashDao,
    private val bondDao: BondDao

) : ViewModel() {
    private val _item = MutableLiveData<PortfolioItemInterface?>()
    val item: LiveData<PortfolioItemInterface?> get() = _item

    fun loadItem(assetId: Int) {
        viewModelScope.launch {
            _item.value = portfolioItemRepository.getItemById(assetId.toLong())
        }
    }

    suspend fun addItem(assetId: Long) {
        viewModelScope.launch {
            val asset = assetDao.getAssetById(assetId)
            val item: PortfolioItemInterface = when (asset.type) {
                DomainItemType.STOCK -> stockDao.getStockById(assetId)
                DomainItemType.BOND -> bondDao.getBondById(assetId)
                DomainItemType.CASH -> cashDao.getCashById(assetId)
            } ?: return@launch

            portfolioItemsList.add(item)
        }
    }
}
