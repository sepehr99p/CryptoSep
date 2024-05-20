package com.example.cryptosep.data.remote

import com.example.cryptosep.data.model.AccountSummeryResponse
import com.example.cryptosep.data.model.AllTickersResponse
import com.example.cryptosep.data.model.BaseResponse
import com.example.cryptosep.data.model.CurrencyResponse
import com.example.cryptosep.data.model.PriceResponse
import com.example.cryptosep.data.model.SingleTickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KucoinApiService {

    @GET(currencyList)
    suspend fun fetchCurrencyList(): Response<BaseResponse<List<CurrencyResponse>>>

    @GET(fetchTicker)
    suspend fun fetchTicker(@Query("symbol") symbol: String): Response<BaseResponse<SingleTickerResponse>>

    @GET(fetchAllTickers)
    suspend fun fetchAllTickers(): Response<BaseResponse<AllTickersResponse>>

    @GET(marketList)
    suspend fun fetchMarketList(): Response<BaseResponse<List<String>>>

    @GET(candles)
    suspend fun fetchCandles(
        @Query("type") type: String,
        @Query("symbol") symbol: String
    ): Response<BaseResponse<List<List<String>>>>

    @GET(serverTime)
    suspend fun serverTime(): Response<BaseResponse<Long>>

    @GET(fetchPrices)
    suspend fun getPrices(): Response<BaseResponse<PriceResponse>>

    @GET(accountSummary)
    suspend fun getAccountSummery() : Response<BaseResponse<AccountSummeryResponse>>

}