package com.shoppingmall.controller

import com.shoppingmall.dto.CategoryDto
import com.shoppingmall.dto.ProductDto
import com.shoppingmall.dto.ProductRequestDto
import com.shoppingmall.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService
) {

    // 상품 전체 조회
    @GetMapping
    fun getAllProducts(@RequestParam(defaultValue = "0") page: Int,
                             @RequestParam(defaultValue = "10") size : Int ): ResponseEntity<Page<ProductDto>> {
        val pageable = PageRequest.of(page, size, Sort.by("id").ascending())
        val products = productService.getAllProducts(pageable)
        return ResponseEntity.ok(products)
    }

    // 상품 단건 조회
    @GetMapping("{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val product = productService.getProduct(id)
        return ResponseEntity.ok(product)
    }

    // 상품 등록
    @PostMapping(consumes = ["multipart/form-data"])
    fun regProduct(@ModelAttribute param: ProductRequestDto): ResponseEntity<ProductDto> {
        val product = productService.regProduct(param)
        return ResponseEntity.ok(product)
    }

    // 상품 수정
    @PutMapping("{id}", consumes = ["multipart/form-data"])
    fun modProduct(@PathVariable id: Long, @ModelAttribute param: ProductRequestDto) :ResponseEntity<ProductDto> {
        val product = productService.modProduct(id, param);
        return ResponseEntity.ok(product)
    }

    // 상품 삭제
    @PutMapping("{id}/delete")
    fun delProduct(@PathVariable id:Long): ResponseEntity<ProductDto>{
        val product = productService.delProduct(id)
        return ResponseEntity.ok(product)
    }

    // 카테고리 전체 조회
    @GetMapping("/categories")
    fun getAllCategories(): ResponseEntity<List<CategoryDto>> {
        val categories = productService.getAllCategories()
        return ResponseEntity.ok(categories)
    }

}