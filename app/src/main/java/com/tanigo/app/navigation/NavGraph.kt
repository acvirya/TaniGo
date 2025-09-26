package com.tanigo.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tanigo.app.ui.components.BottomNavigationBar
import com.tanigo.app.ui.screens.CartScreenExample
import com.tanigo.app.ui.screens.HomeScreenExample
import com.tanigo.app.ui.screens.NotificationScreenExample
import com.tanigo.app.ui.screens.OrderStatusScreenExample

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreenExample(navController) }
            composable("cart") { CartScreenExample(navController) }
            composable("notification") { NotificationScreenExample(navController) }
            composable("orderStatus") { OrderStatusScreenExample(navController) }
        }
    }
}
