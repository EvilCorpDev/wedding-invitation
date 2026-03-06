package com.tsukor.weddinginvitation.config

import com.tsukor.weddinginvitation.model.aws.AwsSmsProperties
import io.awspring.cloud.sqs.operations.SqsTemplate
import io.awspring.cloud.sqs.operations.SqsTemplateOptions
import io.awspring.cloud.sqs.operations.TemplateAcknowledgementMode
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.pinpointsmsvoicev2.PinpointSmsVoiceV2Client
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.time.format.DateTimeFormatter
import java.util.function.Consumer


@Configuration
@EnableConfigurationProperties(AwsSmsProperties::class)
class AppConfig(
    @Value("\${spring.mail.username}") private val emailUsername: String,
    @Value("\${spring.mail.password}") private val emailPassword: String
) {

    @Bean
    fun isoDateTimeFormatter(): DateTimeFormatter =
        DateTimeFormatter.ISO_DATE_TIME

    @Bean
    fun getJavaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587

        mailSender.username = emailUsername
        mailSender.password = emailPassword

        val props = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.debug"] = "true"

        return mailSender
    }

    @Bean
    fun sqsTemplate(sqsAsyncClient: SqsAsyncClient): SqsTemplate {
        return SqsTemplate.builder()
            .sqsAsyncClient(sqsAsyncClient)
            .configure(Consumer { options: SqsTemplateOptions ->
                options
                    .acknowledgementMode(TemplateAcknowledgementMode.MANUAL)
            })
            .build()
    }

    @Bean
    fun pinpointSmsVoiceV2Client(props: AwsSmsProperties, credentialsProvider: AwsCredentialsProvider): PinpointSmsVoiceV2Client {
        return PinpointSmsVoiceV2Client.builder()
            .region(Region.of(props.region))
            .credentialsProvider(credentialsProvider)
            .build()
    }
}