package com.example.financialportfolioapp.domain.entities

import java.util.Date

interface PortfolioCashInterface {
    var amount: Double
}

interface PortfolioValuablePaperInterface {
    var amount: Int
}

interface PriceHistoryInterface {
    var priceHistory: MutableMap<Date, Double>
}

data class Currency(
    override val id: Int,
    override val name: String,
    override var date: Date,
    override var price: Double,
    override var amount: Double,
) : AssetInterface, PortfolioCashInterface, PriceHistoryInterface {
    override var priceHistory: MutableMap<Date, Double> = mutableMapOf(date to price)
}

interface MetaInfo {
    val country: String
    val economySector: String
}

data class Share(
    override val id: Int,
    override val name: String,
    override var date: Date,
    override var price: Double,
    override var amount: Int,
    override val country: String,
    override val economySector: String,
) : AssetInterface, PortfolioValuablePaperInterface, MetaInfo, PriceHistoryInterface {
    override var priceHistory: MutableMap<Date, Double> = mutableMapOf(date to price)
}

interface InterestBearingBondInterface {
    var futurePrice: Double
    var yieldToMaturity: Double
}

data class InterestBearingBond(
    override val id: Int,
    override val name: String,
    override var date: Date,
    override var price: Double,
    override var amount: Int,
    override val country: String,
    override val economySector: String,
    override var futurePrice: Double,
    override var yieldToMaturity: Double,
) : AssetInterface,
    PortfolioValuablePaperInterface,
    MetaInfo,
    InterestBearingBondInterface,
    PriceHistoryInterface {
    override var priceHistory: MutableMap<Date, Double> = mutableMapOf(date to price)
}

interface ExchangeTradedFund : AssetInterface, MetaInfo {
    val someExchangeTradedFundProperty: Any
}

interface IndexedBond : AssetInterface, MetaInfo {
    val someIndexedBondProperty: Any
}