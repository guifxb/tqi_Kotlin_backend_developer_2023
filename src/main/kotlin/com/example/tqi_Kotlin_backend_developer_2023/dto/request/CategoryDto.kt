package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import jakarta.validation.constraints.NotEmpty

data class CategoryDto(
    @field:NotEmpty(message = "Name is empty") val name: String
) {
    fun toEntity(): Category = Category(
        name = name
    )
}
