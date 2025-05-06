package com.shoppingmall.service

import com.shoppingmall.dto.LoginRequestDto
import com.shoppingmall.dto.LoginResponseDto
import com.shoppingmall.dto.SignupRequestDto
import com.shoppingmall.dto.UserDto
import com.shoppingmall.entity.User
import com.shoppingmall.extension.toDto
import com.shoppingmall.repository.RoleRepository
import com.shoppingmall.repository.UserRepository
import com.shoppingmall.util.JwtUtil
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("해당 이메일로 가입된 계정이 없습니다.")

        val authorities = user.roles.map { SimpleGrantedAuthority(it.name) }

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            authorities
        )
    }

    fun login(param: LoginRequestDto): LoginResponseDto {
        val user = userRepository.findByEmail(param.email)?: throw IllegalArgumentException("해당 이메일로 가입된 계정 정보가 없습니다.")

        passwordEncoder.encode(param.password).equals(user.password)
        if(!passwordEncoder.matches(param.password, user.password)){
            throw BadCredentialsException("비밀번호가 일치하지 않습니다.")
        }

        val roles = user.roles.map { it.name }
        val token: String = jwtUtil.generateToken(user.email, roles)

        return LoginResponseDto( user.id,user.email, user.name, roles, token)
    }


    @Transactional
    fun signUp(param: SignupRequestDto): UserDto{
        if (userRepository.existsByEmail(param.email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다.")
        }

        val encodedPassword = passwordEncoder.encode(param.password)

        val defaultRole = roleRepository.findByName("ROLE_USER")
            ?: throw IllegalStateException("ROLE_USER 권한이 존재하지 않습니다.")

        val user = User(
            email = param.email,
            password = encodedPassword,
            name = param.name,
            address = param.address,
            phone = param.phone,
            roles = mutableSetOf(defaultRole)
        )

        return userRepository.save(user).toDto()
    }

    fun getUserList(pageable: Pageable): Page<UserDto> {
        return userRepository.findByDelYn("N", pageable).map{it.toDto()}
    }

    fun getUser(id: Long): UserDto{
        val user = userRepository.findById(id).orElseThrow{ IllegalArgumentException("사용자를 찾을 수 없습니다.") }
        return user.toDto()
    }

    fun getUserByEmail(email: String): UserDto{
        val user = userRepository.findByEmail(email) ?: throw  IllegalArgumentException("사용자를 찾을 수 없습니다.")
        return user.toDto()
    }

    @Transactional
    fun modUser(param: UserDto): UserDto{
        val id: Long = param.id
        val user = userRepository.findById(id).orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다.") }

        user.name = param.name
        user.address = param.address
        user.phone = param.phone

        return user.toDto()
    }

    @Transactional
    fun delUser(id: Long):UserDto{
        val user = userRepository.findById(id).orElseThrow{ IllegalArgumentException("사용자를 찾을 수 없습니다.") }
        user.delYn = "Y"
        return user.toDto()
    }

}