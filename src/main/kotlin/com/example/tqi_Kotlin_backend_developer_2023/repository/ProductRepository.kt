package com.example.tqi_Kotlin_backend_developer_2023.repository

import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository: JpaRepository<Product, Long>{

    @Query("SELECT * FROM product WHERE" +
            " LOWER(name) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    fun findByName(name: String): List<Product>

    @Query("SELECT * FROM product WHERE barcode = ?1", nativeQuery = true)
    fun findByBarcode(barcode: String): Product?

    @Query("SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
    fun findAllByCategoryId(categoryId: Long): List<Product>

}