package com.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val corsConfigurationSource: CorsConfigurationSource,
    private val oauth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler
) {
    val logger = KotlinLogging.logger {}

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return if (System.getProperty("spring.profiles.active") == "local") {
            logger.info { "########################## local ##########################" }
            filterWithOauthEnabled(http)
        } else {
            logger.info { "########################## non-local ##########################" }
            filterWithOauthDisabled(http)
        }
    }

    private fun filterWithOauthEnabled(http: HttpSecurity): SecurityFilterChain {
        logger.info { "########################## Oauth enabled ##########################" }
        http
            .cors { it.configurationSource(corsConfigurationSource) }
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers(
                        "/health",
                        "/actuator/**",
                        "/api/user"
//                        "/api/button-click",
                    ).permitAll()
                    .anyRequest().authenticated()
            }

            .csrf { it.disable() }
            .oauth2Login { oauth2 ->
                oauth2
                    .successHandler(oauth2AuthenticationSuccessHandler)
                    .defaultSuccessUrl("http://localhost:5173", true)
                    .failureUrl("/login?error")
            }
        return http.build()
    }

    private fun filterWithOauthDisabled(http: HttpSecurity): SecurityFilterChain {
        logger.info { "########################## Oauth disabled ##########################" }
        http
            .cors { it.configurationSource(corsConfigurationSource) }
            .authorizeHttpRequests { it.anyRequest().permitAll() }
            .csrf { it.disable() }
            .oauth2Login { it.disable() }
        return http.build()
    }

}

