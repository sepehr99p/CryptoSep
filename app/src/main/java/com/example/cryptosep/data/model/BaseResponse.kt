package com.example.cryptosep.data.model

data class BaseResponse<T>(
    val code : String,
    val data : T
)
