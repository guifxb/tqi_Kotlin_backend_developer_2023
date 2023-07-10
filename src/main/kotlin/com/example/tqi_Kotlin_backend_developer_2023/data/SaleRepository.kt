package com.example.tqi_Kotlin_backend_developer_2023.data

import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SaleRepository: JpaRepository<Sale, Long>{

    @Query("SELECT * FROM Sale WHERE customer_id = ?1", nativeQuery = true)
    fun findAllByUserId(customerId: Long): List<Sale>


    @Query("SELECT * FROM sale WHERE time >= ?1 AND time <= ?2", nativeQuery = true)
    fun findByTimeInterval(fromTime: Long, toTime: Long): List<Sale>

    @Query("SELECT * FROM sale WHERE payment_options = ?1", nativeQuery = true)
    fun findByPaymentOptions(paymentOptions: String): List<Sale>

}