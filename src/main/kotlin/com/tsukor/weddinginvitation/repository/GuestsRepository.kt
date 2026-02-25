package com.tsukor.weddinginvitation.repository

import com.tsukor.weddinginvitation.repository.model.Guest
import org.springframework.data.repository.CrudRepository

interface GuestsRepository : CrudRepository<Guest, String> {
}