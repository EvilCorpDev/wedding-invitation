package com.tsukor.weddinginvitation.repository

import com.tsukor.weddinginvitation.repository.model.ContactConfirmation
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ContactConfirmationRepository : CrudRepository<ContactConfirmation, UUID> {
}