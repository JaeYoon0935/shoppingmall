package com.shoppingmall.config

object AuthWhitelist {
    val PATH_PATTERNS = listOf(
        "/api/auth/**",
        "/api/products",
        "/api/products/**",
        "/uploads/**"
    )

    val REGEX_PATTERNS = listOf(
        "^/api/auth/.*".toRegex(),
        "^/api/products.*".toRegex(),
        "^/uploads/.*".toRegex()
    )
}