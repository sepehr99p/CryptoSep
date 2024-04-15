package com.example.cryptosep.ui.screen.market

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.extentions.shadowBackground

@Composable
fun MarketListComponent(modifier: Modifier = Modifier, marketList: List<String>) {
    LazyColumn(modifier = modifier) {
        items(marketList) {
            MarketListItemComponent(title = it)
        }
    }
}

@Composable
fun MarketListItemComponent(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .shadowBackground(),
        text = title,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Preview
@Composable
fun MarketListItemComponentPreview() {
    MarketListItemComponent(title = "Title")
}


@Composable
private fun MarketListComponentPreview() {
    MarketListComponent(marketList = listOf())
}