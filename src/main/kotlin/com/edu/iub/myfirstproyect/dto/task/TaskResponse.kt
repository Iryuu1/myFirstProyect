package com.edu.iub.myfirstproyect.dto.task

import com.edu.iub.myfirstproyect.model.TaskPriority
import com.edu.iub.myfirstproyect.model.TaskStatus
import java.time.LocalDateTime

data class TaskResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val status: TaskStatus,
    val priority: TaskPriority,
    val userId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
