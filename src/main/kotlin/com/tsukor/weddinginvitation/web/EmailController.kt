package com.tsukor.weddinginvitation.web

import com.tsukor.weddinginvitation.service.EmailService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/email")
class EmailController(
    private val emailService: EmailService
) {

    @GetMapping("/confirm")
    fun confirm(@RequestParam token: String): ResponseEntity<Void> {
        val result = emailService.confirmEmail(token)

        val redirect = when (result) {
            ConfirmResult.OK -> "/confirmed?status=ok"
            ConfirmResult.EXPIRED -> "/confirmed?status=expired"
            ConfirmResult.INVALID -> "/confirmed?status=invalid"
            ConfirmResult.ALREADY_USED -> "/confirmed?status=used"
        }

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, redirect)
            .build()
    }
}