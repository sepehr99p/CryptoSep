package com.example.cryptosep.domain.entity

data class CurrencyEntity(
    val currency : String,
    val name : String,
    val fullName : String,
    val precision : Int
)
