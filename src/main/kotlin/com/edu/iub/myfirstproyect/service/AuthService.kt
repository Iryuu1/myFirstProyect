package com.edu.iub.myfirstproyect.service

import User
import com.edu.iub.myfirstproyect.dto.RegisterRequest
import com.edu.iub.myfirstproyect.dto.UserResponse
import com.edu.iub.myfirstproyect.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    open fun register(request: RegisterRequest): UserResponse{
        val email = request.Email.trim().lowercase()
        if (userRepository.existsByEmail(email)){
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "Email already exists"

            )
    }

        val user = User(
            email=email,
            fullName= request.fullName.trim(),
            password = passwordEncoder.encode(request.password)!!
        )
        return userRepository.save(user).toResponse()
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