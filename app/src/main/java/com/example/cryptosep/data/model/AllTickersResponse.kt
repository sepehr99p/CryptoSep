package com.example.cryptosep.data.model

data class AllTickersResponse(
    val time : Long,
    val ticker : List<TickerResponse>
)
