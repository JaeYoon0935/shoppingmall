package com.shoppingmall.dto

data class SignupRequestDto(
    val email: String,
    val password: String,
    val name: String,
    val address: String?,
    val phone: String?
)
