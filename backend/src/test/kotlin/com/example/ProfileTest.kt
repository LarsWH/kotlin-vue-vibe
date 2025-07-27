package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment

@SpringBootTest
//@ActiveProfiles("local")
class ProfileTest {

    @Autowired
    lateinit var env: Environment

    @Test
    fun printProfile() {
        assertEquals("local","${env.activeProfiles.joinToString()}")
    }
}
