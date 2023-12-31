package com.example.tqi_Kotlin_backend_developer_2023.domain

import jakarta.persistence.*


@Entity
@Table(name = "order_item")
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    @Column(nullable = false)
    var quantity: Double = 0.0
)