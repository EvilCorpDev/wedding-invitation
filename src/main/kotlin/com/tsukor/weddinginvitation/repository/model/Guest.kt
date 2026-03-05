package com.tsukor.weddinginvitation.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime
import java.util.UUID

@Entity
data class Guest(
    @Id
    val registrationToken: UUID,
    val firstName: String,
    val lastName: String,
    val terms: Boolean,
    val marketing: Boolean,
    val updated: ZonedDateTime,
) {}