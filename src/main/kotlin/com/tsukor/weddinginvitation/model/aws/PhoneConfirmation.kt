package com.tsukor.weddinginvitation.model.aws

import java.util.UUID

data class PhoneConfirmation(
    val registrationToken: UUID,
    val phone: String,
    val lang: String,
)
