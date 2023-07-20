package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import com.example.tqi_Kotlin_backend_developer_2023.domain.Address
import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import jakarta.validation.constraints.NotEmpty

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Name is empty") val firstName: String,
    @field:NotEmpty(message = "Surname is empty") val surName: String,
    @field:NotEmpty(message = "Address is empty") val fullAddress: String,
    @field:NotEmpty(message = "Zipcode is empty") val zipcode: String,
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = firstName
        customer.surName = surName
        customer.address = Address(zipcode, fullAddress)
        return customer
    }
}
