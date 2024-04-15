package com.example.cryptosep.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cryptosep.ui.screen.home.component.common.LoadingComponent
import com.example.cryptosep.ui.screen.currency.component.CurrencyListComponent
import com.example.cryptosep.ui.utils.DataState
import com.example.cryptosep.ui.utils.NavigationItem

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel : HomeViewModel = hiltViewModel()
    val tickerState = viewModel.tickerList.collectAsState()
    val marketState = viewModel.marketList.collectAsState()
    
    Text(text = "home screen", modifier = Modifier.clickable {
        navController.navigate(NavigationItem.Currency.route)
    })


}