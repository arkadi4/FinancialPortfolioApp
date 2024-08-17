package com.example.financialportfolioapp.presentation.assetDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AssetDetailsViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository
) : ViewModel() {
    private val _item = MutableLiveData<Any?>()
    val item: LiveData<Any?> get() = _item

    fun loadItem(assetId: Int) {
        viewModelScope.launch {
            _item.value = portfolioItemRepository.getItemById(assetId.toLong())
        }
    }
}
