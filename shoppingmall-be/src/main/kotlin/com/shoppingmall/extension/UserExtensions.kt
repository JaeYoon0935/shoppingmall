package com.shoppingmall.extension

import com.shoppingmall.dto.UserDto
import com.shoppingmall.entity.User

fun User.toDto(): UserDto {
    return UserDto(
        id = this.id,
        name = this.name,
        address = this.address,
        phone = this.phone,
        email = this.email,
        delYn = this.delYn,
        regDts = this.regDts,
        regUser = this.regUser,
        modDts = this.modDts,
        modUser = this.modUser,
        roles = roles.map { it.name }
    )
}
