package com.shoppingmall.repository

import com.shoppingmall.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>, ProductRepositoryCustom {
}