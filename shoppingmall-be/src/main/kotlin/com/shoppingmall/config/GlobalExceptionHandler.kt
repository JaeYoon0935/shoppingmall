package com.shoppingmall.config

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        val body = mapOf("message" to (ex.message ?: "잘못된 요청입니다."))
        return ResponseEntity.badRequest().body(body)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(ex: BadCredentialsException): ResponseEntity<Map<String, String>> {
        val body = mapOf("message" to (ex.message ?: "인증 정보가 잘못되었습니다."))
        return ResponseEntity.status(401).body(body)
    }
}
