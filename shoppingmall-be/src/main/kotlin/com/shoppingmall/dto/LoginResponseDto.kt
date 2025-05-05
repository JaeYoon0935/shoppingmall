package com.shoppingmall.dto

data class LoginResponseDto (
    val email: String,
    val name: String,
    val roles: List<String>,
    val token: String
)