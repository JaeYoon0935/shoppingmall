package com.shoppingmall.repository

import com.shoppingmall.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role,Long> {
    fun findByName(name: String): Role?
}