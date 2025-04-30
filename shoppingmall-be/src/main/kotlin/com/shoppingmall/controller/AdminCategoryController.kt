package com.shoppingmall.controller

import com.shoppingmall.dto.CategoryDto
import com.shoppingmall.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin/categories")
class AdminCategoryController(
    private val productService: ProductService
) {

    // 카테고리 전체 조회
    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryDto>> {
        val categories = productService.getAllCategories()
        return ResponseEntity.ok(categories)
    }
}
