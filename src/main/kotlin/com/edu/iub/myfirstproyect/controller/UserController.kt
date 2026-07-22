package com.edu.iub.myfirstproyect.controller

import com.edu.iub.myfirstproyect.dto.MessageResponse
import com.edu.iub.myfirstproyect.dto.UserResponse
import com.edu.iub.myfirstproyect.dto.user.ChangePasswordRequest
import com.edu.iub.myfirstproyect.dto.user.UpdateProfileRequest
import com.edu.iub.myfirstproyect.dto.user.UpdateUserRoleRequest
import com.edu.iub.myfirstproyect.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun me(authentication: Authentication): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getProfile(authentication.name))
    }

    @PutMapping("/me")
    fun update(
        @Valid @RequestBody request: UpdateProfileRequest,
        authentication: Authentication
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.updateProfile(authentication.name, request))
    }

    @PostMapping("/me/change-password")
    fun changePassword(
        @Valid @RequestBody request: ChangePasswordRequest,
        authentication: Authentication
    ): ResponseEntity<MessageResponse> {
        userService.changePassword(authentication.name, request)
        return ResponseEntity.ok(MessageResponse("Password changed successfully"))
    }

    @GetMapping
    fun list(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @PutMapping("/{id}/role")
    fun updateRole(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateUserRoleRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.updateRole(id, request))
    }
}
