package com.shoppingmall.dto

import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    val email: String,
    val address: String?,
    val name: String,
    val phone: String?,
    val birth: String?,
    val delYn: String,
    val regDts: LocalDateTime,
    val regUser: String?,
    val modDts: LocalDateTime?,
    val modUser: String?,

    val roles: List<String>
)
