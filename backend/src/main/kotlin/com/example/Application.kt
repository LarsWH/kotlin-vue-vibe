package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class Application

fun main(args: Array<String>) {
    val logger = org.slf4j.LoggerFactory.getLogger(Application::class.java)
    logger.info("########################## Starting Application ##########################");
    runApplication<Application>(*args)
}