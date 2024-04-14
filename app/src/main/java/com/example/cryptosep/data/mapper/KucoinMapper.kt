package com.example.cryptosep.data.mapper

import com.example.cryptosep.data.model.AllTickersResponse
import com.example.cryptosep.data.model.BaseResponse
import com.example.cryptosep.data.model.CurrencyResponse
import com.example.cryptosep.data.model.SingleTickerResponse
import com.example.cryptosep.data.model.TickerResponse
import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.domain.entity.SingleTickerEntity
import com.example.cryptosep.domain.entity.TickerEntity

val currencyListMapper =
    object : MapperCallback<BaseResponse<List<CurrencyResponse>>, List<CurrencyEntity>> {
        override fun map(value: BaseResponse<List<CurrencyResponse>>): List<CurrencyEntity> {
            val result = arrayListOf<CurrencyEntity>()
            value.data.forEach {
                result.add(it.toDomainModel())
            }
            return result
        }
    }

val tickerMapper = object : MapperCallback<BaseResponse<SingleTickerResponse>, SingleTickerEntity> {
    override fun map(value: BaseResponse<SingleTickerResponse>): SingleTickerEntity {
        return value.data.toDomainModel()
    }
}

val tickerListMapper =
    object : MapperCallback<BaseResponse<AllTickersResponse>, List<TickerEntity>> {
        override fun map(value: BaseResponse<AllTickersResponse>): List<TickerEntity> {
            val result = arrayListOf<TickerEntity>()
            value.data.ticker.forEach {
                result.add(it.toDomainModel())
            }
            return result
        }
    }

val marketListMapper = object : MapperCallback<BaseResponse<List<String>>, List<String>> {
    override fun map(value: BaseResponse<List<String>>): List<String> = value.data

}