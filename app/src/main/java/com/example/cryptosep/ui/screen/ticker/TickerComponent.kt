package com.example.cryptosep.ui.screen.ticker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptosep.domain.entity.TickerEntity
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_4
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.extentions.shadowBackground

@Composable
fun TickerListComponent(modifier: Modifier = Modifier, tickerList: List<TickerEntity>) {
    LazyColumn(modifier = modifier) {
        items(tickerList) {
            TickerListItemComponent(tickerEntity = it)
        }
    }
}

@Composable
private fun TickerListItemComponent(modifier: Modifier = Modifier, tickerEntity: TickerEntity) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .shadowBackground()
            .padding(horizontal = padding_4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = tickerEntity.symbol, color = MaterialTheme.colorScheme.onPrimary)
        Column(
            modifier = Modifier.padding(horizontal = padding_8)
        ) {
            Text(
                text = tickerEntity.high,
                color = Color.Green,
                style = TextStyle().copy(fontWeight = FontWeight.W600)
            )
            Text(
                text = tickerEntity.last,
                color = MaterialTheme.colorScheme.onPrimary,
                style = TextStyle().copy(fontWeight = FontWeight.W600)
            )
            Text(
                text = tickerEntity.low,
                color = Color.Red,
                style = TextStyle().copy(fontWeight = FontWeight.W600)
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "price change : ${tickerEntity.changePrice}",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

val mockTickerEntity = TickerEntity(
    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
)

@Preview
@Composable
private fun TickerListItemComponentPreview() {
    TickerListItemComponent(tickerEntity = mockTickerEntity)
}

@Preview
@Composable
private fun TickerListComponentPreview() {
    TickerListComponent(tickerList = listOf())
}