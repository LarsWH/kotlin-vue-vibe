package com.example

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2AuthenticationSuccessHandler : AuthenticationSuccessHandler {
    private val logger = KotlinLogging.logger {}

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oauth2User = authentication.principal as OAuth2User
        val githubUsername = oauth2User.getAttribute<String>("login")
        val githubId = oauth2User.getAttribute<Int>("id")
        val email = oauth2User.getAttribute<String>("email")

        logger.info { "========================== GITHUB AUTH SUCCESS ==========================" }
        logger.info { "GitHub Username: $githubUsername" }
        logger.info { "GitHub ID: $githubId" }
        logger.info { "Email: $email" }
        logger.info { "Authorities: ${authentication.authorities}" }
        logger.info { "All attributes: ${oauth2User.attributes}" }
        logger.info { "========================================================================" }

        // Redirect to frontend
        response.sendRedirect("http://localhost:5173")
    }
}