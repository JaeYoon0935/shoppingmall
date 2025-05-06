package com.shoppingmall.controller

import com.shoppingmall.dto.OrderRequestDto
import com.shoppingmall.dto.OrderResponseDto
import com.shoppingmall.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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


}