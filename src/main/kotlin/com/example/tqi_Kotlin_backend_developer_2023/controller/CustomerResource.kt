package com.example.tqi_Kotlin_backend_developer_2023.controller

import com.example.tqi_Kotlin_backend_developer_2023.dto.request.CustomerDto
import com.example.tqi_Kotlin_backend_developer_2023.dto.request.CustomerUpdateDto
import com.example.tqi_Kotlin_backend_developer_2023.dto.response.CustomerView
import com.example.tqi_Kotlin_backend_developer_2023.service.impl.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RestController
@RequestMapping("api/customer")
@Tag(name = "Customer Controller")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    @Operation(summary = "Create customer", description = "Create a new customer")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Customer created successfully"),
        ApiResponse(responseCode = "422", description = "Invalid request"
        )]
    )
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<CustomerView> {
        val customer = customerService.save(customerDto.toEntity())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(customer.id)
            .toUri()
        return ResponseEntity.created(location).body(CustomerView(customer))
    }

    @GetMapping("/cpf")
    @Operation(summary = "Get customer by CPF", description = "Retrieve a customer by its CPF.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
        ApiResponse(responseCode = "404", description = "Customer not found")
    ])
    fun findByCpf(@RequestParam("cpf") cpf: String): ResponseEntity<CustomerView?> {
        val customer = customerService.findByCpf(cpf)
        return ResponseEntity.ok(CustomerView(customer))
    }

    @GetMapping("/email")
    @Operation(summary = "Get customer by email", description = "Retrieve a customer by its email.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
        ApiResponse(responseCode = "404", description = "Customer not found")
    ])
    fun findByEmail(@RequestParam("email") email: String): ResponseEntity<CustomerView?> {
        val customer = customerService.findByEmail(email)
        return ResponseEntity.ok(CustomerView(customer))
    }

    @PatchMapping
    @Operation(summary = "Update customer", description = "Update an existing customer by its CPF")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        ApiResponse(responseCode = "404", description = "Customer not found"),
        ApiResponse(responseCode = "422", description = "Invalid request"
        )]
    )
    fun updateUser(
        @RequestParam("cpf") cpf: String,
        @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer = customerService.findByCpf(cpf)
        return ResponseEntity.status(HttpStatus.OK)
            .body(CustomerView(customerService.save(customerUpdateDto.toEntity(customer))))
    }

    @DeleteMapping
    @Operation(summary = "Delete customer", description = "Delete an existing customer by its CPF")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            ApiResponse(responseCode = "404", description = "Customer not found")
        ])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestParam("cpf") cpf: String) {
        customerService.deleteByCpf(cpf)
    }

}