package com.shoppingmall.repository

import com.shoppingmall.dto.OrderDetailDto

interface OrderRepositoryCustom {
    fun getUserOrders(id: Long) : List<OrderDetailDto>
}