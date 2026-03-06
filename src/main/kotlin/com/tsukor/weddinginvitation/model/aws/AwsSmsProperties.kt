package com.tsukor.weddinginvitation.model.aws

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws.sms")
data class AwsSmsProperties(
    val region: String,
    val originationIdentity: String? = null,
    val configurationSetName: String? = null,
    val maxPrice: String? = null
)