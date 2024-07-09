package com.example.financialportfolioapp.data

import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.entities.AssetInterface
import com.example.financialportfolioapp.domain.entities.Currency
import com.example.financialportfolioapp.domain.entities.InterestBearingBond
import com.example.financialportfolioapp.domain.entities.PortfolioCashInterface
import com.example.financialportfolioapp.domain.entities.Share
import java.util.Date

object DataSample {
    var assetList: MutableList<AssetInterface> = mutableListOf(
        Asset(1, "USD", Date(), 30.0,),
        Asset(1, "BYN", Date(), 1.0,),
        Asset(1, "Minsk Tractors", Date(), 5_000.0,),
        Asset(1, "Lufthansa", Date(), 10_000.0,),
        Asset(1, "GP Morgan", Date(), 50_000.0,),
        Asset(1, "Columbia Pictures", Date(), 25_000.0,),
    )

    var portfolioItemsList: MutableList<AssetInterface> = mutableListOf(
        Currency(1, "USD", Date(), 30.0, 10.5),
        Share(1, "Minsk Tractors", Date(), 5_000.0, 15, "Belarus", "industry"),
        InterestBearingBond(
            3, "GP Morgan", Date(), 50_000.0,
            20, "USA", "Different", 21_000.0, 1_000.0,
        ),
    )
}
