//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//class SecurityConfig {
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .authorizeHttpRequests { it.anyRequest().permitAll() }
//            .csrf { it.disable() }
//            .oauth2Login { it.disable() }
//        return http.build()
//    }
//}