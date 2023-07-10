package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.OrderItem

interface IOrderItemService {

    //fun calculateTotalPrice(): Double será q é aqui?

    fun save(order: OrderItem): OrderItem

    fun findById(id: Long): OrderItem?

    fun findBySale(saleId: Long): List<OrderItem>

    fun delete(order: OrderItem)
}