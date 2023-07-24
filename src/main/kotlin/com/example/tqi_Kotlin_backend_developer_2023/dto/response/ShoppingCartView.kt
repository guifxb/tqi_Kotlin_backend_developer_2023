package com.example.tqi_Kotlin_backend_developer_2023.dto.response

import com.example.tqi_Kotlin_backend_developer_2023.domain.MeasurementUnit
import com.example.tqi_Kotlin_backend_developer_2023.domain.ShoppingCart


data class ShoppingCartView(
    val products: List<ProductView>,
    val totalPrice: String,
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
                priceUnit = "%.2f".format(it.product.price),
                priceTotal = "%.2f".format(it.product.price * it.quantity)
            )
        },
        productQuantity = shoppingCart.orderItems.size,
        totalPrice = "%.2f".format(shoppingCart.orderItems.sumOf { it.product.price * it.quantity }),
    )

    data class ProductView(
        val id: Int,
        val barcode: String,
        val name: String,
        val quantity: Double,
        val unit: MeasurementUnit,
        val priceUnit: String,
        val priceTotal: String,
        )
}