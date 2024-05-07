package com.example.cryptosep.ui.screen.ticker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.example.cryptosep.domain.entity.TickerEntity

@Stable
interface TickerUiState {
    val name : String
    fun sort(tickerEntity: TickerEntity) : Float?
}

class TickerBidState : TickerUiState {
    override val name: String
        get() = "Bid"

    override fun sort(tickerEntity: TickerEntity): Float? =
        tickerEntity.bestBidSize.takeIf { it.isNotEmpty() }?.toFloat()
}

class TickerChangeRateState : TickerUiState {
    override val name: String
        get() = "Change rate"

    override fun sort(tickerEntity: TickerEntity): Float? =
        tickerEntity.bestBidSize.takeIf { it.isNotEmpty() }?.toFloat()
}

@Composable
fun rememberTickerSortByBidState() : TickerUiState = remember {
    TickerBidState()
}

@Composable
fun rememberTickerSortByChangeRateState() : TickerUiState = remember {
    TickerChangeRateState()
}