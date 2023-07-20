package com.example.tqi_Kotlin_backend_developer_2023.repository

import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CustomerRepository: JpaRepository<Customer, Long>{

    @Query("SELECT * FROM customer WHERE cpf = ?1", nativeQuery = true)
    fun findByCpf(cpf: String): Customer?

    @Query("SELECT * FROM customer WHERE email = ?1", nativeQuery = true)
    fun findByEmail(email: String): Customer?
}