package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.data.ProductRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import com.example.tqi_Kotlin_backend_developer_2023.service.IProductService
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
): IProductService {
    override fun save(product: Product): Product {
        return this.productRepository.save(product)
    }

    override fun findByName(name: String): List<Product> {
        return this.productRepository.findByName(name)
    }

    override fun findByBarcode(barcode: String): Product? {
        return this.productRepository.findByBarcode(barcode)
    }

    override fun findAllByCategory(categoryId: Long): List<Product> {
        return this.productRepository.findAllByCategoryId(categoryId)
    }

    override fun update(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun deleteByBarcode(barcode: String) {
        this.productRepository.deleteByBarCode(barcode)
    }
}