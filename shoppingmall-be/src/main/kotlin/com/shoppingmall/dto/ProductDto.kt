package com.shoppingmall.dto

import org.springframework.web.multipart.MultipartFile

data class ProductDto(
    val id: Long? = null,
    val name: String,
    val description: String?,
    val price: Int,
    val stock: Int,
    val imagePath: String?,
    val categoryName: String?,
    val categoryId: Long?,
    val delYn: String
)

data class ProductRequestDto(
    val name: String,
    val description: String,
    val price: Int,
    val stock: Int,
    val uploadImage: MultipartFile?,
    val categoryId: Long
)