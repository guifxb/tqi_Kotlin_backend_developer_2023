package com.example.tqi_Kotlin_backend_developer_2023.dto.response

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category


data class CategoryView(
    val name: String,
    val id: Long
) {
    constructor(category: Category) : this(
        category.name,
        category.id
    )
}