package com.example.cryptosep.data.mapper

interface MapperCallback<From, To> {
    fun map(value: From): To
}