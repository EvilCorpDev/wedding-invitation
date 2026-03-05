package com.tsukor.weddinginvitation.repository

import com.tsukor.weddinginvitation.repository.model.ContactDetails
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ContactDetailsRepository : CrudRepository<ContactDetails, UUID> {
}