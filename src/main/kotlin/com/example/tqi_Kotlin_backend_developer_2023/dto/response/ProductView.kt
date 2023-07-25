package com.example.tqi_Kotlin_backend_developer_2023.dto.response

import com.example.tqi_Kotlin_backend_developer_2023.domain.MeasurementUnit
import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import java.math.BigDecimal

data class ProductView(
    val barCode: String,
    val name: String,
    val measurementUnit: MeasurementUnit,
    val price: BigDecimal,
    val stock: Double,
    val categoryId: Long?
) {
    constructor(product: Product) : this(
        product.barcode,
        product.name,
        product.measurementUnit,
        product.price,
        product.stock,
        product.category?.id
    )
}
