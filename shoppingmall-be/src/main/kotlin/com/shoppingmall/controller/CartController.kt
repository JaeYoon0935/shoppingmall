package com.shoppingmall.controller

import com.shoppingmall.dto.CartItemDto
import com.shoppingmall.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/cart")
class CartController (
    private val cartService: CartService
){
    @GetMapping
    fun getCartItems(@RequestHeader("Authorization") authHeader: String): ResponseEntity<List<CartItemDto>> {
        val token = authHeader.removePrefix("Bearer ").trim()
        val cartItems = cartService.getCartItems(token)
        return ResponseEntity.ok(cartItems)
    }

    @PostMapping
    fun mergeCartItems(
        @RequestHeader("Authorization") authHeader: String,
        @RequestBody cartItems: List<CartItemDto>
    ): ResponseEntity<Void>{
        val token = authHeader.removePrefix("Bearer ").trim()
        cartService.mergeCartItems(token, cartItems)
        return ResponseEntity.ok().build()
    }

    @PutMapping
    fun updateCartItem(
        @RequestHeader("Authorization") authHeader: String,
        @RequestBody cartItem: CartItemDto
    ): ResponseEntity<Void>{
        val token = authHeader.removePrefix("Bearer ").trim()
        cartService.updateCartItem(token, cartItem)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("{id}")
    fun deleteCartItem(
        @RequestHeader("Authorization") authHeader: String,
        @PathVariable id: Long
    ): ResponseEntity<Void>{
        val token = authHeader.removePrefix("Bearer ").trim()
        cartService.deleteCartItem(token, id)
        return ResponseEntity.ok().build()
    }


}