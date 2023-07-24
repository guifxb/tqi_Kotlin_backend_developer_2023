package com.example.tqi_Kotlin_backend_developer_2023.repository

import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface SaleRepository: JpaRepository<Sale, Long>{

    @Query("SELECT * FROM Sale WHERE customer_id = ?1 ORDER BY time DESC", nativeQuery = true)
    fun findAllByUserId(customerId: Long): List<Sale>

    @Query("SELECT * FROM Sale ORDER BY time DESC", nativeQuery = true)
    override fun findAll(): List<Sale>

    @Query("SELECT * FROM sale WHERE time >= ?1 AND time <= ?2 ORDER BY time DESC", nativeQuery = true)
    fun findByTimeInterval(fromTime: LocalDateTime, toTime: LocalDateTime): List<Sale>

    @Query("SELECT * FROM sale WHERE payment_options = ?1 ORDER BY time DESC", nativeQuery = true)
    fun findByPaymentOptions(paymentOptions: String): List<Sale>

}