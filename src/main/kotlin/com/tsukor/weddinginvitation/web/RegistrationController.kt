package com.tsukor.weddinginvitation.web

import com.tsukor.weddinginvitation.model.RegistrationCode
import com.tsukor.weddinginvitation.model.RegistrationRequest
import com.tsukor.weddinginvitation.model.Success
import com.tsukor.weddinginvitation.service.RegistrationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/registration")
class RegistrationController(
    private val registrationService: RegistrationService
) {

    @PostMapping("/submit")
    fun registerForWedding(@RequestBody registrationRequest: RegistrationRequest): Success {
        registrationService.processRegistrationRequest(registrationRequest)
        return Success()
    }

    @PostMapping("/validate")
    fun validateRegistration(@RequestBody registrationCode: RegistrationCode) =
        registrationService.processActivationCode(registrationCode)
}