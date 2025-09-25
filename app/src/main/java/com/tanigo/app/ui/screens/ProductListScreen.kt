package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tanigo.app.model.Product
import com.tanigo.app.viewmodel.ProductViewModel
import com.tanigo.app.ui.theme.Dimens
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductListScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel()
) {
    val products = productViewModel.getAllProducts()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(Dimens.screenHorizontal)) {
            Text(
                text = "Daftar Produk",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(Dimens.spacingMedium))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(Dimens.spacingSmall),
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingSmall),
                horizontalArrangement = Arrangement.spacedBy(Dimens.spacingSmall)
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
    val formattedPrice = NumberFormat
        .getCurrencyInstance(Locale("in", "ID"))
        .format(product.price)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.elevationSmall)
    ) {
        Column(modifier = Modifier.padding(Dimens.spacingSmall)) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(Dimens.spacingSmall))

            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Text(
                text = formattedPrice,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(Dimens.spacingSmall))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(onClick = onClickDetail) {
                    Text("Detail")
                }
                Button(onClick = onAddToCart) {
                    Text("Tambah")
                }
            }
        }
    }
}
