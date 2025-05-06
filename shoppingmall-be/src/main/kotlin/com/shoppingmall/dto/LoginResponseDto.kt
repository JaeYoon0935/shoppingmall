package com.shoppingmall.dto

data class LoginResponseDto (
    val id: Long,
    val email: String,
    val name: String,
    val roles: List<String>,
    val token: String
)