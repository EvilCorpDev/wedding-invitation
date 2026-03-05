package com.tsukor.weddinginvitation.repository

import com.tsukor.weddinginvitation.repository.model.Guest
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface GuestsRepository : CrudRepository<Guest, UUID> {

    fun findByRegistrationToken(token: UUID): Guest?
}