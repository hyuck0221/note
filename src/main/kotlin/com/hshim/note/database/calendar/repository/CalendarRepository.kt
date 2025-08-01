package com.hshim.note.database.calendar.repository

import com.hshim.note.database.calendar.Calendar
import org.springframework.data.jpa.repository.JpaRepository

interface CalendarRepository : JpaRepository<Calendar, String> {
}