package com.edu.iub.myfirstproyect.repository

import com.edu.iub.myfirstproyect.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {
    fun findAllByOwnerId(ownerId: Long): List<Task>
    fun findByIdAndOwnerId(id: Long, ownerId: Long): Task?
}
