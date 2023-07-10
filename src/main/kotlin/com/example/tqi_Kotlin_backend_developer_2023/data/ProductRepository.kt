package com.example.tqi_Kotlin_backend_developer_2023.data

import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ProductRepository: JpaRepository<Product, Long>{


    @Modifying
    @Query("DELETE * FROM Product WHERE barcode = ?1", nativeQuery = true)
    fun deleteByBarCode(barcode: String)

    @Query("SELECT * FROM Product WHERE barcode = ?1", nativeQuery = true)
    fun findByBarcode(barcode: String): Product?

    @Query("SELECT * FROM Product WHERE name LIKE ?1", nativeQuery = true)
    fun findByName(name: String): List<Product>

    @Query("SELECT * FROM Product WHERE category_id = ?1", nativeQuery = true)
    fun findAllByCategoryId(categoryId: Long): List<Product>

}