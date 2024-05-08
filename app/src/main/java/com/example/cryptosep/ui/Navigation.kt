package com.example.cryptosep.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptosep.ui.screen.candles.CandlesScreen
import com.example.cryptosep.ui.screen.currency.CurrencyScreen
import com.example.cryptosep.ui.screen.home.HomeScreen
import com.example.cryptosep.ui.screen.market.MarketScreen
import com.example.cryptosep.ui.screen.setting.SettingsScreen
import com.example.cryptosep.ui.screen.ticker.TickerScreen

enum class Screen {
    HOME,
    CURRENCY,
    TICKERS,
    MARKET,
    SETTINGS,
    CANDLES
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Currency : NavigationItem(Screen.CURRENCY.name)
    data object Tickers : NavigationItem(Screen.TICKERS.name)
    data object Market : NavigationItem(Screen.MARKET.name)
    data object Settings : NavigationItem(Screen.SETTINGS.name)
    data object Candles : NavigationItem(Screen.CANDLES.name)
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
            TickerScreen{
                navController.navigate("${NavigationItem.Candles.route}/$it")
            }
        }
        composable(NavigationItem.Market.route) {
            MarketScreen()
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen()
        }
        composable("${NavigationItem.Candles.route}/{symbol}") {
            CandlesScreen()
        }
    }
}