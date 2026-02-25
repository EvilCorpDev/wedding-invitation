package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.repository.RegistrationCodeRepository
import com.tsukor.weddinginvitation.repository.model.RegistrationCode
import org.springframework.stereotype.Service
import java.util.UUID

private const val ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

@Service
class RegistrationCodeService(
    private val registrationCodeRepository: RegistrationCodeRepository
) {

    fun generateNewCodes(number: Int): List<String> {
        val codes: ArrayList<RegistrationCode> = ArrayList()
        (0 until number).forEach { _ ->
            codes.add(RegistrationCode(
                code = generateRegistrationToken(),
                activated = false,
                eventToken = UUID.randomUUID(),
                activationDate = null
            ))
        }

        registrationCodeRepository.saveAll(codes)

        return codes.map { it.code }
    }

    private fun generateRegistrationToken(): String {
        fun randomPart(length: Int) =
            (1..length)
                .map { ALLOWED_CHARS.random() }
                .joinToString("")

        return "${randomPart(5)}-${randomPart(4)}"
    }
}