package com.tsukor.weddinginvitation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .logout { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    // CORS preflight
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // Spring error page
                    .requestMatchers("/error").permitAll()

                    // SPA entry
                    .requestMatchers("/", "/index.html", "/confirmed/**").permitAll()

                    // Vite/React build output (most common)
                    .requestMatchers("/assets/**").permitAll()

                    // Common static files (covers favicon, manifest, etc.)
                    .requestMatchers(
                        "/*.css", "/*.js", "/*.map",
                        "/*.ico", "/*.png", "/*.jpg", "/*.jpeg", "/*.svg",
                        "/*.webp", "/*.txt", "/*.json",
                        "/robots.txt", "/favicon.ico", "/manifest.webmanifest"
                    ).permitAll()

                    // API
                    .requestMatchers("/api/**").permitAll()

                    // Everything else (choose one)
                    .anyRequest().denyAll()
                // .anyRequest().permitAll() // (if you prefer simplest for now)
            }

        return http.build()
    }
}