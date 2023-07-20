package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import com.example.tqi_Kotlin_backend_developer_2023.domain.MeasurementUnit
import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero


data class ProductDto(
    @field:NotEmpty(message = "Barcode is empty") val barcode: String,
    @field:NotEmpty(message = "Name is empty") val name: String,
    val measurementUnit: MeasurementUnit,
    @field:Positive(message = "Price can't be negative") val price: Double,
    @field:PositiveOrZero(message = "Stock can't be negative") val stock: Double,
    val categoryId: Long
    ) {

    fun toEntity(): Product = Product(
        barcode = barcode,
        name = name,
        measurementUnit = measurementUnit,
        price = price,
        stock = stock,
        category = Category(categoryId)
    )

}
