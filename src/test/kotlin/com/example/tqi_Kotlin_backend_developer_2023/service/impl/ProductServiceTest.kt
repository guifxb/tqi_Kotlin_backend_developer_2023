package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import com.example.tqi_Kotlin_backend_developer_2023.domain.MeasurementUnit
import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import com.example.tqi_Kotlin_backend_developer_2023.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.math.BigDecimal

class ProductServiceTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var categoryService: CategoryService

    @InjectMocks
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testSaveProduct() {
        // Given
        val categoryId = 1L
        val category = Category(categoryId, "Electronics")
        val productToSave = Product(1L, "123456789", "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, category)
        val savedProduct = productToSave.copy()

        Mockito.`when`(categoryService.findById(categoryId)).thenReturn(category)
        Mockito.`when`(productRepository.save(productToSave)).thenReturn(savedProduct)

        // When
        val result = productService.save(productToSave)

        // Then
        assertThat(result).isEqualTo(savedProduct)
    }

    @Test
    fun testFindByBarcode() {
        // Given
        val barcode = "123456789"
        val product = Product(1L, barcode, "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, null)

        Mockito.`when`(productRepository.findByBarcode(barcode)).thenReturn(product)

        // When
        val result = productService.findByBarcode(barcode)

        // Then
        assertThat(result).isEqualTo(product)
    }

    @Test
    fun testFindByBarcodeThrowsNotFoundException() {
        // Given
        val barcode = "123456789"

        Mockito.`when`(productRepository.findByBarcode(barcode)).thenReturn(null)

        // When/Then
        assertThrows<NotFoundException> {
            productService.findByBarcode(barcode)
        }
    }

    @Test
    fun testDeleteByBarcode() {
        // Given
        val barcode = "123456789"
        val productToDelete = Product(1L, barcode, "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, null)

        Mockito.`when`(productRepository.findByBarcode(barcode)).thenReturn(productToDelete)

        // When
        productService.deleteByBarcode(barcode)

        // Then
        Mockito.verify(productRepository).deleteById(productToDelete.id)
    }
}
