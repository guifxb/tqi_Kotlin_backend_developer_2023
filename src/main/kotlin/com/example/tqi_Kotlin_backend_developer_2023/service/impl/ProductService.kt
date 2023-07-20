package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.repository.ProductRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import com.example.tqi_Kotlin_backend_developer_2023.service.IProductService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService
): IProductService {

    override fun save(product: Product): Product {
        product.apply {
            category = categoryService.findById(product.category?.id!!)
        }
        return this.productRepository.save(product)
    }

    override fun findByName(name: String): List<Product> {
        return this.productRepository.findByName(name)
    }

    override fun findByBarcode(barcode: String): Product {
        return this.productRepository.findByBarcode(barcode) ?: throw NotFoundException()
    }

    override fun findAllByCategory(categoryId: Long): List<Product> {
        return this.productRepository.findAllByCategoryId(categoryId)
    }

    override fun update(barcode: String, product: Product): Product {
        if (product.barcode != barcode) {
            throw IllegalArgumentException("Id mismatch")
        }
        val existingProduct = this.productRepository.findById(product.id).orElseThrow { NotFoundException() }

        val updatedProduct = existingProduct.copy(
            name = product.name,
            measurementUnit = product.measurementUnit,
            price = product.price,
            stock = product.stock
        )
        return this.save(updatedProduct)
    }

    override fun deleteByBarcode(barcode: String) {
        val product = this.findByBarcode(barcode)
        this.productRepository.deleteById(product.id)
    }
}