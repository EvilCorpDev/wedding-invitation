package com.tsukor.weddinginvitation.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime
import java.util.UUID

@Entity
data class RegistrationCode (
    @Id
    val code: String,
    val activated: Boolean,
    val eventToken: UUID,
    val activationDate: ZonedDateTime?
) {}