package com.example.tqi_Kotlin_backend_developer_2023.repository

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CategoryRepository: JpaRepository<Category, Long>{

    @Query("SELECT * FROM Category WHERE" +
            " LOWER(name) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    fun findByName(name: String): List<Category>

}