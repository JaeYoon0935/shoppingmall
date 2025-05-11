package com.shoppingmall.service

import com.shoppingmall.dto.CartItemDto
import com.shoppingmall.entity.Cart
import com.shoppingmall.entity.CartItem
import com.shoppingmall.repository.CartItemRepository
import com.shoppingmall.repository.CartRepository
import com.shoppingmall.repository.ProductRepository
import com.shoppingmall.repository.UserRepository
import com.shoppingmall.util.JwtUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val jwtUtil: JwtUtil
) {

    fun getCartItems(token: String): List<CartItemDto> {
        val userId = jwtUtil.extractUserId(token)
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("사용자가 존재하지 않습니다.") }

        val cart = cartRepository.findByUserId(user.id)
            ?: cartRepository.save(Cart(user = user))

        return cart.cartItems
            .sortedByDescending { it.id }
            .map{CartItemDto(it.product.id, it.quantity)
        }
    }

    @Transactional
    fun mergeCartItems(token:String, cartItems:List<CartItemDto>) {
        val userId = jwtUtil.extractUserId(token)
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("사용자가 존재하지 않습니다.") }

        val cart = cartRepository.findByUserId(user.id)
            ?: cartRepository.save(Cart(user = user))

        for (item in cartItems){
            val product = productRepository.findById(item.productId).orElseThrow { RuntimeException("상품이 존재하지 않습니다.") }

            val cartItem = cartItemRepository.findByCartAndProduct(cart, product)

            if( cartItem != null) {
                cartItem.quantity += item.quantity
            } else {
                val product = productRepository.findById(item.productId).orElseThrow { RuntimeException("상품이 존재하지 않습니다.") }

                cart.cartItems.add(
                    CartItem(cart = cart, product = product, quantity = item.quantity)
                )
            }
        }
        cartRepository.save(cart)
    }

    @Transactional
    fun updateCartItem(token: String, cartItemDto: CartItemDto) {
        val cartItem = findCartItem(token, cartItemDto.productId)
        cartItem.quantity = cartItemDto.quantity
    }

    @Transactional
    fun deleteCartItem(token: String, productId: Long) {
        val cartItem = findCartItem(token, productId)
        cartItemRepository.delete(cartItem)
    }

    fun findCartItem(token:String, productId: Long):CartItem {
        val userId = jwtUtil.extractUserId(token)
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("사용자가 존재하지 않습니다.") }
        val cart = cartRepository.findByUserId(user.id)?: throw RuntimeException("장바구니가 존재하지 않습니다.")
        val product = productRepository.findById(productId).orElseThrow { RuntimeException("상품이 존재하지 않습니다.") }

        return cartItemRepository.findByCartAndProduct(cart, product)?: throw RuntimeException("장바구니에 해당 상품이 없습니다.")
    }

}