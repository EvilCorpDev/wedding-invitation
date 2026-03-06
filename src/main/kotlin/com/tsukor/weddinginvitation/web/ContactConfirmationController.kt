package com.tsukor.weddinginvitation.web

import com.tsukor.weddinginvitation.enums.ConfirmResult
import com.tsukor.weddinginvitation.enums.ContactType
import com.tsukor.weddinginvitation.service.EmailService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/confirmation")
class ContactConfirmationController(
    private val emailService: EmailService
) {

    @GetMapping("/email")
    fun confirmEmail(@RequestParam token: String): ResponseEntity<Void> {
        val result = emailService.confirmWithContactType(token, ContactType.EMAIL)

        val redirect = when (result) {
            ConfirmResult.OK -> "/confirmed/email?status=ok"
            ConfirmResult.EXPIRED -> "/confirmed/email?status=expired"
            ConfirmResult.INVALID -> "/confirmed/email?status=invalid"
            ConfirmResult.ALREADY_USED -> "/confirmed/email?status=used"
        }

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, redirect)
            .build()
    }

    @GetMapping("/phone")
    fun confirmPhone(@RequestParam token: String): ResponseEntity<Void> {
        val result = emailService.confirmWithContactType(token, ContactType.PHONE)

        val redirect = when (result) {
            ConfirmResult.OK -> "/confirmed/phone?status=ok"
            ConfirmResult.EXPIRED -> "/confirmed/phone?status=expired"
            ConfirmResult.INVALID -> "/confirmed/phone?status=invalid"
            ConfirmResult.ALREADY_USED -> "/confirmed/phone?status=used"
        }

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, redirect)
            .build()
    }
}