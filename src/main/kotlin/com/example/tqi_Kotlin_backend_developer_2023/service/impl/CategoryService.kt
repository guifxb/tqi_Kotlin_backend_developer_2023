package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.repository.CategoryRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import com.example.tqi_Kotlin_backend_developer_2023.service.ICategoryService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
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

    override fun deleteById(id: Long) {
        val cat = this.findById(id)
        this.categoryRepository.deleteById(cat.id)
    }

    override fun findById(id: Long): Category {
        return this.categoryRepository.findById(id).orElseThrow{ NotFoundException() }
    }
}