package com.example.tqi_Kotlin_backend_developer_2023.controller

import com.example.tqi_Kotlin_backend_developer_2023.domain.Category
import com.example.tqi_Kotlin_backend_developer_2023.dto.request.CategoryDto
import com.example.tqi_Kotlin_backend_developer_2023.dto.response.CategoryView
import com.example.tqi_Kotlin_backend_developer_2023.service.impl.CategoryService
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
@RequestMapping("api/category")
@Tag(name = "Category Controller")
class CategoryResource(
    private val categoryService: CategoryService
) {

    @PostMapping
    @Operation(summary = "Create category", description = "Create a new category")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Category created successfully"),
        ApiResponse(responseCode = "422", description = "Invalid request"
        )]
    )
    fun saveCategory(@RequestBody @Valid categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        val category = categoryService.save(categoryDto.toEntity())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(category.id)
            .toUri()
        return ResponseEntity.created(location).body(CategoryDto(category.name))
    }

    @GetMapping
    @Operation(summary = "Search for category", description = "Get a list of category by name. Return empty list if not found.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successful operation")
    ])
    fun findByName(@RequestParam("name") name: String): ResponseEntity<List<CategoryView>> {
        val list = categoryService.findByName(name).stream().map { category: Category -> CategoryView(category) }
            .collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

    @DeleteMapping
    @Operation(summary = "Delete category", description = "Delete an existing category by its ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            ApiResponse(responseCode = "404", description = "Category not found"
        )]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@RequestParam("id") id: Long) {
        categoryService.deleteById(id)
    }

}