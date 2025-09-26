package com.tanigo.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.tanigo.app.ui.components.BottomNavigationBar
import com.tanigo.app.ui.components.NavigationDrawer

import com.tanigo.app.ui.screens.HomeScreenExample
import com.tanigo.app.ui.screens.CartScreenExample
//import com.tanigo.app.ui.screens.NotificationScreen
//import com.tanigo.app.ui.screens.OrderStatusScreen
import com.tanigo.app.ui.screens.LoginScreen
import com.tanigo.app.ui.screens.RegisterScreen
import com.tanigo.app.ui.screens.HomeScreen
import com.tanigo.app.ui.screens.HomeTopAppBar
import kotlinx.coroutines.launch

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawer { route ->
                scope.launch { drawerState.close() }
                navController.navigate(route)
            }
        }
    ) {
        Scaffold(
            topBar = {
                when (currentRoute) {
                    "home" -> HomeTopAppBar(navController, drawerState)
                    else -> {}
                }
            },
            bottomBar = {
                when (currentRoute) {
                    "home" -> BottomNavigationBar(navController)
                    else -> {}
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding).background(MaterialTheme.colorScheme.surface)){
                NavHost(
                    navController = navController,
                    startDestination = "login",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("home") { HomeScreen(navController) }
//
//                composable("cart") { CartScreen() }
//                composable("notification") { NotificationScreenContent() }
//                composable("orderStatus") { OrderStatusScreenContent() }
                }
            }

        }
    }
}


//@Composable fun NavGraph() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "login") {
//        composable("login") { LoginScreen(navController) }
//        composable("register") { RegisterScreen(navController) }
//        composable("home") { HomeScreenExample(navController) }
//        composable("cart") { CartScreenExample(navController) }
//        composable("home") {HomeScreen(navController)}
//        // composable("notification") { NotificationScreen(navController) }
//        // composable("orderStatus") { OrderStatusScreen(navController) }
//    }
//}
