package com.example.cryptosep.domain.usecase

import com.example.cryptosep.domain.entity.SingleTickerEntity
import com.example.cryptosep.domain.repository.KucoinRepository
import com.example.cryptosep.domain.utils.DefaultRetryPolicy
import com.example.cryptosep.domain.utils.ResultState
import com.example.cryptosep.domain.utils.checkError
import com.example.cryptosep.domain.utils.retryWithPolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class FetchTickerUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(symbol: String): Flow<ResultState<SingleTickerEntity>> = flow {
        emit(kucoinRepository.fetchTicker(symbol))
    }.retryWithPolicy(DefaultRetryPolicy())
        .catch { emit(checkError(it)) }
        .onStart { emit(ResultState.Loading()) }
}