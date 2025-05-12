package com.shoppingmall.repository

import com.shoppingmall.entity.Cart
import com.shoppingmall.entity.CartItem
import com.shoppingmall.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CartItemRepository: JpaRepository<CartItem, Long> {

    fun findByCartAndProduct(cart: Cart, product: Product): CartItem?

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = :cartId and ci.product.id IN :productIds")
    fun deleteByCardIdAndProductIds(@Param("cartId") cartId: Long, @Param("productIds") productIds:List<Long>)


}