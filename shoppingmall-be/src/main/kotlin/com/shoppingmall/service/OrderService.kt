package com.shoppingmall.service

import com.shoppingmall.dto.OrderRequestDto
import com.shoppingmall.dto.OrderResponseDto
import com.shoppingmall.entity.Order
import com.shoppingmall.entity.OrderItem
import com.shoppingmall.extension.toOrderResponseDto
import com.shoppingmall.repository.OrderRepository
import com.shoppingmall.repository.ProductRepository
import com.shoppingmall.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {

    @Transactional
    fun handlePayment(param: OrderRequestDto): OrderResponseDto {
        val user = userRepository.findById(param.userId).orElseThrow { RuntimeException("사용자가 존재하지 않습니다.") }

        val orderItems = param.orderItems.map{ item ->
            val product = productRepository.findById(item.productId).orElseThrow { RuntimeException("상품이 존재하지 않습니다.") }

            if(item.orderPrice != product.price){
                throw IllegalArgumentException("결제 상품의 가격을 다시 확인해주세요.")
            }

            OrderItem(
                productId = product.id,
                quantity = item.quantity,
                orderPrice = product.price
            )
        }

        var totalPrice = 0
        for (item in orderItems){
            totalPrice += item.orderPrice * item.quantity
        }

        val order = Order(
            user = user,
            orderDate = LocalDateTime.now(),
            totalPrice = totalPrice,
            status = "PAID"
        )

        orderItems.forEach { it.order = order } //각 orderItem에 order 엔티티 연결
        order.orderItems.addAll(orderItems)

        return orderRepository.save(order).toOrderResponseDto()

    }
}