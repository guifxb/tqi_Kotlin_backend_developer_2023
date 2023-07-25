package com.example.tqi_Kotlin_backend_developer_2023.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime


@Entity
@Table(name = "sale")
class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    val customer: Customer? = null,

    @Enumerated(EnumType.STRING)
    val paymentOptions: PaymentOptions,

    @Column
    val totalPrice: BigDecimal,

    @Column
    val time: LocalDateTime,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id")
    var orderItems: MutableList<OrderItem> = mutableListOf()

)
