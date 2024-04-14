package com.example.cryptosep.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.ui.screen.home.component.common.LoadingComponent
import com.example.cryptosep.ui.screen.home.component.currency.CurrencyListComponent
import com.example.cryptosep.ui.utils.DataState

@Composable
fun HomeScreen() {
    val viewModel : HomeViewModel = hiltViewModel()
    val tickerState = viewModel.tickerList.collectAsState()
    val currencyState = viewModel.currencyList.collectAsState()
    val marketState = viewModel.marketList.collectAsState()


    when(currencyState.value) {
        is DataState.FailedState -> LoadingComponent() // todo : impl later
        is DataState.LoadedState -> CurrencyListComponent(currencyEntities = currencyState.value.data!!)
        is DataState.LoadingState -> LoadingComponent()
    }

}