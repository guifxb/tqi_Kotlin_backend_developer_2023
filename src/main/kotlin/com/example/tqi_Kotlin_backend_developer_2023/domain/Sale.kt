package com.example.tqi_Kotlin_backend_developer_2023.domain

import jakarta.persistence.*


@Entity
@Table(name = "sale")
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    val customer: Customer? = null,

    @Enumerated(EnumType.STRING)
    val paymentOptions: PaymentOptions,

    @Column
    val totalPrice: Double,

    @Column
    val time: Long = System.currentTimeMillis(),

    @ManyToMany
    @JoinTable(
        name = "sale_product_quantity",
        joinColumns = [JoinColumn(name = "sale_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    val products: List<OrderItem> = mutableListOf()
)
