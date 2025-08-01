package com.hshim.note.model.calendar

import com.hshim.note.database.calendar.CalendarDateRecord
import util.DateUtil.dateToString

class CalendarDateRecordResponse(
    val id: String,
    val calendar: CalendarResponse,
    val startDateTime: String,
    val endDateTime: String,
    val title: String,
    val description: String?,
) {
    constructor(calendarDateRecord: CalendarDateRecord): this (
        id = calendarDateRecord.id,
        calendar = CalendarResponse(calendarDateRecord.calendar),
        startDateTime = calendarDateRecord.startDateTime.dateToString(),
        endDateTime = calendarDateRecord.endDateTime.dateToString(),
        title = calendarDateRecord.title,
        description = calendarDateRecord.description,
    )
}