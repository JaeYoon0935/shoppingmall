package com.shoppingmall.dto

import jakarta.transaction.Status
import java.time.LocalDateTime

data class OrderRequestDto(
    val userId: Long,
    val orderItems: List<OrderItemDto>,
    val totalPrice: Int,
)

data class OrderResponseDto(
    val id: Long,
    val totalPrice: Int,
    val orderDate: LocalDateTime
)

data class OrderItemDto(
    val productId: Long,
    val quantity: Int,
    val orderPrice: Int
)

data class OrderDetailDto(
    val orderId: Long,
    val orderDate: LocalDateTime,
    val totalPrice: Int,
    val status: String,
    val orderItemId: Long,
    val quantity: Int,
    val orderPrice: Int,
    val productId: Long,
    val productName: String,
    val imagePath: String?

)