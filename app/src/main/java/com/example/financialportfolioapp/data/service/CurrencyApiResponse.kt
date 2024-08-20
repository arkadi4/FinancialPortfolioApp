package com.example.financialportfolioapp.data.service

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyApiResponse(
    @SerialName("Date")
    val date: String,
    @SerialName("Cur_Abbreviation")
    val abbreviation: String,
    @SerialName("Cur_Scale")
    val scale: Int,
    @SerialName("Cur_OfficialRate")
    val rate: Double
)
