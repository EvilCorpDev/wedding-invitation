package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.email.props.ConfirmationEmailParams
import com.tsukor.weddinginvitation.email.props.TemplateRenderer
import com.tsukor.weddinginvitation.email.template.ConfirmationEmailTemplates
import com.tsukor.weddinginvitation.repository.ContactConfirmationRepository
import com.tsukor.weddinginvitation.repository.ContactDetailsRepository
import com.tsukor.weddinginvitation.enums.ConfirmResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.UUID


@Service
class EmailService(
    private val mailSender: JavaMailSender,
    private val contactDetailsRepository: ContactDetailsRepository,
    private val contactConfirmationRepository: ContactConfirmationRepository,
    @Value("\${app.mail.from}") private val from: String,
    @Value("\${app.mail.confirmExpiresInHours}") private val expiresIn: Long,
) {

    fun sendEmailConfirmation(to: String, params: ConfirmationEmailParams) {
        val subject = TemplateRenderer.renderSubject(ConfirmationEmailTemplates.SUBJECT, params)
        val html = TemplateRenderer.renderHtml(ConfirmationEmailTemplates.HTML, params)
        val text = TemplateRenderer.renderText(ConfirmationEmailTemplates.TEXT, params)

        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, Charsets.UTF_8.name())

        helper.setFrom(from)
        helper.setTo(to)
        helper.setSubject(subject)

        // IMPORTANT: setText(text, html) => multipart/alternative
        helper.setText(text, html)

        mailSender.send(message)
    }

    fun confirmEmail(token: String): ConfirmResult {
        var tokenUuid: UUID? = null
        try {
            tokenUuid = UUID.fromString(token)
        } catch (e: IllegalArgumentException) {
            //log("Invalid token format: $token", e)
            return ConfirmResult.INVALID
        }
        val contactConfirmationStatus = contactConfirmationRepository.findById(tokenUuid)
        if (contactConfirmationStatus.isPresent) {
            val get = contactConfirmationStatus.get()
            if (get.emailLinkSent.plusHours(expiresIn) < ZonedDateTime.now()) {
                return ConfirmResult.EXPIRED
            } else {
                val details = contactDetailsRepository.findById(tokenUuid)
                if (details.isPresent) {
                    if (details.get().emailConfirmed) {
                        return ConfirmResult.ALREADY_USED
                    }
                    return ConfirmResult.OK
                }
                return ConfirmResult.INVALID
            }
        }
        return ConfirmResult.INVALID
    }
}