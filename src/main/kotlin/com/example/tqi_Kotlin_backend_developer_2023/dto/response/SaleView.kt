package com.example.tqi_Kotlin_backend_developer_2023.dto.response

import com.example.tqi_Kotlin_backend_developer_2023.domain.*
import java.time.LocalDateTime

class SaleView(
    val id: Long,
    val products: List<ProductView>,
    val productQuantity: Int,
    val totalPrice: String,
    val paymentOptions: PaymentOptions,
    val customerCpf: String,
    val time: LocalDateTime
) {
    constructor(sale: Sale) : this(
        id = sale.id,
        products = sale.orderItems.mapIndexed { index, it ->
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
        productQuantity = sale.orderItems.size,
        totalPrice = "%.2f".format(sale.orderItems.sumOf { it.product.price * it.quantity }),
        customerCpf = sale.customer?.cpf ?: "",
        paymentOptions = sale.paymentOptions,
        time = sale.time
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