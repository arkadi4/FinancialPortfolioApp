package com.example.financialportfolioapp.domain.entities

data class Asset(
    val id: Int,
    val name: String,
    val type: DomainItemType
)

enum class DomainItemType {
    STOCK, BOND, CASH
}
