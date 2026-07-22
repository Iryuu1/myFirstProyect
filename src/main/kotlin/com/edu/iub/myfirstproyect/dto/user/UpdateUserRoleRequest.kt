package com.edu.iub.myfirstproyect.dto.user

import com.edu.iub.myfirstproyect.model.UserRole
import jakarta.validation.constraints.NotNull

data class UpdateUserRoleRequest(
    @field:NotNull
    val role: UserRole? = null
)
