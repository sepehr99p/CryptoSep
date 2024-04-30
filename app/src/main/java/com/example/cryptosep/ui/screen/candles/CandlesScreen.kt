package com.example.cryptosep.ui.screen.candles

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.domain.entity.CandleEntity
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
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianLayerModel
import com.patrykandpatrick.vico.core.cartesian.data.RandomCartesianModelGenerator
import com.patrykandpatrick.vico.core.cartesian.layer.CartesianLayer
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.data.ExtraStore

@Composable
fun CandlesScreen(
    viewModel: CandlesViewModel = hiltViewModel()
) {
    val candlesState = viewModel.candles.collectAsState()
    when (candlesState.value) {
        is DataState.FailedState -> {
            ErrorComponent(message = "Failed to fetch data")
        }

        is DataState.LoadedState -> {
            CandlesList(candles = candlesState.value.data ?: listOf())
        }

        is DataState.LoadingState -> {
            LoadingComponent()
        }
    }
}


@Composable
private fun ComposeChart10(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier,
) {
    val marker2 = rememberDefaultCartesianMarker(label = TextComponent.build())
//    val marker = rememberMarker(showIndicator = false)
    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberCandlestickCartesianLayer(),
            startAxis = rememberStartAxis(),
            bottomAxis =
            rememberBottomAxis(
                guideline = null,
                itemPlacer =
                remember {
                    AxisItemPlacer.Horizontal.default(
                        spacing = 3,
                        addExtremeLabelPadding = true
                    )
                },
            ),
        ),
        modelProducer = modelProducer,
        marker = marker2,
        modifier = modifier,
        horizontalLayout = HorizontalLayout.fullWidth(),
    )
}


@SuppressLint("RestrictedApi")
@Composable
fun CandlesList(modifier: Modifier = Modifier, candles: List<CandleEntity>) {
    val modelProducer = remember { CartesianChartModelProducer.build() }
    candles.forEach {
        modelProducer.tryRunTransaction {
            add(RandomCartesianModelGenerator.getRandomCandlestickLayerModelPartial())
        }
    }
    ComposeChart10(modifier = Modifier, modelProducer = modelProducer)

//    LazyColumn(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(padding_8)
//    ) {
//        candles.forEach {
//            item {
//                CandleListItem(candle = it)
//            }
//        }
//    }
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
//    CandleListItem()
}

@Preview
@Composable
private fun CandlesListPreview() {
    CandlesList(candles = listOf())
}