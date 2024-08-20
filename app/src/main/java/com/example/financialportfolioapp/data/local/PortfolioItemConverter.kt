package com.example.financialportfolioapp.data.local

import androidx.room.TypeConverter
import com.example.financialportfolioapp.domain.entities.AppCurrencies
import com.example.financialportfolioapp.domain.entities.Price
import java.util.Calendar
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PortfolioItemConverter {
    @TypeConverter
    fun fromPrice(price: Price?): String? {
        return price?.let {
            Json.encodeToString(it)
        }
    }

    @TypeConverter
    fun toPrice(priceString: String?): Price? {
        return priceString?.let {
            Json.decodeFromString(it)
        }
    }

    @TypeConverter
    fun fromAppCurrencies(currency: AppCurrencies?): String? {
        return currency?.name
    }

    @TypeConverter
    fun toAppCurrencies(currencyName: String?): AppCurrencies? {
        return currencyName?.let { AppCurrencies.valueOf(it) }
    }

    @TypeConverter
    fun fromCalendar(calendar: Calendar?): Long? {
        return calendar?.timeInMillis
    }

    @TypeConverter
    fun toCalendar(millis: Long?): Calendar? {
        return millis?.let {
            Calendar.getInstance().apply { timeInMillis = it }
        }
    }
}
