package com.example.financialportfolioapp.domain.entities

import java.util.Date

abstract class ValuablePapers: Asset() {
    abstract var amount: Int
    var nameOfDefaultCurrency: String = "BYN"
    abstract var priceHistory: MutableMap<Date, Double>
    // TODO: implement meta information(country, economy sector)
}

data class Stock(
    override val name: String,
    override var amount: Int,
    override var price: Double,
) : ValuablePapers() {
    private var date: Date = Date()
    override var priceHistory: MutableMap<Date, Double> = mutableMapOf(date to price)
}

data class InterestBearingBond(
    override val name: String,
    override var amount: Int,
    override var price: Double,
    var futurePrice: Double,
    var yieldToMaturity: Double,
) : ValuablePapers() {
    private var date: Date = Date()
    override var priceHistory: MutableMap<Date, Double> = mutableMapOf(date to price)
}

// TODO: implement in future
//data class ExchangeTradedFund(
//
//) : ValuablePapers()

//data class IndexedBond(
//
//) : ValuablePapers()