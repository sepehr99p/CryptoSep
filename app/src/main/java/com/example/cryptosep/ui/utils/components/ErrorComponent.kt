package com.example.cryptosep.ui.utils.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptosep.ui.theme.dimen.padding_8

@Composable
fun ErrorComponent(message: String) {
    Box(modifier = Modifier.fillMaxWidth().padding(padding_8)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun ErrorComponentPreview() {
    ErrorComponent("some error")
}