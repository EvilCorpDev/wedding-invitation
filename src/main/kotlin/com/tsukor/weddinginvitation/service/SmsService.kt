package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.model.TwilioProperties
import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber
import org.springframework.stereotype.Service

@Service
class SmsService (
    private val client: TwilioRestClient,
    private val twilioProperties: TwilioProperties,
) {

    fun sendSMS(to: String, body: String) {
        require(to.matches(Regex("^\\+[1-9][0-9]{1,18}$"))) {
            "Phone number must be in E.164 format, e.g. +48123123123"
        }

        MessageCreator(
            PhoneNumber(to),
            PhoneNumber(twilioProperties.from),
            body
        ).create(client)
    }
}