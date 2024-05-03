package com.example.cryptosep.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.cryptosep.R
import com.example.cryptosep.ui.NavigationItem
import com.example.cryptosep.ui.theme.dimen.corner_8
import com.example.cryptosep.ui.theme.dimen.padding_8
import com.example.cryptosep.ui.utils.extentions.shadowBackground

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxWidth()) {
        HomeItems(title = stringResource(id = R.string.ticker)) {
            navController.navigate(NavigationItem.Tickers.route)
        }
        HomeItems(title = stringResource(id = R.string.currency)) {
            navController.navigate(NavigationItem.Currency.route)
        }
        HomeItems(title = stringResource(id = R.string.markets)) {
            navController.navigate(NavigationItem.Market.route)
        }
        HomeItems(title = stringResource(id = R.string.settings)) {
            navController.navigate(NavigationItem.Settings.route)
        }
    }

}


@Composable
private fun HomeItems(modifier: Modifier = Modifier, title: String, callback: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(padding_8)
        .clip(RoundedCornerShape(corner_8))
        .shadowBackground()
        .padding(padding_8)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            callback.invoke()
        }) {
        Text(text = title, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview
@Composable
fun HomeItemsPreview() {
    HomeItems(title = "title"){}
}