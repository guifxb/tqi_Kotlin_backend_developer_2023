package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.repository.CustomerRepository
import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import com.example.tqi_Kotlin_backend_developer_2023.service.ICustomerService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {

    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)
    }

    override fun findByCpf(cpf: String): Customer {
        return this.customerRepository.findByCpf(cpf) ?: throw NotFoundException()
    }

    override fun findByEmail(email: String): Customer {
       return this.customerRepository.findByEmail(email) ?: throw NotFoundException()
    }

    override fun deleteByCpf(cpf: String) {
        val customer = this.findByCpf(cpf)
        this.customerRepository.deleteById(customer.id)
    }

}