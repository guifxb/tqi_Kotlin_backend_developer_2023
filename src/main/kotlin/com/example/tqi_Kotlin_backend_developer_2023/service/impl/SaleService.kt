package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.data.SaleRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale
import com.example.tqi_Kotlin_backend_developer_2023.service.ISaleService
import org.springframework.stereotype.Service

@Service
class SaleService(
    private val saleRepository: SaleRepository
): ISaleService {
    override fun save(sale: Sale): Sale {
        return this.saleRepository.save(sale)
    }

    override fun findById(id: Long): Sale? {
        return this.saleRepository.findById(id).orElseThrow {
            RuntimeException("Sale not found")
        }
    }

    override fun findByCustomer(customerId: Long): List<Sale> {
        return this.findByCustomer(customerId)
    }

    override fun findByTimeInterval(fromTime: Long, toTime: Long): List<Sale> {
        return this.saleRepository.findByTimeInterval(fromTime, toTime)
    }

    override fun findByPaymentOptions(paymentOptions: String): List<Sale> {
        return this.saleRepository.findByPaymentOptions(paymentOptions)
    }

    override fun delete(sale: Sale) {
        this.saleRepository.deleteById(sale.id)
    }
}