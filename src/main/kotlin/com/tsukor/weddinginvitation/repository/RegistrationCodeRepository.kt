package com.tsukor.weddinginvitation.repository

import com.tsukor.weddinginvitation.repository.model.RegistrationCode
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.ZonedDateTime
import java.util.UUID

interface RegistrationCodeRepository : CrudRepository<RegistrationCode, String> {

    @Modifying
    @Transactional
    @Query("UPDATE RegistrationCode SET activated=true, activationDate=:activationDate WHERE code=:code")
    fun setActivated(code: String, activationDate: ZonedDateTime = ZonedDateTime.now())

    fun findByEventToken(token: UUID): RegistrationCode?
}