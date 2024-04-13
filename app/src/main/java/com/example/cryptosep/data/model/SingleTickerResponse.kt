package com.example.cryptosep.data.model

data class SingleTickerResponse(
    val sequence: String,
    val price: String,
    val size: String,
    val bestAsk: String,
    val bestAskSize: String,
    val bestBid: String,
    val bestBidSize: String,
    val time: Long
)
