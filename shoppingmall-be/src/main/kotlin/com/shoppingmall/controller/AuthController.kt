package com.shoppingmall.controller

import com.shoppingmall.dto.LoginRequestDto
import com.shoppingmall.dto.LoginResponseDto
import com.shoppingmall.dto.SignupRequestDto
import com.shoppingmall.dto.UserDto
import com.shoppingmall.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/signUp")
    fun signUp(@RequestBody param: SignupRequestDto): ResponseEntity<UserDto>{
        val user = userService.signUp(param)
        return ResponseEntity.ok(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody param: LoginRequestDto): ResponseEntity<LoginResponseDto>{
        val user = userService.login(param)
        return ResponseEntity.ok(user)
    }

}