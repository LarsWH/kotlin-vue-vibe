package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController @Autowired constructor(
    private val jdbcTemplate: JdbcTemplate
) {
    @GetMapping("/health")
    fun health(): Map<String, String> {
        return try {
            jdbcTemplate.queryForObject("SELECT 1", Int::class.java)
            mapOf("status" to "UP")
        } catch (ex: Exception) {
            mapOf("status" to "DOWN", "error" to ex.message.orEmpty())
        }
    }
}

