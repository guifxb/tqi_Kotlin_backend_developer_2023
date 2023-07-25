package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.domain.*
import com.example.tqi_Kotlin_backend_developer_2023.repository.CustomerRepository
import com.example.tqi_Kotlin_backend_developer_2023.repository.SaleRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.time.LocalDateTime

class SaleServiceTest {

    @Mock
    private lateinit var saleRepository: SaleRepository

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @InjectMocks
    private lateinit var saleService: SaleService

    private lateinit var customer: Customer
    private lateinit var product: Product
    private lateinit var orderItem: OrderItem
    private lateinit var sale: Sale

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        // Create sample data for testing
        customer = Customer(1L, "12345678910", "John", "Doe", "john.doe@example.com", "password")
        product = Product(1L, "123456789", "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, null)
        orderItem = OrderItem(1L, product, 2.0)
        sale = Sale(1L, customer, PaymentOptions.CREDIT_CARD, BigDecimal(2400), LocalDateTime.now())
        sale.orderItems.add(orderItem)
    }

    @Test
    fun testSaveSale() {
        // Given
        Mockito.`when`(saleRepository.save(sale)).thenReturn(sale)

        // When
        val result = saleService.save(sale)

        // Then
        assertThat(result).isEqualTo(sale)
    }

    @Test
    fun testFindAllSales() {
        // Given
        val salesList = listOf(sale)
        Mockito.`when`(saleRepository.findAll()).thenReturn(salesList)

        // When
        val result = saleService.findAll()

        // Then
        assertThat(result).isEqualTo(salesList)
    }

    @Test
    fun testFindSaleById() {
        // Given
        val saleId = 1L
        Mockito.`when`(saleRepository.findById(saleId)).thenReturn(java.util.Optional.of(sale))

        // When
        val result = saleService.findById(saleId)

        // Then
        assertThat(result).isEqualTo(sale)
    }

    @Test
    fun testFindByCustomer() {
        // Given
        val customerCpf = "12345678910"
        Mockito.`when`(customerRepository.findByCpf(customerCpf)).thenReturn(customer)
        Mockito.`when`(saleRepository.findAllByUserId(customer.id)).thenReturn(listOf(sale))

        // When
        val result = saleService.findByCustomer(customerCpf)

        // Then
        assertThat(result).containsExactly(sale)
    }

    @Test
    fun testFindByTimeInterval() {
        // Given
        val fromTime = LocalDateTime.of(2023, 1, 1, 0, 0)
        val toTime = LocalDateTime.now()
        val salesList = listOf(sale)
        Mockito.`when`(saleRepository.findByTimeInterval(fromTime, toTime)).thenReturn(salesList)

        // When
        val result = saleService.findByTimeInterval(fromTime, toTime)

        // Then
        assertThat(result).isEqualTo(salesList)
    }

    @Test
    fun testFindByPaymentOptions() {
        // Given
        val paymentOptions = PaymentOptions.CREDIT_CARD.toString()
        val salesList = listOf(sale)
        Mockito.`when`(saleRepository.findByPaymentOptions(paymentOptions)).thenReturn(salesList)

        // When
        val result = saleService.findByPaymentOptions(paymentOptions)

        // Then
        assertThat(result).isEqualTo(salesList)
    }

    @Test
    fun testDeleteSale() {
        // When
        saleService.delete(sale)

        // Then
        Mockito.verify(saleRepository).deleteById(sale.id)
    }
}
