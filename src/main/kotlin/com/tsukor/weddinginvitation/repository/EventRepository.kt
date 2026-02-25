package com.tsukor.weddinginvitation.repository

import com.tsukor.weddinginvitation.repository.model.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, String> {
}