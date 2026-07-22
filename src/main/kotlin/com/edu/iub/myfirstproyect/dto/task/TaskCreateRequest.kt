package com.edu.iub.myfirstproyect.dto.task

import com.edu.iub.myfirstproyect.model.TaskPriority
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class TaskCreateRequest(
    @field:NotBlank
    val title: String = "",

    val description: String? = null,

    @field:NotNull
    val priority: TaskPriority? = null
)
