package com.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = ["http://localhost:5173"], allowCredentials = "true")
class ButtonController {
    private val logger = KotlinLogging.logger {}

    @PostMapping("/button-click")
    fun handleButtonClick(
        @RequestBody request: Map<String, Any>,
        @AuthenticationPrincipal user: OAuth2User?
    ): String {
        val username = user?.getAttribute<String>("login") ?: "anonymous"
        logger.info { "Button clicked by GitHub user: $username, Timestamp: ${request["timestamp"]}" }
        return "Button click received from $username at ${request["timestamp"]}"
    }
}