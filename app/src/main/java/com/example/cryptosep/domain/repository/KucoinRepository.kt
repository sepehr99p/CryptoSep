package com.example.cryptosep.domain.repository

import com.example.cryptosep.domain.entity.CandleEntity
import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.domain.entity.SingleTickerEntity
import com.example.cryptosep.domain.entity.TickerEntity
import com.example.cryptosep.domain.utils.ResultState

interface KucoinRepository {

    suspend fun fetchCurrencyList() : ResultState<List<CurrencyEntity>>

    suspend fun fetchTicker(symbol : String) : ResultState<SingleTickerEntity>

    suspend fun fetchAllTickers() : ResultState<List<TickerEntity>>

    suspend fun fetchMarketList() : ResultState<List<String>>

    suspend fun fetchCandles(interval : String, symbol: String) : ResultState<List<CandleEntity>>

    suspend fun fetchServerTime() : ResultState<Long>

    suspend fun fetchPrices() : ResultState<String>

}