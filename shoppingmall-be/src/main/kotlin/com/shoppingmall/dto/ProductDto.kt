package com.shoppingmall.dto

import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

data class ProductDto(
    val id: Long? = null,
    val name: String,
    val description: String?,
    val price: Int,
    val stock: Int,
    val imagePath: String?,
    val categoryName: String?,
    val categoryId: Long?,
    val delYn: String,
    val regDts: LocalDateTime?,
    val regUser: String?,
    val modDts: LocalDateTime?,
    val modUser: String?
)

data class ProductRequestDto(
    val name: String,
    val description: String,
    val price: Int,
    val stock: Int,
    val uploadImage: MultipartFile?,
    val categoryId: Long,
    val regDts: String?,
    val regUser: String?,
    val modDts: String?,
    val modUser: String?
)

data class ProductCardDto (
    val id: Long? = null,
    val name: String,
    val price: Int,
    val imagePath: String?,
    val stock: Int,
    val categoryName: String?,
    val delYn: String,
    val categoryId: Long?,
    val regDts: LocalDateTime?,
)