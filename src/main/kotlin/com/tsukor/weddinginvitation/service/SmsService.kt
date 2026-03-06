package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.model.aws.AwsSmsProperties
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.pinpointsmsvoicev2.PinpointSmsVoiceV2Client
import software.amazon.awssdk.services.pinpointsmsvoicev2.model.MessageType
import software.amazon.awssdk.services.pinpointsmsvoicev2.model.PinpointSmsVoiceV2Exception
import software.amazon.awssdk.services.pinpointsmsvoicev2.model.SendTextMessageRequest

@Service
class SmsService (
    private val client: PinpointSmsVoiceV2Client,
    private val props: AwsSmsProperties
) {

    fun sendTransactionalSms(to: String, body: String): String {
        require(to.matches(Regex("^\\+[1-9][0-9]{1,18}$"))) {
            "Phone number must be in E.164 format, e.g. +48123123123"
        }

        val requestBuilder = SendTextMessageRequest.builder()
            .destinationPhoneNumber(to)
            .messageBody(body)
            .messageType(MessageType.TRANSACTIONAL)

        props.originationIdentity
            ?.takeIf { it.isNotBlank() }
            ?.let { requestBuilder.originationIdentity(it) }

        props.configurationSetName
            ?.takeIf { it.isNotBlank() }
            ?.let { requestBuilder.configurationSetName(it) }

        props.maxPrice
            ?.takeIf { it.isNotBlank() }
            ?.let { requestBuilder.maxPrice(it) }

        return try {
            val response = client.sendTextMessage(requestBuilder.build())
            response.messageId()
        } catch (e: PinpointSmsVoiceV2Exception) {
            throw SmsSendingException(
                "AWS SMS send failed: ${e.awsErrorDetails()?.errorMessage() ?: e.message}",
                e
            )
        }
    }
}

class SmsSendingException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)