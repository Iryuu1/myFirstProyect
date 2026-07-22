package com.edu.iub.myfirstproyect.config

import com.edu.iub.myfirstproyect.model.User
import com.edu.iub.myfirstproyect.model.UserRole
import com.edu.iub.myfirstproyect.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    override fun run(vararg args: String) {
        if (!userRepository.existsByEmail("admin@taskflow.com")) {
            val admin = User(
                email = "admin@taskflow.com",
                fullName = "System Administrator",
                password = passwordEncoder.encode("AdminSecret123")!!,
                role = UserRole.ADMIN
            )
            userRepository.save(admin)
        }
    }
}
