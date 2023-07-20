package com.example.tqi_Kotlin_backend_developer_2023.controller

import com.example.tqi_Kotlin_backend_developer_2023.domain.Product
import com.example.tqi_Kotlin_backend_developer_2023.dto.request.ProductDto
import com.example.tqi_Kotlin_backend_developer_2023.dto.request.ProductUpdateDto
import com.example.tqi_Kotlin_backend_developer_2023.dto.response.ProductView
import com.example.tqi_Kotlin_backend_developer_2023.service.impl.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.stream.Collectors


@RestController
@RequestMapping("api/product")
@Tag(name = "Product Controller")
class ProductResource(
    private val productService: ProductService
) {

    @PostMapping
    @Operation(summary = "Create product", description = "Create a new product")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Product created successfully"),
        ApiResponse(responseCode = "422", description = "Invalid request"
        )]
    )
    fun saveProduct(@RequestBody @Valid productDto: ProductDto): ResponseEntity<ProductView> {
        val product = this.productService.save(productDto.toEntity())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(product.id)
            .toUri()
        return ResponseEntity.created(location).body(ProductView(product))
    }

    @GetMapping("/barcode")
    @Operation(summary = "Get product by barcode", description = "Retrieve a product by its barcode.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation"),
        ApiResponse(responseCode = "404", description = "Product not found")
    ])
    fun findByBarcode(@RequestParam("barcode") barcode: String): ResponseEntity<ProductView?> {
        val product = this.productService.findByBarcode(barcode)
        return ResponseEntity.ok(ProductView(product))
    }

    @GetMapping("/name")
    @Operation(summary = "Get product by name", description = "Get a list of products by name. Return empty list if not found.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation")
    ])
    fun findByName(@RequestParam("name") name: String): ResponseEntity<List<ProductView>> {
        val list = this.productService.findByName(name).stream().map { product: Product -> ProductView(product) }
            .collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

    @GetMapping("/category")
    @Operation(summary = "Get product by category", description = "Get a list of products by name. Return empty list if not found.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation")

    ])
    fun findByCategory(@RequestParam("category") categoryId: Long): ResponseEntity<List<ProductView>> {
        val list =
            this.productService.findAllByCategory(categoryId).stream().map { product: Product -> ProductView(product) }
                .collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

    @PatchMapping
    @Operation(summary = "Update product", description = "Update an existing product by its barcode")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Product updated successfully"),
        ApiResponse(responseCode = "404", description = "Product not found"),
        ApiResponse(responseCode = "422", description = "Invalid request"
        )]
    )
    fun updateProduct(
        @RequestParam("barcode") barcode: String,
        @RequestBody @Valid productUpdateDto: ProductUpdateDto
    ): ResponseEntity<ProductView> {
        val product = this.productService.findByBarcode(barcode)
        return ResponseEntity.status(HttpStatus.OK)
            .body(ProductView(this.productService.update(barcode, productUpdateDto.toEntity(product))))
    }

    @DeleteMapping
    @Operation(summary = "Delete product", description = "Delete an existing product by its barcode")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            ApiResponse(responseCode = "404", description = "Product not found"
        )]
    )
    fun deleteProduct(@RequestParam("barcode") barcode: String) {
        this.productService.deleteByBarcode(barcode)
    }

}