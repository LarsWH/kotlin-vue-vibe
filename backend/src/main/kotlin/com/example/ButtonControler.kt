package com.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = ["http://localhost:5173"], allowCredentials = "true")
class ButtonController {
    private val logger = KotlinLogging.logger {}

    @PostMapping("/button-click")
    fun handleButtonClick(@RequestBody request: Map<String, Any>): String {
        logger.info { "Button clicked! Timestamp: ${request["timestamp"]}" }
        return "Button click received 01 at ${request["timestamp"]}"
    }
}