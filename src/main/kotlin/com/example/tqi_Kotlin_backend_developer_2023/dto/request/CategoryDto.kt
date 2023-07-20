package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import jakarta.validation.constraints.NotNull

data class CategoryDto(
    @field:NotNull(message = "Field is empty") val name: String
) {
    fun toEntity(): Category = Category(
        name = name
    )
}
