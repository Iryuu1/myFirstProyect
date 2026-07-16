package com.edu.iub.myfirstproyect.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import jakarta.persistence.EnumType

@Entity
@Table(name = "users")
open class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var email: String ="",

    @Column(nullable = false)
    var fullName: String ="",

    @Column(nullable = false)
    var password: String="",

    @Column(nullable = false)
    val active: Boolean = true,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = UserRole.USER,

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
)