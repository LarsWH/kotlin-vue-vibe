package com.example

//import org.springframework.scheduling.annotation.EnableScheduling
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
open class Application

fun main(args: Array<String>) {
    val logger = KotlinLogging.logger {}
    logger.info("########################## Starting Application ##########################")
    logger.info("###  Active profiles: ${System.getProperty("spring.profiles.active")}")
    runApplication<Application>(*args)
}