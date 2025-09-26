package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.screenHorizontal)
    ){
        Scaffold(
            topBar = {
                HomeTopAppBar(navController)
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

@Composable
fun HomeTopAppBar(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = Dimens.spacingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hi Jacky!", style = MaterialTheme.typography.labelSmall)
            Text("Good Morning!", style = MaterialTheme.typography.titleLarge)
        }

        Image(
            painter = painterResource(id = R.drawable.user_circle),
            contentDescription = "Profile",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun HomeBottomNavigationBar(){

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



