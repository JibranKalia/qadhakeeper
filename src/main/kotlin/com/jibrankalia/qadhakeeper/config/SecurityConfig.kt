package com.jibrankalia.qadhakeeper.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/ping")
                    .permitAll() // Allow unauthenticated access to /ping
                    .anyRequest()
                    .authenticated() // Require authentication for other endpoints
            }
            .formLogin()
        return http.build()
    }
}
