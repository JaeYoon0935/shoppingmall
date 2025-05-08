package com.shoppingmall.dto

import java.time.LocalDateTime

data class SignupRequestDto(
    val email: String,
    val password: String,
    val name: String,
    val address: String?,
    val phone: String?,
    val birth: String?,
    val regDts: LocalDateTime?,
    val regUser: String?,
)
