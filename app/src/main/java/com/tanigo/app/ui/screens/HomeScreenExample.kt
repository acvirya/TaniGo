package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tanigo.app.R
import com.tanigo.app.ui.theme.Dimens
import kotlinx.coroutines.launch

// Data produk dummy
data class Product(
    val name: String,
    val price: String,
    val imageRes: Int,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExample(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val categories = listOf("All", "Traktor", "Pompa", "Cangkul", "Sabit")
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    val products = listOf(
        Product("Traktor Mini", "Rp 15.000.000", R.drawable.traktor_mini, "Traktor"),
        Product("Pompa Air", "Rp 2.500.000", R.drawable.pompa_air, "Pompa"),
        Product("Cangkul Baja", "Rp 150.000", R.drawable.cangkul, "Cangkul"),
        Product("Sabit Tajam", "Rp 75.000", R.drawable.sabit, "Sabit")
    )

    // 🔎 Filter produk berdasarkan kategori & search
    val filteredProducts = products.filter { product ->
        val matchesCategory = selectedCategory == "All" || product.category == selectedCategory
        val matchesSearch = product.name.contains(searchQuery, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Menu",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { /* TODO: navigasi ke profile */ }
                )
                NavigationDrawerItem(
                    label = { Text("Cart") },
                    selected = false,
                    onClick = { navController.navigate("cart") }
                )
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { /* TODO: navigasi ke settings */ }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("Hi Jacky!", fontSize = 14.sp, color = Color.Gray)
                            Text("Good Morning!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Person, // icon profile
                                contentDescription = "Profile"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimens.screenHorizontal)
            ) {
                // Search bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari alat pertanian") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Category filter row (scrollable)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories.size) { index ->
                        val category = categories[index]
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = {
                                Text(category, maxLines = 1)
                            },
                            modifier = Modifier.heightIn(min = 36.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Product Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredProducts) { product ->
                        ProductCard(product, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, navController: NavController) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                // nanti bisa navigate ke detail
                // navController.navigate("detail/${product.name}")
            }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(product.name, fontWeight = FontWeight.Medium, fontSize = 14.sp)
        Text(product.price, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
