package com.example.cryptosep.data.model

data class AccountResponse(
    val id: String,//accountId
    val currency: String,
    val type: String, //Account type, including main and trade
    val balance: String,//Total assets of a currency
    val available: String, //Available assets of a currency
    val holds: String//Hold assets of a currency
)