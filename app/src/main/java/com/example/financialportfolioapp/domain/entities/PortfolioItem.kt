package com.example.financialportfolioapp.domain.entities

import java.util.Calendar

enum class AppCurrencies(
    val currencyName: String
) {
    BYN("BYN"),
    USD("USD"),
    RUB("RUB"),
    CNY("CNY")
}

class Price(
    var priceValue: Double,
    var priceCurrency: AppCurrencies,
    var dateOfLastPriceUpdate: Calendar
) {
    var priceString: String = "$priceValue ${priceCurrency.currencyName}"
    var history: MutableMap<Calendar, String> = mutableMapOf(dateOfLastPriceUpdate to priceString)
}

interface PortfolioItemInterface : AssetInterface {
    val amount: Double
    val price: Price
}

data class Cash(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price
) : PortfolioItemInterface

data class Stock(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price
) : PortfolioItemInterface

data class Bond(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price,
    val futurePrice: Double,
    val yieldToMaturity: Double
) : PortfolioItemInterface

interface MetaInfoInterface {
    val country: String
    val economySector: String
}

interface ExchangeTradedFundInterface : PortfolioItemInterface, MetaInfoInterface {
    val someExchangeTradedFundProperty: Any
}

interface IndexedBondInterface : PortfolioItemInterface, MetaInfoInterface {
    val someIndexedBondProperty: Any
}
