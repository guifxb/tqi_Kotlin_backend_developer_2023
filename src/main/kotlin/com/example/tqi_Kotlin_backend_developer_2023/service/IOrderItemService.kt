package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.OrderItem

interface IOrderItemService {

    fun save(order: OrderItem): OrderItem

    fun findById(id: Long): OrderItem?

    fun findBySale(saleId: Long): List<OrderItem>

    fun delete(order: OrderItem)
}