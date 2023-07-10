package com.example.tqi_Kotlin_backend_developer_2023.domain

import jakarta.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val cpf: String = "",

    @Column(nullable = false)
    var firstName: String = "",

    @Column(nullable = false)
    var surName: String = "",

    @Column(nullable = false, unique = true)
    val email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    @Embedded
    var address: Address = Address()

)