package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale
import com.example.tqi_Kotlin_backend_developer_2023.repository.CustomerRepository
import com.example.tqi_Kotlin_backend_developer_2023.repository.SaleRepository
import com.example.tqi_Kotlin_backend_developer_2023.service.ISaleService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SaleService(
    private val saleRepository: SaleRepository,
    private val customerRepository: CustomerRepository
): ISaleService {

    override fun save(sale: Sale): Sale {
        return saleRepository.save(sale)
    }

    override fun findAll(): List<Sale> {
        return saleRepository.findAll()
    }

    override fun findById(id: Long): Sale {
        return saleRepository.findById(id).orElseThrow { NotFoundException() }
    }

    override fun findByCustomer(cpf: String): List<Sale> {
        val user = customerRepository.findByCpf(cpf) ?: throw NotFoundException()
        return saleRepository.findAllByUserId(user.id)
    }

    override fun findByTimeInterval(fromTime: LocalDateTime, toTime: LocalDateTime): List<Sale> {
        return saleRepository.findByTimeInterval(fromTime, toTime)
    }

    override fun findByPaymentOptions(paymentOptions: String): List<Sale> {
        return saleRepository.findByPaymentOptions(paymentOptions)
    }

    override fun delete(sale: Sale) {
        saleRepository.deleteById(sale.id)
    }
}