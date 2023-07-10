package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.data.OrderItemRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.OrderItem
import com.example.tqi_Kotlin_backend_developer_2023.service.IOrderItemService
import org.springframework.stereotype.Service

@Service
class OrderItemService(
    private val orderItemRepository: OrderItemRepository
): IOrderItemService {
    override fun save(order: OrderItem): OrderItem {
        return this.orderItemRepository.save(order)
    }

    override fun findById(id: Long): OrderItem? {
        return this.orderItemRepository.findById(id).orElseThrow {
            RuntimeException("Item not found")
        }
    }

    override fun findBySale(saleId: Long): List<OrderItem> {
        return this.orderItemRepository.findBySaleId(saleId)
    }

    override fun delete(order: OrderItem) {
        return this.orderItemRepository.delete(order)
    }
}