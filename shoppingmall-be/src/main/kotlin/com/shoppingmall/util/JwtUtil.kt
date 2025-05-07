package com.shoppingmall.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {

    companion object {
        private const val SECRET = "abcdefghijklmnopqrstuvwxyz123456" // 최소 32자 이상
        private const val EXPIRATION_TIME = 1000 * 60 * 60 // 1시간
    }

    private lateinit var secretKey: SecretKey

    @PostConstruct
    fun init() {
        secretKey = Keys.hmacShaKeyFor(SECRET.toByteArray())
    }

    fun generateToken(username: String, roles: List<String>, id: Long): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .claim("id", id)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(secretKey, SignatureAlgorithm.HS256) // key 객체로 서명
            .compact()
    }

    fun extractUsername(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

/*    fun extractRoles(token: String): List<String> {
        val claims = extractAllClaims(token)
        return claims["roles"] as List<String>
    }*/

    fun extractRoles(token: String): List<String> {
        val claims = extractAllClaims(token)
        val rawRoles = claims["roles"]

        // 타입 캐스팅 안전 처리
        return when (rawRoles) {
            is List<*> -> rawRoles.mapNotNull { it?.toString() }
            is String -> listOf(rawRoles)
            null -> emptyList()
            else -> throw IllegalArgumentException("Invalid roles format in token")
        }
    }

    fun extractUserId(token: String): Long {
        val claims = extractAllClaims(token)
        val id = claims["id"]

        // 타입 캐스팅 안전 처리
        return when (id) {
            is Int -> id.toLong()
            is Long -> id
            is Double -> id.toLong()
            is String -> id.toLongOrNull() ?: throw RuntimeException("Invalid ID format")
            else -> throw RuntimeException("Invalid or missing user ID in token")
        }
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey) // 서명에 사용된 비밀 키
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}
