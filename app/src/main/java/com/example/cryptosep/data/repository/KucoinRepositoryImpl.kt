package com.example.cryptosep.data.repository

import com.example.cryptosep.data.mapper.candlesMapper
import com.example.cryptosep.data.mapper.currencyListMapper
import com.example.cryptosep.data.mapper.marketListMapper
import com.example.cryptosep.data.mapper.serverTimeMapper
import com.example.cryptosep.data.mapper.tickerListMapper
import com.example.cryptosep.data.mapper.tickerMapper
import com.example.cryptosep.data.mapper.toResultState
import com.example.cryptosep.data.remote.KucoinApiService
import com.example.cryptosep.domain.entity.CandleEntity
import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.domain.entity.SingleTickerEntity
import com.example.cryptosep.domain.entity.TickerEntity
import com.example.cryptosep.domain.repository.KucoinRepository
import com.example.cryptosep.domain.utils.ResultState
import javax.inject.Inject

class KucoinRepositoryImpl @Inject constructor(private val apiService: KucoinApiService) :
    KucoinRepository {
    override suspend fun fetchCurrencyList(): ResultState<List<CurrencyEntity>> =
        toResultState(apiService.fetchCurrencyList(), currencyListMapper)

    override suspend fun fetchTicker(symbol: String): ResultState<SingleTickerEntity> =
        toResultState(apiService.fetchTicker(symbol), tickerMapper)

    override suspend fun fetchAllTickers(): ResultState<List<TickerEntity>> =
        toResultState(apiService.fetchAllTickers(), tickerListMapper)

    override suspend fun fetchMarketList(): ResultState<List<String>> =
        toResultState(apiService.fetchMarketList(), marketListMapper)

    override suspend fun fetchCandles(
        interval: String,
        symbol: String
    ): ResultState<List<CandleEntity>> =
        toResultState(apiService.fetchCandles(interval, symbol), candlesMapper)

    override suspend fun fetchServerTime(): ResultState<Long> =
        toResultState(apiService.serverTime(), serverTimeMapper)
}