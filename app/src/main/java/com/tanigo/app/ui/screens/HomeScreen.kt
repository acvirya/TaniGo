package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tanigo.app.R
import com.tanigo.app.ui.theme.Dimens
import com.tanigo.app.ui.theme.TaniGoTheme
import com.tanigo.app.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tanigo.app.data.repository.DummyRepository
import com.tanigo.app.ui.components.ProductCard

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val products = viewModel.products.collectAsState().value
    // Main Container
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        // Banner Container
        Column{
        // TODO: Banner
        }
        // Search bar
        Row{
//            OutlinedTextField(
//                value = searchQuery,
//                onValueChange = { searchQuery = it },
//                placeholder = { Text("Cari alat pertanian") },
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search"
//                    )
//                },
//                modifier = Modifier.fillMaxWidth()
//            )
        }



        // Product Recommendation Container
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(products) { product ->
                ProductCard(product = product, navController =  navController)
            }
        }

        // Category Container
        Column{
        // TODO: Category
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
            painter = painterResource(id = R.drawable.blank_gray_circle),
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

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    TaniGoTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}

