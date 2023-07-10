package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.data.CustomerRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import com.example.tqi_Kotlin_backend_developer_2023.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)
    }

    override fun findByCpf(cpf: String): Customer? {
        return this.findByCpf(cpf) ?: throw RuntimeException("Customer not found")
    }

    override fun findByEmail(email: String): Customer? {
       return this.findByEmail(email) ?: throw RuntimeException("Customer not found")
    }

    override fun deleteById(id: Long) {
        this.customerRepository.deleteById(id)
    }

}