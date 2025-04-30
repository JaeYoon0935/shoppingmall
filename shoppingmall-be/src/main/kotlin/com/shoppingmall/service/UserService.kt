package com.shoppingmall.service

import com.shoppingmall.dto.UserDto
import com.shoppingmall.extension.toDto
import com.shoppingmall.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository
){
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