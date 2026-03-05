package com.tsukor.weddinginvitation.model.aws

import java.util.UUID

data class EmailConfirmation(
    val registrationToken: UUID,
    val email: String,
)
