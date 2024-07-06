package com.example.financialportfolioapp.domain.entities

abstract class Asset {
    abstract var price: Double
    abstract val name: String
}