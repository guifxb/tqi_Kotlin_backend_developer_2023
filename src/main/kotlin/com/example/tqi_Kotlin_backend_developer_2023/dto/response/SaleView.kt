package com.example.tqi_Kotlin_backend_developer_2023.dto.response

import com.example.tqi_Kotlin_backend_developer_2023.domain.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime

class SaleView(
    val id: Long,
    val products: List<ProductView>,
    val productQuantity: Int,
    val totalPrice: BigDecimal,
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
                priceUnit = it.product.price.setScale(2, RoundingMode.HALF_EVEN),
                priceTotal = (it.product.price * BigDecimal.valueOf(it.quantity)).setScale(2, RoundingMode.HALF_EVEN)
            )
        },
        productQuantity = sale.orderItems.size,
        totalPrice = (sale.orderItems.sumOf { it.product.price * BigDecimal.valueOf(it.quantity) }).setScale(2, RoundingMode.HALF_EVEN),
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
        val priceUnit: BigDecimal,
        val priceTotal: BigDecimal,
    )
}