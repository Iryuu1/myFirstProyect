package com.edu.iub.myfirstproyect.dto.task

import com.edu.iub.myfirstproyect.model.TaskPriority
import com.edu.iub.myfirstproyect.model.TaskStatus

data class TaskUpdateRequest(
    val title: String? = null,
    val description: String? = null,
    val status: TaskStatus? = null,
    val priority: TaskPriority? = null
)
