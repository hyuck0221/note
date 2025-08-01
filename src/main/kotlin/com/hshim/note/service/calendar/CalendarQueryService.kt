package com.hshim.note.service.calendar

import com.hshim.note.database.calendar.repository.CalendarDateRecordRepository
import com.hshim.note.database.calendar.repository.CalendarRepository
import com.hshim.note.model.calendar.CalendarDateRecordResponse
import com.hshim.note.model.calendar.CalendarResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalTime
import java.time.YearMonth

@Service
@Transactional(readOnly = true)
class CalendarQueryService(
    private val calendarRepository: CalendarRepository,
    private val calendarDateRecordRepository: CalendarDateRecordRepository,
) {
    fun findAllByCodes(codes: List<String>): List<CalendarResponse> {
        return calendarRepository.findAllById(codes).map { CalendarResponse(it) }
    }

    fun findAllByDateYearMonthBetween(
        codes: List<String>,
        startYearMonth: YearMonth,
        endYearMonth: YearMonth,
    ): List<CalendarDateRecordResponse> {
        return calendarDateRecordRepository.findAllByCalendarCodeInAndDateBetween(
            codes = codes,
            startDateTime = startYearMonth.atDay(1).atStartOfDay(),
            endDateTime = endYearMonth.atEndOfMonth().atTime(LocalTime.MAX),
        ).map { CalendarDateRecordResponse(it) }
    }
}