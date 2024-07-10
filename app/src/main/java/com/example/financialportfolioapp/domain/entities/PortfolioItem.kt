package com.example.financialportfolioapp.domain.entities

import java.util.Calendar

interface PortfolioItemInterface : AssetInterface {
    var amount: Double
    var price: Double
    var currency: String
    var dateOfLastPriceUpdate: Calendar
    var priceHistory: MutableMap<Calendar, Double>
}

data class Cash(
    override val id: Int,
    override val name: String,
    override var amount: Double,
    override var price: Double,
    override var currency: String,
    override var dateOfLastPriceUpdate: Calendar
) : PortfolioItemInterface {
    override var priceHistory: MutableMap<Calendar, Double> =
        mutableMapOf(dateOfLastPriceUpdate to price)
}

data class Stock(
    override val id: Int,
    override val name: String,
    override var amount: Double,
    override var price: Double,
    override var currency: String,
    override var dateOfLastPriceUpdate: Calendar,
) : PortfolioItemInterface {
    override var priceHistory: MutableMap<Calendar, Double> =
        mutableMapOf(dateOfLastPriceUpdate to price)
}

data class Bond(
    override val id: Int,
    override val name: String,
    override var amount: Double,
    override var price: Double,
    override var currency: String,
    override var dateOfLastPriceUpdate: Calendar,
    var futurePrice: Double,
    var yieldToMaturity: Double,
) : PortfolioItemInterface {
    override var priceHistory: MutableMap<Calendar, Double> =
        mutableMapOf(dateOfLastPriceUpdate to price)
}

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