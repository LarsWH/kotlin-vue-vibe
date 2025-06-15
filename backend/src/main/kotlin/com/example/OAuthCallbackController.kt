//package com.example
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.http.*
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.client.RestTemplate
//import org.springframework.web.servlet.view.RedirectView
//import jakarta.servlet.http.HttpSession
//
//@Controller
//class OAuthCallbackController(
//    @Value("\${GITHUB_CLIENT_ID}") private val clientId: String,
//    @Value("\${GITHUB_CLIENT_SECRET}") private val clientSecret: String
//) {
//    private val restTemplate = RestTemplate()
//    val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)
//
//    @GetMapping("/login/oauth2/code/github")
//    fun githubCallback(
//        @RequestParam code: String,
//        session: HttpSession
//    ): RedirectView {
//        logger.info("########################## Oauth 1 ##########################");
//        // Exchange code for access token
//        val tokenUrl = "https://github.com/login/oauth/access_token"
//        val tokenRequest = mapOf(
//            "client_id" to clientId,
//            "client_secret" to clientSecret,
//            "code" to code
//        )
//        val headers = HttpHeaders()
//        headers.accept = listOf(MediaType.APPLICATION_JSON)
//        val request = HttpEntity(tokenRequest, headers)
//        val tokenResponse = restTemplate.postForEntity(tokenUrl, request, Map::class.java)
//        val accessToken = tokenResponse.body?.get("access_token") as? String
//        logger.info("########################## Oauth 2 ##########################");
//
//        if (accessToken.isNullOrBlank()) {
//            return RedirectView("/login?error")
//        }
//
//        // Get user info from GitHub
//        val userHeaders = HttpHeaders()
//        userHeaders.setBearerAuth(accessToken)
//        val userRequest = HttpEntity<Void>(userHeaders)
//        val userResponse = restTemplate.exchange(
//            "https://api.github.com/user",
//            HttpMethod.GET,
//            userRequest,
//            Map::class.java
//        )
//        val userInfo = userResponse.body
//        logger.info("########################## Oauth 3 ##########################");
//
//        // Store user info and token in session
//        session.setAttribute("GITHUB_USER", userInfo)
//        session.setAttribute("GITHUB_TOKEN", accessToken)
//
//        // Redirect to home or dashboard
//        logger.info("########################## Oauth 4 ##########################");
//        return RedirectView("/")
//    }
//}