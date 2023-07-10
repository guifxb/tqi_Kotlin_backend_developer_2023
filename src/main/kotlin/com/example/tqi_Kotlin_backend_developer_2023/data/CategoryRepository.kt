package com.example.tqi_Kotlin_backend_developer_2023.data

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface CategoryRepository: JpaRepository<Category, Long>{

    @Query("SELECT * FROM Category WHERE name LIKE ?1", nativeQuery = true)
    fun findByName(name: String): List<Category>

    @Modifying
    @Query("DELETE FROM Category WHERE name = ?1", nativeQuery = true)
    fun deleteByName(name: String)

}