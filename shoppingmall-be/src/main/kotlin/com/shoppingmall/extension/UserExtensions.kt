package com.shoppingmall.extension

import com.shoppingmall.dto.UserDto
import com.shoppingmall.entity.User

fun User.toDto(): UserDto {
    return UserDto(
        id = this.id,
        email = this.email,
        address = this.address,
        name = this.name,
        phone = this.phone,
        birth = this.birth,
        delYn = this.delYn,
        regDts = this.regDts,
        regUser = this.regUser,
        modDts = this.modDts,
        modUser = this.modUser,
        roles = roles.map { it.name }
    )
}
