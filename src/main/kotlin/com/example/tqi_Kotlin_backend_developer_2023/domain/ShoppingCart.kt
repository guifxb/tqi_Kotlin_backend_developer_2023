package com.example.tqi_Kotlin_backend_developer_2023.domain

data class ShoppingCart(
    var product: List<OrderItem>,
    var TotalPrice: Double,
    var isLogged: Boolean = false,
    var customer: Customer? = null
)
