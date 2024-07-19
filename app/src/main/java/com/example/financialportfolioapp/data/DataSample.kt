package com.example.financialportfolioapp.data

import com.example.financialportfolioapp.domain.entities.AppCurrencies
import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.entities.Price
import com.example.financialportfolioapp.domain.entities.Stock
import java.util.Calendar

object DataSample {

    val assetList: MutableList<Asset> = mutableListOf(
        Asset(1, "USD"),
        Asset(2, "BYN"),
        Asset(3, "Minsk Tractors"),
        Asset(4, "Lufthansa"),
        Asset(5, "GP Morgan"),
        Asset(6, "Columbia Pictures")
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
            )
        ),
        Bond(
            3,
            "GP Morgan",
            300.0,
            Price(
                30.0, AppCurrencies.BYN, Calendar.getInstance()
            ),
            350.0,
            50.0
        )
    )
}
