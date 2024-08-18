package com.example.financialportfolioapp.data.service

import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {
    @GET("/exrates/rates/{base}?parammode=2")
    suspend fun getExchangeRate(@Path("base") base: String): CurrencyApiResponse
}
