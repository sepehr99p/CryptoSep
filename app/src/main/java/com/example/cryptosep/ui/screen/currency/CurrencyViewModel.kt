package com.example.cryptosep.ui.screen.currency

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.domain.usecase.CurrencyListUseCase
import com.example.cryptosep.domain.utils.ResultState
import com.example.cryptosep.ui.screen.home.HomeViewModel
import com.example.cryptosep.ui.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyUseCase: CurrencyListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "CurrencyViewModel"
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



}