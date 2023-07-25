package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.domain.Customer
import com.example.tqi_Kotlin_backend_developer_2023.repository.CustomerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

class CustomerServiceTest {

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @InjectMocks
    private lateinit var customerService: CustomerService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testSaveCustomer() {
        // Given
        val customerToSave = Customer(1L, "John Doe", "john@example.com", "12345678900")
        val savedCustomer = Customer(1L, "John Doe", "john@example.com", "12345678900")

        Mockito.`when`(customerRepository.save(customerToSave)).thenReturn(savedCustomer)

        // When
        val result = customerService.save(customerToSave)

        // Then
        assertThat(result).isEqualTo(savedCustomer)
    }

    @Test
    fun testFindByCpf() {
        // Given
        val cpf = "12345678900"
        val customer = Customer(1L, "John Doe", "john@example.com", cpf)

        Mockito.`when`(customerRepository.findByCpf(cpf)).thenReturn(customer)

        // When
        val result = customerService.findByCpf(cpf)

        // Then
        assertThat(result).isEqualTo(customer)
    }

    @Test
    fun testFindByCpfThrowsNotFoundException() {
        // Given
        val cpf = "12345678900"

        Mockito.`when`(customerRepository.findByCpf(cpf)).thenReturn(null)

        // When/Then
        assertThrows<NotFoundException> {
            customerService.findByCpf(cpf)
        }
    }

    @Test
    fun testFindByEmail() {
        // Given
        val email = "john@example.com"
        val customer = Customer(1L, "John Doe", email, "12345678900")

        Mockito.`when`(customerRepository.findByEmail(email)).thenReturn(customer)

        // When
        val result = customerService.findByEmail(email)

        // Then
        assertThat(result).isEqualTo(customer)
    }

    @Test
    fun testFindByEmailThrowsNotFoundException() {
        // Given
        val email = "john@example.com"

        Mockito.`when`(customerRepository.findByEmail(email)).thenReturn(null)

        // When/Then
        assertThrows<NotFoundException> {
            customerService.findByEmail(email)
        }
    }

    @Test
    fun testDeleteByCpf() {
        // Given
        val cpf = "12345678900"
        val existingCustomer = Customer(1L, "John Doe", "john@example.com", cpf)

        // Simulando o comportamento do repository quando um cliente é encontrado pelo CPF
        Mockito.`when`(customerRepository.findByCpf(cpf)).thenReturn(existingCustomer)

        // When
        customerService.deleteByCpf(cpf)

        // Then
        // Verifica se o repositório foi chamado para excluir o cliente pelo CPF
        Mockito.verify(customerRepository).deleteById(existingCustomer.id)
    }

    @Test
    fun testDeleteByCpfThrowsNotFoundException() {
        // Given
        val cpf = "12345678900"

        // Simulando o comportamento do repository quando um cliente NÃO é encontrado pelo CPF
        Mockito.`when`(customerRepository.findByCpf(cpf)).thenReturn(null)

        // When/Then
        assertThrows<NotFoundException> {
            customerService.deleteByCpf(cpf)
        }
    }


}
