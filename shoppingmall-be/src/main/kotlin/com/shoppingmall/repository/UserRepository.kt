package com.shoppingmall.repository

import com.shoppingmall.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository: JpaRepository<User, Long> {

    @EntityGraph(attributePaths = ["roles"])
    @Query("""
                SELECT u FROM User u
                JOIN u.roles r
                WHERE u.delYn = :delYn
                AND r.id = 2
                AND NOT EXISTS (
                    SELECT 1 FROM User u2
                    JOIN u2.roles r2
                    WHERE u2.id = u.id AND r2.id = 1
                )
            """)    // ROLE_USER = 1, ROLE_USER = 2
    fun findUsersByDelYnAndRoleIsUser(@Param("delYn") delYn: String, pageable: Pageable): Page<User>

    fun existsByEmail(email: String): Boolean

    @EntityGraph(attributePaths = ["roles"])
    fun findByEmail(email: String): User?
}