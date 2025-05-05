package com.shoppingmall.dto

import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    val name: String,
    val address: String?,
    val phone: String?,
    val email: String,
    val delYn: String,
    val regDts: LocalDateTime,
    val regUser: String?,
    val modDts: LocalDateTime?,
    val modUser: String?,

    val roles: List<String>
)
