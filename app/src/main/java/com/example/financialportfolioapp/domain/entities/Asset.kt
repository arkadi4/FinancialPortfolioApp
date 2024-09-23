package com.example.financialportfolioapp.domain.entities

import com.example.financialportfolioapp.data.entities.AssetEntity
import com.example.financialportfolioapp.data.entities.ItemType
import kotlinx.serialization.Serializable

@Serializable
data class Asset(
    val id: Int,
    val name: String,
    val type: DomainItemType
)

fun Asset.toAssetDbEntity(): AssetEntity {
    return AssetEntity(
        id = id.toLong(),
        name = name,
        portfolioItemType = when (type) {
            DomainItemType.CASH -> ItemType.CASH
            DomainItemType.STOCK -> ItemType.STOCK
            DomainItemType.BOND -> ItemType.BOND
        }
    )
}

enum class DomainItemType {
    STOCK, BOND, CASH
}
