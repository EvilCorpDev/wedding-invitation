package com.tsukor.weddinginvitation.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
data class Event(
    @Id
    val id: String,
    val date: ZonedDateTime,
    val venueName: String,
    val venueAddress: String,
    val scheduleText: String,
    val dressCode: String,
    val extraInfo: String
) {}