package com.example.tqi_Kotlin_backend_developer_2023.data

import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long>{

    fun findByCpf(cpf: String): Customer?

    fun findByEmail(email: String): Customer?
}