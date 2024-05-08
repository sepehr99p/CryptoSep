package com.example.cryptosep.ui.screen.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cryptosep.ui.screen.currency.component.CurrencyListComponent
import com.example.cryptosep.ui.utils.UiState
import com.example.cryptosep.ui.utils.components.LoadingComponent

@Composable
fun CurrencyScreen(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<CurrencyViewModel>()
    val currencyState = viewModel.currencyList.collectAsState()


    when (currencyState.value) {
        is UiState.Failed -> LoadingComponent()
        is UiState.Success -> CurrencyListComponent(currencyEntities = (currencyState.value as UiState.Success).data!!)
        is UiState.Loading -> LoadingComponent()
        is UiState.Initialize -> {

        }
    }
}


