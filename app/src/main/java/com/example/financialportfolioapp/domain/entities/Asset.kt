package com.example.financialportfolioapp.domain.entities

import java.util.Date

interface AssetInterface {
    val id: Int
    val name: String
    var date: Date
    var price: Double
}

data class Asset(
    override val id: Int,
    override val name: String,
    override var date: Date,
    override var price: Double,
) : AssetInterface