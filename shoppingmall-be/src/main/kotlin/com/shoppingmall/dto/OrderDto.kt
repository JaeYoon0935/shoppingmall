package com.shoppingmall.dto

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