package com.tanigo.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.tanigo.app.ui.screens.HomeScreenExample
import com.tanigo.app.ui.screens.CartScreenExample
//import com.tanigo.app.ui.screens.NotificationScreen
//import com.tanigo.app.ui.screens.OrderStatusScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreenExample(navController) }
        composable("cart") { CartScreenExample(navController) }
//        composable("notification") { NotificationScreen(navController) }
//        composable("orderStatus") { OrderStatusScreen(navController) }
    }
}