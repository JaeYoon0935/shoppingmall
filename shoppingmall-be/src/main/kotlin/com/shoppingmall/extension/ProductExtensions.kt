package com.shoppingmall.extension

import com.shoppingmall.dto.ProductDto
import com.shoppingmall.entity.Category
import com.shoppingmall.entity.Product

fun Product.toDto(): ProductDto {
    return ProductDto(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        stock = this.stock,
        imagePath = this.imagePath,
        categoryName = this.category?.name,
        categoryId = this.category?.id,
        delYn = this.delYn
    )
}

fun ProductDto.toEntity(category: Category): Product {
    return Product(
        name = this.name,
        price = this.price,
        stock = this.stock,
        description = this.description,
        imagePath = this.imagePath,
        category = category,
        delYn = this.delYn
    )
}