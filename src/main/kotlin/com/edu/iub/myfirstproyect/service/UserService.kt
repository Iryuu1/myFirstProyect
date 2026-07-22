package com.edu.iub.myfirstproyect.service

import com.edu.iub.myfirstproyect.dto.UserResponse
import com.edu.iub.myfirstproyect.dto.user.ChangePasswordRequest
import com.edu.iub.myfirstproyect.dto.user.UpdateProfileRequest
import com.edu.iub.myfirstproyect.dto.user.UpdateUserRoleRequest
import com.edu.iub.myfirstproyect.model.User
import com.edu.iub.myfirstproyect.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
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

    fun changePassword(currentEmail: String, request: ChangePasswordRequest) {
        val user = findUserByEmail(currentEmail)

        if (!passwordEncoder.matches(request.currentPassword, user.password)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is invalid")
        }

        user.password = passwordEncoder.encode(request.newPassword)!!
        userRepository.save(user)
    }

    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }

    fun updateRole(userId: Long, request: UpdateUserRoleRequest): UserResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") }

        user.role = requireNotNull(request.role)
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
