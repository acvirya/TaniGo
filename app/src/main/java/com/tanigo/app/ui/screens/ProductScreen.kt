package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tanigo.app.viewmodel.ProductViewModel
import com.tanigo.app.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    productId: Int,
    productViewModel: ProductViewModel = viewModel()
) {
    val product = productViewModel.getProductById(productId)

    if (product != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(product.name) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Kembali"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.screenHorizontal),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { productViewModel.addToCart(product) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Tambah ke Keranjang")
                    }
                    Button(
                        onClick = { /* lanjut ke checkout */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Beli Sekarang")
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(Dimens.screenHorizontal)
            ) {
                // Foto produk
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(Dimens.spacingMedium))

                // Nama & harga
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Rp ${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(Dimens.spacingMedium))

                Divider()

                Spacer(modifier = Modifier.height(Dimens.spacingMedium))

                // Deskripsi
                Text(
                    text = "Deskripsi",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text("Produk tidak ditemukan")
        }
    }
}
