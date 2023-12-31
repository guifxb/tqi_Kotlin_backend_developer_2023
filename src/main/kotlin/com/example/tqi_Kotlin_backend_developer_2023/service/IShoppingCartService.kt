package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.PaymentOptions
import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale
import com.example.tqi_Kotlin_backend_developer_2023.domain.ShoppingCart
import java.math.BigDecimal

interface IShoppingCartService {

    fun getShoppingCart(): ShoppingCart

    fun addToCart(barcode: String, quantity: Double): ShoppingCart

    fun removeFromCart(id: Int): ShoppingCart

    fun calculateTotalPrice(): BigDecimal

    fun checkout(cpf: String?, paymentOptions: PaymentOptions): Sale
}