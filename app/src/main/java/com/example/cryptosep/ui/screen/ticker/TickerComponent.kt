package com.example.cryptosep.ui.screen.ticker

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptosep.R
import com.example.cryptosep.domain.entity.SingleTickerEntity
import com.example.cryptosep.domain.entity.TickerEntity
import com.example.cryptosep.ui.theme.Bold_12
import com.example.cryptosep.ui.theme.Medium_10
import com.example.cryptosep.ui.theme.Medium_12
import com.example.cryptosep.ui.theme.Regular_10
import com.example.cryptosep.ui.theme.dimen.border_1
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_2
import com.example.cryptosep.ui.theme.dimen.padding_4
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.extentions.shadowBackground

@Composable
fun TickerListComponent(
    modifier: Modifier = Modifier,
    tickerList: List<TickerEntity>,
    onTickerClicked: ((symbol: String) -> Unit)? = null
) {
    LazyColumn(modifier = modifier) {
        items(tickerList.sortedBy { it.bestAskSize.toFloat() }) {
            TickerListItemComponent(tickerEntity = it, onTickerClicked = onTickerClicked)
        }
    }
}

@Composable
internal fun TickerListItemComponent(
    modifier: Modifier = Modifier,
    tickerEntity: TickerEntity,
    onTickerClicked: ((symbol: String) -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(horizontal = padding_8, vertical = padding_2),
            text = tickerEntity.symbol,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding_8)
                .clip(RoundedCornerShape(corner_8))
                .shadowBackground()
                .padding(horizontal = padding_4)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onTickerClicked?.invoke(tickerEntity.symbol)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = padding_8)
                    .weight(1f)
            ) {
                TickerPriceItemComponent(
                    modifier = Modifier.weight(1f),
                    value = tickerEntity.high,
                    title = stringResource(id = R.string.high),
                    color = Color.Green
                )
                TickerPriceItemComponent(
                    modifier = Modifier.weight(1f),
                    value = tickerEntity.last,
                    title = stringResource(id = R.string.last)
                )
                TickerPriceItemComponent(
                    modifier = Modifier.weight(1f),
                    value = tickerEntity.low,
                    title = stringResource(id = R.string.low),
                    color = Color.Red
                )
            }
            Box(modifier = Modifier) {
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = "${tickerEntity.changeRate} %",
                    color = if (tickerEntity.changePrice.contains("-")) {
                        Color.Red
                    } else {
                        Color.Green
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
fun TickerPriceItemComponent(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    value: String,
    title: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = padding_4, vertical = padding_2),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
            style = Regular_10
        )
        Text(
            modifier = Modifier.padding(horizontal = padding_4, vertical = padding_2),
            text = value,
            color = color.copy(alpha = 0.7f),
            style = Medium_12,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

val mockTickerEntity = TickerEntity(
    "symbol",
    "name",
    "buy",
    "sell",
    "",
    "",
    "234",
    "1234134",
    "1234",
    "1234",
    "",
    "",
    "1234",
    "1234",
    "",
    "",
    "",
    "",
)

@Composable
fun SingleTickerComponent(
    modifier: Modifier = Modifier,
    singleTickerEntity: SingleTickerEntity,
    onTickerClicked: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .border(
                shape = RoundedCornerShape(corner_8),
                width = border_1,
                color = MaterialTheme.colorScheme.secondary
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding_8)
                .clip(RoundedCornerShape(corner_8))
                .shadowBackground()
                .padding(horizontal = padding_4)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onTickerClicked?.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = padding_8)
                    .weight(1f)
            ) {
                TickerPriceItemComponent(
                    modifier = Modifier.weight(1f),
                    value = singleTickerEntity.price,
                    title = stringResource(id = R.string.price),
                    color = Color.Green
                )
                TickerPriceItemComponent(
                    modifier = Modifier.weight(1f),
                    value = singleTickerEntity.size,
                    title = stringResource(id = R.string.size)
                )
            }
        }
    }

}

@Composable
fun TickerScreenTopBar(callback: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(shape = RoundedCornerShape(corner_8))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(padding_8)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                callback.invoke()
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = stringResource(id = R.string.ticker),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            painter = rememberVectorPainter(image = Icons.Default.Search),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun TickerScreenTopBarPreview() {
    TickerScreenTopBar {}
}

@Preview
@Composable
private fun SingleTickerComponentPreview() {
    SingleTickerComponent(
        singleTickerEntity = SingleTickerEntity(
            sequence = "sequence",
            price = "price",
            size = "size",
            bestAsk = "best ask price",
            bestBidSize = "best bid price",
            bestBid = "best bid",
            bestAskSize = "best ask size",
            time = 9283759084L
        )
    ) {

    }
}

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

@Preview
@Composable
private fun TickerPriceItemComponentPreview() {
    TickerPriceItemComponent(
        title = "title",
        value = "value"
    )
}