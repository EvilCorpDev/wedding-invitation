package com.tsukor.weddinginvitation.web

import com.tsukor.weddinginvitation.email.props.ConfirmationEmailParams
import com.tsukor.weddinginvitation.model.Consent
import com.tsukor.weddinginvitation.model.GuestDetails
import com.tsukor.weddinginvitation.model.RegistrationRequest
import com.tsukor.weddinginvitation.service.EmailService
import com.tsukor.weddinginvitation.service.RegistrationCodeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val registrationCodeService: RegistrationCodeService,
    private val emailService: EmailService,
) {

    @PostMapping("/code")
    fun generateRegistrationCodes(@RequestParam("number") number: Int,) =
        registrationCodeService.generateNewCodes(number)

    @GetMapping("/guest")
    fun getGuestDetails(): RegistrationRequest {
        return RegistrationRequest("", GuestDetails("", "", "", ""), Consent(terms = true, marketing = true))
    }

    @PostMapping("/email")
    fun sendTestEmail() {
        val confirmUrl = "https://wedding.tsukor.com/api/email/confirm?token=${UUID.randomUUID().toString()}"

        emailService.sendEmailConfirmation(
            to = "androidghost77@gmail.com",
            params = ConfirmationEmailParams(
                coupleNames = "Tan4ik & Zakhar",
                guestName = "Zakhar Kliap".trim(),
                confirmUrl = confirmUrl,
                expiresIn = "48 hours",
                weddingDomain = "wedding.tsukor.com"
            )
        )
    }
}