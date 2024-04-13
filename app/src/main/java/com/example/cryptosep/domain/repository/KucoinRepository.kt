package com.example.cryptosep.domain.repository

interface KucoinRepository {

    suspend fun fetchCurrencyList()

    suspend fun fetchTicker(symbol : String)

    suspend fun fetchAllTickers()

}