package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.email.props.ConfirmationEmailParams
import com.tsukor.weddinginvitation.enums.ContactType
import com.tsukor.weddinginvitation.model.aws.EmailConfirmation
import com.tsukor.weddinginvitation.repository.ContactConfirmationRepository
import com.tsukor.weddinginvitation.repository.GuestsRepository
import com.tsukor.weddinginvitation.repository.model.ContactConfirmation
import io.awspring.cloud.sqs.annotation.SqsListener
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class SqsService(
    private val sqsTemplate: SqsTemplate,
    private val emailService: EmailService,
    private val contactConfirmationRepository: ContactConfirmationRepository,
    private val guestRepository: GuestsRepository
) {
    fun queueEmailConfirmation(token: UUID, email: String) {
        sqsTemplate.send(EmailConfirmation(token, email))
    }

    @SqsListener("\${aws.sqs.queue.url}")
    fun sendEmailVerification(emailConfirmation: EmailConfirmation) {
        val guest = guestRepository.findByRegistrationToken(emailConfirmation.registrationToken) ?: return
        val confirmUrl = "https://wedding.tsukor.com/api/email/confirm?token=${emailConfirmation.registrationToken}"
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
}