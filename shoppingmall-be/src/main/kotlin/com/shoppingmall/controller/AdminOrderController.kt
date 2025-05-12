package com.shoppingmall.controller

import com.shoppingmall.dto.OrderDetailDto
import com.shoppingmall.service.OrderService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/admin/order")
class AdminOrderController (
    val orderService: OrderService
){

    @GetMapping
    fun getUserOrders(@RequestParam(defaultValue = "0") page: Int,
                            @RequestParam(defaultValue = "10") size : Int): ResponseEntity<Page<OrderDetailDto>>{
        val pageable = PageRequest.of(page, size, Sort.by("id").descending())
        val orders = orderService.getAllUserOrders(pageable)

        return ResponseEntity.ok(orders)
    }

}