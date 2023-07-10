package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findByCpf(cpf: String): Customer?

    fun findByEmail(email: String): Customer?

    fun deleteById(id: Long)


}