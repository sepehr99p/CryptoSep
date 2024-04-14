package com.example.cryptosep.domain.usecase

import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.domain.repository.KucoinRepository
import com.example.cryptosep.domain.utils.DefaultRetryPolicy
import com.example.cryptosep.domain.utils.ResultState
import com.example.cryptosep.domain.utils.checkError
import com.example.cryptosep.domain.utils.retryWithPolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class FetchCurrencyListUseCase(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(): Flow<ResultState<List<CurrencyEntity>>> =
        flow {
            emit(kucoinRepository.fetchCurrencyList())
        }.retryWithPolicy(DefaultRetryPolicy())
            .catch { emit(checkError(it)) }
            .onStart { emit(ResultState.Loading()) }
}

