package com.example.cryptosep.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptosep.ui.screen.currency.CurrencyScreen
import com.example.cryptosep.ui.screen.home.HomeScreen
import com.example.cryptosep.ui.screen.market.MarketScreen
import com.example.cryptosep.ui.screen.ticker.TickerScreen

enum class Screen {
    HOME,
    CURRENCY,
    TICKERS,
    MARKET
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Currency : NavigationItem(Screen.CURRENCY.name)
    object Tickers : NavigationItem(Screen.TICKERS.name)
    object Market : NavigationItem(Screen.MARKET.name)
}


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationItem.Currency.route) {
            CurrencyScreen(navController)
        }
        composable(NavigationItem.Tickers.route) {
            TickerScreen()
        }
        composable(NavigationItem.Market.route) {
            MarketScreen()
        }
    }
}