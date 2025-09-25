package com.tanigo.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tanigo.app.ui.screens.HomeScreenExample
import com.tanigo.app.ui.screens.ProductListScreen
import com.tanigo.app.ui.screens.ProductScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreenExample(navController) }
        composable("product_list") { ProductListScreen(navController) }

        // halaman detail produk dengan argumen productId
        composable(
            "product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            ProductScreen(navController, productId)
        }
    }
}
