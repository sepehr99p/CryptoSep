package com.example.cryptosep.ui.screen.ticker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptosep.domain.entity.TickerEntity
import com.example.cryptosep.domain.usecase.FetchTickerUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val tickerUseCase: FetchTickerUseCase,
    private val tickerListUseCase: TickerListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "TickerViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)


    private val _tickerList =
        MutableStateFlow<DataState<List<TickerEntity>?>>(DataState.LoadingState(null))
    val tickerList: StateFlow<DataState<List<TickerEntity>?>> = _tickerList

    init {
        fetchTickerList()
    }


    private fun fetchTickerList() {
        scope.launch {
            tickerListUseCase.invoke().catch {
                _tickerList.value = DataState.FailedState(data = null)
            }.collect {
                when (it) {
                    is ResultState.Error -> {
                        _tickerList.value = DataState.FailedState(data = null)
                    }
                    is ResultState.Loading -> {
                        _tickerList.value = DataState.LoadingState(data = null)
                    }

                    is ResultState.Success -> {
                        _tickerList.value = DataState.LoadedState(data = it.data)
                    }
                }
            }
        }
    }


}