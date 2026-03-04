package com.tsukor.weddinginvitation.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime
import java.util.UUID

@Entity
data class ContactConfirmation(
    @Id
    val registrationToken: UUID,
    val phoneLinkSent: ZonedDateTime,
    val emailLinkSent: ZonedDateTime,
) {}