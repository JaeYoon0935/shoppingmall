package com.shoppingmall.repository

import com.shoppingmall.dto.OrderDetailDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface OrderRepositoryCustom {
    fun getUserOrders(id: Long) : List<OrderDetailDto>
    fun getAllUserOrders(pageable: Pageable) : Page<OrderDetailDto>
}