package com.example.cryptosep.data.repository

import com.example.cryptosep.domain.repository.KucoinRepository

class KucoinRepositoryImpl : KucoinRepository {
    override suspend fun fetchCurrencyList() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTicker(symbol: String) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAllTickers() {
        TODO("Not yet implemented")
    }
}