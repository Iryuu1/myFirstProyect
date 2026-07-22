package com.edu.iub.myfirstproyect.service

import com.edu.iub.myfirstproyect.dto.UserResponse
import com.edu.iub.myfirstproyect.dto.user.UpdateProfileRequest
import com.edu.iub.myfirstproyect.model.User
import com.edu.iub.myfirstproyect.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getProfile(currentEmail: String): UserResponse {
        val user = findUserByEmail(currentEmail)
        return user.toResponse()
    }

    fun updateProfile(currentEmail: String, request: UpdateProfileRequest): UserResponse {
        val user = findUserByEmail(currentEmail)

        request.email?.let { newEmail ->
            val normalizedEmail = newEmail.trim().lowercase()
            if (userRepository.existsByEmailAndIdNot(normalizedEmail, requireNotNull(user.id))) {
                throw ResponseStatusException(HttpStatus.CONFLICT, "Email already exists")
            }
            user.email = normalizedEmail
        }

        request.fullName?.let { user.fullName = it.trim() }

        return userRepository.save(user).toResponse()
    }

    private fun findUserByEmail(email: String) =
        userRepository.findByEmail(email)
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")

    private fun User.toResponse(): UserResponse {
        return UserResponse(
            id = requireNotNull(id),
            email = email,
            fullName = fullName,
            active = active,
            role = role,
            createdAt = createdAt
        )
    }
}
