package com.tsukor.weddinginvitation.repository.model

import com.tsukor.weddinginvitation.enums.ContactType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.time.ZonedDateTime
import java.util.UUID

@Entity
data class ContactConfirmation(
    @Id
    val registrationToken: UUID,
    val linkSent: ZonedDateTime,
    @Enumerated(EnumType.STRING)
    val contactType: ContactType,
) {}