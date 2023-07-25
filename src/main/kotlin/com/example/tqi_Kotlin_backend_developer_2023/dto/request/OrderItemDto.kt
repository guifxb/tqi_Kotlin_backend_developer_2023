package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive

class OrderItemDto(
    @field:NotEmpty(message = "Barcode can't be empty") val barcode: String,
    @field:Positive(message = "Quantity can't be empty and must be positive") val quantity: Double
) {
    fun toEntity(): OrderItemDto = OrderItemDto(
        barcode = barcode,
        quantity = quantity
    )
}
