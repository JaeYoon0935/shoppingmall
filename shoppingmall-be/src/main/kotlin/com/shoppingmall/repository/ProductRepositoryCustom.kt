package com.shoppingmall.repository
import com.shoppingmall.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryCustom {
    fun findNotDeletedWithCategory(pageable: Pageable): Page<Product>
}