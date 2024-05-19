package com.example.cryptosep.data.repository

import com.example.cryptosep.data.mapper.candlesMapper
import com.example.cryptosep.data.mapper.currencyListMapper
import com.example.cryptosep.data.mapper.marketListMapper
import com.example.cryptosep.data.mapper.priceMapper
import com.example.cryptosep.data.mapper.serverTimeMapper
import com.example.cryptosep.data.mapper.tickerListMapper
import com.example.cryptosep.data.mapper.tickerMapper
import com.example.cryptosep.data.mapper.toResultState
import com.example.cryptosep.data.remote.KucoinApiService
import com.example.cryptosep.data.utils.ERROR_CONNECTION
import com.example.cryptosep.data.utils.NetworkConnection
import com.example.cryptosep.domain.entity.CandleEntity
import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.domain.entity.SingleTickerEntity
import com.example.cryptosep.domain.entity.TickerEntity
import com.example.cryptosep.domain.repository.KucoinRepository
import com.example.cryptosep.domain.utils.ResultState
import javax.inject.Inject

class KucoinRepositoryImpl @Inject constructor(
    private val apiService: KucoinApiService,
    private val networkConnection: NetworkConnection
) :
    KucoinRepository {

    private fun <T> noInternetResultState() =
        ResultState.Error<T>(message = ERROR_CONNECTION)

    override suspend fun fetchCurrencyList(): ResultState<List<CurrencyEntity>> {
        return if (networkConnection.isInternetOn()) {
            toResultState(apiService.fetchCurrencyList(), currencyListMapper)
        } else {
            noInternetResultState()
        }
    }

    override suspend fun fetchTicker(symbol: String): ResultState<SingleTickerEntity> {
        return if (networkConnection.isInternetOn()) {
            toResultState(apiService.fetchTicker(symbol), tickerMapper)
        } else {
            noInternetResultState()
        }
    }


    override suspend fun fetchAllTickers(): ResultState<List<TickerEntity>> {
        return if (networkConnection.isInternetOn()) {
            toResultState(apiService.fetchAllTickers(), tickerListMapper)
        } else {
            noInternetResultState()
        }
    }


    override suspend fun fetchMarketList(): ResultState<List<String>> {
        return if (networkConnection.isInternetOn()) {
            toResultState(apiService.fetchMarketList(), marketListMapper)
        } else {
            noInternetResultState()
        }
    }

    override suspend fun fetchCandles(
        interval: String,
        symbol: String
    ): ResultState<List<CandleEntity>> {
        return if (networkConnection.isInternetOn()) {
            toResultState(apiService.fetchCandles(interval, symbol), candlesMapper)
        } else {
            noInternetResultState()
        }
    }

    override suspend fun fetchServerTime(): ResultState<Long> {
        return if (networkConnection.isInternetOn()) {
            toResultState(apiService.serverTime(), serverTimeMapper)
        } else {
            noInternetResultState()
        }
    }

    override suspend fun fetchPrices(): ResultState<String> {
        return if (networkConnection.isInternetOn()) {
            toResultState(apiService.getPrices(), priceMapper)
        } else {
            noInternetResultState()
        }
    }
}