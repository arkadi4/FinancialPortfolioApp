package com.example.financialportfolioapp.domain.entities

abstract class Currencies: Asset() {
    abstract var amount: Double
    var nameOfDefaultCurrency: String = "BYN"
}

data class USD(
    override var amount: Double,
) : Currencies() {
    override val name: String = "USD"
    override var price: Double = 3.21
}

data class RUB(
    override var amount: Double,
) : Currencies() {
    override val name: String = "RUB"
    override var price: Double = 0.036
}

data class BYN(
    override var amount: Double,
) : Currencies() {
    override val name: String = "BYN"
    override var price: Double = 1.00
}

data class CNY(
    override var amount: Double,
) : Currencies() {
    override val name: String = "CNY"
    override var price: Double = 0.44
}
