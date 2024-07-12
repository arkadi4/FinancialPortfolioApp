package com.example.financialportfolioapp.data

import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.entities.Stock
import java.util.Calendar

object DataSample {
    var assetList: MutableList<Asset> = mutableListOf(
        Asset(1, "USD"),
        Asset(2, "BYN"),
        Asset(3, "Minsk Tractors"),
        Asset(4, "Lufthansa"),
        Asset(5, "GP Morgan"),
        Asset(6, "Columbia Pictures")
    )

    var portfolioItemsList: MutableList<PortfolioItemInterface> = mutableListOf(
        Cash(
            1, "USD", 15.0, 3.20, "BYN", Calendar.getInstance()
        ),
        Stock(
            2, "Minsk Tractors", 20.0, 5_000.0, "BYN",
            Calendar.getInstance()
        ),
        Bond(
            3, "GP Morgan", 30.0, 50_000.0, "BYN",
            Calendar.getInstance(), 51_000.0, 1_000.0
        )
    )
}
