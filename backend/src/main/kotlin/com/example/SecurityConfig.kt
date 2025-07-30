package com.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    val logger = KotlinLogging.logger {}

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        return filterWithOauthEnabled(http)
        return filterWithOauthDisabled(http)
    }

    private fun filterWithOauthEnabled(http: HttpSecurity): SecurityFilterChain {
        logger.info { "########################## Oauth enabled ##########################" }
        http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers(
                        "/health",
                        "/actuator/**",
//                        "/api/button-click",
                    ).permitAll()
                    .anyRequest().authenticated()
            }

            .csrf { it.disable() }
            .oauth2Login { oauth2 ->
                oauth2.defaultSuccessUrl("http://localhost:5173", true)
                    .failureUrl("/login?error")
            }
        return http.build()
    }

    private fun filterWithOauthDisabled(http: HttpSecurity): SecurityFilterChain {
        logger.info { "########################## Oauth disabled ##########################" }
        http
            .authorizeHttpRequests { it.anyRequest().permitAll() }
            .csrf { it.disable() }
            .oauth2Login { it.disable() }
        return http.build()
    }

}

