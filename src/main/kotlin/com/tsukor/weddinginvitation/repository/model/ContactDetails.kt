package com.tsukor.weddinginvitation.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class ContactDetails(
    @Id
    val registrationToken: UUID,
    val phone: String,
    val phoneConfirmed: Boolean,
    val email: String,
    val emailConfirmed: Boolean,
) {}