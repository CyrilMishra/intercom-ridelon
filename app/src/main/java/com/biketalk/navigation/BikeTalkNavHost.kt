package com.biketalk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.biketalk.ui.MainScreen
import com.biketalk.viewmodel.IntercomViewModel

@Composable
fun BikeTalkNavHost(viewModel: IntercomViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(viewModel) }
    }
}
