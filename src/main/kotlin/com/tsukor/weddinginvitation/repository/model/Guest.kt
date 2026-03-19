package com.tsukor.weddinginvitation.repository.model

import com.tsukor.weddinginvitation.enums.Lang
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
    val updated: ZonedDateTime,
    @Enumerated(EnumType.STRING)
    val lang: Lang,
) {}