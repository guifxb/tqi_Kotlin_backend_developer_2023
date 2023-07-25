package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.time.LocalDateTime


class ShoppingCartServiceTest {

    @Mock
    private lateinit var productService: ProductService

    @Mock
    private lateinit var saleService: SaleService

    @Mock
    private lateinit var customerService: CustomerService

    @Mock
    private lateinit var orderItemService: OrderItemService

    private lateinit var shoppingCart: ShoppingCart
    private lateinit var shoppingCartService: ShoppingCartService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        shoppingCart = ShoppingCart()
        shoppingCartService = ShoppingCartService(
            productService,
            saleService,
            customerService,
            orderItemService,
            shoppingCart
        )
    }


    @Test
    fun testAddToCart() {
        // Given
        val productBarcode = "123456789"
        val product = Product(1L, productBarcode, "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, null)
        val quantity = 2.0

        Mockito.`when`(productService.findByBarcode(productBarcode)).thenReturn(product)

        // When
        val result = shoppingCartService.addToCart(productBarcode, quantity)

        // Then
        assertThat(result.orderItems).hasSize(1)
        assertThat(result.orderItems[0].product).isEqualTo(product)
        assertThat(result.orderItems[0].quantity).isEqualTo(quantity)
    }

    @Test
    fun testRemoveFromCart() {
        // Given
        val productBarcode = "123456789"
        val product = Product(1L, productBarcode, "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, null)
        val quantity = 2.0
        shoppingCart.orderItems.add(OrderItem(1, product, quantity))

        // When
        val result = shoppingCartService.removeFromCart(1)

        // Then
        assertThat(result.orderItems).isEmpty()
    }

    @Test
    fun testCalculateTotalPrice() {
        // Given
        val product1 = Product(1L, "123456789", "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, null)
        val product2 = Product(2L, "987654321", "Mouse", MeasurementUnit.UNID, BigDecimal(50), 20.0, null)
        val quantity1 = 2.0
        val quantity2 = 3.0
        shoppingCart.orderItems.add(OrderItem(1, product1, quantity1))
        shoppingCart.orderItems.add(OrderItem(2, product2, quantity2))

        // When
        val result = shoppingCartService.calculateTotalPrice()

        // Then
        assertThat(result).isEqualTo((product1.price * BigDecimal(quantity1)) + (product2.price * BigDecimal.valueOf(quantity2)))
    }

    @Test
    fun testCheckout() {
        // Given
        val productBarcode = "123456789"
        val product = Product(1L, productBarcode, "Laptop", MeasurementUnit.UNID, BigDecimal(1200), 10.0, null)
        val quantity = 2.0
        shoppingCart.orderItems.add(OrderItem(1, product, quantity))

        val customerCpf = "12345678910"
        val paymentOptions = PaymentOptions.CREDIT_CARD
        val totalPrice = (product.price * BigDecimal.valueOf(quantity))

        Mockito.`when`(productService.findByBarcode(productBarcode)).thenReturn(product)
        Mockito.`when`(customerService.findByCpf(customerCpf)).thenReturn(Customer(1L, cpf = customerCpf))


        // When
        val sale = shoppingCartService.checkout(customerCpf, paymentOptions)

        // Then
        assertThat(sale.customer?.cpf).isEqualTo(customerCpf)
        assertThat(sale.orderItems).hasSize(1)
        assertThat(sale.orderItems[0].product).isEqualTo(product)
        assertThat(sale.orderItems[0].quantity).isEqualTo(quantity)
        assertThat(sale.paymentOptions).isEqualTo(paymentOptions)
        assertThat(sale.totalPrice).isEqualTo(totalPrice)
        assertThat(sale.time).isBeforeOrEqualTo(LocalDateTime.now())
    }
}
