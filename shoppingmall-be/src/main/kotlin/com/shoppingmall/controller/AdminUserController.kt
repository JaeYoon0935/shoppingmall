package com.shoppingmall.controller

import com.shoppingmall.dto.UserDto
import com.shoppingmall.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/user")
class AdminUserController (
    private val userService: UserService
){
    @GetMapping
    fun getUserList(@RequestParam(defaultValue = "0") page: Int,
                          @RequestParam(defaultValue = "10") size: Int ): ResponseEntity<Page<UserDto>>{
        val pageable = PageRequest.of(page, size, Sort.by("id").ascending())
        val userList = userService.getUserList(pageable)
        return ResponseEntity.ok(userList)
    }

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDto>{
        val user = userService.getUser(id)
        return ResponseEntity.ok(user)
    }

    @PutMapping("{id}")
    fun modUser(@RequestBody param: UserDto): ResponseEntity<UserDto>{
        val user = userService.modUser(param)
        return ResponseEntity.ok(user)
    }

    @PutMapping("{id}/delete")
    fun delUser(@PathVariable id: Long): ResponseEntity<UserDto>{
        val user = userService.delUser(id)
        return ResponseEntity.ok(user)
    }

}