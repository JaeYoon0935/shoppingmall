package com.shoppingmall.repository
import com.shoppingmall.dto.ProductCardDto
import com.shoppingmall.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryCustom {
    fun findNotDeletedWithCategory(pageable: Pageable): Page<ProductCardDto>

    fun findRecentTopProductsByCategory(limit: Int): List<ProductCardDto>

    fun findProductsByCategory(id: Long, pageable: Pageable): Page<ProductCardDto>

    fun findByQuery(query: String, pageable: Pageable): Page<ProductCardDto>
}