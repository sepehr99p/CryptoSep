package com.example.cryptosep.ui.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptosep.ui.theme.dimen.padding_16

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.padding(padding_16)) {
        ExchangeSelection()
        SecretKeyInsertion()
    }
}


@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}