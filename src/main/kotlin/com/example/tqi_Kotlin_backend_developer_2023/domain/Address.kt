package com.example.tqi_Kotlin_backend_developer_2023.domain

import jakarta.persistence.*

@Embeddable
data class Address(
    @Column(nullable = false)
    val zipcode: String = "",

    @Column(nullable = false)
    val fullAddress: String = "",

)