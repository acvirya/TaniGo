package com.tanigo.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tanigo.app.model.Product
import com.tanigo.app.repository.ProductRepository

class ProductViewModel : ViewModel() {

    var cartItems by mutableStateOf(listOf<Product>())
        private set

    fun addToCart(product: Product) {
        cartItems = cartItems + product
    }

    fun getAllProducts(): List<Product> {
        return ProductRepository.getAllProducts()
    }

    fun getProductById(id: Int): Product? {
        return getAllProducts().find { it.id == id }
    }
}
