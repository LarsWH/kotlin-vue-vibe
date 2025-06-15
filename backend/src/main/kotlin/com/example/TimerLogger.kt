package com.example

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TimerLogger {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedRate = 3000)
    fun logEveryThreeSeconds() {
        logger.info("Timer:  ${System.currentTimeMillis() % 100000}")
    }
}