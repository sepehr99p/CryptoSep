package com.example.cryptosep.ui.screen.ticker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.ui.utils.DataState
import com.example.cryptosep.ui.utils.components.LoadingComponent

@Composable
fun TickerScreen() {
    val viewModel = hiltViewModel<TickerViewModel>()
    val tickerListState = viewModel.tickerList.collectAsState()

    when(tickerListState.value) {
        is DataState.FailedState -> LoadingComponent() // todo : impl later
        is DataState.LoadedState -> TickerListComponent(tickerList = tickerListState.value.data!!)
        is DataState.LoadingState -> LoadingComponent()
    }


}