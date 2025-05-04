package com.shoppingmall.service

import com.shoppingmall.dto.SignupRequestDto
import com.shoppingmall.dto.UserDto
import com.shoppingmall.entity.User
import com.shoppingmall.extension.toDto
import com.shoppingmall.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
){
    
    @Transactional
    fun signUp(param: SignupRequestDto): UserDto{
        if (userRepository.existsByEmail(param.email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다.")
        }

        val encodedPassword = passwordEncoder.encode(param.password)

        val user = User(
            email = param.email,
            password = encodedPassword,
            name = param.name,
            address = param.address,
            phone = param.phone
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