package com.tsukor.weddinginvitation.web

import com.tsukor.weddinginvitation.model.Consent
import com.tsukor.weddinginvitation.model.GuestDetails
import com.tsukor.weddinginvitation.model.RegistrationRequest
import com.tsukor.weddinginvitation.service.RegistrationCodeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val registrationCodeService: RegistrationCodeService
) {

    @PostMapping("/code")
    fun generateRegistrationCodes(@RequestParam("number") number: Int,) =
        registrationCodeService.generateNewCodes(number)

    @GetMapping("/gues")
    fun getGuestDetails(): RegistrationRequest {
        return RegistrationRequest("", GuestDetails("", "", "", ""), Consent(terms = true, marketing = true))
    }
}