package com.shoppingmall.repository

import com.shoppingmall.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}