package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.email.props.ConfirmationEmailParams
import com.tsukor.weddinginvitation.email.props.TemplateRenderer
import com.tsukor.weddinginvitation.enums.ContactType
import com.tsukor.weddinginvitation.enums.Lang
import com.tsukor.weddinginvitation.model.aws.EmailConfirmation
import com.tsukor.weddinginvitation.model.aws.PhoneConfirmation
import com.tsukor.weddinginvitation.repository.ContactConfirmationRepository
import com.tsukor.weddinginvitation.repository.GuestsRepository
import com.tsukor.weddinginvitation.repository.model.ContactConfirmation
import com.tsukor.weddinginvitation.sms.template.ConfirmationSmsTemplate
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
    @Value("\${app.mail.confirmExpiresInHours}") private val expiresIn: Long
) {
    fun queueEmailConfirmation(token: UUID, email: String, lang: Lang) {
        sqsTemplate.send(sqsEmailUrl, EmailConfirmation(token, email, lang.name))
    }

    fun queuePhoneConfirmation(token: UUID, phone: String, lang: Lang) {
        sqsTemplate.send(sqsPhoneUrl, PhoneConfirmation(token, phone, lang.name))
    }

    @SqsListener("\${aws.sqs.queue.email.url}")
    fun sendEmailVerification(emailConfirmation: EmailConfirmation) {
        val guest = guestRepository.findByRegistrationToken(emailConfirmation.registrationToken) ?: return
        val confirmUrl =
            "https://wedding.tsukor.com/api/confirmation/email?token=${emailConfirmation.registrationToken}"
        emailService.sendEmailConfirmation(
            emailConfirmation.email,
            ConfirmationEmailParams(
                coupleNames = "Tan4ik & Zakhar",
                guestName = "${guest.firstName} ${guest.lastName}",
                confirmUrl = confirmUrl,
                expiresIn = expiresIn.toString(),
                weddingDomain = "wedding.tsukor.com"
            ),
            Lang.fromString(emailConfirmation.lang)
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
        val confirmUrl =
            "https://wedding.tsukor.com/api/confirmation/phone?token=${phoneConfirmation.registrationToken}"
        smsService.sendSMS(
            phoneConfirmation.phone,
            TemplateRenderer.renderSms(ConfirmationSmsTemplate.getSmsText(Lang.fromString(phoneConfirmation.lang)), confirmUrl),
        )
        contactConfirmationRepository.save(
            ContactConfirmation(
                registrationToken = phoneConfirmation.registrationToken,
                linkSent = ZonedDateTime.now(),
                contactType = ContactType.PHONE
            )
        )
    }
}