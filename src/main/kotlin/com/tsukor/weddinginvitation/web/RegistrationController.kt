package com.tsukor.weddinginvitation.web

import com.tsukor.weddinginvitation.model.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/registration")
class RegistrationController {

    @PostMapping("/submit")
    fun registerForWedding(@RequestBody registrationRequest: RegistrationRequest): Success {
        print(registrationRequest)
        return Success()
    }

    @PostMapping("/validate")
    fun validateRegistration(@RequestBody registrationCode: RegistrationCode): RegistrationDetails {
        print(registrationCode)
        return RegistrationDetails(
            ok = true,
            registrationToken = UUID.randomUUID().toString(),
            event = Event(
                coupleNames = "Tan4ik & Zak",
                dateIso = "2026-08-01",
                venueName = "Venue",
                venueAddress = "Street 1\nCity",
                scheduleText = "16:00 ceremony\n18:00 dinner",
                dressCode = "Smart casual",
                extraInfo = "Any extra info"
            ),
            alreadySubmitted = false
        )
    }
}