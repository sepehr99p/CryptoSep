package com.example.cryptosep.ui.screen.market

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptosep.domain.usecase.MarketListUseCase
import com.example.cryptosep.domain.utils.ResultState
import com.example.cryptosep.ui.utils.DataState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarketViewModel @Inject constructor(
    private val marketListUseCase: MarketListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MarketViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)

    private val _marketList =
        MutableStateFlow<DataState<List<String>?>>(DataState.LoadingState(null))
    val marketList: StateFlow<DataState<List<String>?>> = _marketList

    init {
        fetchMarketList()
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

}