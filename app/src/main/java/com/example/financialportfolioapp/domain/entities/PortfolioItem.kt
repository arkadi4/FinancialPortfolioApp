package com.example.financialportfolioapp.domain.entities

import kotlinx.serialization.Serializable

enum class AppCurrencies(
    val currencyName: String
) {
    BYN("BYN"),
    USD("USD"),
    RUB("RUB"),
    CNY("CNY")
}

@Serializable
class Price(
    var priceValue: Double,
    var priceCurrency: AppCurrencies
//    @Serializable(with = CalendarAsStringSerializer::class)
//    var dateOfLastPriceUpdate: Calendar
) {
    fun getPriceString(): String = "$priceValue ${priceCurrency.currencyName}"
//    val history: MutableMap<@Serializable(
//        with = CalendarAsStringSerializer::class
//    ) Calendar, String> =
//        mutableMapOf(dateOfLastPriceUpdate to getPriceString())
}

interface PortfolioItemInterface {
    val id: Int
    val name: String
    val amount: Double
    val price: Price
}

sealed class PortfolioItem(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price
) : PortfolioItemInterface

data class Cash(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price,
    val exchangeRatioToUSD: Double
) : PortfolioItem(
    id, name, amount, price
)

data class Stock(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price,
    val dividends: Double
) : PortfolioItem(
    id, name, amount, price
)

data class Bond(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price,
    val futurePrice: Price,
    val yieldToMaturity: Double
) : PortfolioItem(
    id, name, amount, price
)

interface MetaInfoInterface {
    val country: String
    val economySector: String
}

interface ExchangeTradedFundInterface : PortfolioItemInterface, MetaInfoInterface {
    val someExchangeTradedFundProperty: Double
}

interface IndexedBondInterface : PortfolioItemInterface, MetaInfoInterface {
    val someIndexedBondProperty: Double
}
