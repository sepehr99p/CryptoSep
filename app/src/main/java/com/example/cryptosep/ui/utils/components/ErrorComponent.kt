package com.example.cryptosep.ui.utils.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptosep.ui.theme.dimen.padding_4
import com.example.cryptosep.ui.theme.dimen.padding_8

@Composable
fun ErrorComponent(message: String, callback: (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding_8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = message,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row(modifier = Modifier.clickable {
            callback?.invoke()
        }) {
            Text(
                modifier = Modifier
                    .padding(horizontal = padding_4),
                text = "Retry",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "retry",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun ErrorComponentPreview() {
    ErrorComponent("some error")
}