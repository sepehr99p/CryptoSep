package com.example.cryptosep.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.ui.screen.home.component.currency.CurrencyListComponent

@Composable
fun HomeScreen() {
    val viewModel : HomeViewModel = hiltViewModel()
    val tickerState = viewModel.tickerList.collectAsState()
    val currencyState = viewModel.currencyList.collectAsState()
    val marketState = viewModel.marketList.collectAsState()

    currencyState.value.data?.let {
        CurrencyListComponent(currencyEntities = it)
    }

}