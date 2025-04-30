package com.shoppingmall.dto

data class UserDto(
    val id: Long,
    val name: String,
    val address: String?,
    val phone: String?,
    val email: String,
    val delYn: String
)
