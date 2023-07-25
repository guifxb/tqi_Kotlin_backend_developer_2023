package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import com.example.tqi_Kotlin_backend_developer_2023.domain.MeasurementUnit
import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal

data class ProductUpdateDto(
    val name: String,
    val measurementUnit: MeasurementUnit,
    @field:Positive(message = "Price can't be negative") @field:DecimalMax(value = "9999999999.99", message = "Price can have at most 2 decimal places") val price: BigDecimal,
    @field:PositiveOrZero(message = "Can't be negative") val stock: Double,
) {
    fun toEntity(product: Product): Product {
        product.name = name
        product.measurementUnit = measurementUnit
        product.price = price
        product.stock = stock
        return product
    }
}