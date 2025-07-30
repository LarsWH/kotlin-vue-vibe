package com.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/user")
    fun getUser(@AuthenticationPrincipal user: OAuth2User?): Map<String, Any?> {
        logger.info { "User info requested for: ${user?.getAttribute<String>("login")}" }

        return if (user != null) {
            mapOf(
                "authenticated" to true,
                "username" to user.getAttribute<String>("login"),
                "id" to user.getAttribute<Int>("id"),
                "email" to user.getAttribute<String>("email"),
                "name" to user.getAttribute<String>("name"),
                "avatarUrl" to user.getAttribute<String>("avatar_url")
            )
        } else {
            mapOf("authenticated" to false)
        }
    }
}