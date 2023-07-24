package com.example.tqi_Kotlin_backend_developer_2023.domain

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val barcode: String = "",

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var measurementUnit: MeasurementUnit,

    @Column(nullable = false)
    var price: Double = 0.0,

    @Column(nullable = false)
    var stock : Double = 0.0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category?

) {


}