package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.Product

interface IProductService {


    fun save(product: Product): Product

    fun findByName(name: String): List<Product>

    fun findByBarcode(barcode: String): Product?

    fun findAllByCategory(categoryId: Long): List<Product>

    fun update(barcode: String, product: Product): Product

    fun deleteByBarcode(barcode: String)

}