package com.tsukor.weddinginvitation.model

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "twilio")
data class TwilioProperties(
    val accountSid: String,
    val authToken: String,
    val from: String,
)
