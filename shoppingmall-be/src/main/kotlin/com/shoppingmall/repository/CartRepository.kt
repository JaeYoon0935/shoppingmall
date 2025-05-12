package com.shoppingmall.repository

import com.shoppingmall.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository: JpaRepository<Cart, Long> {

    fun findByUserId(userId: Long): Cart?
}