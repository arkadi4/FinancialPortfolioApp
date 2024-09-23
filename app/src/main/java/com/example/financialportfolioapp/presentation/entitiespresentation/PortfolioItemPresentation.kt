package com.example.financialportfolioapp.presentation.entitiespresentation

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.PortfolioItem
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.entities.Stock
//import com.example.financialportfolioapp.presentation.portfoliolist.rv.BasePortfolioListViewHolder
//import com.example.financialportfolioapp.presentation.portfoliolist.rv.BondViewHolder
//import com.example.financialportfolioapp.presentation.portfoliolist.rv.CashViewHolder
//import com.example.financialportfolioapp.presentation.portfoliolist.rv.StockViewHolder

//object TypesFactory {
//    @Composable
//    fun Holder(type: String, item: PortfolioItemUiModel) {
//        return when (type) {
////            "cash" -> {
////                CashItem(cashUiModel = item as CashUiModel)
////            }
////            "stock" -> StockItem(stockUiModel = item as StockUiModel)
////            "bond" -> BondItem(bondUiModel = item as BondUiModel)
////            else -> throw RuntimeException("Illegal view type")
//        }
//    }
//}

//sealed class PortfolioItemUiModel {
//    abstract val item: PortfolioItemInterface
//    abstract fun type(): String
//    abstract fun getId(): Int
//}

//@Composable
//fun AbstractItem(
//    item: PortfolioItemInterface
//) {
//    Row(
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(8.dp)
//    ) {
//        Text(text = item.name)
//        Button(onClick = { /*TODO*/ }) {
//            Text("details")
//        }
//    }
//}

//data class CashUiModel(val cash: Cash) : PortfolioItemUiModel() {
//    override val item = cash
//    override fun type() = "cash"
//    override fun getId(): Int = cash.id
//}



//data class StockUiModel(val stock: Stock) : PortfolioItemUiModel() {
//    override val item = stock
//    override fun type() = "stock"
//    override fun getId(): Int = stock.id
//}

//@Composable
//fun StockItem(
//    stockUiModel: StockUiModel
//) {
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .fillMaxHeight(0.2f)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(8.dp)
//        ) {
//            Text(text = stockUiModel.stock.name)
//            Button(onClick = { /*TODO*/ }) {
//                Text("details")
//            }
//        }
//    }
//}

//data class BondUiModel(val bond: Bond) : PortfolioItemUiModel() {
//    override val item = bond
//    override fun type() = "bond"
//    override fun getId(): Int = bond.id
//}

//@Composable
//fun BondItem(
//    bondUiModel: BondUiModel
//) {
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .fillMaxHeight(0.2f)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(8.dp)
//        ) {
//            Text(text = bondUiModel.bond.name)
//            Button(onClick = { /*TODO*/ }) {
//                Text("details")
//            }
//        }
//    }
//}


//object TypesFactory {
//    fun holder(type: Int, view: View): BasePortfolioListViewHolder<*> {
//        return when (type) {
//            R.layout.item_cash_portfolio_list -> CashViewHolder(view)
//            R.layout.item_stock_portfolio_list -> StockViewHolder(view)
//            R.layout.item_bond_portfolio_list -> BondViewHolder(view)
//            else -> throw RuntimeException("Illegal view type")
//        }
//    }
//}
//
//abstract class PortfolioItemUiModel {
//    abstract fun type(): Int
//    abstract fun getId(): Int
//}
//
//data class CashUiModel(val cash: Cash) : PortfolioItemUiModel() {
//    override fun type() = R.layout.item_cash_portfolio_list
//    override fun getId(): Int = cash.id
//}
//
//data class StockUiModel(val stock: Stock) : PortfolioItemUiModel() {
//    override fun type() = R.layout.item_stock_portfolio_list
//    override fun getId(): Int = stock.id
//}
//
//data class BondUiModel(val bond: Bond) : PortfolioItemUiModel() {
//    override fun type() = R.layout.item_bond_portfolio_list
//    override fun getId(): Int = bond.id
//}
