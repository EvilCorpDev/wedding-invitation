package com.tsukor.weddinginvitation.service

import com.tsukor.weddinginvitation.enums.Lang
import com.tsukor.weddinginvitation.repository.GuestsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GuestService(
    private val guestsRepository: GuestsRepository,
) {
    fun getGuestLang(token: String): Lang? {
        var tokenUuid: UUID?
        try {
            tokenUuid = UUID.fromString(token)
        } catch (e: IllegalArgumentException) {
            //log("Invalid token format: $token", e)
            return Lang.EN
        }
        val guest = guestsRepository.findById(tokenUuid)
        return guest.map { it.lang }.orElse(Lang.EN)
    }
}