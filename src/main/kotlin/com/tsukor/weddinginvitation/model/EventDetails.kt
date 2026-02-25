package com.tsukor.weddinginvitation.model

data class EventDetails(
    val coupleNames: String,
    val dateIso: String,
    val venueName: String,
    val venueAddress: String,
    val scheduleText: String,
    val dressCode: String,
    val extraInfo: String
)