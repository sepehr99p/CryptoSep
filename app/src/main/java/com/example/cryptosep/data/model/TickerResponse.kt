package com.example.cryptosep.data.model

data class TickerResponse(
    val symbol : String,
    val symbolName : String,
    val buy : String,
    val sell : String,
    val bestBidSize : String,
    val bestAskSize : String,
    val changeRate : String,
    val changePrice : String,
    val high : String,
    val low : String,
    val vol : String,
    val volValue : String,
    val last : String,
    val averagePrice : String,
    val takerFeeRate : String,
    val makerFeeRate : String,
    val takerCoefficient : String,
    val makerCoefficient : String
)
