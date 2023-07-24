package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.OrderItem

interface IOrderItemService {

    fun save(orderItem: OrderItem): OrderItem

    fun findById(id: Long): OrderItem?

    fun delete(order: OrderItem)
}