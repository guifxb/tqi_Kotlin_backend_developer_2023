package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import com.example.tqi_Kotlin_backend_developer_2023.repository.CategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*


class CategoryServiceTest {

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @InjectMocks
    private lateinit var categoryService: CategoryService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testSaveCategory() {
        // Given
        val categoryToSave = Category(1L, "Electronics")
        val savedCategory = Category(1L, "Electronics")

        Mockito.`when`(categoryRepository.save(categoryToSave)).thenReturn(savedCategory)

        // When
        val result = categoryService.save(categoryToSave)

        // Then
        assertThat(result).isEqualTo(savedCategory)
    }

    @Test
    fun testFindByName() {
        // Given
        val categoryName = "Electronics"
        val categoryList = listOf(Category(1L, "Electronics"), Category(2L, "Electronics"))

        Mockito.`when`(categoryRepository.findByName(categoryName)).thenReturn(categoryList)

        // When
        val result = categoryService.findByName(categoryName)

        // Then
        assertThat(result).isEqualTo(categoryList)
    }

    @Test
    fun testFindById() {
        // Given
        val categoryId = 1L
        val category = Category(categoryId, "Electronics")

        Mockito.`when`(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category))

        // When
        val result = categoryService.findById(categoryId)

        // Then
        assertThat(result).isEqualTo(category)
    }

    @Test
    fun testFindByIdThrowsNotFoundException() {
        // Given
        val categoryId = 1L

        Mockito.`when`(categoryRepository.findById(categoryId)).thenReturn(Optional.empty())

        // When/Then
        assertThrows<NotFoundException> {
            categoryService.findById(categoryId)
        }
    }

    @Test
    fun testDeleteById() {
        // Given
        val categoryId = 1L
        val categoryToDelete = Category(categoryId, "Electronics")

        Mockito.`when`(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryToDelete))

        // When
        categoryService.deleteById(categoryId)

        // Then
        Mockito.verify(categoryRepository).deleteById(categoryId)
    }
}