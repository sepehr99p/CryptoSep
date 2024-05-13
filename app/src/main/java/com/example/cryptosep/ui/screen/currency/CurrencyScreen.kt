package com.example.cryptosep.ui.screen.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.domain.entity.CurrencyEntity
import com.example.cryptosep.ui.screen.currency.component.CurrencyListComponent
import com.example.cryptosep.ui.utils.UiState
import com.example.cryptosep.ui.utils.components.LoadingComponent

@Composable
fun CurrencyScreen() {
    val viewModel = hiltViewModel<CurrencyViewModel>()
    val currencyState = viewModel.currencyList.collectAsState()


    when (currencyState.value) {
        is UiState.Failed -> LoadingComponent()
        is UiState.Success -> CurrencyListComponent(currencyEntities = (currencyState.value as UiState.Success).data!!)
        is UiState.Loading -> LoadingComponent()
        is UiState.Initialize -> {}
    }
}

val mockCurrencyEntity = CurrencyEntity(
    currency = "currency",
    name = "name",
    fullName = "full name",
    precision = 24
)

@Preview
@Composable
private fun CurrencyScreenPreview() {
    CurrencyScreen()
}

@Preview
@Composable
private fun CurrencyListComponentPreview(modifier: Modifier = Modifier) {
    CurrencyListComponent(
        currencyEntities = listOf(
            mockCurrencyEntity,
            mockCurrencyEntity,
            mockCurrencyEntity,
            mockCurrencyEntity
        )
    )
}


