package com.tanigo.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(){
    NavigationBar {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, contentDescription = "Home") }, selected = true, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.LocationOn, contentDescription = "Nearby") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Person, contentDescription = "Profile") }, selected = false, onClick = {})
    }
}