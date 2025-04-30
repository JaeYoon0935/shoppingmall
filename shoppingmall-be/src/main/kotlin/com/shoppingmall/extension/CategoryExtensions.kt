package com.shoppingmall.extension

import com.shoppingmall.dto.CategoryDto
import com.shoppingmall.entity.Category

fun Category.toDto(): CategoryDto {
    return CategoryDto(
        id = this.id,
        name = this.name
    )
}