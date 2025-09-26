package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tanigo.app.model.Product
import com.tanigo.app.viewmodel.ProductViewModel
import com.tanigo.app.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }

    val products = productViewModel
        .getAllProducts()
        .filter { it.name.contains(searchQuery, ignoreCase = true) } // filter sesuai input

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Produk") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Dimens.screenHorizontal)
        ) {
            // 🔍 Kolom Pencarian
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text("Cari produk...") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(Dimens.spacingSmall))

            // Grid Produk
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(Dimens.spacingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingMedium),
                horizontalArrangement = Arrangement.spacedBy(Dimens.spacingMedium)
            ) {
                items(products) { product ->
                    ProductGridItemCard(
                        product = product,
                        onAddToCart = { productViewModel.addToCart(product) },
                        onClickDetail = {
                            navController.navigate("product/${product.id}")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun ProductGridItemCard(
    product: Product,
    onAddToCart: () -> Unit,
    onClickDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Foto Produk
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(Dimens.spacingSmall))

            // Nama & Harga
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Text(
                    text = "Rp ${product.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(Dimens.spacingSmall))

            // Tombol Aksi
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                OutlinedButton(
                    onClick = onClickDetail,
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minWidth = 70.dp), // biar muat 1 baris
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp) // 🔽 lebih ramping
                ) {
                    Text(
                        text = "Detail",
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        softWrap = false
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onAddToCart,
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minWidth = 70.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp) // 🔽 lebih ramping
                ) {
                    Text(
                        text = "Add",
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        softWrap = false
                    )
                }
            }
        }
    }
}
