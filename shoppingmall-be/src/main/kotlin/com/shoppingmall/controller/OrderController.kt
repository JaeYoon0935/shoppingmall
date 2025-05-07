package com.shoppingmall.controller

import com.shoppingmall.dto.OrderItemDto
import com.shoppingmall.dto.OrderRequestDto
import com.shoppingmall.dto.OrderResponseDto
import com.shoppingmall.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/order")
class OrderController(
    private val orderService: OrderService
)
{
    @PostMapping
    fun handlePayment(@RequestBody param: OrderRequestDto): ResponseEntity<OrderResponseDto>{
        val order = orderService.handlePayment(param)
        return ResponseEntity.ok(order)
    }

    @GetMapping("{id}")
    fun getOrder(@PathVariable id: Long, @RequestHeader("Authorization") authHeader: String):ResponseEntity<OrderResponseDto>{
        val token = authHeader.removePrefix("Bearer ").trim()
        val order = orderService.getOrder(id, token)
        return ResponseEntity.ok(order)
    }


}