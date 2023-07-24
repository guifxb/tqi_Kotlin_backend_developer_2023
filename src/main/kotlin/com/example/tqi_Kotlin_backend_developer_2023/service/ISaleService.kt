package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale
import java.time.LocalDateTime

interface ISaleService {


    fun save(sale: Sale): Sale

    fun findAll(): List<Sale>

    fun findById(id: Long): Sale

    fun findByCustomer(cpf: String): List<Sale>

    fun findByTimeInterval(fromTime: LocalDateTime, toTime: LocalDateTime): List<Sale>

    fun findByPaymentOptions(paymentOptions: String): List<Sale>

    fun delete(sale: Sale)

}