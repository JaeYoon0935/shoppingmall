package com.shoppingmall.config

import com.shoppingmall.filter.JwtAuthenticationFilter
import com.shoppingmall.util.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class JwtFilterConfig {

    @Bean
    fun jwtAuthenticationFilter(
        jwtUtil: JwtUtil,
        @Lazy userDetailsService: UserDetailsService
    ): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(jwtUtil, userDetailsService)
    }
}