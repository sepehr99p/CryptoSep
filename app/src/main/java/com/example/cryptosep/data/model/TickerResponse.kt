package com.example.cryptosep.data.model

import com.example.cryptosep.domain.entity.TickerEntity

data class TickerResponse(
    val symbol: String,
    val symbolName: String,
    val buy: String,
    val sell: String,
    val bestBidSize: String,
    val bestAskSize: String,
    val changeRate: String,
    val changePrice: String,
    val high: String,
    val low: String,
    val vol: String,
    val volValue: String,
    val last: String,
    val averagePrice: String,
    val takerFeeRate: String,
    val makerFeeRate: String,
    val takerCoefficient: String,
    val makerCoefficient: String
) {
    fun toDomainModel(): TickerEntity = TickerEntity(
        symbol = symbol,
        symbolName = symbolName,
        buy = buy,
        sell = sell,
        bestBidSize = bestBidSize,
        bestAskSize = bestAskSize,
        changeRate = changeRate,
        changePrice = changePrice,
        high = high,
        low = low,
        vol = vol,
        volValue = volValue,
        last = last,
        averagePrice = averagePrice,
        takerFeeRate = takerFeeRate,
        makerFeeRate = makerFeeRate,
        takerCoefficient = takerCoefficient,
        makerCoefficient = makerCoefficient
    )
}

