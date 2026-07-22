package com.edu.iub.myfirstproyect.controller

import com.edu.iub.myfirstproyect.dto.UserResponse
import com.edu.iub.myfirstproyect.dto.user.UpdateProfileRequest
import com.edu.iub.myfirstproyect.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun me(authentication: Authentication): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getProfile(authentication.name))
    }

    @PutMapping("/me")
    fun update(
        @Valid @RequestBody request: UpdateProfileRequest,
        authentication: Authentication
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.updateProfile(authentication.name, request))
    }
}
