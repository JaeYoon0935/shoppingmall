package com.shoppingmall.repository

import com.shoppingmall.entity.Cart
import com.shoppingmall.entity.CartItem
import com.shoppingmall.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CartRepository: JpaRepository<Cart, Long> {

    fun findByUserId(userId: Long): Cart?

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
    fun findItemsByCartId(@Param("cartId") cartId: Long): List<CartItem>

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    fun findItemByCartIdAndProductId(
        @Param("cartId") cartId: Long,
        @Param("productId") productId: Long
    ): CartItem?

}