package com.example.tqi_Kotlin_backend_developer_2023.controller

import com.example.tqi_Kotlin_backend_developer_2023.domain.PaymentOptions
import com.example.tqi_Kotlin_backend_developer_2023.dto.request.OrderItemDto
import com.example.tqi_Kotlin_backend_developer_2023.dto.response.SaleView
import com.example.tqi_Kotlin_backend_developer_2023.dto.response.ShoppingCartView
import com.example.tqi_Kotlin_backend_developer_2023.service.impl.ShoppingCartService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/shopping-cart")
@Tag(name = "ShoppingCart Controller")
class ShoppingCartResource(
    private val shoppingCartService: ShoppingCartService
) {

    @PostMapping
    @Operation(summary = "Checkout", description = "Checkout the sale, asking for user CPF and payment method. This also register the sale and clears the cart.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Sale created successfully"), //tem q ver se é essas mesmo pq olha
        ApiResponse(responseCode = "400", description = "Bad Request"
        )]
    )
    fun checkout(@RequestParam("cpf") cpf: String?, @RequestParam("payment") payment: PaymentOptions): ResponseEntity<SaleView> {
        val sale = shoppingCartService.checkout(cpf, payment)
        return ResponseEntity.ok(SaleView(sale))
    }

    @GetMapping
    @Operation(summary = "Get shopping cart", description = "Get the shopping cart")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Shopping cart retrieved successfully"),
        ApiResponse(responseCode = "404", description = "Shopping cart not found")
    ])
    fun getShoppingCart(): ResponseEntity<ShoppingCartView> {
        val cart = shoppingCartService.getShoppingCart()
        return ResponseEntity.ok(ShoppingCartView(cart))
    }

    @PutMapping
    @Operation(summary = "Update shopping cart", description = "Add product to the shopping cart")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Shopping cart updated successfully"),
    ])
    fun updateShoppingCart(@RequestBody orderItemDto: OrderItemDto): ResponseEntity<ShoppingCartView> {
        val orderItem = orderItemDto.toEntity()
        val cart = shoppingCartService.addToCart(orderItem.barcode, orderItem.quantity)
        return ResponseEntity.ok(ShoppingCartView(cart))
    }

    @DeleteMapping
    @Operation(summary = "Delete shopping cart", description = "Remove product from the shopping cart by it's ID on cart")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Shopping cart updated successfully"),
    ])
    fun deleteShoppingCart(@RequestParam id: Int): ResponseEntity<ShoppingCartView> {
        val cart = shoppingCartService.removeFromCart(id)
        return ResponseEntity.ok(ShoppingCartView(cart))
    }







}