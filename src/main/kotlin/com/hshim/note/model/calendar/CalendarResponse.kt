package com.hshim.note.model.calendar

import com.hshim.note.database.calendar.Calendar

class CalendarResponse(
    val code: String,
    val title: String,
    val description: String?,
    val color: String,
) {
    constructor(calendar: Calendar): this (
        code = calendar.code,
        title = calendar.title,
        description = calendar.description,
        color = calendar.color,
    )
}