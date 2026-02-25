package com.tsukor.weddinginvitation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter

@Configuration
class AppConfig {

    @Bean
    fun isoDateTimeFormatter(): DateTimeFormatter =
        DateTimeFormatter.ISO_DATE_TIME
}