package com.shoppingmall.repository

import com.shoppingmall.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>, OrderRepositoryCustom {
}