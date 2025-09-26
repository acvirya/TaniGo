package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tanigo.app.R
import com.tanigo.app.ui.theme.Dimens
import com.tanigo.app.ui.theme.TaniGoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(
                    onDestinationClicked = { route ->
                        // navigate ke route
                        scope.launch { drawerState.close() }
                    }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    HomeTopAppBar(navController, drawerState)
                },
                bottomBar = {
                    HomeBottomNavigationBar()
                },
                content = { innerPadding ->
                    HomeContent(innerPadding)
                }
            )
        }
    }

}



@Composable
fun DrawerContent(onDestinationClicked: (route: String) -> Unit) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){

        // Main Container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Dimens.spacingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Container Profile
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_circle),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                Text(
                    text = "Mea",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Container Navigasi
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.spacingMedium),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Profile",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("home") }
                        .padding(8.dp)
                )

                Text(
                    text = "Account",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("home") }
                        .padding(8.dp)
                )

                Text(
                    text = "Settings",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("settings") }
                        .padding(8.dp)
                )

                Text(
                    text = "FAQ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("settings") }
                        .padding(8.dp)
                )

                Text(
                    text = "Log Out",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("settings") }
                        .padding(8.dp)
                )
            }
        }

    }





}


@Composable
fun HomeTopAppBar(navController: NavController, drawerState: DrawerState){
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(Dimens.spacingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hello, Mea", style = MaterialTheme.typography.labelSmall)
            Text("Good Morning!", style = MaterialTheme.typography.titleLarge)
        }

        Image(
            painter = painterResource(id = R.drawable.user_circle),
            contentDescription = "Profile",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .clickable {
                    scope.launch{
                        drawerState.open()
                    }
                }
        )
    }
}

@Composable
fun HomeBottomNavigationBar(){
    NavigationBar {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, contentDescription = "Home") }, selected = true, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.LocationOn, contentDescription = "Nearby") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Person, contentDescription = "Profile") }, selected = false, onClick = {})
    }
}

@Composable
fun HomeContent(innerPadding: PaddingValues){

}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    TaniGoTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun DrawerPreview(){
    TaniGoTheme {
        DrawerContent( {})
    }
}


