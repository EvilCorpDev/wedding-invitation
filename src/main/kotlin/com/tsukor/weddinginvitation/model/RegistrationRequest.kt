package com.tsukor.weddinginvitation.model

data class RegistrationRequest(val registrationToken: String, val guest: GuestDetails, val consent: Consent)
