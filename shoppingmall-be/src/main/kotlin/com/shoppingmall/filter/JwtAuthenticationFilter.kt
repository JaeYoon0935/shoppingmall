package com.shoppingmall.filter

import com.shoppingmall.config.AuthWhitelist
import com.shoppingmall.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService // token 사용으로 현재는 미사용, DB 기반 인증 방식 대응용
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val requestURI = request.requestURI

        // 인증 없이 접근할 수 있는 경로 (로그인, 회원가입, 업로드 이미지)
        if(AuthWhitelist.REGEX_PATTERNS.any { it.matches(requestURI) }) {
            chain.doFilter(request, response)
            return
        }

        var token: String? = request.getHeader("Authorization")

        // 토큰이 없는 경우 401 반환
        if (token == null || !token.startsWith("Bearer ")) {
            responseUnauthorized(response, "No Token Provided")
            return
        }

        token = token.substring(7)
        if (jwtUtil.validateToken(token)) {
            val username = jwtUtil.extractUsername(token)
            val roles = jwtUtil.extractRoles(token)
            val authorities = roles.map { SimpleGrantedAuthority(it) }
            //val userDetails = userDetailsService.loadUserByUsername(username) // 기존에 먼저 구현하였으나, 이후 클라이언트 token 사용 하기 위해 주석처리함. (DB 접근 미사용)

            val auth = UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities
                //userDetails.authorities
            )

            SecurityContextHolder.getContext().authentication = auth
        } else {
            responseUnauthorized(response, "Invalid Token")
            return
        }

        chain.doFilter(request, response)
    }

    private fun responseUnauthorized(response: HttpServletResponse, message: String){
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.writer.write("Unauthorized: $message")
    }
}
