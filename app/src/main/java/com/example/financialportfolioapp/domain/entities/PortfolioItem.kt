package com.example.financialportfolioapp.domain.entities

import com.example.financialportfolioapp.presentation.portfoliolist.rv.TypeFactory
//import com.example.financialportfolioapp.presentation.portfolio.rv.TypesFactory
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
    fun getPriceString(): String = "$priceValue ${priceCurrency.currencyName}"
    val history: MutableMap<Calendar, String> =
        mutableMapOf(dateOfLastPriceUpdate to getPriceString())
}

interface PortfolioItemInterface {
    val id: Int
    val name: String
    val amount: Double
    val price: Price
}

interface Visitable {
    fun type(typeFactory: TypeFactory): Int
}

abstract class PortfolioItem(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price
) : PortfolioItemInterface, Visitable {
    abstract override fun type(typeFactory: TypeFactory): Int
}

data class Cash(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price
) : PortfolioItem (id, name, amount, price) {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class Stock(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price,
    val dividends: Double
) : PortfolioItem (id, name, amount, price) {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class Bond(
    override val id: Int,
    override val name: String,
    override val amount: Double,
    override val price: Price,
    val futurePrice: Double,
    val yieldToMaturity: Double
) : PortfolioItem (id, name, amount, price) {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

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
