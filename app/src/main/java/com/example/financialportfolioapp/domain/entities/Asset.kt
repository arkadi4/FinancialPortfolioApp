package com.example.financialportfolioapp.domain.entities

interface AssetInterface {
    val id: Int
    val name: String
}

data class Asset(
    override val id: Int,
    override val name: String,
) : AssetInterface
