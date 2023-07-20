package com.example.tqi_Kotlin_backend_developer_2023.dto.request

import com.example.tqi_Kotlin_backend_developer_2023.domain.Address
import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.br.CPF


data class CustomerDto(
    @field:NotEmpty(message = "Name is empty") val name: String,
    @field:NotEmpty(message = "Surname is empty") val surName: String,
    @field:CPF(message = "CPF is invalid") val cpf: String,
    @field:Email(message = "Email is invalid") val email: String,
    @field:NotEmpty(message = "Password is empty") val password: String,
    @field:NotEmpty(message = "ZipCode is empty") val zipCode: String,
    @field:NotEmpty(message = "Address is empty") val fullAddress: String

    ) {

    fun toEntity(): Customer = Customer(
        firstName = name,
        surName = surName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(zipCode, fullAddress)
    )
}