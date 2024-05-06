package com.example.cryptosep.ui.screen.candles

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.R
import com.example.cryptosep.domain.entity.CandleEntity
import com.example.cryptosep.ui.theme.dimen.border_2
import com.example.cryptosep.ui.theme.dimen.corner_16
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.DataState
import com.example.cryptosep.ui.utils.components.ErrorComponent
import com.example.cryptosep.ui.utils.components.LoadingComponent
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.fullWidth
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberCandlestickCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.marker.rememberDefaultCartesianMarker
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.HorizontalLayout
import com.patrykandpatrick.vico.core.cartesian.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.candlestickSeries
import com.patrykandpatrick.vico.core.common.component.TextComponent

@Composable
fun CandlesScreen(
    viewModel: CandlesViewModel = hiltViewModel()
) {
    val candlesState = viewModel.candles.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val expanded = remember {
            mutableStateOf(false)
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding_8)
                .border(
                    width = border_2,
                    shape = RoundedCornerShape(corner_16),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                .padding(padding_8)
                .clickable {
                    expanded.value = true
                },
            text = viewModel.interval.value ?: "",
            color = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }) {
            viewModel.intervalList.forEach {
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = it.uppercase()) },
                    colors = MenuDefaults.itemColors()
                        .copy(textColor = MaterialTheme.colorScheme.onPrimary),
                    onClick = {
                        viewModel.interval.value = it
                        expanded.value = false
                        viewModel.fetchCandles()
                    })
            }
        }

        when (candlesState.value) {
            is DataState.FailedState -> {
                ErrorComponent(message = stringResource(id = R.string.error_candles)) {
                    viewModel.fetchCandles()
                }
            }

            is DataState.LoadedState -> {
                CandlesList(
                    modifier = Modifier.weight(1f),
                    candles = candlesState.value.data ?: listOf()
                )
            }

            is DataState.LoadingState -> {
                LoadingComponent()
            }
        }
    }
}


@Composable
private fun CandleChart(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier,
) {
    val marker = rememberDefaultCartesianMarker(label = TextComponent.build())
    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberCandlestickCartesianLayer(),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                guideline = null,
                itemPlacer = remember {
                    AxisItemPlacer.Horizontal.default(
                        spacing = 3,
                        addExtremeLabelPadding = true
                    )
                },
            ),
        ),
        modelProducer = modelProducer,
        marker = marker,
        modifier = modifier,
        horizontalLayout = HorizontalLayout.fullWidth(),
    )
}


@SuppressLint("RestrictedApi")
@Composable
fun CandlesList(modifier: Modifier = Modifier, candles: List<CandleEntity>) {
    val modelProducer = remember { CartesianChartModelProducer.build() }
    modelProducer.tryRunTransaction {
        candlestickSeries(
            x = candles.map { it.time.toInt() }, // todo : convert it later
            opening = candles.map { it.opening.toFloat() },
            closing = candles.map { it.closing.toFloat() },
            low = candles.map { it.lowest.toFloat() },
            high = candles.map { it.highest.toFloat() },
        )
    }
    CandleChart(modifier = modifier, modelProducer = modelProducer)
}

@Composable
fun CandleListItem(modifier: Modifier = Modifier, candle: CandleEntity) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(shape = RoundedCornerShape(corner_8))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(padding_8)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = candle.amount,
            color = Color.Green
        )

        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = candle.time,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Preview
@Composable
private fun CandleListItemPreview() {
    CandleListItem(
        candle = CandleEntity(
            time = "time",
            opening = "opening",
            closing = "closing",
            highest = "highest",
            lowest = "lowest",
            volume = "volume",
            amount = "amount"
        )
    )
}

@Preview
@Composable
private fun CandlesListPreview() {
    CandlesList(candles = listOf())
}

@Preview
@Composable
private fun CandlesScreenPreview() {
    CandlesScreen()
}