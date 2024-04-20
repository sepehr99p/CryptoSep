package com.example.cryptosep.ui.screen.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExchangeSelection(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = "Select exchange",
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = "drop down to be impl",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun ExchangeSelectionPreview() {
    ExchangeSelection()
}

@Composable
fun SecretKeyInsertion() {

}

@Preview
@Composable
private fun SecretKeyInsertionPreview() {
    SecretKeyInsertion()
}