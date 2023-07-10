package com.example.tqi_Kotlin_backend_developer_2023.data

import com.example.tqi_Kotlin_backend_developer_2023.domain.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderItemRepository: JpaRepository<OrderItem, Long> {

    @Query("SELECT * FROM OrderItem WHERE sale_id = ?1", nativeQuery = true)
    fun findBySaleId(saleId: Long): List<OrderItem>
}