package com.shoppingmall.repository

import com.shoppingmall.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByDelYn(delYn: String, pageable: Pageable): Page<User>

    fun existsByEmail(email: String): Boolean
}