package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.email.props.ConfirmationEmailParams
import com.tsukor.weddinginvitation.email.props.TemplateRenderer
import com.tsukor.weddinginvitation.email.template.ConfirmationEmailTemplates
import com.tsukor.weddinginvitation.repository.ContactConfirmationRepository
import com.tsukor.weddinginvitation.repository.ContactDetailsRepository
import com.tsukor.weddinginvitation.enums.ConfirmResult
import com.tsukor.weddinginvitation.enums.ContactType
import com.tsukor.weddinginvitation.enums.Lang
import com.tsukor.weddinginvitation.repository.model.ContactDetails
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

    fun sendEmailConfirmation(to: String, params: ConfirmationEmailParams, lang: Lang) {
        val subject = TemplateRenderer.renderSubject(ConfirmationEmailTemplates.getSubject(lang), params)
        val html = TemplateRenderer.renderHtml(ConfirmationEmailTemplates.getHTML(lang), params)
        val text = TemplateRenderer.renderText(ConfirmationEmailTemplates.getText(lang), params)

        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, Charsets.UTF_8.name())

        helper.setFrom(from)
        helper.setTo(to)
        helper.setSubject(subject)

        // IMPORTANT: setText(text, html) => multipart/alternative
        helper.setText(text, html)

        mailSender.send(message)
    }

    fun confirmWithContactType(token: String, contactType: ContactType): ConfirmResult {
        var tokenUuid: UUID?
        try {
            tokenUuid = UUID.fromString(token)
        } catch (e: IllegalArgumentException) {
            //log("Invalid token format: $token", e)
            return ConfirmResult.INVALID
        }
        val contactConfirmationStatus = contactConfirmationRepository.findByContactType(contactType, tokenUuid) ?: return ConfirmResult.INVALID
        if (contactConfirmationStatus.linkSent.plusHours(expiresIn) < ZonedDateTime.now()) {
            return ConfirmResult.EXPIRED
        } else {
            val details = contactDetailsRepository.findById(tokenUuid)
            if (details.isPresent) {
                val contacts = details.get()
                return when (contactType) {
                    ContactType.EMAIL -> checkEmailConfirmed(contacts)
                    ContactType.PHONE -> checkPhoneConfirmed(contacts)
                }
            }
            return ConfirmResult.INVALID
        }
    }

    fun checkEmailConfirmed(contacts: ContactDetails): ConfirmResult {
        if (contacts.emailConfirmed) {
            return ConfirmResult.ALREADY_USED
        }
        contacts.emailConfirmed = true
        contactDetailsRepository.save(contacts)
        return ConfirmResult.OK
    }

    fun checkPhoneConfirmed(contacts: ContactDetails): ConfirmResult {
        if (contacts.phoneConfirmed) {
            return ConfirmResult.ALREADY_USED
        }
        contacts.phoneConfirmed = true
        contactDetailsRepository.save(contacts)
        return ConfirmResult.OK
    }
}