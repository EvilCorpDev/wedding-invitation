package com.tsukor.weddinginvitation.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class ContactDetails(
    @Id
    val registrationToken: UUID,
    val phone: String,
    var phoneConfirmed: Boolean,
    val email: String,
    var emailConfirmed: Boolean,
) {}