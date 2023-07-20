package com.example.tqi_Kotlin_backend_developer_2023.dto.response

import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer

data class CustomerView(
    val firstName: String,
    val surName: String,
    val cpf: String,
    val email: String,
    val fullAddress: String,
    val zipcode: String
) {
    constructor(customer: Customer) : this(
        customer.firstName,
        customer.surName,
        customer.cpf,
        customer.email,
        customer.address.fullAddress,
        customer.address.zipcode
    )
}