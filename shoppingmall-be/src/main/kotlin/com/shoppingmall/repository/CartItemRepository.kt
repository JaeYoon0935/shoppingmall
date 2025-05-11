package com.shoppingmall.repository

import com.shoppingmall.entity.Cart
import com.shoppingmall.entity.CartItem
import com.shoppingmall.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository: JpaRepository<CartItem, Long> {

    fun findByCartAndProduct(cart: Cart, product: Product): CartItem?
}