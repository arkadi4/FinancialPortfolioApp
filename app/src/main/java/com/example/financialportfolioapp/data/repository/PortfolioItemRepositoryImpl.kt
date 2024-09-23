package com.example.financialportfolioapp.data.repository

import android.util.Log
import com.example.financialportfolioapp.data.DataSample
import com.example.financialportfolioapp.data.entities.AssetEntity
import com.example.financialportfolioapp.data.entities.BondEntity
import com.example.financialportfolioapp.data.entities.CashEntity
import com.example.financialportfolioapp.data.entities.ItemType
import com.example.financialportfolioapp.data.entities.StockEntity
import com.example.financialportfolioapp.data.local.dao.AssetDao
import com.example.financialportfolioapp.data.local.dao.BondDao
import com.example.financialportfolioapp.data.local.dao.CashDao
import com.example.financialportfolioapp.data.local.dao.StockDao
import com.example.financialportfolioapp.data.local.dao.UniqueIdDao
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.DomainItemType
import com.example.financialportfolioapp.domain.entities.PortfolioItem
import com.example.financialportfolioapp.domain.entities.Price
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioItemRepositoryImpl @Inject constructor(
    private val assetDao: AssetDao,
    private val stockDao: StockDao,
    private val bondDao: BondDao,
    private val cashDao: CashDao,
    private val uniqueIdDao: UniqueIdDao
) : PortfolioItemRepository {
    override suspend fun addStock(name: String, amount: Double, price: Price, dividends: Double) {
        withContext(Dispatchers.IO) {
            val newId = uniqueIdDao.generateNewId()
            val asset = AssetEntity(id = newId, name = name, portfolioItemType = ItemType.STOCK)
            assetDao.insertAsset(asset)

            val stock = StockEntity(
                id = newId,
                name = name,
                amount = amount,
                dividends = dividends,
                price = price
            )
            stockDao.insert(stock)
        }
    }

    override suspend fun addBond(
        name: String,
        amount: Double,
        price: Price,
        futurePrice: Price,
        yieldToMaturity: Double
    ) {
        withContext(Dispatchers.IO) {
            val newId = uniqueIdDao.generateNewId()
            val asset = AssetEntity(id = newId, name = name, portfolioItemType = ItemType.BOND)
            assetDao.insertAsset(asset)

            val bond = BondEntity(
                id = newId,
                name = name, price = price,
                futurePrice = futurePrice,
                yieldToMaturity = yieldToMaturity,
                amount = amount
            )
            bondDao.insert(bond)
        }
    }

    override suspend fun addCash(
        name: String,
        amount: Double,
        price: Price,
        exchangeRatioToUSD: Double
    ) {
        withContext(Dispatchers.IO) {
            val newId = uniqueIdDao.generateNewId()
            val asset = AssetEntity(id = newId, name = name, portfolioItemType = ItemType.CASH)

            Log.e("qqq", "asset $asset")
            assetDao.insertAsset(asset)

            val cash = CashEntity(
                id = newId,
                name = name,
                price = price,
                amount = amount,
                exchangeRatioToUSD = exchangeRatioToUSD
            )
            Log.e("qqq", "cash $cash")
            cashDao.insert(cash)
        }
    }

    override suspend fun getItems(): List<PortfolioItem> {
        return withContext(Dispatchers.IO) {
            Log.e("qqq", "getItems ${cashDao.getAllCash()}")
            cashDao.getAllCash() + stockDao.getAllStocks() + bondDao.getAllBonds()
        }
    }

    override suspend fun addSamples() {
        withContext(Dispatchers.IO) {
            DataSample.portfolioItemsList.forEach {
//            Log.e("qqq", "item $it")
                when (it) {
                    is Cash -> {
                        addCash(
                            name = it.name,
                            amount = it.amount,
                            price = it.price,
                            exchangeRatioToUSD = it.exchangeRatioToUSD
                        )
                    }

                    is Stock -> {
                        addStock(
                            name = it.name,
                            amount = it.amount,
                            price = it.price,
                            dividends = it.dividends
                        )
                    }

                    is Bond -> {
                        addBond(
                            name = it.name,
                            amount = it.amount,
                            price = it.price,
                            futurePrice = it.futurePrice,
                            yieldToMaturity = it.yieldToMaturity
                        )
                    }
                }
            }
        }
    }

    override suspend fun getAllCashObjects(): List<Cash> {
        return withContext(Dispatchers.IO) {
            cashDao.getAllCash()
        }
    }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            cashDao.deleteAllCash()
            stockDao.deleteAllStocks()
            bondDao.deleteAllBonds()
        }
    }

    override suspend fun getPortfolioItemById(itemId: Long): PortfolioItem? {
        return withContext(Dispatchers.IO) {
            val asset = assetDao.getAssetById(itemId)
            when (asset.type) {
                DomainItemType.STOCK -> stockDao.getStockById(itemId)
                DomainItemType.BOND -> bondDao.getBondById(itemId)
                DomainItemType.CASH -> cashDao.getCashById(itemId)
            }
        }
    }

    override suspend fun getItemById(itemId: Long): PortfolioItem? {
        return withContext(Dispatchers.IO) {
//            Log.e("qqq", "itemId ${itemId}")
            val asset = assetDao.getAssetById(itemId)
//            Log.e("qqq", "assset ${asset}")
            when (asset.type) {
                DomainItemType.STOCK -> stockDao.getStockById(itemId)
                DomainItemType.BOND -> bondDao.getBondById(itemId)
                DomainItemType.CASH -> cashDao.getCashById(itemId)
            }
        }
    }

    override suspend fun deleteItemById(itemId: Long) {
        withContext(Dispatchers.IO) {
            val asset = assetDao.getAssetById(itemId)
            when (asset.type) {
                DomainItemType.STOCK -> stockDao.deleteStockById(itemId)
                DomainItemType.BOND -> bondDao.deleteBondById(itemId)
                DomainItemType.CASH -> cashDao.deleteCashById(itemId)
            }
        }
    }
}
