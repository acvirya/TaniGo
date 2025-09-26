package com.tanigo.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tanigo.app.R
import com.tanigo.app.ui.components.ProductCard
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
//                        ProductCard(product, navController)
                    }
                }
            }


@Preview
@Composable
fun HomeScreenExamplePreview() {

    HomeScreenExample(navController = NavController(LocalContext.current))
}

