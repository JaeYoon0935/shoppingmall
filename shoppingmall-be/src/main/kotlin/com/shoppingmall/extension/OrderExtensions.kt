package com.shoppingmall.extension

import com.shoppingmall.dto.OrderResponseDto
import com.shoppingmall.entity.Order

fun Order.toOrderResponseDto(): OrderResponseDto{
    return OrderResponseDto(
        orderId = this.id,
        totalPrice = this.totalPrice,
        orderDate = this.orderDate
    )
}