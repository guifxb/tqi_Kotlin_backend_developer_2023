package com.example.tqi_Kotlin_backend_developer_2023.service

import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale

interface ISaleService {

    fun save(sale: Sale): Sale

    fun findById(id: Long): Sale?

    fun findByCustomer(customerId: Long): List<Sale>

    fun findByTimeInterval(fromTime: Long, toTime: Long): List<Sale>

    fun findByPaymentOptions(paymentOptions: String): List<Sale>

    fun delete(sale: Sale)

}