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

class HomeViewModel @Inject constructor(
    private val tickerUseCase: TickerListUseCase,
    private val currencyUseCase: CurrencyListUseCase,
    private val marketListUseCase: MarketListUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)

    private val _currencyList =
        MutableStateFlow<DataState<List<CurrencyEntity>?>>(DataState.LoadingState(null))
    val currencyList: StateFlow<DataState<List<CurrencyEntity>?>> = _currencyList

    private val _tickerList =
        MutableStateFlow<DataState<List<TickerEntity>?>>(DataState.LoadingState(null))
    val tickerList: StateFlow<DataState<List<TickerEntity>?>> = _tickerList

    private val _marketList =
        MutableStateFlow<DataState<List<String>?>>(DataState.LoadingState(null))
    val marketList: StateFlow<DataState<List<String>?>> = _marketList

    init {
        fetchCurrencyList()
        fetchMarketList()
        fetchTickerList()
    }

    private fun fetchCurrencyList() {
        scope.launch {
            currencyUseCase.invoke().catch {
                _currencyList.value = DataState.FailedState(data = null)
            }.collect {
                when (it) {
                    is ResultState.Error -> _currencyList.value = DataState.FailedState(data = null)
                    is ResultState.Loading -> _currencyList.value =
                        DataState.LoadingState(data = null)

                    is ResultState.Success -> _currencyList.value =
                        DataState.LoadedState(data = it.data)
                }
            }
        }
    }

    private fun fetchMarketList() {
        scope.launch {
            marketListUseCase.invoke().catch {
                _marketList.value = DataState.FailedState(data = null)
            }.collect {
                when (it) {
                    is ResultState.Error -> _marketList.value = DataState.FailedState(data = null)
                    is ResultState.Loading -> _marketList.value =
                        DataState.LoadingState(data = null)

                    is ResultState.Success -> _marketList.value =
                        DataState.LoadedState(data = it.data)
                }
            }
        }
    }

    private fun fetchTickerList() {
        scope.launch {
            tickerUseCase.invoke().catch {
                _tickerList.value = DataState.FailedState(data = null)
            }.collect {
                when (it) {
                    is ResultState.Error -> _tickerList.value = DataState.FailedState(data = null)
                    is ResultState.Loading -> _tickerList.value =
                        DataState.LoadingState(data = null)

                    is ResultState.Success -> _tickerList.value =
                        DataState.LoadedState(data = it.data)
                }
            }
        }
    }


}