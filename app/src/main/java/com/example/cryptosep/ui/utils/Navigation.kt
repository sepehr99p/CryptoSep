package com.example.cryptosep.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptosep.ui.screen.currency.CurrencyScreen
import com.example.cryptosep.ui.screen.home.HomeScreen

enum class Screen {
    HOME,
    CURRENCY,
    TICKERS,
    TICKER,
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Currency : NavigationItem(Screen.CURRENCY.name)
    object Tickers : NavigationItem(Screen.TICKERS.name)
    object Ticker : NavigationItem(Screen.TICKER.name)
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
    }
}