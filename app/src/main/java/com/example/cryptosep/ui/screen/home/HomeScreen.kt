package com.example.cryptosep.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cryptosep.ui.NavigationItem

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel : HomeViewModel = hiltViewModel()
    
    Text(text = "home screen", modifier = Modifier.clickable {
        navController.navigate(NavigationItem.Currency.route)
    })


}