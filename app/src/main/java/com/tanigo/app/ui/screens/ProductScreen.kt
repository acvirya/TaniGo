package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun ProductScreen(
    navController: NavController,
    productId: Int,
    productViewModel: ProductViewModel = viewModel()
) {
    val product = productViewModel.getProductById(productId)

    if (product != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Dimens.screenHorizontal)
        ) {
            // 🔙 Tombol Back ke List
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← Kembali")
            }

            Spacer(modifier = Modifier.height(Dimens.spacingMedium))

            // Foto produk
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(Dimens.spacingMedium))

            // Nama & harga
            Text(product.name, style = MaterialTheme.typography.titleLarge)
            Text("Rp ${product.price}", color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.height(Dimens.spacingMedium))

            // Deskripsi
            Text("Deskripsi", style = MaterialTheme.typography.titleLarge)
            Text(product.description)

            Spacer(modifier = Modifier.height(Dimens.spacingLarge))

            // Tombol aksi
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { productViewModel.addToCart(product) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Tambah ke Keranjang")
                }

                Button(
                    onClick = { /* nanti ke checkout */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Beli Sekarang")
                }
            }
        }
    } else {
        Text("Produk tidak ditemukan")
    }
}
