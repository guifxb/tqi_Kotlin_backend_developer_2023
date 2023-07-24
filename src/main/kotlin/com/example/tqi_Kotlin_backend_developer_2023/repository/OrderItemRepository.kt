package com.example.tqi_Kotlin_backend_developer_2023.repository

import com.example.tqi_Kotlin_backend_developer_2023.domain.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository: JpaRepository<OrderItem, Long> {

}