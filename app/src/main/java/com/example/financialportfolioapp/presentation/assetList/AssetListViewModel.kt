package com.example.financialportfolioapp.presentation.assetList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.data.DataSample
import com.example.financialportfolioapp.domain.entities.Asset

class AssetListViewModel : ViewModel() {
    private val _assets = MutableLiveData<List<Asset>>()
    val assets: LiveData<List<Asset>> get() = _assets

    init {
        _assets.value = loadSampleData()
    }

    private fun loadSampleData(): MutableList<Asset> {
        return DataSample.assetList
    }
}
