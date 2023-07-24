package com.example.tqi_Kotlin_backend_developer_2023.domain

import jakarta.persistence.*


@Entity
@Table(name = "category")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String = ""
)