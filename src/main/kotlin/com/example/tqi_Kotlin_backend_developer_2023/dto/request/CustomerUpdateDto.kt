package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import com.example.tqi_Kotlin_backend_developer_2023.domain.Address
import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import jakarta.validation.constraints.NotEmpty

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Field cannot be empty") val firstName: String,
    @field:NotEmpty(message = "Field cannot be empty") val surName: String,
    @field:NotEmpty(message = "Field cannot be empty") val fullAddress: String,
    @field:NotEmpty(message = "Field cannot be empty") val zipcode: String,
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = firstName
        customer.surName = surName
        customer.address = Address(zipcode, fullAddress)
        return customer
    }
}
