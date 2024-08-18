package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.data.service.CurrencyApiResponse

interface CurrencyRepository {
    suspend fun getCurrencyRate(currencyAbr: String): CurrencyApiResponse
}
