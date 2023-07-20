package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category

interface ICategoryService {

    fun save(category: Category): Category

    fun findByName(name: String): List<Category>

    fun deleteById(id: Long)

    fun findById(id: Long): Category
}