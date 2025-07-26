package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("idea")
open class DatabaseIntegrationTest @Autowired constructor(
    val jdbcTemplate: JdbcTemplate
) {
    @Test
    @Transactional
    open fun `write and delete value in database`() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS test_table (id SERIAL PRIMARY KEY, value TEXT)")
        val value = "test-value"

        // Insert value
        jdbcTemplate.update("INSERT INTO test_table (value) VALUES (?)", value)
        val count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM test_table WHERE value = ?",
            Int::class.java, value
        )
        assertEquals(1, count)

        // Delete value
        jdbcTemplate.update("DELETE FROM test_table WHERE value = ?", value)
        val countAfterDelete = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM test_table WHERE value = ?",
            Int::class.java, value
        )
        assertEquals(0, countAfterDelete)
    }
}