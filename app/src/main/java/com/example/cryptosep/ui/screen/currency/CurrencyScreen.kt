package com.example.cryptosep.ui.screen.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cryptosep.ui.screen.currency.component.CurrencyListComponent
import com.example.cryptosep.ui.utils.components.LoadingComponent
import com.example.cryptosep.ui.utils.DataState

@Composable
fun CurrencyScreen(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<CurrencyViewModel>()
    val currencyState = viewModel.currencyList.collectAsState()


    when(currencyState.value) {
        is DataState.FailedState -> LoadingComponent() // todo : impl later
        is DataState.LoadedState -> CurrencyListComponent(currencyEntities = currencyState.value.data!!)
        is DataState.LoadingState -> LoadingComponent()
    }
}


