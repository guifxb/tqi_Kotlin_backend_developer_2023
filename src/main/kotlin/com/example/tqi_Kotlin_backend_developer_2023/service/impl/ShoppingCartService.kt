package com.example.tqi_Kotlin_backend_developer_2023.service.impl

import com.example.tqi_Kotlin_backend_developer_2023.domain.*
import com.example.tqi_Kotlin_backend_developer_2023.repository.OrderItemRepository
import com.example.tqi_Kotlin_backend_developer_2023.service.IShoppingCartService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ShoppingCartService(
    private val productService: ProductService,
    private val saleService: SaleService,
    private val customerService: CustomerService,
    private val shoppingCart: ShoppingCart = ShoppingCart(), private val orderItemRepository: OrderItemRepository
): IShoppingCartService {

    override fun getShoppingCart(): ShoppingCart {
        return shoppingCart
    }

    override fun addToCart(barcode: String, quantity: Double): ShoppingCart {
        val orderItem = OrderItem(
            product = productService.findByBarcode(barcode),
            quantity = quantity
        )
        shoppingCart.orderItems.add(orderItem)
        return shoppingCart
    }

    override fun removeFromCart(id: Int): ShoppingCart {
        if (id > shoppingCart.orderItems.size || id < 1) {
            throw NotFoundException()
        }
        shoppingCart.orderItems.removeAt(id - 1)
        return shoppingCart
    }

    override fun calculateTotalPrice(): Double {
        // Apply discount logic here, if any
        return shoppingCart.orderItems.sumOf { it.product.price * it.quantity }
    }

    override fun checkout(cpf: String?, paymentOptions: PaymentOptions): Sale {
        if (shoppingCart.orderItems.isEmpty()) {
            throw NotFoundException()
        }
        when (paymentOptions) {
            // Implement payment procedure
            PaymentOptions.CREDIT_CARD -> { }
            PaymentOptions.CASH -> { }
            PaymentOptions.DEBIT_CARD -> { }
            PaymentOptions.PIX -> { }
        }
        val productsCopy = shoppingCart.orderItems.toMutableList()

        val sale = Sale(
            customer = if (cpf == null) null else try {
                customerService.findByCpf(cpf)
            } catch (e: NotFoundException) { // In case the customer has no account but need CPF on receipt
                Customer(cpf = cpf)
            },
            orderItems = shoppingCart.orderItems,
            paymentOptions = paymentOptions,
            time = LocalDateTime.now(),
            totalPrice = calculateTotalPrice()
        )
        saleService.save(sale)
        for (orderItem in shoppingCart.orderItems) {
            val orderBarcode = orderItem.product.barcode
            val newStock = productService.findByBarcode(orderBarcode).stock
            productService.update(orderBarcode, orderItem.product.copy(stock = newStock - orderItem.quantity))
            orderItemRepository.save(orderItem)
        }
        sale.orderItems = productsCopy
        shoppingCart.orderItems.clear()
        return sale
    }
}
