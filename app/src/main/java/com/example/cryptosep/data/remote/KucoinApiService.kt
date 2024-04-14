package com.example.cryptosep.data.remote

import com.example.cryptosep.data.model.AllTickersResponse
import com.example.cryptosep.data.model.BaseResponse
import com.example.cryptosep.data.model.CurrencyResponse
import com.example.cryptosep.data.model.SingleTickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KucoinApiService {

    @GET("/api/v3/currencies")
    suspend fun fetchCurrencyList(): Response<BaseResponse<List<CurrencyResponse>>>

    @GET("/api/v1/market/orderbook/level1")
    suspend fun fetchTicker(@Query("symbol") symbol: String): Response<BaseResponse<SingleTickerResponse>>

    @GET("/api/v1/market/allTickers")
    suspend fun fetchAllTickers(): Response<BaseResponse<AllTickersResponse>>

    @GET("/api/v1/markets")
    suspend fun fetchMarketList() : Response<BaseResponse<List<String>>>

}