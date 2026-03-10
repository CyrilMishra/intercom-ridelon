package com.biketalk.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.biketalk.ui.MainScreen
import com.biketalk.viewmodel.MainViewModel

@Composable
fun BikeTalkNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            val vm: MainViewModel = hiltViewModel()
            MainScreen(vm)
        }
    }
}
