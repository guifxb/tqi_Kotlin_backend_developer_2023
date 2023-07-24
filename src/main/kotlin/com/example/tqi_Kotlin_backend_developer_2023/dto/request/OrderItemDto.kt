package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import jakarta.validation.constraints.NotEmpty

class OrderItemDto(
    @field:NotEmpty(message = "Name is empty") val barcode: String,
    @field:NotEmpty(message = "Quantity is empty") val quantity: Double
) {
    fun toEntity(): OrderItemDto = OrderItemDto(
        barcode = barcode,
        quantity = quantity
    )
}
