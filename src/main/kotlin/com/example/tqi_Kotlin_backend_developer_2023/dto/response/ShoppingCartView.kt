package com.example.tqi_Kotlin_backend_developer_2023.dto.response

import com.example.tqi_Kotlin_backend_developer_2023.domain.MeasurementUnit
import com.example.tqi_Kotlin_backend_developer_2023.domain.ShoppingCart
import java.math.BigDecimal
import java.math.RoundingMode


data class ShoppingCartView(
    val products: List<ProductView>,
    val totalPrice: BigDecimal,
    val productQuantity: Int
) {
    constructor(shoppingCart: ShoppingCart) : this(
            products = shoppingCart.orderItems.mapIndexed { index, it ->
            ProductView(
                id = index + 1,
                barcode = it.product.barcode,
                name = it.product.name,
                quantity = it.quantity,
                unit = it.product.measurementUnit,
                priceUnit = it.product.price.setScale(2, RoundingMode.HALF_EVEN),
                priceTotal = (it.product.price * BigDecimal.valueOf(it.quantity)).setScale(2, RoundingMode.HALF_EVEN)
            )
        },
        productQuantity = shoppingCart.orderItems.size,
        totalPrice = (shoppingCart.orderItems.sumOf { it.product.price * BigDecimal.valueOf(it.quantity) }).setScale(2, RoundingMode.HALF_EVEN),
    )

    data class ProductView(
        val id: Int,
        val barcode: String,
        val name: String,
        val quantity: Double,
        val unit: MeasurementUnit,
        val priceUnit: BigDecimal,
        val priceTotal: BigDecimal,
        )
}