package com.tanigo.app.data.repository

import com.tanigo.app.R
import com.tanigo.app.data.model.*

object DummyRepository {

    // 3. Products
    val products = listOf(
        Product(
            id = "product1",
            name = "Cangkul",
            price = 50000.0,
            imageRes = R.drawable.cangkul,
            category = "Cangkul",
            description = "Cangkul description",
            rating = 4.5f,
            soldCount = 10,
            stock = 10,
            reviews = emptyList()
        ),
        Product(
            id = "product2",
            name = "Traktor Mini",
            price = 1500000.0,
            imageRes = R.drawable.traktor_mini,
            category = "Traktor",
            description = "Traktor Mini description",
            rating = 4.8f,
            soldCount = 4,
            stock = 50,
            reviews = emptyList()
        ),
        Product(
            id = "product3",
            name = "Sabit",
            price = 150000.0,
            imageRes = R.drawable.sabit,
            category = "Sabit",
            description = "Sabit description",
            rating = 4.0f,
            soldCount = 500,
            stock = 20,
            reviews = emptyList()
        ),
        Product(
            id = "product4",
            name = "Pompa Air",
            price = 1500000.0,
            imageRes = R.drawable.pompa_air,
            category = "Pompa Air",
            description = "Pompa Air Description",
            rating = 4.7f,
            soldCount = 24,
            stock = 40,
            reviews = emptyList()
        ),
    )

    // 4. Cart Items
    val cartItems = listOf(
        CartItem(productId = "product1", quantity = 1),
        CartItem(productId = "product2", quantity = 3)
    )

    // 5. Orders
    val orders = listOf(
        Order(
            id = "order1",
            userId = "user1",
            items = cartItems,
            totalPrice = cartItems.sumOf { item ->
                val product = products.find { it.id == item.productId }!!
                product.price * item.quantity
            },
            status = "pending",
            timestamp = System.currentTimeMillis()
        )
    )

    fun getAllProducts(): List<Product> {
        return products
    }

}
