package com.example.cryptosep.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.domain.entity.TickerEntity
import com.example.cryptosep.domain.usecase.CurrencyListUseCase
import com.example.cryptosep.domain.usecase.MarketListUseCase
import com.example.cryptosep.domain.usecase.TickerListUseCase
import com.example.cryptosep.domain.utils.ResultState
import com.example.cryptosep.ui.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tickerUseCase: TickerListUseCase,
    private val currencyUseCase: CurrencyListUseCase,
    private val marketListUseCase: MarketListUseCase
) : ViewModel() {








}