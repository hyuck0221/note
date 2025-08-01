package com.hshim.note.model.calendar

import com.hshim.note.database.calendar.Calendar
import com.hshim.note.database.calendar.CalendarDateRecord
import util.DateUtil.stringToDate

class CalendarDateRecordRequest(
    val startDateTime: String,
    val endDateTime: String,
    val title: String,
    val description: String?,
) {
    fun toEntity(code: String) = CalendarDateRecord(
        calendar = Calendar.of(code),
        startDateTime = startDateTime.stringToDate(),
        endDateTime = endDateTime.stringToDate(),
        title = title,
        description = description,
    )

    fun updateTo(entity: CalendarDateRecord) {
        entity.startDateTime = startDateTime.stringToDate()
        entity.endDateTime = endDateTime.stringToDate()
        entity.title = title
        entity.description = description
    }
}