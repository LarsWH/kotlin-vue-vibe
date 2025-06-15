/*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)
        logger.info("########################## Configuring Security ##########################")
        http
//            .authorizeHttpRequests { it.anyRequest().permitAll() }
//            .csrf { it.disable() }
//            .oauth2Login { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/health", "/actuator/**").permitAll()
                    .anyRequest().permitAll()
            }
            .csrf { it.disable() }
            .oauth2Login { it.disable() }
        return http.build()
    }
}

 */