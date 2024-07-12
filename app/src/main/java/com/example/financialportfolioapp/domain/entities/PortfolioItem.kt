package com.example.financialportfolioapp.domain.entities

import java.util.Calendar

const val BynToByn = 1.0
const val BynToUSD = 0.32
const val UsdToByn = 32.0
const val UsdToUsd = 1.0

enum class AppCurrencies(
    val currencyName: String,
    var exchangeRatio: MutableMap<String, Double>
) {
    BYN(
        "BYN",
        mutableMapOf(
            "BYN" to BynToByn,
            "USD" to BynToUSD
        )
    ),
    USD(
        "USD",
        mutableMapOf(
            "BYN" to UsdToByn,
            "USD" to UsdToUsd
        )
    ),
//    RUB,
//    CNY
}

class Price(
    var priceValue: Double,
    var priceCurrency: AppCurrencies,
    var dateOfLastPriceUpdate: Calendar
) {
    var priceString: String = "$priceValue ${priceCurrency.currencyName}"
    var history: MutableMap<Calendar, String> = mutableMapOf(dateOfLastPriceUpdate to priceString)

    fun changeToAnotherCurrency(anotherCurrency: AppCurrencies) {
        priceValue = priceValue * anotherCurrency.exchangeRatio.get(anotherCurrency.currencyName)!!
        priceCurrency = anotherCurrency
    }

    fun updatePrice(newDateOfLastPriceUpdate: Calendar, newPriceValue: Double) {
        dateOfLastPriceUpdate = newDateOfLastPriceUpdate
        priceString = "$newPriceValue ${priceCurrency.currencyName}"
        history.put(newDateOfLastPriceUpdate, priceString)
    }
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
