package com.example.tqi_Kotlin_backend_developer_2023.controller

import com.example.tqi_Kotlin_backend_developer_2023.domain.Sale
import com.example.tqi_Kotlin_backend_developer_2023.dto.response.SaleView
import com.example.tqi_Kotlin_backend_developer_2023.service.impl.SaleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.stream.Collectors


@RestController
@RequestMapping("api/sale")
@Tag(name = "Sale Report Controller")
class SaleReportResource(
    private val saleService: SaleService
) {

    @GetMapping
    @Operation(summary = "Get all sales", description = "Get all sales, ordered by most recent.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
    ])
    fun getAllSales(): ResponseEntity<List<SaleView>> {
        val list = saleService.findAll().stream().map { sale: Sale -> SaleView(sale) }.collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

    @GetMapping("/id")
    @Operation(summary = "Get sale by ID", description = "Get sale by ID.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
    ])
    fun getSaleById(@RequestParam("id") id: Long): ResponseEntity<SaleView?> {
        val sale = saleService.findById(id)
        return ResponseEntity.ok(SaleView(sale))
    }

    @GetMapping("/customer")
    @Operation(summary = "Get all sales from a customer", description = "Get all sales from a customer, ordered by most recent.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
    ])
    fun getAllSalesByCustomer(@RequestParam("cpf") cpf: String): ResponseEntity<List<SaleView>> {
        val list = saleService.findByCustomer(cpf).stream().map { sale: Sale -> SaleView(sale) }.collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

    @GetMapping("/time")
    @Operation(summary = "Get all sales from a time period", description = "Get all sales from time1 to time2, ordered by most recent.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
    ])
    fun getAllSalesByTimeInterval(
        @RequestParam("fromTime") fromTime: LocalDateTime,
        @RequestParam("toTime") toTime: LocalDateTime
    ): ResponseEntity<List<SaleView>> {
        val list = saleService.findByTimeInterval(fromTime, toTime).stream().map { sale: Sale -> SaleView(sale) }.collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

    @GetMapping("/payment")
    @Operation(summary = "Get sales by payment method", description = "Get sales by payment method, ordered by most recent.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
    ])
    fun getAllSalesByPaymentOptions(@RequestParam("paymentMethod") paymentOptions: String): ResponseEntity<List<SaleView>> {
        val list = saleService.findByPaymentOptions(paymentOptions).stream().map { sale: Sale -> SaleView(sale) }.collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

}