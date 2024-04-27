package com.example.cryptosep.ui.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.ui.theme.dimen.padding_16

@Composable
fun SettingsScreen() {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val selectedExchange = remember { mutableStateOf("Kucoin")}

    Column(modifier = Modifier.padding(padding_16)) {
        ExchangeSelection(selectedExchange = selectedExchange, list = viewModel.exchanges)
        SecretKeyInsertion()
    }
}


@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}