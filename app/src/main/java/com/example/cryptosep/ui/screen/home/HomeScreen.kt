package com.example.cryptosep.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.cryptosep.R
import com.example.cryptosep.ui.NavigationItem
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_24
import com.example.cryptosep.ui.theme.dimen.padding_32
import com.example.cryptosep.ui.theme.dimen.padding_4
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.extentions.shadowBackground

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxWidth()) {
        TickerItem(title = stringResource(id = R.string.ticker)) {
            navController.navigate(NavigationItem.Tickers.route)
        }
        Row {
            HomeItems(
                modifier = Modifier.weight(1f),
                title = stringResource(id = R.string.currency)
            ) {
                navController.navigate(NavigationItem.Currency.route)
            }
            HomeItems(
                modifier = Modifier.weight(1f),
                title = stringResource(id = R.string.markets)
            ) {
                navController.navigate(NavigationItem.Market.route)
            }
        }
        HomeItems(title = stringResource(id = R.string.settings)) {
            navController.navigate(NavigationItem.Settings.route)
        }
    }

}

@Composable
private fun TickerItem(modifier: Modifier = Modifier, title: String, callback: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = padding_8, vertical = padding_4)
        .clip(RoundedCornerShape(corner_8))
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            callback.invoke()
        }
        .shadowBackground()
        .padding(horizontal = padding_8, vertical = padding_32)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun HomeItems(modifier: Modifier = Modifier, title: String, callback: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = padding_8, vertical = padding_4)
        .clip(RoundedCornerShape(corner_8))
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            callback.invoke()
        }
        .shadowBackground()
        .padding(horizontal = padding_8, vertical = padding_24)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun HomeItemsPreview() {
    HomeItems(title = "title") {}
}

@Preview
@Composable
private fun TickerItemPreview(modifier: Modifier = Modifier) {
    TickerItem(title = "title") {}
}