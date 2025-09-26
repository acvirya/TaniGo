package com.tanigo.app.data.model

data class Review (
    val user: User,
    val rating: Float,
    val comment: String,
    val timestamp: Long
)