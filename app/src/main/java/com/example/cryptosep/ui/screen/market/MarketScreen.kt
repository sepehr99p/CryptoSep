package com.example.cryptosep.ui.screen.market

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.ui.utils.DataState
import com.example.cryptosep.ui.utils.components.LoadingComponent

@Composable
fun MarketScreen() {
    val viewModel = hiltViewModel<MarketViewModel>()
    val marketListState = viewModel.marketList.collectAsState()

    when (marketListState.value) {
        is DataState.FailedState -> LoadingComponent()
        is DataState.LoadedState -> MarketListComponent(marketList = marketListState.value.data!!)
        is DataState.LoadingState -> LoadingComponent()
    }
}