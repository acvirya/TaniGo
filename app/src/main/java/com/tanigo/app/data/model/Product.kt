package com.tanigo.app.data.model

data class Product (
    val id: String,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val category: String,
    val description: String,
    val rating: Float,
    val soldCount: Int,
    val stock: Int,
    val reviews: List<Review> = emptyList()
)