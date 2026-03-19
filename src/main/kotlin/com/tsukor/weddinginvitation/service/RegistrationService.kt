package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.enums.Lang
import com.tsukor.weddinginvitation.exception.NotFoundException
import com.tsukor.weddinginvitation.model.EventDetails
import com.tsukor.weddinginvitation.model.RegistrationCode
import com.tsukor.weddinginvitation.model.RegistrationDetails
import com.tsukor.weddinginvitation.model.RegistrationRequest
import com.tsukor.weddinginvitation.repository.ContactDetailsRepository
import com.tsukor.weddinginvitation.repository.RegistrationCodeRepository
import com.tsukor.weddinginvitation.repository.GuestsRepository
import com.tsukor.weddinginvitation.repository.EventRepository
import com.tsukor.weddinginvitation.repository.model.ContactDetails
import com.tsukor.weddinginvitation.repository.model.Guest
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Service
class RegistrationService(
    private val guestsRepository: GuestsRepository,
    private val registrationCodeRepository: RegistrationCodeRepository,
    private val contactDetailsRepository: ContactDetailsRepository,
    private val eventRepository: EventRepository,
    private val isoDateTimeFormatter: DateTimeFormatter,
    private val sqsService: SqsService
) {

    fun processRegistrationRequest(request: RegistrationRequest) {
        val registrationToken = UUID.fromString(request.registrationToken)
        registrationCodeRepository.findByEventToken(registrationToken)
            ?: throw NotFoundException("Can't find registration token: ${request.registrationToken}")

        val lang = Lang.fromString(request.lang)
        val guest = Guest(
            registrationToken,
            request.guest.firstName,
            request.guest.lastName,
            request.consent.terms,
            ZonedDateTime.now(),
            lang,
        )
        guestsRepository.save(guest)

        val contactDetails = ContactDetails(
            registrationToken,
            request.guest.phone,
            false,
            request.guest.email,
            false
        )
        contactDetailsRepository.save(contactDetails)

        sqsService.queuePhoneConfirmation(registrationToken, request.guest.phone, lang)
        sqsService.queueEmailConfirmation(registrationToken, request.guest.email, lang)
    }

    fun processActivationCode(code: RegistrationCode): RegistrationDetails {
        val existingCode = registrationCodeRepository.findById(code.code).orElseThrow { NotFoundException("Can't find registration code: ${code.code}") }
        val event = eventRepository.findAll().first()

        if(!existingCode.activated) {
            registrationCodeRepository.setActivated(code.code)
        }

        return RegistrationDetails(
            ok = true,
            registrationToken = existingCode.eventToken.toString(),
            event = EventDetails(
                coupleNames = "Tan4ik & Zak",
                dateIso = isoDateTimeFormatter.format(event.date),
                venueName = event.venueName,
                venueAddress = event.venueAddress,
                scheduleText = event.scheduleText,
                dressCode = event.dressCode,
                extraInfo = event.extraInfo,
            ),
            alreadySubmitted = existingCode.activated
        )
    }
}