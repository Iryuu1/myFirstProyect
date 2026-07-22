package com.edu.iub.myfirstproyect.controller

import com.edu.iub.myfirstproyect.dto.LoginRequest
import com.edu.iub.myfirstproyect.dto.RegisterRequest
import com.edu.iub.myfirstproyect.dto.TokenResponse
import com.edu.iub.myfirstproyect.dto.UserResponse
import com.edu.iub.myfirstproyect.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService) {

    @PostMapping("/register")
    open fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).
        body(authService.register(request))
    }

    @PostMapping("/login")
    open fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(authService.login(request))
    }
}