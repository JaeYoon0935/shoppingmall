package com.shoppingmall.controller

import com.shoppingmall.dto.UserDto
import com.shoppingmall.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUser(@RequestParam email: String): ResponseEntity<UserDto>{
        val user = userService.getUserByEmail(email)
        return ResponseEntity.ok(user)
    }

}