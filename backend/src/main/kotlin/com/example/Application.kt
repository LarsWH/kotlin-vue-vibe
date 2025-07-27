package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.runApplication
//import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class Application

fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger(Application::class.java)
    logger.info("########################## Starting Application ##########################")
    logger.info("###  Active profiles: ${System.getProperty("spring.profiles.active")}")
    runApplication<Application>(*args)
}