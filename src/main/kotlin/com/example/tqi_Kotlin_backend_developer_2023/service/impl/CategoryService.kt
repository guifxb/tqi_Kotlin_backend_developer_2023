package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.data.CategoryRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import com.example.tqi_Kotlin_backend_developer_2023.service.ICategoryService
import org.springframework.stereotype.Service


@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
): ICategoryService {
    override fun save(category: Category): Category {
        return this.categoryRepository.save(category)
    }

    override fun findByName(name: String): List<Category> {
        return categoryRepository.findByName(name)
    }

    override fun deleteByName(name: String) {
        this.categoryRepository.deleteByName(name)
    }
}