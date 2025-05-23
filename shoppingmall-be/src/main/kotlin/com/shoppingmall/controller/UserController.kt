package com.shoppingmall.controller

import com.shoppingmall.dto.OrderDetailDto
import com.shoppingmall.dto.UserDto
import com.shoppingmall.service.OrderService
import com.shoppingmall.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
    private val orderService: OrderService
) {
    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDto>{
        val user = userService.getUser(id)
        return ResponseEntity.ok(user)
    }

    @PutMapping("{id}")
    fun modUser(@PathVariable id: Long,
                      @RequestBody param: UserDto): ResponseEntity<UserDto>{
        val user = userService.modUser(id, param)
        return ResponseEntity.ok(user)
    }

    @GetMapping("{id}/orders")
    fun getUserOrders(@PathVariable id: Long) :ResponseEntity<List<OrderDetailDto>>{
        val orders = orderService.getUserOrders(id)
        return ResponseEntity.ok(orders)
    }

}