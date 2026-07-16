package com.edu.iub.myfirstproyect.dto

import com.edu.iub.myfirstproyect.model.UserRole
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val email: String,
    val fullName: String,
    val active: Boolean,
    val role: UserRole,
    val createdAt: LocalDateTime,
)
