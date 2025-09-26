package com.tanigo.app.data.model

data class Order(
    val id: String,
    val userId: String,
    val items: List<CartItem>,
    val totalPrice: Double,
    val status: String,           // misal "pending", "paid", "shipped", "completed"
    val timestamp: Long
)