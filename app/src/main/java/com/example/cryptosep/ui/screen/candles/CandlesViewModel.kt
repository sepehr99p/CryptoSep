package com.example.cryptosep.ui.screen.candles

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptosep.domain.entity.CandleEntity
import com.example.cryptosep.domain.usecase.CandlesUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandlesViewModel @Inject constructor(
    private val candlesUseCase: CandlesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val TAG = "CandlesViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)

    private val _candles =
        MutableStateFlow<DataState<List<CandleEntity>?>>(DataState.LoadingState(null))
    val candles: StateFlow<DataState<List<CandleEntity>?>> = _candles

    private val navigationParam = savedStateHandle.get<String>("symbol")

    val interval: StateFlow<String> = MutableStateFlow("1day")

    init {
        fetchCandles()
    }

    fun fetchCandles(symbol: String = navigationParam ?: "") {
        scope.launch {
            candlesUseCase.invoke(interval = interval.value, symbol = symbol).catch {
                _candles.value = DataState.FailedState(data = null)
            }.collect {
                when (it) {
                    is ResultState.Error -> {
                        _candles.value = DataState.FailedState(data = null)
                    }

                    is ResultState.Loading -> {
                        _candles.value = DataState.LoadingState(data = null)
                    }

                    is ResultState.Success -> {
                        _candles.value = DataState.LoadedState(data = it.data)
                    }
                }
            }

        }
    }


}