package com.example.cryptosep.ui.screen.ticker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.DataState
import com.example.cryptosep.ui.utils.components.ErrorComponent
import com.example.cryptosep.ui.utils.components.LoadingComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickerScreen(
    onTickerClicked: (symbol: String) -> Unit
) {
    val viewModel = hiltViewModel<TickerViewModel>()
    val tickerListState = viewModel.tickerList.collectAsState()
    val ticker = viewModel.ticker.collectAsState()
    val searchTicker = remember { mutableStateOf("") }
    val showSearchBar = remember { mutableStateOf(false) }

    Column {
        if (showSearchBar.value) {
            val interactionSource = remember { MutableInteractionSource() }
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
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    dividerColor = MaterialTheme.colorScheme.primary
                ),
                onActiveChange = {
//                    showSearchBar.value = it
                },
                interactionSource = interactionSource,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            showSearchBar.value = false
                        }, imageVector = Icons.Default.Close, contentDescription = null
                    )
                }
            ) {
                when (ticker.value) {
                    is DataState.FailedState -> ErrorComponent(" :/ ")
                    is DataState.LoadedState -> SingleTickerComponent(
                        singleTickerEntity = ticker.value.data!!,
                        onTickerClicked = { onTickerClicked.invoke(searchTicker.value) })

                    is DataState.LoadingState -> LoadingComponent()
                }

            }
        }
        TickerScreenTopBar(
            callback = { showSearchBar.value = showSearchBar.value.not() }
        )
        when (tickerListState.value) {
            is DataState.FailedState -> ErrorComponent("ticker list error")
            is DataState.LoadedState -> TickerListComponent(tickerList = tickerListState.value.data!!) {
                onTickerClicked.invoke(it)
            }

            is DataState.LoadingState -> LoadingComponent()
        }
    }


}

@Preview
@Composable
private fun TickerScreenPreview() {
//    TickerScreen()
}