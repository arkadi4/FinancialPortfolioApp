package com.example.financialportfolioapp.data.repository

import com.example.financialportfolioapp.data.service.CurrencyApiResponse
import com.example.financialportfolioapp.data.service.CurrencyApiService
import com.example.financialportfolioapp.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApiService: CurrencyApiService
) : CurrencyRepository {
    override suspend fun getCurrencyRate(currencyAbr: String): CurrencyApiResponse {
        return currencyApiService.getExchangeRate(currencyAbr)
    }
}
