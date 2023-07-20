package com.example.tqi_Kotlin_backend_developer_2023.exception

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<String?> {
        return ResponseEntity<String?>(ex.message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleJdbcSQLIntegrityConstraintViolationException(ex: SQLIntegrityConstraintViolationException): ResponseEntity<String> {
        val errorMessage = "Bad request: ${
            ex.message?.substringBefore(":")?.trim()
                ?: "Unique identifier conflicts"
        }"
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNoContentException(): ResponseEntity<String> {
        return ResponseEntity("Resource ID not found.", HttpStatus.NOT_FOUND)
    }
}


