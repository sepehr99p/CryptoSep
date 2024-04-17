package com.example.cryptosep.ui.screen.ticker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.DataState
import com.example.cryptosep.ui.utils.components.ErrorComponent
import com.example.cryptosep.ui.utils.components.LoadingComponent
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickerScreen() {
    val viewModel = hiltViewModel<TickerViewModel>()
    val tickerListState = viewModel.tickerList.collectAsState()
    val ticker = viewModel.ticker.collectAsState()
    val searchTicker = remember { mutableStateOf("") }
    val showSearchBar = remember { mutableStateOf(false) }

    Column {
        if (showSearchBar.value) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding_8)
                    .clip(RoundedCornerShape(corner_8))
                    .padding(padding_8),
                query = searchTicker.value,
                shape = RoundedCornerShape(corner_8),
                placeholder = { Text(text = "BTC-USDT") },
                onQueryChange = { searchTicker.value = it.uppercase() },
                onSearch = { viewModel.getTicker(searchTicker.value) },
                active = true,
                onActiveChange = {

                }
            ) {
                when (ticker.value) {
                    is DataState.FailedState -> ErrorComponent(" :/ ")
                    is DataState.LoadedState -> SingleTickerComponent(singleTickerEntity = ticker.value.data!!)
                    is DataState.LoadingState -> LoadingComponent()
                }

            }
        }
        TickerScreenTopBar {
            showSearchBar.value = showSearchBar.value.not()
        }
        when (tickerListState.value) {
            is DataState.FailedState -> LoadingComponent() // todo : impl later
            is DataState.LoadedState -> TickerListComponent(tickerList = tickerListState.value.data!!)
            is DataState.LoadingState -> LoadingComponent()
        }
    }


}

@Preview
@Composable
fun TickerScreenPreview() {
    TickerScreen()
}