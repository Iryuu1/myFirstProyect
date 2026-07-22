package com.edu.iub.myfirstproyect.service

import com.edu.iub.myfirstproyect.model.User
import com.edu.iub.myfirstproyect.dto.LoginRequest
import com.edu.iub.myfirstproyect.dto.RegisterRequest
import com.edu.iub.myfirstproyect.dto.TokenResponse
import com.edu.iub.myfirstproyect.dto.UserResponse
import com.edu.iub.myfirstproyect.exception.InvalidCredentialsException
import com.edu.iub.myfirstproyect.exception.DuplicateResourceException
import com.edu.iub.myfirstproyect.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {
     fun register(request: RegisterRequest): UserResponse{
        val email = request.Email.trim().lowercase()
        if (userRepository.existsByEmail(email)){
            throw DuplicateResourceException("Email already exists")
        }

        val user = User(
            email=email,
            fullName= request.fullName.trim(),
            password = passwordEncoder.encode(request.password)!!
        )
        return userRepository.save(user).toResponse()
    }
    fun login(request: LoginRequest): TokenResponse {
        val email = request.email.trim().lowercase()
        val user = userRepository.findByEmail(email)
            ?: throw InvalidCredentialsException("Invalid credentials")

        if (!user.active || !passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialsException("Invalid credentials")
        }

        val token = jwtService.generateToken(user)
        return TokenResponse(
            accessToken = token,
            expiresIn = jwtService.expirationMinutes * 60
        )
    }
    fun User.toResponse(): UserResponse{
        return UserResponse(
            id= requireNotNull(id),
            email = email,
            fullName = fullName,
            active = active,
            role= role,
            createdAt = createdAt,
        )
    }
}