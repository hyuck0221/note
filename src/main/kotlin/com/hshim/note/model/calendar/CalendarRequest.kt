package com.hshim.note.model.calendar

import com.hshim.note.database.calendar.Calendar

class CalendarRequest(
    val title: String,
    val description: String?,
    val color: String,
) {
    fun updateTo(calendar: Calendar) {
        calendar.title = title
        calendar.description = description
        calendar.color = color
    }
}