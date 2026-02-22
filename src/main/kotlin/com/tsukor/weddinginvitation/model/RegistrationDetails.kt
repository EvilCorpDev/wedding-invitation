package com.tsukor.weddinginvitation.model

data class RegistrationDetails(val ok: Boolean, val registrationToken: String, val event: Event, val alreadySubmitted: Boolean)
