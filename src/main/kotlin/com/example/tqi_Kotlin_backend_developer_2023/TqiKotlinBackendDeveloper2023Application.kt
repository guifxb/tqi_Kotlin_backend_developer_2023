package com.example.tqi_Kotlin_backend_developer_2023

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(servers = [Server(url = "/", description = "Default Server URL")])
@SpringBootApplication
class TqiKotlinBackendDeveloper2023Application

fun main(args: Array<String>) {
	runApplication<TqiKotlinBackendDeveloper2023Application>(*args)
}