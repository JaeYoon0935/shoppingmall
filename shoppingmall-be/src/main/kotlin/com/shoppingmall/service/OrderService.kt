package com.shoppingmall.service

import com.shoppingmall.dto.OrderDetailDto
import com.shoppingmall.dto.OrderRequestDto
import com.shoppingmall.dto.OrderResponseDto
import com.shoppingmall.entity.Order
import com.shoppingmall.entity.OrderItem
import com.shoppingmall.extension.toOrderResponseDto
import com.shoppingmall.repository.CartItemRepository
import com.shoppingmall.repository.CartRepository
import com.shoppingmall.repository.OrderRepository
import com.shoppingmall.repository.ProductRepository
import com.shoppingmall.repository.UserRepository
import com.shoppingmall.util.JwtUtil
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val jwtUtil: JwtUtil
) {

    fun getOrder(id: Long, token:String): OrderResponseDto {

        val order = orderRepository.findById(id).orElseThrow { RuntimeException("주문이 존재하지 않습니다.") }

        val requestUserId  = jwtUtil.extractUserId(token)

        if( order.user.id != requestUserId){
            throw AccessDeniedException("해당 주문에 접근할 수 없습니다.")
        }

        return order.toOrderResponseDto()
    }

    fun getUserOrders(id: Long): List<OrderDetailDto>{
        val user = userRepository.findById(id).orElseThrow { RuntimeException("사용자가 존재하지 않습니다.") }
        val orders = orderRepository.getUserOrders(user.id)
        return orders
    }

    @Transactional
    fun handlePayment(param: OrderRequestDto): OrderResponseDto {
        val user = userRepository.findById(param.userId).orElseThrow { RuntimeException("사용자가 존재하지 않습니다.") }

        val orderItems = param.orderItems.map{ item ->
            val product = productRepository.findById(item.productId).orElseThrow { RuntimeException("상품이 존재하지 않습니다.") }

            if(item.orderPrice != product.price){
                throw IllegalArgumentException("결제 상품의 가격을 다시 확인해주세요.")
            }

            OrderItem(
                product = product,
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
            status = "PAID" //결제완료
        )

        orderItems.forEach { it.order = order } //각 orderItem에 order 엔티티 연결
        order.orderItems.addAll(orderItems)

        val orderResult = orderRepository.save(order)

        // 주문 이후, 장바구니에 해당 상품 있으면 제거
        val cart = cartRepository.findByUserId(user.id) ?: throw IllegalStateException("장바구니가 없습니다.")
        val orderProductIds = orderItems.mapNotNull{ it.product?.id }

        cartItemRepository.deleteByCardIdAndProductIds(cart.id, orderProductIds)

        return orderResult.toOrderResponseDto()

    }

    fun getAllUserOrders(pageable: Pageable): Page<OrderDetailDto> {
        val orders = orderRepository.getAllUserOrders(pageable)
        return orders
    }

}