package com.tsukor.weddinginvitation.repository

import com.tsukor.weddinginvitation.enums.ContactType
import com.tsukor.weddinginvitation.repository.model.ContactConfirmation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.UUID

interface ContactConfirmationRepository : CrudRepository<ContactConfirmation, UUID> {

    @Query("select cc from ContactConfirmation cc where cc.contactType = :contactType and cc.registrationToken = :token")
    fun findByContactType(@Param("contactType") contactType: ContactType, @Param("token") token: UUID): ContactConfirmation?

}