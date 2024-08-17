package com.example.financialportfolioapp.data

import com.example.financialportfolioapp.domain.entities.AppCurrencies
import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.DomainItemType
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.entities.Price
import com.example.financialportfolioapp.domain.entities.Stock
import java.util.Calendar

object DataSample {

    val assetList: MutableList<Asset> = mutableListOf(
        Asset(1, "USD", DomainItemType.CASH),
        Asset(2, "BYN", DomainItemType.CASH),
        Asset(3, "Minsk Tractors", DomainItemType.STOCK),
        Asset(4, "Lufthansa", DomainItemType.STOCK),
        Asset(5, "GP Morgan", DomainItemType.BOND),
        Asset(6, "Columbia Pictures", DomainItemType.BOND)
    )

    val portfolioItemsList: MutableList<PortfolioItemInterface> = mutableListOf(
        Cash(
            1,
            "USD",
            15.0,
            Price(
                1.0, AppCurrencies.USD, Calendar.getInstance()
            )
        ),
        Stock(
            2,
            "Minsk Tractors",
            20.0,
            Price(
                500.0, AppCurrencies.BYN, Calendar.getInstance()
            ),
            0.25
        ),
        Bond(
            3,
            "GP Morgan",
            300.0,
            Price(
                30.0, AppCurrencies.BYN, Calendar.getInstance()
            ),
            Price(
                35.0, AppCurrencies.BYN, Calendar.getInstance()
            ),
            50.0
        )
    )

    fun deleteItem(id: Int) {
        val item = portfolioItemsList.firstOrNull { it.id == id }
        portfolioItemsList.remove(item)
    }

    fun deleteAsset(id: Int) {
        val asset = assetList.firstOrNull { it.id == id }
        assetList.remove(asset)
    }
}
