package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.email.props.ConfirmationEmailParams
import com.tsukor.weddinginvitation.enums.ContactType
import com.tsukor.weddinginvitation.model.aws.EmailConfirmation
import com.tsukor.weddinginvitation.model.aws.PhoneConfirmation
import com.tsukor.weddinginvitation.repository.ContactConfirmationRepository
import com.tsukor.weddinginvitation.repository.GuestsRepository
import com.tsukor.weddinginvitation.repository.model.ContactConfirmation
import io.awspring.cloud.sqs.annotation.SqsListener
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class SqsService(
    private val sqsTemplate: SqsTemplate,
    private val emailService: EmailService,
    private val smsService: SmsService,
    private val contactConfirmationRepository: ContactConfirmationRepository,
    private val guestRepository: GuestsRepository,
    @Value("\${aws.sqs.queue.email.url}") private val sqsEmailUrl: String,
    @Value("\${aws.sqs.queue.phone.url}") private val sqsPhoneUrl: String,
) {
    fun queueEmailConfirmation(token: UUID, email: String) {
        sqsTemplate.send(sqsEmailUrl, EmailConfirmation(token, email))
    }

    fun queuePhoneConfirmation(token: UUID, phone: String) {
        sqsTemplate.send(sqsPhoneUrl, PhoneConfirmation(token, phone))
    }

    @SqsListener("\${aws.sqs.queue.email.url}")
    fun sendEmailVerification(emailConfirmation: EmailConfirmation) {
        val guest = guestRepository.findByRegistrationToken(emailConfirmation.registrationToken) ?: return
        val confirmUrl = "https://wedding.tsukor.com/api/confirmation/email?token=${emailConfirmation.registrationToken}"
        emailService.sendEmailConfirmation(emailConfirmation.email,
            ConfirmationEmailParams(
                        coupleNames = "Tan4ik & Zakhar",
                        guestName = "${guest.firstName} ${guest.lastName}",
                        confirmUrl = confirmUrl,
                        expiresIn = "48 hours",
                        weddingDomain = "wedding.tsukor.com"
                    )
        )
        contactConfirmationRepository.save(
            ContactConfirmation(
                registrationToken = emailConfirmation.registrationToken,
                linkSent = ZonedDateTime.now(),
                contactType = ContactType.EMAIL
            )
        )
    }

    @SqsListener("\${aws.sqs.queue.phone.url}")
    fun sendPhoneVerification(phoneConfirmation: PhoneConfirmation) {
        val confirmUrl = "https://wedding.tsukor.com/api/confirmation/phone?token=${phoneConfirmation.registrationToken}"
        smsService.sendSMS(phoneConfirmation.phone, "Please confirm you phone number for wedding registration \n $confirmUrl")
        contactConfirmationRepository.save(
            ContactConfirmation(
                registrationToken = phoneConfirmation.registrationToken,
                linkSent = ZonedDateTime.now(),
                contactType = ContactType.PHONE
            )
        )
    }
}