package com.tanigo.app.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageRes: Int // tetap Int meskipun jpg/png
)