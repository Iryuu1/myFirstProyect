package com.edu.iub.myfirstproyect.dto.user

import jakarta.validation.constraints.Email

data class UpdateProfileRequest(
    @field:Email
    val email: String? = null,

    val fullName: String? = null
)
