package com.example.cryptosep.data.model

import com.example.cryptosep.domain.entity.CurrencyEntity

data class CurrencyResponse(
    val currency: String,
    val name: String,
    val fullName: String,
    val precision: Int
) {
    fun toDomainModel(): CurrencyEntity = CurrencyEntity(
        currency = currency,
        name = name,
        fullName = fullName,
        precision = precision
    )
}
