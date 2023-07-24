package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.repository.OrderItemRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.OrderItem
import com.example.tqi_Kotlin_backend_developer_2023.service.IOrderItemService
import org.springframework.stereotype.Service

@Service
class OrderItemService(
    private val orderItemRepository: OrderItemRepository
): IOrderItemService {

    override fun save(orderItem: OrderItem): OrderItem {
        return orderItemRepository.save(orderItem)
    }

    override fun findById(id: Long): OrderItem? {
        return orderItemRepository.findById(id).orElseThrow {
            RuntimeException("Item not found")
        }
    }

    override fun delete(order: OrderItem) {
        return orderItemRepository.delete(order)
    }
}